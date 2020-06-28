package com.ruoyi.mind.registered.controller;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.registered.service.IDbPatientMessageService;
import com.ruoyi.system.domain.SysPost;
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
import com.ruoyi.mind.registered.domain.DbPatientEntrust;
import com.ruoyi.mind.registered.service.IDbPatientEntrustService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医嘱(关联诊断记录 ,关联主治医师)Controller
 *
 * @author zheng
 * @date 2020-06-18
 */
@Controller
@RequestMapping("/registered/entrust")
public class DbPatientEntrustController extends BaseController {
    private String prefix = "registered/entrust";


    @Autowired
    private IDbPatientMessageService dbPatientMessageService;

    @Autowired
    private IDbPatientEntrustService dbPatientEntrustService;

    @RequiresPermissions("registered:entrust:view")
    @GetMapping()
    public String entrust() {
        return prefix + "/entrust";
    }

    /**
     * 查询医嘱(关联诊断记录 ,关联主治医师)列表
     */
    @RequiresPermissions("registered:entrust:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbPatientEntrust dbPatientEntrust) {
        startPage();
        List<DbPatientEntrust> list = dbPatientEntrustService.selectDbPatientEntrustList(dbPatientEntrust);
        return getDataTable(list);
    }

    /**
     * 导出医嘱(关联诊断记录 ,关联主治医师)列表
     */
    @RequiresPermissions("registered:entrust:export")
    @Log(title = "医嘱(关联诊断记录 ,关联主治医师)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbPatientEntrust dbPatientEntrust) {
        List<DbPatientEntrust> list = dbPatientEntrustService.selectDbPatientEntrustList(dbPatientEntrust);
        ExcelUtil<DbPatientEntrust> util = new ExcelUtil<DbPatientEntrust>(DbPatientEntrust.class);
        return util.exportExcel(list, "entrust");
    }

    /**
     * 新增医嘱(关联诊断记录 ,关联主治医师)
     */
    @GetMapping("/add")
    public String add() {

        return prefix + "/add";
    }

    /**
     * 新增保存医嘱(关联诊断记录 ,关联主治医师)
     */
    @RequiresPermissions("registered:entrust:add")
    @Log(title = "医嘱(关联诊断记录 ,关联主治医师)", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbPatientEntrust dbPatientEntrust) {
        return toAjax(dbPatientEntrustService.insertDbPatientEntrust(dbPatientEntrust));
    }

    /**
     * 修改医嘱(关联诊断记录 ,关联主治医师)
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DbPatientEntrust dbPatientEntrust = dbPatientEntrustService.selectDbPatientEntrustById(id);
        mmap.put("dbPatientEntrust", dbPatientEntrust);
        return prefix + "/edit";
    }

    /**
     * 修改保存医嘱(关联诊断记录 ,关联主治医师)
     */
    @RequiresPermissions("registered:entrust:edit")
    @Log(title = "医嘱(关联诊断记录 ,关联主治医师)", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbPatientEntrust dbPatientEntrust) {
        return toAjax(dbPatientEntrustService.updateDbPatientEntrust(dbPatientEntrust));
    }

