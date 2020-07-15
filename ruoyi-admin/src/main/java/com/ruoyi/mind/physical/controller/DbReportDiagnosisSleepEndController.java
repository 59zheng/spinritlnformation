package com.ruoyi.mind.physical.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisSleep;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisSleepService;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.utils.Doc2HtmlUtil;
import com.ruoyi.mind.utils.TableListUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 睡眠监测报告Controller
 *
 * @author zheng
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/physical/sleep1")
public class DbReportDiagnosisSleepEndController extends BaseController {
    private String prefix = "physical/sleep1";

    @Autowired
    private IDbReportDiagnosisSleepService dbReportDiagnosisSleepService;

    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("physical:sleep:view")
    @GetMapping()
    public String sleep() {
        return prefix + "/sleep";
    }

    /**
     * 查询睡眠监测报告树列表
     */
    @RequiresPermissions("physical:sleep:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbReportDiagnosisSleep> list(DbReportDiagnosisSleep dbReportDiagnosisSleep) {
        /*
         *     *查询符合条件的需要睡眠监测的病人
         *     *是否已拥有父级id
         *     *返回列表
         * */
        List<DbReportDiagnosisSleep> list = dbReportDiagnosisSleepService.selectDbReportDiagnosisSleepList(dbReportDiagnosisSleep);
        return list;
    }

    /**
     * 导出睡眠监测报告列表
     */
    @RequiresPermissions("physical:sleep:export")
    @Log(title = "睡眠监测报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbReportDiagnosisSleep dbReportDiagnosisSleep) {
        List<DbReportDiagnosisSleep> list = dbReportDiagnosisSleepService.selectDbReportDiagnosisSleepList(dbReportDiagnosisSleep);
        ExcelUtil<DbReportDiagnosisSleep> util = new ExcelUtil<DbReportDiagnosisSleep>(DbReportDiagnosisSleep.class);
        return util.exportExcel(list, "sleep");
    }

    /**
     * 新增睡眠监测报告
     */
    @GetMapping(value = {"/add/{id}", "/add/"})
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap) {
        if (StringUtils.isNotNull(id)) {
            mmap.put("dbReportDiagnosisSleep", dbReportDiagnosisSleepService.selectDbReportDiagnosisSleepById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存睡眠监测报告
     */
    @RequiresPermissions("physical:sleep:add")
    @Log(title = "睡眠监测报告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbReportDiagnosisSleep dbReportDiagnosisSleep) {
        /*
         *获取父级id添加其他信息
         * */
        DbReportDiagnosisSleep dbReportDiagnosisSleep1 = dbReportDiagnosisSleepService.selectDbReportDiagnosisSleepById(dbReportDiagnosisSleep.getFatherId());
        dbReportDiagnosisSleep1.setId(null);
        dbReportDiagnosisSleep1.setDocumentAddress(dbReportDiagnosisSleep.getDocumentAddress());
        dbReportDiagnosisSleep1.setFatherId(dbReportDiagnosisSleep.getFatherId());
        dbReportDiagnosisSleep1.setCreateTime(new Date());
        dbReportDiagnosisSleep1.setPatientName(DateUtils.getDate());
        int i = dbReportDiagnosisSleepService.insertDbReportDiagnosisSleep(dbReportDiagnosisSleep1);
//
        int sleep = TableListUtils.updateResult(dbReportDiagnosisSleep1.getId(), "sleep", dbReportDiagnosisSleep1.getPatientId());
        return toAjax(sleep);
    }

    /**
     * 修改睡眠监测报告
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DbReportDiagnosisSleep dbReportDiagnosisSleep = dbReportDiagnosisSleepService.selectDbReportDiagnosisSleepById(id);
        mmap.put("dbReportDiagnosisSleep", dbReportDiagnosisSleep);
        return prefix + "/edit";
    }

    /**
     * 修改保存睡眠监测报告
     */
    @RequiresPermissions("physical:sleep:edit")
    @Log(title = "睡眠监测报告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbReportDiagnosisSleep dbReportDiagnosisSleep) {
        return toAjax(dbReportDiagnosisSleepService.updateDbReportDiagnosisSleep(dbReportDiagnosisSleep));
    }

    /**
     * 删除
     */
    @RequiresPermissions("physical:sleep:remove")
    @Log(title = "睡眠监测报告", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id) {
        return toAjax(dbReportDiagnosisSleepService.deleteDbReportDiagnosisSleepById(id));
    }

    /**
     * 选择睡眠监测报告树
     */
    @GetMapping(value = {"/selectSleepTree/{id}", "/selectSleepTree/"})
    public String selectSleepTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap) {
        if (StringUtils.isNotNull(id)) {
            mmap.put("dbReportDiagnosisSleep", dbReportDiagnosisSleepService.selectDbReportDiagnosisSleepById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载睡眠监测报告树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        List<Ztree> ztrees = dbReportDiagnosisSleepService.selectDbReportDiagnosisSleepTree();
        return ztrees;
    }

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = Global.getProfile();



    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbReportDiagnosisSleep dbReportDiagnosisSleep = dbReportDiagnosisSleepService.selectDbReportDiagnosisSleepById(userId);
        String documentAddress = dbReportDiagnosisSleep.getDocumentAddress();
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
        return prefix + "/teamLook";
    }
}
