package com.ruoyi.mind.CorrectionAndTreatment.controller;

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
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindTreat;
import com.ruoyi.mind.CorrectionAndTreatment.service.IDbMindTreatService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 心理治疗Controller
 * 
 * @author zheng
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/CorrectionAndTreatment/treat")
public class DbMindTreatController extends BaseController
{
    private String prefix = "CorrectionAndTreatment/treat";

    @Autowired
    private IDbMindTreatService dbMindTreatService;

    @RequiresPermissions("CorrectionAndTreatment:treat:view")
    @GetMapping()
    public String treat()
    {
        return prefix + "/treat";
    }

    /**
     * 查询心理治疗树列表
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbMindTreat> list(DbMindTreat dbMindTreat)
    {
        List<DbMindTreat> list = dbMindTreatService.selectDbMindTreatList(dbMindTreat);
        return list;
    }

    /**
     * 导出心理治疗列表
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:export")
    @Log(title = "心理治疗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbMindTreat dbMindTreat)
    {
        List<DbMindTreat> list = dbMindTreatService.selectDbMindTreatList(dbMindTreat);
        ExcelUtil<DbMindTreat> util = new ExcelUtil<DbMindTreat>(DbMindTreat.class);
        return util.exportExcel(list, "treat");
    }

    /**
     * 新增心理治疗
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbMindTreat", dbMindTreatService.selectDbMindTreatById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存心理治疗
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:add")
    @Log(title = "心理治疗", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbMindTreat dbMindTreat)
    {
        return toAjax(dbMindTreatService.insertDbMindTreat(dbMindTreat));
    }

    /**
     * 修改心理治疗
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbMindTreat dbMindTreat = dbMindTreatService.selectDbMindTreatById(id);
        mmap.put("dbMindTreat", dbMindTreat);
        return prefix + "/edit";
    }

    /**
     * 修改保存心理治疗
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:edit")
    @Log(title = "心理治疗", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbMindTreat dbMindTreat)
    {
        return toAjax(dbMindTreatService.updateDbMindTreat(dbMindTreat));
    }

    /**
     * 删除
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:remove")
    @Log(title = "心理治疗", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbMindTreatService.deleteDbMindTreatById(id));
    }

    /**
     * 选择心理治疗树
     */
    @GetMapping(value = { "/selectTreatTree/{id}", "/selectTreatTree/" })
    public String selectTreatTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbMindTreat", dbMindTreatService.selectDbMindTreatById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载心理治疗树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbMindTreatService.selectDbMindTreatTree();
        return ztrees;
    }
}
