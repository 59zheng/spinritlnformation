package com.ruoyi.mind.lab.controller;

import java.util.Date;
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
import com.ruoyi.mind.lab.domain.DbLabTdm;
import com.ruoyi.mind.lab.service.IDbLabTdmService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 血药浓度检测Controller
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/lab/tdm")
public class DbLabTdmController extends BaseController
{
    private String prefix = "lab/tdm";

    @Autowired
    private IDbLabTdmService dbLabTdmService;


    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("lab:tdm:view")
    @GetMapping()
    public String tdm()
    {
        return prefix + "/tdm";
    }

    /**
     * 查询血药浓度检测列表
     */
    @RequiresPermissions("lab:tdm:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbLabTdm dbLabTdm)
    {
        startPage();
        List<DbPatientMessage> list = TableListUtils.getList("TDM");
        return getDataTable(list);
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
     * 导出血药浓度检测列表
     */
    @RequiresPermissions("lab:tdm:export")
    @Log(title = "血药浓度检测", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbLabTdm dbLabTdm)
    {
        List<DbLabTdm> list = dbLabTdmService.selectDbLabTdmList(dbLabTdm);
        ExcelUtil<DbLabTdm> util = new ExcelUtil<DbLabTdm>(DbLabTdm.class);
        return util.exportExcel(list, "tdm");
    }

    /**
     * 新增血药浓度检测
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存血药浓度检测
     */
    @RequiresPermissions("lab:tdm:add")
    @Log(title = "血药浓度检测", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbLabTdm dbLabTdm)
    {
        /*
         * 新增保存  关联表id给中间表  标记完成
         * */
        SysUser sysUser = ShiroUtils.getSysUser();
        SysUser sysUser1 = sysUserService.selectUserById(TableListUtils.getTeamId(dbLabTdm.getPatientId()));

        dbLabTdm.setAttendingPhysician(sysUser1.getUserName());
        dbLabTdm.setTechnicianName(sysUser.getUserName());
        dbLabTdm.setExecutionTime(new Date());
        int i = dbLabTdmService.insertDbLabTdm(dbLabTdm);
//
        Long id = dbLabTdm.getId();
        Long patientId = dbLabTdm.getPatientId();
        int induced = TableListUtils.updateResult(id, "TDM", patientId);
        return toAjax(induced);
    }

    /**
     * 修改血药浓度检测
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbLabTdm dbLabTdm = dbLabTdmService.selectDbLabTdmById(id);
        mmap.put("dbLabTdm", dbLabTdm);
        return prefix + "/edit";
    }

    /**
     * 修改保存血药浓度检测
     */
    @RequiresPermissions("lab:tdm:edit")
    @Log(title = "血药浓度检测", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbLabTdm dbLabTdm)
    {
        return toAjax(dbLabTdmService.updateDbLabTdm(dbLabTdm));
    }

    /**
     * 删除血药浓度检测
     */
    @RequiresPermissions("lab:tdm:remove")
    @Log(title = "血药浓度检测", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbLabTdmService.deleteDbLabTdmByIds(ids));
    }
}
