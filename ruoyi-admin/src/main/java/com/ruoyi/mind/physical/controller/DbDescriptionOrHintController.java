package com.ruoyi.mind.physical.controller;

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
import com.ruoyi.mind.physical.domain.DbDescriptionOrHint;
import com.ruoyi.mind.physical.service.IDbDescriptionOrHintService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 导出word结果描述与提示对应Controller
 * 
 * @author zheng
 * @date 2020-06-29
 */
@Controller
@RequestMapping("/physical/hint")
public class DbDescriptionOrHintController extends BaseController
{
    private String prefix = "physical/hint";

    @Autowired
    private IDbDescriptionOrHintService dbDescriptionOrHintService;

    @RequiresPermissions("physical:hint:view")
    @GetMapping()
    public String hint()
    {
        return prefix + "/hint";
    }

    /**
     * 查询导出word结果描述与提示对应列表
     */
    @RequiresPermissions("physical:hint:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbDescriptionOrHint dbDescriptionOrHint)
    {
        startPage();
        List<DbDescriptionOrHint> list = dbDescriptionOrHintService.selectDbDescriptionOrHintList(dbDescriptionOrHint);
        return getDataTable(list);
    }

    /**
     * 导出导出word结果描述与提示对应列表
     */
    @RequiresPermissions("physical:hint:export")
    @Log(title = "导出word结果描述与提示对应", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbDescriptionOrHint dbDescriptionOrHint)
    {
        List<DbDescriptionOrHint> list = dbDescriptionOrHintService.selectDbDescriptionOrHintList(dbDescriptionOrHint);
        ExcelUtil<DbDescriptionOrHint> util = new ExcelUtil<DbDescriptionOrHint>(DbDescriptionOrHint.class);
        return util.exportExcel(list, "hint");
    }

    /**
     * 新增导出word结果描述与提示对应
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存导出word结果描述与提示对应
     */
    @RequiresPermissions("physical:hint:add")
    @Log(title = "导出word结果描述与提示对应", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbDescriptionOrHint dbDescriptionOrHint)
    {
        return toAjax(dbDescriptionOrHintService.insertDbDescriptionOrHint(dbDescriptionOrHint));
    }

    /**
     * 修改导出word结果描述与提示对应
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbDescriptionOrHint dbDescriptionOrHint = dbDescriptionOrHintService.selectDbDescriptionOrHintById(id);
        mmap.put("dbDescriptionOrHint", dbDescriptionOrHint);
        return prefix + "/edit";
    }

    /**
     * 修改保存导出word结果描述与提示对应
     */
    @RequiresPermissions("physical:hint:edit")
    @Log(title = "导出word结果描述与提示对应", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbDescriptionOrHint dbDescriptionOrHint)
    {
        return toAjax(dbDescriptionOrHintService.updateDbDescriptionOrHint(dbDescriptionOrHint));
    }

    /**
     * 删除导出word结果描述与提示对应
     */
    @RequiresPermissions("physical:hint:remove")
    @Log(title = "导出word结果描述与提示对应", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbDescriptionOrHintService.deleteDbDescriptionOrHintByIds(ids));
    }
}
