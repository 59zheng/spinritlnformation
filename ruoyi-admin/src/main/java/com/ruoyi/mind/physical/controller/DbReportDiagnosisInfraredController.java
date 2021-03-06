package com.ruoyi.mind.physical.controller;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.utils.TableListUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInfrared;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisInfraredService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 红外热成像及血流图检查Controller
 * 
 * @author zheng
 * @date 2020-06-29
 */
@Controller
@RequestMapping("/physical/infrared")
public class DbReportDiagnosisInfraredController extends BaseController
{
    private String prefix = "physical/infrared";

    @Autowired
    private IDbReportDiagnosisInfraredService dbReportDiagnosisInfraredService;

    @RequiresPermissions("physical:infrared:view")
    @GetMapping()
    public String infrared()
    {
        return prefix + "/infrared";
    }

    /**
     * 查询红外热成像及血流图检查列表
     */
    @RequiresPermissions("physical:infrared:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        /*
         *
         * 病人信息  <泛型>
         * */
        startPage();
        List<DbPatientMessage> induced = TableListUtils.getList("infrared");
        return getDataTable(induced);
    }

    /**
     * 导出红外热成像及血流图检查列表
     */
    @RequiresPermissions("physical:infrared:export")
    @Log(title = "红外热成像及血流图检查", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        List<DbReportDiagnosisInfrared> list = dbReportDiagnosisInfraredService.selectDbReportDiagnosisInfraredList(dbReportDiagnosisInfrared);
        ExcelUtil<DbReportDiagnosisInfrared> util = new ExcelUtil<DbReportDiagnosisInfrared>(DbReportDiagnosisInfrared.class);
        return util.exportExcel(list, "infrared");
    }

    /**
     * 新增红外热成像及血流图检查
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增脑电报告(物理诊断下边)
     */
    @GetMapping("/addonly/{userId}")
    public String addonly( ModelMap map,@PathVariable("userId") Long userId) {

        map.put("userId",userId);
        return prefix + "/add";
    }


    /**
     * 新增保存红外热成像及血流图检查
     */
    @RequiresPermissions("physical:infrared:add")
    @Log(title = "红外热成像及血流图检查", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        /*
         * 新增保存  关联表id给中间表  标记完成
         * */
        SysUser sysUser = ShiroUtils.getSysUser();
//        技师签名
        dbReportDiagnosisInfrared.setSignatureTechnician(sysUser.getSignatureURL());
        dbReportDiagnosisInfrared.setIsSee("1");
        int i = dbReportDiagnosisInfraredService.insertDbReportDiagnosisInfrared(dbReportDiagnosisInfrared);
        Long id = dbReportDiagnosisInfrared.getId();
        Long patientId = dbReportDiagnosisInfrared.getPatientId();
        int induced = TableListUtils.updateResult(id, "infrared", patientId);
        return toAjax(induced);
    }

    /**
     * 修改红外热成像及血流图检查
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbReportDiagnosisInfrared dbReportDiagnosisInfrared = dbReportDiagnosisInfraredService.selectDbReportDiagnosisInfraredById(id);
        mmap.put("dbReportDiagnosisInfrared", dbReportDiagnosisInfrared);
        return prefix + "/edit";
    }

    /**
     * 修改保存红外热成像及血流图检查
     */
    @RequiresPermissions("physical:infrared:edit")
    @Log(title = "红外热成像及血流图检查", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        return toAjax(dbReportDiagnosisInfraredService.updateDbReportDiagnosisInfrared(dbReportDiagnosisInfrared));
    }

    /**
     * 删除红外热成像及血流图检查
     */
    @RequiresPermissions("physical:infrared:remove")
    @Log(title = "红外热成像及血流图检查", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbReportDiagnosisInfraredService.deleteDbReportDiagnosisInfraredByIds(ids));
    }
}
