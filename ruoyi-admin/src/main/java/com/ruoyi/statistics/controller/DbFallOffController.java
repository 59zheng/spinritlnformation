package com.ruoyi.statistics.controller;

import java.util.List;

import com.ruoyi.statistics.service.IDbFallOffService;
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
import com.ruoyi.statistics.domain.DbFallOff;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 脱落列Controller
 *
 * @author zheng
 * @date 2020-08-11
 */
@Controller
@RequestMapping("/statistics/off")
public class DbFallOffController extends BaseController
{
    private String prefix = "statistics/off";

    @Autowired
    private IDbFallOffService dbFallOffService;

    @RequiresPermissions("statistics:off:view")
    @GetMapping()
    public String off()
    {
        return prefix + "/off";
    }

    /**
     * 查询脱落列列表
     */
    @RequiresPermissions("statistics:off:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbFallOff dbFallOff)
    {
        dbFallOff.setIsOff("1");
        startPage();
        List<DbFallOff> list = dbFallOffService.selectDbFallOffList(dbFallOff);
        return getDataTable(list);
    }

    /**
     * 导出脱落列列表
     */
    @RequiresPermissions("statistics:off:export")
    @Log(title = "脱落列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbFallOff dbFallOff)
    {
        List<DbFallOff> list = dbFallOffService.selectDbFallOffList(dbFallOff);
        ExcelUtil<DbFallOff> util = new ExcelUtil<DbFallOff>(DbFallOff.class);
        return util.exportExcel(list, "off");
    }

    /**
     * 新增脱落列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存脱落列
     */
    @RequiresPermissions("statistics:off:add")
    @Log(title = "脱落列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbFallOff dbFallOff)
    {
        return toAjax(dbFallOffService.insertDbFallOff(dbFallOff));
    }

    /**
     * 修改脱落列
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbFallOff dbFallOff = dbFallOffService.selectDbFallOffById(id);
        mmap.put("dbFallOff", dbFallOff);
        return prefix + "/edit";
    }

    /**
     * 修改保存脱落列
     */
    @RequiresPermissions("statistics:off:edit")
    @Log(title = "脱落列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbFallOff dbFallOff)
    {
        dbFallOff.setIsOff("0");
        return toAjax(dbFallOffService.updateDbFallOff(dbFallOff));
    }

    /**
     * 删除脱落列
     */
    @RequiresPermissions("statistics:off:remove")
    @Log(title = "脱落列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbFallOffService.deleteDbFallOffByIds(ids));
    }


    /*
    *
    * 定时处理查询脱落
    * */
    public  void timing(){

    }






}
