package com.ruoyi.mind.research.controller;

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
import com.ruoyi.mind.research.domain.DbResearchPharmacological;
import com.ruoyi.mind.research.service.IDbResearchPharmacologicalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 药理研究Controller
 * 
 * @author zheng
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/research/pharmacological")
public class DbResearchPharmacologicalController extends BaseController
{
    private String prefix = "research/pharmacological";

    @Autowired
    private IDbResearchPharmacologicalService dbResearchPharmacologicalService;

    @RequiresPermissions("research:pharmacological:view")
    @GetMapping()
    public String pharmacological()
    {
        return prefix + "/pharmacological";
    }

    /**
     * 查询药理研究树列表
     */
    @RequiresPermissions("research:pharmacological:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbResearchPharmacological> list(DbResearchPharmacological dbResearchPharmacological)
    {
        List<DbResearchPharmacological> list = dbResearchPharmacologicalService.selectDbResearchPharmacologicalList(dbResearchPharmacological);
        return list;
    }

    /**
     * 导出药理研究列表
     */
    @RequiresPermissions("research:pharmacological:export")
    @Log(title = "药理研究", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbResearchPharmacological dbResearchPharmacological)
    {
        List<DbResearchPharmacological> list = dbResearchPharmacologicalService.selectDbResearchPharmacologicalList(dbResearchPharmacological);
        ExcelUtil<DbResearchPharmacological> util = new ExcelUtil<DbResearchPharmacological>(DbResearchPharmacological.class);
        return util.exportExcel(list, "pharmacological");
    }

    /**
     * 新增药理研究
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbResearchPharmacological", dbResearchPharmacologicalService.selectDbResearchPharmacologicalById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存药理研究
     */
    @RequiresPermissions("research:pharmacological:add")
    @Log(title = "药理研究", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbResearchPharmacological dbResearchPharmacological)
    {
        if (dbResearchPharmacological.getPatientId()==null){
            dbResearchPharmacological.setParentId(0L);
        }

        return toAjax(dbResearchPharmacologicalService.insertDbResearchPharmacological(dbResearchPharmacological));
    }

    /**
     * 修改药理研究
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbResearchPharmacological dbResearchPharmacological = dbResearchPharmacologicalService.selectDbResearchPharmacologicalById(id);
        mmap.put("dbResearchPharmacological", dbResearchPharmacological);
        return prefix + "/edit";
    }

    /**
     * 修改保存药理研究
     */
    @RequiresPermissions("research:pharmacological:edit")
    @Log(title = "药理研究", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbResearchPharmacological dbResearchPharmacological)
    {
        return toAjax(dbResearchPharmacologicalService.updateDbResearchPharmacological(dbResearchPharmacological));
    }

    /**
     * 删除
     */
    @RequiresPermissions("research:pharmacological:remove")
    @Log(title = "药理研究", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbResearchPharmacologicalService.deleteDbResearchPharmacologicalById(id));
    }

    /**
     * 选择药理研究树
     */
    @GetMapping(value = { "/selectPharmacologicalTree/{id}", "/selectPharmacologicalTree/" })
    public String selectPharmacologicalTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbResearchPharmacological", dbResearchPharmacologicalService.selectDbResearchPharmacologicalById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载药理研究树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbResearchPharmacologicalService.selectDbResearchPharmacologicalTree();
        return ztrees;
    }
}
