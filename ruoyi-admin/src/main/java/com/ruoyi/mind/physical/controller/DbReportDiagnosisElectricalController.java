package com.ruoyi.mind.physical.controller;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.utils.TableListUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.mind.physical.domain.DbReportDiagnosisElectrical;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisElectricalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 脑电诊断Controller
 * 
 * @author zheng
 * @date 2020-07-01
 */
@Controller
@RequestMapping("/physical/electrical")
public class DbReportDiagnosisElectricalController extends BaseController
{
    private String prefix = "physical/electrical";

    @Autowired
    private IDbReportDiagnosisElectricalService dbReportDiagnosisElectricalService;


    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("physical:electrical:view")
    @GetMapping()
    public String electrical()
    {
        return prefix + "/electrical";
    }

    /**
     * 查询脑电诊断列表
     */
    @RequiresPermissions("physical:electrical:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        /*
         *
         * 病人信息  <泛型>
         * */
        startPage();
        List<DbPatientMessage> induced = TableListUtils.getList("electrical");
        return getDataTable(induced);
    }

    /**
     * 导出脑电诊断列表
     */
    @RequiresPermissions("physical:electrical:export")
    @Log(title = "脑电诊断", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        List<DbReportDiagnosisElectrical> list = dbReportDiagnosisElectricalService.selectDbReportDiagnosisElectricalList(dbReportDiagnosisElectrical);
        ExcelUtil<DbReportDiagnosisElectrical> util = new ExcelUtil<DbReportDiagnosisElectrical>(DbReportDiagnosisElectrical.class);
        return util.exportExcel(list, "electrical");
    }

    /**
     * 新增脑电诊断
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存脑电诊断
     */
    @RequiresPermissions("physical:electrical:add")
    @Log(title = "脑电诊断", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        /*
         * 新增保存  关联表id给中间表  标记完成
         * */
        SysUser sysUser = ShiroUtils.getSysUser();
//        技师签名
        dbReportDiagnosisElectrical.setSignatureTechnician(sysUser.getSignatureURL());
//        医生签名
        SysUser sysUser1 = sysUserService.selectUserById(TableListUtils.getTeamId(dbReportDiagnosisElectrical.getPatientId()));
        dbReportDiagnosisElectrical.setSignatureDoctor(sysUser1.getSignatureURL());
        int i = dbReportDiagnosisElectricalService.insertDbReportDiagnosisElectrical(dbReportDiagnosisElectrical);
        Long id = dbReportDiagnosisElectrical.getId();
        Long patientId = dbReportDiagnosisElectrical.getPatientId();
        int induced = TableListUtils.updateResult(id, "electrical", patientId);
        return toAjax(induced);
    }

    /**
     * 修改脑电诊断
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbReportDiagnosisElectrical dbReportDiagnosisElectrical = dbReportDiagnosisElectricalService.selectDbReportDiagnosisElectricalById(id);
        mmap.put("dbReportDiagnosisElectrical", dbReportDiagnosisElectrical);
        return prefix + "/edit";
    }

    /**
     * 修改保存脑电诊断
     */
    @RequiresPermissions("physical:electrical:edit")
    @Log(title = "脑电诊断", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        return toAjax(dbReportDiagnosisElectricalService.updateDbReportDiagnosisElectrical(dbReportDiagnosisElectrical));
    }

    /**
     * 删除脑电诊断
     */
    @RequiresPermissions("physical:electrical:remove")
    @Log(title = "脑电诊断", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbReportDiagnosisElectricalService.deleteDbReportDiagnosisElectricalByIds(ids));
    }


    /**
     * 新增脑电报告(物理诊断下边)
     */
    @GetMapping("/addonly/{userId}")
    public String addonly( ModelMap map,@PathVariable("userId") Long userId) {
        map.put("userId",userId);
        return prefix + "/add";
    }

}
