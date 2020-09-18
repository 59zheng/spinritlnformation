package com.ruoyi.mind.CorrectionAndTreatment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindCorrect;
import com.ruoyi.mind.CorrectionAndTreatment.service.IDbMindCorrectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 心理矫正Controller
 * 
 * @author zheng
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/CorrectionAndTreatment/correct")
public class DbMindCorrectController extends BaseController
{
    private String prefix = "CorrectionAndTreatment/correct";

    @Autowired
    private IDbMindCorrectService dbMindCorrectService;

    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("CorrectionAndTreatment:correct:view")
    @GetMapping()
    public String correct()
    {
        return prefix + "/correct";
    }

    /**
     * 查询心理矫正树列表
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbMindCorrect> list(DbMindCorrect dbMindCorrect)
    {
        /*
         *     *查询符合条件的需要治疗的病人
         *     *是否已拥有父级id
         *     *返回列表
         *
         * */
        List<DbPatientMessageVo2> tms = TableListUtils.getListCurc("correct");
        List<DbMindCorrect> list = new ArrayList<>();
        tms.forEach(item -> {
//            判断是否完成
            if (item.getDbPatientAssociated().getAssociatedId() != null) {
//                  绑定过
                DbMindCorrect dbMindCorrect1 = dbMindCorrectService.selectDbMindCorrectById(item.getDbPatientAssociated().getAssociatedId());
//                是否完成
                if (dbMindCorrect1.getHowMany() == 0) {
//                    已完成
                    TableListUtils.updateResultOk(item.getDbPatientAssociated().getAssociatedId(), "DBS", item.getDbPatientAssociated().getPatientId());

                }else {
                    DbMindCorrect dbCureTms3 = new DbMindCorrect();
                    dbCureTms3.setFatherId(item.getDbPatientAssociated().getAssociatedId());

                    List<DbMindCorrect> dbMindCorrects = dbMindCorrectService.selectDbMindCorrectList(dbCureTms3);
                    list.add(dbMindCorrect1);
                    list.addAll(dbMindCorrects);
                }

            } else {
                DbMindCorrect dbCureTms1 = new DbMindCorrect();
                Long id = item.getDbPatientMessage().getId();
                dbCureTms1.setPatientId(id);
                dbCureTms1.setFatherId(0L);

                dbCureTms1.setPatientName(item.getDbPatientMessage().getPatientName());
//            主治医生
                SysUser sysUser1 = sysUserService.selectUserById(item.getDbPatientMessage().getTaemId());
                dbCureTms1.setAttendingPhysician(sysUser1.getUserName());
/*//            技师名称
            dbCureTms1.setTechnicianName(ShiroUtils.getSysUser().getUserName());*/
//          数量
                dbCureTms1.setHowMany(item.getDbPatientAssociated().getTreatmentNum());
                List<DbMindCorrect> dbMindCorrects = dbMindCorrectService.selectDbMindCorrectList(dbCureTms1);
                if (dbMindCorrects == null || dbMindCorrects.size() == 0) {
                    dbCureTms1.setExecutionTime(new Date());
                    dbMindCorrectService.insertDbMindCorrect(dbCureTms1);
                    list.add(dbCureTms1);
                }else {
                    list.addAll(dbMindCorrects);
                }
            }

        });
        return list;
    }

    /**
     * 导出心理矫正列表
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:export")
    @Log(title = "心理矫正", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbMindCorrect dbMindCorrect)
    {
        List<DbMindCorrect> list = dbMindCorrectService.selectDbMindCorrectList(dbMindCorrect);
        ExcelUtil<DbMindCorrect> util = new ExcelUtil<DbMindCorrect>(DbMindCorrect.class);
        return util.exportExcel(list, "correct");
    }

    /**
     * 新增心理矫正
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbMindCorrect", dbMindCorrectService.selectDbMindCorrectById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存心理矫正
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:add")
    @Log(title = "心理矫正", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbMindCorrect dbMindCorrect)
    {
        Long fatherId = dbMindCorrect.getFatherId();
        DbMindCorrect dbMindCorrect1 = dbMindCorrectService.selectDbMindCorrectById(fatherId);
        /*
         * 修改父的剩余次数
         * */
        dbMindCorrect1.setHowMany(dbMindCorrect.getHowMany() - 1);
        int i = dbMindCorrectService.updateDbMindCorrect(dbMindCorrect1);
        dbMindCorrect1.setId(null);
        dbMindCorrect1.setPatientName(null);
        dbMindCorrect1.setTechnicianName(ShiroUtils.getSysUser().getUserName());
        dbMindCorrect1.setExecutionTime(new Date());
        dbMindCorrect1.setDocumentAddress(dbMindCorrect.getDocumentAddress());
        dbMindCorrect1.setFatherId(dbMindCorrect.getFatherId());
        dbMindCorrect1.setHowMany(null);
        int i1 = dbMindCorrectService.insertDbMindCorrect(dbMindCorrect1);
        /*
         * 修改id
         * */
        int tms = TableListUtils.updateResultId(fatherId, "correct", dbMindCorrect1.getPatientId());
        return toAjax(tms);
    }

    /**
     * 修改心理矫正
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbMindCorrect dbMindCorrect = dbMindCorrectService.selectDbMindCorrectById(id);
        mmap.put("dbMindCorrect", dbMindCorrect);
        return prefix + "/edit";
    }

    /**
     * 修改保存心理矫正
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:edit")
    @Log(title = "心理矫正", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbMindCorrect dbMindCorrect)
    {
        return toAjax(dbMindCorrectService.updateDbMindCorrect(dbMindCorrect));
    }

    /**
     * 删除
     */
    @RequiresPermissions("CorrectionAndTreatment:correct:remove")
    @Log(title = "心理矫正", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbMindCorrectService.deleteDbMindCorrectById(id));
    }

    /**
     * 选择心理矫正树
     */
    @GetMapping(value = { "/selectCorrectTree/{id}", "/selectCorrectTree/" })
    public String selectCorrectTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbMindCorrect", dbMindCorrectService.selectDbMindCorrectById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载心理矫正树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbMindCorrectService.selectDbMindCorrectTree();
        return ztrees;
    }
    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbMindCorrect dbMindCorrect = dbMindCorrectService.selectDbMindCorrectById(userId);
        String documentAddress = dbMindCorrect.getDocumentAddress();
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
