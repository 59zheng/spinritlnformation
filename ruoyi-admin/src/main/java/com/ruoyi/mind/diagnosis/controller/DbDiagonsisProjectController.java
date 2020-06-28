package com.ruoyi.mind.diagnosis.controller;

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
import com.ruoyi.mind.diagnosis.domain.DbDiagonsisProject;
import com.ruoyi.mind.diagnosis.service.IDbDiagonsisProjectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 诊断项目Controller
 * 
 * @author 正
 * @date 2020-06-22
 */
@Controller
@RequestMapping("/diagnosis/project")
public class DbDiagonsisProjectController extends BaseController
{
    private String prefix = "diagnosis/project";

    @Autowired
    private IDbDiagonsisProjectService dbDiagonsisProjectService;

    @RequiresPermissions("diagnosis:project:view")
    @GetMapping()
    public String project()
    {
        return prefix + "/project";
    }

    /**
     * 查询诊断项目树列表
     */
    @RequiresPermissions("diagnosis:project:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbDiagonsisProject> list(DbDiagonsisProject dbDiagonsisProject)
    {
        List<DbDiagonsisProject> list = dbDiagonsisProjectService.selectDbDiagonsisProjectList(dbDiagonsisProject);
        return list;
    }

    /**
     * 导出诊断项目列表
     */
    @RequiresPermissions("diagnosis:project:export")
    @Log(title = "诊断项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbDiagonsisProject dbDiagonsisProject)
    {
        List<DbDiagonsisProject> list = dbDiagonsisProjectService.selectDbDiagonsisProjectList(dbDiagonsisProject);
        ExcelUtil<DbDiagonsisProject> util = new ExcelUtil<DbDiagonsisProject>(DbDiagonsisProject.class);
        return util.exportExcel(list, "project");
    }

    /**
     * 新增诊断项目
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbDiagonsisProject", dbDiagonsisProjectService.selectDbDiagonsisProjectById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存诊断项目
     */
    @RequiresPermissions("diagnosis:project:add")
    @Log(title = "诊断项目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbDiagonsisProject dbDiagonsisProject)
    {
        return toAjax(dbDiagonsisProjectService.insertDbDiagonsisProject(dbDiagonsisProject));
    }

    /**
     * 修改诊断项目
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbDiagonsisProject dbDiagonsisProject = dbDiagonsisProjectService.selectDbDiagonsisProjectById(id);
        mmap.put("dbDiagonsisProject", dbDiagonsisProject);
        return prefix + "/edit";
    }

    /**
     * 修改保存诊断项目
     */
    @RequiresPermissions("diagnosis:project:edit")
    @Log(title = "诊断项目", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbDiagonsisProject dbDiagonsisProject)
    {
        return toAjax(dbDiagonsisProjectService.updateDbDiagonsisProject(dbDiagonsisProject));
    }

    /**
     * 删除
     */
    @RequiresPermissions("diagnosis:project:remove")
    @Log(title = "诊断项目", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbDiagonsisProjectService.deleteDbDiagonsisProjectById(id));
    }

    /**
     * 选择诊断项目树
     */
    @GetMapping(value = { "/selectProjectTree/{id}", "/selectProjectTree/" })
    public String selectProjectTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbDiagonsisProject", dbDiagonsisProjectService.selectDbDiagonsisProjectById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载诊断项目树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbDiagonsisProjectService.selectDbDiagonsisProjectTree();
        return ztrees;
    }
}