    /**
     * 删除医嘱(关联诊断记录 ,关联主治医师)
     */
    @RequiresPermissions("registered:entrust:remove")
    @Log(title = "医嘱(关联诊断记录 ,关联主治医师)", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dbPatientEntrustService.deleteDbPatientEntrustByIds(ids));
    }

    /*
     * 添加医嘱
     * */
    @GetMapping("/entrustAppend/{userId}")
    public String entrustAppend(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("userId", userId);
        DbPatientEntrust dbPatientEntrust = new DbPatientEntrust();

        dbPatientEntrust.setPatientId(Long.valueOf(userId));
        // 获取当前的用户信息
        SysUser sysUser = ShiroUtils.getSysUser();
        Long userId1 = sysUser.getUserId();
        dbPatientEntrust.setUserId(userId1);
        List<DbPatientEntrust> dbPatientEntrusts = dbPatientEntrustService.selectDbPatientEntrustList(dbPatientEntrust);
        if (dbPatientEntrusts.size()>0) {
            DbPatientEntrust dbPatientEntrust1 = dbPatientEntrusts.get(0);
            String entrustContent = dbPatientEntrust1.getEntrustContent();
            List<Map<String, String>> parse = (List<Map<String, String>>) JSONArray.parse(entrustContent);
            List<Map<String, String>> maps = new ArrayList<>();
            parse.forEach(item -> {
                Set<String> strings = item.keySet();
                strings.forEach(key -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("userCode", key);
                    map.put("userName", item.get(key));
                    maps.add(map);
                });
            });
            mmap.put("data", maps);
        }

        return prefix + "/add";
    }

    /**
     * 新增保存医嘱(关联诊断记录 ,关联主治医师)
     */
    @GetMapping("/entrustAppendSave")
    @ResponseBody
    public AjaxResult entrustAppendSave(String ids, String userId) {
        List<Map> maps = (List<Map>) JSON.parse(ids);
        ArrayList<Map> maps2 = new ArrayList<>();
        for (Map map : maps) {
            Map<String, String> maps1 = dbPatientEntrustService.entrustAppendSave(map);
            maps2.add(maps1);
        }
        String s = JSON.toJSONString(maps2);
        DbPatientEntrust dbPatientEntrust = new DbPatientEntrust();

        dbPatientEntrust.setPatientId(Long.valueOf(userId));
        // 获取当前的用户信息
        SysUser sysUser = ShiroUtils.getSysUser();
        Long userId1 = sysUser.getUserId();
        dbPatientEntrust.setUserId(userId1);
        List<DbPatientEntrust> dbPatientEntrusts = dbPatientEntrustService.selectDbPatientEntrustList(dbPatientEntrust);
        int i = 0;
        if (dbPatientEntrusts != null) {
            DbPatientEntrust dbPatientEntrust1 = dbPatientEntrusts.get(0);
            if (!dbPatientEntrust1.getEntrustContent().equals(s)){
                dbPatientEntrust1.setEntrustContent(s);
                i = dbPatientEntrustService.updateDbPatientEntrust(dbPatientEntrust1);
            }
        } else {
            dbPatientEntrust.setEntrustContent(s);
            i = dbPatientEntrustService.insertDbPatientEntrust(dbPatientEntrust);
        }
//        添加病人记录表对应的医嘱id
        List<DbPatientEntrust> dbPatientEntrusts2 = dbPatientEntrustService.selectDbPatientEntrustList(dbPatientEntrust);
        DbPatientEntrust dbPatientEntrust1 = dbPatientEntrusts2.get(0);
        DbPatientMessage dbPatientMessage = new DbPatientMessage();
        dbPatientMessage.setId(dbPatientEntrust1.getPatientId());
        dbPatientMessage.setEntrustId(dbPatientEntrust1.getId());
        return toAjax(dbPatientMessageService.updateDbPatientMessage(dbPatientMessage));
    }


    @Autowired
    private ISysUserService userService;

    /*
     * 添加主治团队
     * */
    @GetMapping("/teamAppend/{userId}")
    public String teamAppend(@PathVariable("userId") Long userId, ModelMap mmap) {
        SysUser sysUser = new SysUser();
        List<SysUser> sysUsers = new ArrayList<>();
        DbPatientEntrust dbPatientEntrust1 = new DbPatientEntrust();
        dbPatientEntrust1.setPatientId(userId);
        List<DbPatientEntrust> dbPatientEntrusts = dbPatientEntrustService.selectDbPatientEntrustList(dbPatientEntrust1);
        DbPatientEntrust dbPatientEntrust = dbPatientEntrusts.get(0);
        sysUsers = userService.selectUserList(sysUser);
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
     * 添加主治团队
     * */
    @GetMapping("/teamAppendSave")
    @ResponseBody
    public AjaxResult teamAppendAll(String ids, String userId) {

        DbPatientEntrust dbPatientEntrust = new DbPatientEntrust();
        dbPatientEntrust.setSendId(ids);
        dbPatientEntrust.setPatientId(Long.valueOf(userId));
        // 获取当前的用户信息
        SysUser sysUser = ShiroUtils.getSysUser();
        Long userId1 = sysUser.getUserId();
        dbPatientEntrust.setUserId(userId1);
        return toAjax(dbPatientEntrustService.insertDbPatientEntrust(dbPatientEntrust));
    }
}
