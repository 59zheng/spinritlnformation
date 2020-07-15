package com.ruoyi.mind.lab.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.lab.domain.DbLabDna;
import com.ruoyi.mind.lab.domain.DbLabTdm;
import com.ruoyi.mind.lab.service.IDbLabTdmService;
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
 * 血药浓度检测Controller
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/lab/tdm1")
public class DbLabTdmEndController extends BaseController
{
    private String prefix = "lab/tdm1";

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
        List<DbPatientMessageVo> tdm = TableListUtils.getLisOver("TDM");
        return getDataTable(tdm);
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
    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbLabTdm dbLabTdm = dbLabTdmService.selectDbLabTdmById(userId);
        String documentAddress = dbLabTdm.getDocumentAddress();
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
