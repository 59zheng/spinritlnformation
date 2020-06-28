package com.ruoyi.mind.registered.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.json.JSON;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.registered.domain.DbPatientEntrust;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.catalina.User;
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

/**
 * 诊断记录(正在就诊)Controller
 *
 * @author zheng
 * @date 2020-06-05
 */
@Controller
@RequestMapping("/registered/message")
public class DbPatientMessageController extends BaseController {
    private String prefix = "registered/message";

    @Autowired
    private IDbPatientMessageService dbPatientMessageService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysPostService postService;


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
        dbPatientMessage.setIsInquiry("0");
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
         new ArrayList<>();

        SysUser sysUser = new SysUser();
        List<SysUser> sysUsers  = userService.selectUserList(sysUser);
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
        List<SysUser> sysUsers =new ArrayList<>();
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
     * 添加主治团队
     * */
    @GetMapping("/diagnosisAdd")
    @ResponseBody
    public AjaxResult diagnosisAdd(String str) {
        String[] split = str.split(",");
        for (String s : split) {
            System.out.println(s);
        }
        return toAjax(1);
    }


    /*
     * 确认就诊
     * */
    @GetMapping("/confirm")
    @ResponseBody
    public AjaxResult confirm(String userId) {
        Long aLong = Long.parseLong(userId);
//   修改状态为已就诊
        DbPatientMessage dbPatientMessage = new DbPatientMessage();
        dbPatientMessage.setId(aLong);
        dbPatientMessage.setIsInquiry("1");
        return toAjax(dbPatientMessageService.updateDbPatientMessage(dbPatientMessage));
    }

}
