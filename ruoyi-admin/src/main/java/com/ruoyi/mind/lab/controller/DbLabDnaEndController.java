package com.ruoyi.mind.lab.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.cure.domain.DbCureEct;
import com.ruoyi.mind.lab.domain.DbLabDna;
import com.ruoyi.mind.lab.service.IDbLabDnaService;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.registered.domain.DbPatientMessageVo;
import com.ruoyi.mind.utils.Doc2HtmlUtil;
import com.ruoyi.mind.utils.TableListUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 基因检测Controller
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/lab/dna1")
public class DbLabDnaEndController extends BaseController
{
    private String prefix = "lab/dna1";

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
        /*
         *
         * 病人信息  <泛型>
         * */
        startPage();
        List<DbPatientMessageVo> list = TableListUtils.getLisOver("DNA");
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
        return toAjax(dbLabDnaService.insertDbLabDna(dbLabDna));
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
    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbLabDna dbLabDna = dbLabDnaService.selectDbLabDnaById(userId);
        String documentAddress = dbLabDna.getDocumentAddress();
        String path = TableListUtils.getPath(documentAddress);
        Doc2HtmlUtil coc2HtmlUtil = Doc2HtmlUtil.getDoc2HtmlUtilInstance();
        File file = null;
        FileInputStream fileInputStream = null;

        file = new File(path);
        fileInputStream = new FileInputStream(file);
        String pathPage = TableListUtils.getPathPage(documentAddress);
        String PathType = TableListUtils.getPathType(documentAddress);
        String s = coc2HtmlUtil.file2pdf(fileInputStream, pathPage, PathType);
//        返回生成pdf路径  转化为服务器路径

        String replace = TableListUtils.getPathE(documentAddress,s);
        mmap.put("path",replace);
        return  "common/teamLook";
    }
}
