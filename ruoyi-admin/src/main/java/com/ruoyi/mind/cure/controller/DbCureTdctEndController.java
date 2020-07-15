package com.ruoyi.mind.cure.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.cure.domain.DbCureTdct;
import com.ruoyi.mind.cure.domain.DbCureTms;
import com.ruoyi.mind.cure.service.IDbCureTdctService;
import com.ruoyi.mind.registered.domain.DbPatientMessageVo2;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 经颅直流电治疗Controller
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/cure/tdct1")
public class DbCureTdctEndController extends BaseController
{
    private String prefix = "cure/tdct1";

    @Autowired
    private IDbCureTdctService dbCureTdctService;



    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("cure:tdct:view")
    @GetMapping()
    public String tdct()
    {
        return prefix + "/tdct";
    }

    /**
     * 查询经颅直流电治疗树列表
     */
    @RequiresPermissions("cure:tdct:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbCureTdct> list(DbCureTdct dbCureTdct)
    {
        /*
         *     *查询符合条件的需要治疗的病人
         *     *是否已拥有父级id
         *     *返回列表
         *
         * */
        /*
         *     *查询符合条件的需要治疗的病人
         *     *是否已拥有父级id
         *     *返回列表
         *
         * */
        List<DbPatientMessageVo2> tms = TableListUtils.getLisOverCurc("TMS");
        List<DbCureTdct> list = new ArrayList<>();
        tms.forEach(itm->{
            DbCureTdct dbCureTdct1 = dbCureTdctService.selectDbCureTdctById(itm.getDbPatientAssociated().getAssociatedId());
            DbCureTdct dbCureTms3 = new DbCureTdct();
            dbCureTms3.setFatherId(itm.getDbPatientAssociated().getAssociatedId());
            List<DbCureTdct> dbCureTdcts = dbCureTdctService.selectDbCureTdctList(dbCureTms3);
            list.add(dbCureTdct1);
            list.addAll(dbCureTdcts);

        });
        return  list;
    }

    /**
     * 导出经颅直流电治疗列表
     */
    @RequiresPermissions("cure:tdct:export")
    @Log(title = "经颅直流电治疗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbCureTdct dbCureTdct)
    {
        List<DbCureTdct> list = dbCureTdctService.selectDbCureTdctList(dbCureTdct);
        ExcelUtil<DbCureTdct> util = new ExcelUtil<DbCureTdct>(DbCureTdct.class);
        return util.exportExcel(list, "tdct");
    }

    /**
     * 新增经颅直流电治疗
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbCureTdct", dbCureTdctService.selectDbCureTdctById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存经颅直流电治疗
     */
    @RequiresPermissions("cure:tdct:add")
    @Log(title = "经颅直流电治疗", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbCureTdct dbCureTdct)
    {

        Long fatherId = dbCureTdct.getFatherId();
        DbCureTdct dbCureTms1 = dbCureTdctService.selectDbCureTdctById(fatherId);
        /*
         * 修改父的剩余次数
         * */
        dbCureTms1.setHowMany(dbCureTms1.getHowMany() - 1);
        int i = dbCureTdctService.updateDbCureTdct(dbCureTms1);
        dbCureTms1.setId(null);
        dbCureTms1.setPatientName(null);
        dbCureTms1.setTechnicianName(ShiroUtils.getSysUser().getUserName());
        dbCureTms1.setExecutionTime(new Date());
        dbCureTms1.setDocumentAddress(dbCureTdct.getDocumentAddress());
        dbCureTms1.setFatherId(dbCureTdct.getFatherId());
        dbCureTms1.setHowMany(null);
        int i1 = dbCureTdctService.insertDbCureTdct(dbCureTms1);
        /*
         * 修改id
         * */
        int tms = TableListUtils.updateResultId(fatherId, "TDCT", dbCureTms1.getPatientId());

        return toAjax(tms);
    }

    /**
     * 修改经颅直流电治疗
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbCureTdct dbCureTdct = dbCureTdctService.selectDbCureTdctById(id);
        mmap.put("dbCureTdct", dbCureTdct);
        return prefix + "/edit";
    }

    /**
     * 修改保存经颅直流电治疗
     */
    @RequiresPermissions("cure:tdct:edit")
    @Log(title = "经颅直流电治疗", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbCureTdct dbCureTdct)

    {
        Long fatherId = dbCureTdct.getFatherId();
        DbCureTdct dbCureTms1 = dbCureTdctService.selectDbCureTdctById(fatherId);
        /*
         * 修改父的剩余次数
         * */
        dbCureTms1.setHowMany(dbCureTms1.getHowMany() - 1);
        int i = dbCureTdctService.updateDbCureTdct(dbCureTms1);
        dbCureTms1.setId(null);
        dbCureTms1.setTechnicianName(ShiroUtils.getSysUser().getUserName());
        dbCureTms1.setExecutionTime(new Date());
        dbCureTms1.setDocumentAddress(dbCureTdct.getDocumentAddress());
        dbCureTms1.setFatherId(dbCureTdct.getFatherId());
        dbCureTms1.setHowMany(null);
        int i1 = dbCureTdctService.insertDbCureTdct(dbCureTms1);
        /*
         * 修改id
         * */
        int tms = TableListUtils.updateResultId(fatherId, "TDCT", dbCureTms1.getPatientId());

        return toAjax(tms);
    }

    /**
     * 删除
     */
    @RequiresPermissions("cure:tdct:remove")
    @Log(title = "经颅直流电治疗", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbCureTdctService.deleteDbCureTdctById(id));
    }

    /**
     * 选择经颅直流电治疗树
     */
    @GetMapping(value = { "/selectTdctTree/{id}", "/selectTdctTree/" })
    public String selectTdctTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbCureTdct", dbCureTdctService.selectDbCureTdctById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载经颅直流电治疗树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbCureTdctService.selectDbCureTdctTree();
        return ztrees;
    }
    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbCureTdct dbCureTdct = dbCureTdctService.selectDbCureTdctById(userId);
        String documentAddress = dbCureTdct.getDocumentAddress();
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
