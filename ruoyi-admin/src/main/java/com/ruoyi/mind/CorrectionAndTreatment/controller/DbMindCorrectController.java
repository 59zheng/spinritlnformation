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
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindCorrect;
import com.ruoyi.mind.CorrectionAndTreatment.service.IDbMindCorrectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 心理矫正Controller
 * 
 * @author zheng
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/CorrectionAndTreatment/correct")
public class DbMindCorrectController extends BaseController
{
    private String prefix = "CorrectionAndTreatment/correct";

    @Autowired
    private IDbMindCorrectService dbMindCorrectService;

    @RequiresPermissions("CorrectionAndTreatment:correct:view")
    @GetMapping()
    public String correct()
    {
        return prefix + "/correct";
    }

    /**
     * 查询心理矫正树列表
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbMindCorrect> list(DbMindCorrect dbMindCorrect)
    {
        List<DbMindCorrect> list = dbMindCorrectService.selectDbMindCorrectList(dbMindCorrect);
        return list;
    }

    /**
     * 导出心理矫正列表
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:export")
    @Log(title = "心理矫正", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbMindCorrect dbMindCorrect)
    {
        List<DbMindCorrect> list = dbMindCorrectService.selectDbMindCorrectList(dbMindCorrect);
        ExcelUtil<DbMindCorrect> util = new ExcelUtil<DbMindCorrect>(DbMindCorrect.class);
        return util.exportExcel(list, "correct");
    }

    /**
     * 新增心理矫正
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbMindCorrect", dbMindCorrectService.selectDbMindCorrectById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存心理矫正
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:add")
    @Log(title = "心理矫正", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbMindCorrect dbMindCorrect)
    {
        return toAjax(dbMindCorrectService.insertDbMindCorrect(dbMindCorrect));
    }

    /**
     * 修改心理矫正
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbMindCorrect dbMindCorrect = dbMindCorrectService.selectDbMindCorrectById(id);
        mmap.put("dbMindCorrect", dbMindCorrect);
        return prefix + "/edit";
    }

    /**
     * 修改保存心理矫正
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:edit")
    @Log(title = "心理矫正", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbMindCorrect dbMindCorrect)
    {
        return toAjax(dbMindCorrectService.updateDbMindCorrect(dbMindCorrect));
    }

    /**
     * 删除
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:remove")
    @Log(title = "心理矫正", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbMindCorrectService.deleteDbMindCorrectById(id));
    }

    /**
     * 选择心理矫正树
     */
    @GetMapping(value = { "/selectCorrectTree/{id}", "/selectCorrectTree/" })
    public String selectCorrectTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbMindCorrect", dbMindCorrectService.selectDbMindCorrectById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载心理矫正树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbMindCorrectService.selectDbMindCorrectTree();
        return ztrees;
    }
}
