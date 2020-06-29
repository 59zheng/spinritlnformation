package com.ruoyi.mind.registered.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.diagnosis.domain.DbDiagonsisProject;
import com.ruoyi.mind.diagnosis.service.IDbDiagonsisProjectService;
import com.ruoyi.mind.registered.domain.DbPatientAssociated;
import com.ruoyi.mind.registered.service.IDbPatientAssociatedService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.lang.StringEscapeUtils;
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
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.registered.service.IDbPatientMessageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import springfox.documentation.spring.web.json.Json;

/**
 * 诊断记录(正在就诊)Controller
 *
 * @author zheng
 * @date 2020-06-05
 */
@Controller
@RequestMapping(value = "/registered/message1", produces = {"application/json;charset=UTF-8"})
public class DbPatientMessageEndController extends BaseController {
    private String prefix = "registered/message1";

    @Autowired
    private IDbPatientMessageService dbPatientMessageService;

    @Autowired
    private ISysUserService userService;


    @Autowired
    private IDbDiagonsisProjectService dbDiagonsisProjectService;


    @Autowired
    private IDbPatientAssociatedService dbPatientAssociatedService;


    @RequiresPermissions("registered:message:view")
    @GetMapping()
    public String message() {
        return prefix + "/message";
    }

    /**
     * 查询病人,缴费之后创建列表
     */
    @RequiresPermissions("registered:message:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbPatientMessage dbPatientMessage) {
        dbPatientMessage.setIsInquiry("1");
        startPage();
        List<DbPatientMessage> list = dbPatientMessageService.selectDbPatientMessageList(dbPatientMessage);
        return getDataTable(list);
    }

