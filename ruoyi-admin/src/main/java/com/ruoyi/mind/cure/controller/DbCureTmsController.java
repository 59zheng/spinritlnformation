package com.ruoyi.mind.cure.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisSleep;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.registered.domain.DbPatientMessageVo2;
import com.ruoyi.mind.utils.Doc2HtmlUtil;
import com.ruoyi.mind.utils.TableListUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.velocity.runtime.directive.Break;
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
import com.ruoyi.mind.cure.domain.DbCureTms;
import com.ruoyi.mind.cure.service.IDbCureTmsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 经颅磁刺激治疗Controller
 *
 * @author zheng
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/cure/tms")
public class DbCureTmsController extends BaseController {
    private String prefix = "cure/tms";

    @Autowired
    private IDbCureTmsService dbCureTmsService;

    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("cure:tms:view")
    @GetMapping()
    public String tms() {
        return prefix + "/tms";
    }

    /**
     * 查询经颅磁刺激治疗树列表
     */
    @RequiresPermissions("cure:tms:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbCureTms> list(DbCureTms dbCureTms) {
        /*
         *     *查询符合条件的需要治疗的病人
         *     *是否已拥有父级id
         *     *返回列表
         *
         * */
        List<DbPatientMessageVo2> tms = TableListUtils.getListCurc("TMS");
        List<DbCureTms> list = new ArrayList<>();
        tms.forEach(item -> {
//            判断是否完成
            if (item.getDbPatientAssociated().getAssociatedId() != null) {
//                  绑定过
                DbCureTms dbCureTms1 = dbCureTmsService.selectDbCureTmsById(item.getDbPatientAssociated().getAssociatedId());
//                是否完成
                if (dbCureTms1.getHowMany() == 0) {
//                    已完成
                    TableListUtils.updateResultOk(item.getDbPatientAssociated().getAssociatedId(), "TMS", item.getDbPatientAssociated().getPatientId());

                }else {
                    DbCureTms dbCureTms3 = new DbCureTms();
                    dbCureTms3.setFatherId(item.getDbPatientAssociated().getAssociatedId());

                    List<DbCureTms> dbCureTms32 = dbCureTmsService.selectDbCureTmsList(dbCureTms3);
                    list.add(dbCureTms1);
                    list.addAll(dbCureTms32);
                }

            } else {
                DbCureTms dbCureTms1 = new DbCureTms();
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
                List<DbCureTms> dbCureTms2 = dbCureTmsService.selectDbCureTmsList(dbCureTms1);
                if (dbCureTms2 == null || dbCureTms2.size() == 0) {
                    dbCureTms1.setExecutionTime(new Date());
                    dbCureTmsService.insertDbCureTms(dbCureTms1);
                    list.add(dbCureTms1);
                }else {
                    list.addAll(dbCureTms2);
                }
            }

        });
        return list;
    }

    /**
     * 导出经颅磁刺激治疗列表
     */
    @RequiresPermissions("cure:tms:export")
    @Log(title = "经颅磁刺激治疗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbCureTms dbCureTms) {
        List<DbCureTms> list = dbCureTmsService.selectDbCureTmsList(dbCureTms);
        ExcelUtil<DbCureTms> util = new ExcelUtil<DbCureTms>(DbCureTms.class);
        return util.exportExcel(list, "tms");
    }

    /**
     * 新增经颅磁刺激治疗
     */
    @GetMapping(value = {"/add/{id}", "/add/"})
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap) {
        if (StringUtils.isNotNull(id)) {
            mmap.put("dbCureTms", dbCureTmsService.selectDbCureTmsById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存经颅磁刺激治疗
     */
    @RequiresPermissions("cure:tms:add")
    @Log(title = "经颅磁刺激治疗", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbCureTms dbCureTms) {
        Long fatherId = dbCureTms.getFatherId();
        DbCureTms dbCureTms1 = dbCureTmsService.selectDbCureTmsById(fatherId);
        /*
         * 修改父的剩余次数
         * */
        dbCureTms1.setHowMany(dbCureTms1.getHowMany() - 1);
        int i = dbCureTmsService.updateDbCureTms(dbCureTms1);
        dbCureTms1.setId(null);
        dbCureTms1.setPatientName(null);
        dbCureTms1.setTechnicianName(ShiroUtils.getSysUser().getUserName());
        dbCureTms1.setExecutionTime(new Date());
        dbCureTms1.setDocumentAddress(dbCureTms.getDocumentAddress());
        dbCureTms1.setFatherId(dbCureTms.getFatherId());
        dbCureTms1.setHowMany(null);
        int i1 = dbCureTmsService.insertDbCureTms(dbCureTms1);
        /*
         * 修改id
         * */
        int tms = TableListUtils.updateResultId(fatherId, "TMS", dbCureTms1.getPatientId());

        return toAjax(tms);
    }

    /**
     * 修改经颅磁刺激治疗
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DbCureTms dbCureTms = dbCureTmsService.selectDbCureTmsById(id);
        mmap.put("dbCureTms", dbCureTms);
        return prefix + "/edit";
    }

    /**
     * 修改保存经颅磁刺激治疗
     */
    @RequiresPermissions("cure:tms:edit")
    @Log(title = "经颅磁刺激治疗", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbCureTms dbCureTms) {
        return toAjax(dbCureTmsService.updateDbCureTms(dbCureTms));
    }

    /**
     * 删除
     */
    @RequiresPermissions("cure:tms:remove")
    @Log(title = "经颅磁刺激治疗", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id) {
        return toAjax(dbCureTmsService.deleteDbCureTmsById(id));
    }

    /**
     * 选择经颅磁刺激治疗树
     */
    @GetMapping(value = {"/selectTmsTree/{id}", "/selectTmsTree/"})
    public String selectTmsTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap) {
        if (StringUtils.isNotNull(id)) {
            mmap.put("dbCureTms", dbCureTmsService.selectDbCureTmsById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载经颅磁刺激治疗树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        List<Ztree> ztrees = dbCureTmsService.selectDbCureTmsTree();
        return ztrees;
    }
    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbCureTms dbCureTms = dbCureTmsService.selectDbCureTmsById(userId);
        String documentAddress = dbCureTms.getDocumentAddress();
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
