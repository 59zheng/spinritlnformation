package com.ruoyi.mind.CorrectionAndTreatment.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindTreat;
import com.ruoyi.mind.CorrectionAndTreatment.service.IDbMindTreatService;
import com.ruoyi.mind.cure.domain.DbCureDbs;
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
 * 心理治疗Controller
 * 
 * @author zheng
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/CorrectionAndTreatment/treat1")
public class DbMindTreatEndController extends BaseController
{
    private String prefix = "CorrectionAndTreatment/treat1";

    @Autowired
    private IDbMindTreatService dbMindTreatService;


    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("CorrectionAndTreatment:treat:view")
    @GetMapping()
    public String treat()
    {
        return prefix + "/treat";
    }

    /**
     * 查询心理治疗树列表
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbMindTreat> list(DbMindTreat dbMindTreat)
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
        List<DbPatientMessageVo2> tms = TableListUtils.getLisOverCurc("treat");
        List<DbMindTreat> list = new ArrayList<>();
        tms.forEach(itm->{
            DbMindTreat dbMindTreat1 = dbMindTreatService.selectDbMindTreatById(itm.getDbPatientAssociated().getAssociatedId());
            DbMindTreat dbCureTms3 = new DbMindTreat();
            dbCureTms3.setFatherId(itm.getDbPatientAssociated().getAssociatedId());
            List<DbMindTreat> dbMindTreats = dbMindTreatService.selectDbMindTreatList(dbCureTms3);
            list.add(dbMindTreat1);
            list.addAll(dbMindTreats);

        });
        return  list;
    }

    /**
     * 导出心理治疗列表
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:export")
    @Log(title = "心理治疗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbMindTreat dbMindTreat)
    {
        List<DbMindTreat> list = dbMindTreatService.selectDbMindTreatList(dbMindTreat);
        ExcelUtil<DbMindTreat> util = new ExcelUtil<DbMindTreat>(DbMindTreat.class);
        return util.exportExcel(list, "treat");
    }

    /**
     * 新增心理治疗
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbMindTreat", dbMindTreatService.selectDbMindTreatById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存心理治疗
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:add")
    @Log(title = "心理治疗", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbMindTreat dbMindTreat)
    {
        Long fatherId = dbMindTreat.getFatherId();
        DbMindTreat dbMindTreat1 = dbMindTreatService.selectDbMindTreatById(fatherId);
        /*
         * 修改父的剩余次数
         * */
        dbMindTreat1.setHowMany(dbMindTreat.getHowMany() - 1);
        int i = dbMindTreatService.updateDbMindTreat(dbMindTreat1);
        dbMindTreat1.setId(null);
        dbMindTreat1.setPatientName(null);
        dbMindTreat1.setTechnicianName(ShiroUtils.getSysUser().getUserName());
        dbMindTreat1.setExecutionTime(new Date());
        dbMindTreat1.setDocumentAddress(dbMindTreat.getDocumentAddress());
        dbMindTreat1.setFatherId(dbMindTreat.getFatherId());
        dbMindTreat1.setHowMany(null);
        int i1 = dbMindTreatService.insertDbMindTreat(dbMindTreat1);
        /*
         * 修改id
         * */
        int tms = TableListUtils.updateResultId(fatherId, "treat", dbMindTreat1.getPatientId());
        return toAjax(tms);
    }

    /**
     * 修改心理治疗
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbMindTreat dbMindTreat = dbMindTreatService.selectDbMindTreatById(id);
        mmap.put("dbMindTreat", dbMindTreat);
        return prefix + "/edit";
    }

    /**
     * 修改保存心理治疗
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:edit")
    @Log(title = "心理治疗", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbMindTreat dbMindTreat)
    {
        return toAjax(dbMindTreatService.updateDbMindTreat(dbMindTreat));
    }

    /**
     * 删除
     */
    @RequiresPermissions("CorrectionAndTreatment:treat:remove")
    @Log(title = "心理治疗", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbMindTreatService.deleteDbMindTreatById(id));
    }

    /**
     * 选择心理治疗树
     */
    @GetMapping(value = { "/selectTreatTree/{id}", "/selectTreatTree/" })
    public String selectTreatTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbMindTreat", dbMindTreatService.selectDbMindTreatById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载心理治疗树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbMindTreatService.selectDbMindTreatTree();
        return ztrees;
    }
    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbMindTreat dbMindTreat = dbMindTreatService.selectDbMindTreatById(userId);
        String documentAddress = dbMindTreat.getDocumentAddress();
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