    /**
     * 导出病人,缴费之后创建列表
     */
    @RequiresPermissions("registered:message:export")
    @Log(title = "病人,缴费之后创建", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbPatientMessage dbPatientMessage) {
        List<DbPatientMessage> list = dbPatientMessageService.selectDbPatientMessageList(dbPatientMessage);
        ExcelUtil<DbPatientMessage> util = new ExcelUtil<DbPatientMessage>(DbPatientMessage.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 新增病人,缴费之后创建
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存病人,缴费之后创建
     */
    @RequiresPermissions("registered:message:add")
    @Log(title = "病人,缴费之后创建", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbPatientMessage dbPatientMessage) {
        return toAjax(dbPatientMessageService.insertDbPatientMessage(dbPatientMessage));
    }

    /**
     * 修改病人,缴费之后创建
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DbPatientMessage dbPatientMessage = dbPatientMessageService.selectDbPatientMessageById(id);
        mmap.put("dbPatientMessage", dbPatientMessage);
        return prefix + "/edit";
    }

    /**
     * 修改保存病人,缴费之后创建
     */
    @RequiresPermissions("registered:message:edit")
    @Log(title = "病人,缴费之后创建", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbPatientMessage dbPatientMessage) {
        return toAjax(dbPatientMessageService.updateDbPatientMessage(dbPatientMessage));
    }

    /**
     * 删除病人,缴费之后创建
     */
    @RequiresPermissions("registered:message:remove")
    @Log(title = "病人,缴费之后创建", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dbPatientMessageService.deleteDbPatientMessageByIds(ids));
    }

    /*
     * 添加主治团队
     * */
    @GetMapping("/teamAppend/{userId}")
    public String teamAppend(@PathVariable("userId") Long userId, ModelMap mmap) {
        SysUser sysUser = new SysUser();
        List<SysUser> sysUsers = userService.selectUserList(sysUser);
        SysUser sysUser1 = ShiroUtils.getSysUser();
        for (int i = 0; i < sysUsers.size(); i++) {
            if (sysUsers.get(i).getUserId() == sysUser1.getUserId()) {
                sysUsers.remove(i);
            }
        }
        mmap.put("user", sysUsers);
        mmap.put("userId", userId);
        return prefix + "/teamAppend";
    }

    /*
     * 查看主治团队
     * */
    @GetMapping("/teamLook/{userId}")
    public String teamLook(@PathVariable("userId") Long userId, ModelMap mmap) {
        DbPatientMessage dbPatientMessage = dbPatientMessageService.selectDbPatientMessageById(userId);
        String teamAttending = dbPatientMessage.getTeamAttending();
        String[] split = teamAttending.split(",");
        List<SysUser> sysUsers = new ArrayList<>();
        for (String aLong : split) {
            sysUsers.add(userService.selectUserById(Long.valueOf(aLong)));
        }
        mmap.put("user", sysUsers);
        mmap.put("userId", userId);
        return prefix + "/teamLook";
    }

    /*
     * 添加主治团队
     * */
    @GetMapping("/teamAppendSave")
    @ResponseBody
    public AjaxResult teamAppendAll(String ids, String userId) {
        DbPatientMessage dbPatientMessage = new DbPatientMessage();
        dbPatientMessage.setId(Long.valueOf(userId));
        dbPatientMessage.setTeamAttending(ids);
        return toAjax(dbPatientMessageService.updateDbPatientMessage(dbPatientMessage));
    }

    /*
     * 添加检测项目
     * */
    @GetMapping("/diagnosisAdd/{userId}")
    public String diagnosisAdd(@PathVariable("userId") Long userId, ModelMap map) {
        map.put("userId", userId);
        DbDiagonsisProject dbDiagonsisProject = new DbDiagonsisProject();
        List<DbDiagonsisProject> dbDiagonsisProjects = dbDiagonsisProjectService.selectDbDiagonsisProjectList(dbDiagonsisProject);
        for (int i = 0; i < dbDiagonsisProjects.size(); i++) {
            if (dbDiagonsisProjects.get(i).getProductId() == null) {
                dbDiagonsisProjects.remove(dbDiagonsisProjects.get(i));
            }
        }


//        已经选中的
        List<DbDiagonsisProject> dbDiagonsisProjects2 = new ArrayList<>();
        DbPatientMessage dbPatientMessage = dbPatientMessageService.selectDbPatientMessageById(userId);
        String isAddDetection = dbPatientMessage.getIsAddDetection();
        String[] split = isAddDetection.split(",");
        if (split.length>0){

        for (String s1 : split) {
            if (!s1.equals("")) {
                DbDiagonsisProject dbDiagonsisProject1 = dbDiagonsisProjectService.selectDbDiagonsisProjectById(Long.valueOf(s1));
                dbDiagonsisProjects2.add(dbDiagonsisProject1);
                if (dbDiagonsisProjects.size() > 0) {
                    for (DbDiagonsisProject diagonsisProject : dbDiagonsisProjects) {
                        if (diagonsisProject.getId().toString().equals(s1)) {
                            dbDiagonsisProjects.remove(diagonsisProject);
                            if (dbDiagonsisProjects.size() == 0) {
                                break;
                            }
                        }
                    }
                }
            }
        }



        }
        String json = JSON.toJSONString(dbDiagonsisProjects).trim();
        String s = StringEscapeUtils.unescapeHtml(json);
        String json2 = JSON.toJSONString(dbDiagonsisProjects2).trim();
        String s2 = StringEscapeUtils.unescapeHtml(json2);
        map.put("list2", s2);
        map.put("list", s);
        return prefix + "/duallistbox";
    }

    /*
     * 保存检测项目
     * */
    @GetMapping("/diagnosisSave")
    @ResponseBody
    public AjaxResult diagnosisSave(String ids, String userId) {
//
        String[] split = ids.split(",");
//        更改记录表
        DbPatientMessage dbPatientMessage = dbPatientMessageService.selectDbPatientMessageById(Long.valueOf(userId));
        dbPatientMessage.setIsAddDetection(ids);
        for (String s : split) {
            DbDiagonsisProject dbDiagonsisProject = dbDiagonsisProjectService.selectDbDiagonsisProjectById(Long.valueOf(s));
            DbPatientAssociated dbPatientAssociated = new DbPatientAssociated();
            dbPatientAssociated.setPatientId(Long.valueOf(userId));
            dbPatientAssociated.setAssociatedTable(dbDiagonsisProject.getCodeName());
            dbPatientAssociated.setIsOk("0");
            List<DbPatientAssociated> dbPatientAssociateds = dbPatientAssociatedService.selectDbPatientAssociatedList(dbPatientAssociated);
            if (dbPatientAssociateds.size() > 0) {
                DbPatientAssociated dbPatientAssociated1 = dbPatientAssociateds.get(0);
                dbPatientAssociated1.setCreateTime(new Date());
                dbPatientAssociatedService.updateDbPatientAssociated(dbPatientAssociated1);
            } else {
                int i = dbPatientAssociatedService.insertDbPatientAssociated(dbPatientAssociated);
            }
        }
        return toAjax(dbPatientMessageService.updateDbPatientMessage(dbPatientMessage));
    }

}