package com.ruoyi.mind.registered.controller;

import java.util.List;
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
import com.ruoyi.mind.registered.domain.DbPatientAssociated;
import com.ruoyi.mind.registered.service.IDbPatientAssociatedService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 病人诊断记录关联检测报告Controller
 * 
 * @author zheng
 * @date 2020-06-23
 */
@Controller
@RequestMapping("/registered/associated")
public class DbPatientAssociatedController extends BaseController
{
    private String prefix = "registered/associated";

    @Autowired
    private IDbPatientAssociatedService dbPatientAssociatedService;

    @RequiresPermissions("registered:associated:view")
    @GetMapping()
    public String associated()
    {
        return prefix + "/associated";
    }

    /**
     * 查询病人诊断记录关联检测报告列表
     */
    @RequiresPermissions("registered:associated:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbPatientAssociated dbPatientAssociated)
    {
        startPage();
        List<DbPatientAssociated> list = dbPatientAssociatedService.selectDbPatientAssociatedList(dbPatientAssociated);
        return getDataTable(list);
    }

    /**
     * 导出病人诊断记录关联检测报告列表
     */
    @RequiresPermissions("registered:associated:export")
    @Log(title = "病人诊断记录关联检测报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbPatientAssociated dbPatientAssociated)
    {
        List<DbPatientAssociated> list = dbPatientAssociatedService.selectDbPatientAssociatedList(dbPatientAssociated);
        ExcelUtil<DbPatientAssociated> util = new ExcelUtil<DbPatientAssociated>(DbPatientAssociated.class);
        return util.exportExcel(list, "associated");
    }

    /**
     * 新增病人诊断记录关联检测报告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存病人诊断记录关联检测报告
     */
    @RequiresPermissions("registered:associated:add")
    @Log(title = "病人诊断记录关联检测报告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbPatientAssociated dbPatientAssociated)
    {
        return toAjax(dbPatientAssociatedService.insertDbPatientAssociated(dbPatientAssociated));
    }

    /**
     * 修改病人诊断记录关联检测报告
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbPatientAssociated dbPatientAssociated = dbPatientAssociatedService.selectDbPatientAssociatedById(id);
        mmap.put("dbPatientAssociated", dbPatientAssociated);
        return prefix + "/edit";
    }

    /**
     * 修改保存病人诊断记录关联检测报告
     */
    @RequiresPermissions("registered:associated:edit")
    @Log(title = "病人诊断记录关联检测报告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbPatientAssociated dbPatientAssociated)
    {
        return toAjax(dbPatientAssociatedService.updateDbPatientAssociated(dbPatientAssociated));
    }

    /**
     * 删除病人诊断记录关联检测报告
     */
    @RequiresPermissions("registered:associated:remove")
    @Log(title = "病人诊断记录关联检测报告", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbPatientAssociatedService.deleteDbPatientAssociatedByIds(ids));
    }
}
