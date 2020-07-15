package com.ruoyi.mind.cure.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.cure.domain.DbCureEct;
import com.ruoyi.mind.cure.domain.DbCureTdct;
import com.ruoyi.mind.cure.service.IDbCureEctService;
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
 * 电休克治疗Controller
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/cure/ect1")
public class DbCureEcteEndController extends BaseController
{
    private String prefix = "cure/ect1";


    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IDbCureEctService dbCureEctService;

    @RequiresPermissions("cure:ect:view")
    @GetMapping()
    public String ect()
    {
        return prefix + "/ect";
    }

    /**
     * 查询电休克治疗树列表
     */
    @RequiresPermissions("cure:ect:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbCureEct> list(DbCureEct dbCureEct)
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
        List<DbPatientMessageVo2> tms = TableListUtils.getLisOverCurc("ECT");
        List<DbCureEct> list = new ArrayList<>();
        tms.forEach(itm->{
            DbCureEct dbCureEct1 = dbCureEctService.selectDbCureEctById(itm.getDbPatientAssociated().getAssociatedId());
            DbCureEct dbCureTms3 = new DbCureEct();
            dbCureTms3.setFatherId(itm.getDbPatientAssociated().getAssociatedId());
            List<DbCureEct> dbCureEcts = dbCureEctService.selectDbCureEctList(dbCureTms3);
            list.add(dbCureEct1);
            list.addAll(dbCureEcts);

        });
        return  list;
    }

    /**
     * 导出电休克治疗列表
     */
    @RequiresPermissions("cure:ect:export")
    @Log(title = "电休克治疗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbCureEct dbCureEct)
    {
        List<DbCureEct> list = dbCureEctService.selectDbCureEctList(dbCureEct);
        ExcelUtil<DbCureEct> util = new ExcelUtil<DbCureEct>(DbCureEct.class);
        return util.exportExcel(list, "ect");
    }

    /**
     * 新增电休克治疗
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbCureEct", dbCureEctService.selectDbCureEctById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存电休克治疗
     */
    @RequiresPermissions("cure:ect:add")
    @Log(title = "电休克治疗", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbCureEct dbCureEct)
    {
        Long fatherId = dbCureEct.getFatherId();
        DbCureEct dbCureTms1 = dbCureEctService.selectDbCureEctById(fatherId);
        /*
         * 修改父的剩余次数
         * */
        dbCureTms1.setHowMany(dbCureTms1.getHowMany() - 1);
        int i = dbCureEctService.updateDbCureEct(dbCureTms1);
        dbCureTms1.setId(null);
        dbCureTms1.setPatientName(null);
        dbCureTms1.setTechnicianName(ShiroUtils.getSysUser().getUserName());
        dbCureTms1.setExecutionTime(new Date());
        dbCureTms1.setDocumentAddress(dbCureEct.getDocumentAddress());
        dbCureTms1.setFatherId(dbCureEct.getFatherId());
        dbCureTms1.setHowMany(null);
        int i1 = dbCureEctService.insertDbCureEct(dbCureTms1);
        /*
         * 修改id
         * */
        int tms = TableListUtils.updateResultId(fatherId, "ECT", dbCureTms1.getPatientId());

        return toAjax(tms);
    }

    /**
     * 修改电休克治疗
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbCureEct dbCureEct = dbCureEctService.selectDbCureEctById(id);
        mmap.put("dbCureEct", dbCureEct);
        return prefix + "/edit";
    }

    /**
     * 修改保存电休克治疗
     */
    @RequiresPermissions("cure:ect:edit")
    @Log(title = "电休克治疗", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbCureEct dbCureEct)
    {
        return toAjax(dbCureEctService.updateDbCureEct(dbCureEct));
    }

    /**
     * 删除
     */
    @RequiresPermissions("cure:ect:remove")
    @Log(title = "电休克治疗", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbCureEctService.deleteDbCureEctById(id));
    }

    /**
     * 选择电休克治疗树
     */
    @GetMapping(value = { "/selectEctTree/{id}", "/selectEctTree/" })
    public String selectEctTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbCureEct", dbCureEctService.selectDbCureEctById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载电休克治疗树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbCureEctService.selectDbCureEctTree();
        return ztrees;
    }
    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbCureEct dbCureEct = dbCureEctService.selectDbCureEctById(userId);
        String documentAddress = dbCureEct.getDocumentAddress();
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
