package com.ruoyi.statistics.controller;

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
import com.ruoyi.statistics.domain.DbChatMessage;
import com.ruoyi.statistics.service.IDbChatMessageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 聊天记录Controller
 * 
 * @author zheng
 * @date 2020-08-13
 */
@Controller
@RequestMapping("/statistics/message")
public class DbChatMessageController extends BaseController
{
    private String prefix = "statistics/message";

    @Autowired
    private IDbChatMessageService dbChatMessageService;

    @RequiresPermissions("statistics:message:view")
    @GetMapping()
    public String message()
    {
        return prefix + "/message";
    }

    /**
     * 查询聊天记录列表
     */
    @RequiresPermissions("statistics:message:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbChatMessage dbChatMessage)
    {
        startPage();
        List<DbChatMessage> list = dbChatMessageService.selectDbChatMessageList(dbChatMessage);
        return getDataTable(list);
    }
    /**
     * 导出聊天记录列表
     */
    @RequiresPermissions("statistics:message:export")
    @Log(title = "聊天记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbChatMessage dbChatMessage)
    {
        List<DbChatMessage> list = dbChatMessageService.selectDbChatMessageList(dbChatMessage);
        ExcelUtil<DbChatMessage> util = new ExcelUtil<DbChatMessage>(DbChatMessage.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 新增聊天记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存聊天记录
     */
    @RequiresPermissions("statistics:message:add")
    @Log(title = "聊天记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbChatMessage dbChatMessage)
    {
        return toAjax(dbChatMessageService.insertDbChatMessage(dbChatMessage));
    }

    /**
     * 修改聊天记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbChatMessage dbChatMessage = dbChatMessageService.selectDbChatMessageById(id);
        mmap.put("dbChatMessage", dbChatMessage);
        return prefix + "/edit";
    }

    /**
     * 修改保存聊天记录
     */
    @RequiresPermissions("statistics:message:edit")
    @Log(title = "聊天记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbChatMessage dbChatMessage)
    {
        return toAjax(dbChatMessageService.updateDbChatMessage(dbChatMessage));
    }

    /**
     * 删除聊天记录
     */
    @RequiresPermissions("statistics:message:remove")
    @Log(title = "聊天记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbChatMessageService.deleteDbChatMessageByIds(ids));
    }
}
