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
import com.ruoyi.mind.lab.domain.DbLabDna;
import com.ruoyi.mind.lab.service.IDbLabDnaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基因检测Controller
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/lab/dna")
public class DbLabDnaController extends BaseController
{
    private String prefix = "lab/dna";

    @Autowired
    private IDbLabDnaService dbLabDnaService;

    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("lab:dna:view")
    @GetMapping()
    public String dna()
    {
        return prefix + "/dna";
    }

    /**
     * 查询基因检测列表
     */
    @RequiresPermissions("lab:dna:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbLabDna dbLabDna)
    {
        startPage();
        List<DbPatientMessage> list = TableListUtils.getList("DNA");
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
     * 导出基因检测列表
     */
    @RequiresPermissions("lab:dna:export")
    @Log(title = "基因检测", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbLabDna dbLabDna)
    {
        List<DbLabDna> list = dbLabDnaService.selectDbLabDnaList(dbLabDna);
        ExcelUtil<DbLabDna> util = new ExcelUtil<DbLabDna>(DbLabDna.class);
        return util.exportExcel(list, "dna");
    }

    /**
     * 新增基因检测
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存基因检测
     */
    @RequiresPermissions("lab:dna:add")
    @Log(title = "基因检测", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbLabDna dbLabDna)
    {

        /*
         * 新增保存  关联表id给中间表  标记完成
         * */
        SysUser sysUser = ShiroUtils.getSysUser();
        SysUser sysUser1 = sysUserService.selectUserById(TableListUtils.getTeamId(dbLabDna.getPatientId()));

        dbLabDna.setAttendingPhysician(sysUser1.getUserName());
        dbLabDna.setTechnicianName(sysUser.getUserName());
        dbLabDna.setExecutionTime(new Date());
        int i = dbLabDnaService.insertDbLabDna(dbLabDna);
//
        Long id = dbLabDna.getId();
        Long patientId = dbLabDna.getPatientId();
        int induced = TableListUtils.updateResult(id, "DNA", patientId);
        return toAjax(induced);
    }

    /**
     * 修改基因检测
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbLabDna dbLabDna = dbLabDnaService.selectDbLabDnaById(id);
        mmap.put("dbLabDna", dbLabDna);
        return prefix + "/edit";
    }

    /**
     * 修改保存基因检测
     */
    @RequiresPermissions("lab:dna:edit")
    @Log(title = "基因检测", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbLabDna dbLabDna)
    {
        return toAjax(dbLabDnaService.updateDbLabDna(dbLabDna));
    }

    /**
     * 删除基因检测
     */
    @RequiresPermissions("lab:dna:remove")
    @Log(title = "基因检测", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbLabDnaService.deleteDbLabDnaByIds(ids));
    }
}
