package com.ruoyi.mind.cure.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.cure.domain.DbCureEct;
import com.ruoyi.mind.cure.domain.DbCureTms;
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
import com.ruoyi.mind.cure.domain.DbCureDbs;
import com.ruoyi.mind.cure.service.IDbCureDbsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 脑深部刺激治疗Controller
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/cure/dbs")
public class DbCureDbsController extends BaseController
{
    private String prefix = "cure/dbs";

    @Autowired
    private IDbCureDbsService dbCureDbsService;

    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("cure:dbs:view")
    @GetMapping()
    public String dbs()
    {
        return prefix + "/dbs";
    }

    /**
     * 查询脑深部刺激治疗树列表
     */
    @RequiresPermissions("cure:dbs:list")
    @PostMapping("/list")
    @ResponseBody
    public List<DbCureDbs> list(DbCureDbs dbCureDbs)
    {
        /*
         *     *查询符合条件的需要治疗的病人
         *     *是否已拥有父级id
         *     *返回列表
         *
         * */
        List<DbPatientMessageVo2> tms = TableListUtils.getListCurc("DBS");
        List<DbCureDbs> list = new ArrayList<>();
        tms.forEach(item -> {
//            判断是否完成
            if (item.getDbPatientAssociated().getAssociatedId() != null) {
//                  绑定过
                DbCureDbs dbCureDbs1 = dbCureDbsService.selectDbCureDbsById(item.getDbPatientAssociated().getAssociatedId());
//                是否完成
                if (dbCureDbs1.getHowMany() == 0) {
//                    已完成
                    TableListUtils.updateResultOk(item.getDbPatientAssociated().getAssociatedId(), "DBS", item.getDbPatientAssociated().getPatientId());

                }else {
                    DbCureDbs dbCureTms3 = new DbCureDbs();
                    dbCureTms3.setFatherId(item.getDbPatientAssociated().getAssociatedId());

                    List<DbCureDbs> dbCureDbs2 = dbCureDbsService.selectDbCureDbsList(dbCureTms3);
                    list.add(dbCureDbs1);
                    list.addAll(dbCureDbs2);
                }

            } else {
                DbCureDbs dbCureTms1 = new DbCureDbs();
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
                List<DbCureDbs> dbCureDbs1 = dbCureDbsService.selectDbCureDbsList(dbCureTms1);
                if (dbCureDbs1 == null || dbCureDbs1.size() == 0) {
                    dbCureTms1.setExecutionTime(new Date());
                    dbCureDbsService.insertDbCureDbs(dbCureTms1);
                    list.add(dbCureTms1);
                }else {
                    list.addAll(dbCureDbs1);
                }
            }

        });
        return list;
    }

    /**
     * 导出脑深部刺激治疗列表
     */
    @RequiresPermissions("cure:dbs:export")
    @Log(title = "脑深部刺激治疗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbCureDbs dbCureDbs)
    {
        List<DbCureDbs> list = dbCureDbsService.selectDbCureDbsList(dbCureDbs);
        ExcelUtil<DbCureDbs> util = new ExcelUtil<DbCureDbs>(DbCureDbs.class);
        return util.exportExcel(list, "dbs");
    }

    /**
     * 新增脑深部刺激治疗
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbCureDbs", dbCureDbsService.selectDbCureDbsById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存脑深部刺激治疗
     */
    @RequiresPermissions("cure:dbs:add")
    @Log(title = "脑深部刺激治疗", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbCureDbs dbCureDbs)


    {
        Long fatherId = dbCureDbs.getFatherId();
        DbCureDbs dbCureDbs1 = dbCureDbsService.selectDbCureDbsById(fatherId);
        /*
         * 修改父的剩余次数
         * */
        dbCureDbs1.setHowMany(dbCureDbs1.getHowMany() - 1);
        int i = dbCureDbsService.updateDbCureDbs(dbCureDbs1);
        dbCureDbs1.setId(null);
        dbCureDbs1.setPatientName(null);
        dbCureDbs1.setTechnicianName(ShiroUtils.getSysUser().getUserName());
        dbCureDbs1.setExecutionTime(new Date());
        dbCureDbs1.setDocumentAddress(dbCureDbs.getDocumentAddress());
        dbCureDbs1.setFatherId(dbCureDbs.getFatherId());
        dbCureDbs1.setHowMany(null);
        int i1 = dbCureDbsService.insertDbCureDbs(dbCureDbs1);
        /*
         * 修改id
         * */
        int tms = TableListUtils.updateResultId(fatherId, "DBS", dbCureDbs1.getPatientId());
        return toAjax(tms);
    }

    /**
     * 修改脑深部刺激治疗
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbCureDbs dbCureDbs = dbCureDbsService.selectDbCureDbsById(id);
        mmap.put("dbCureDbs", dbCureDbs);
        return prefix + "/edit";
    }

    /**
     * 修改保存脑深部刺激治疗
     */
    @RequiresPermissions("cure:dbs:edit")
    @Log(title = "脑深部刺激治疗", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbCureDbs dbCureDbs)
    {
        return toAjax(dbCureDbsService.updateDbCureDbs(dbCureDbs));
    }

    /**
     * 删除
     */
    @RequiresPermissions("cure:dbs:remove")
    @Log(title = "脑深部刺激治疗", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(dbCureDbsService.deleteDbCureDbsById(id));
    }

    /**
     * 选择脑深部刺激治疗树
     */
    @GetMapping(value = { "/selectDbsTree/{id}", "/selectDbsTree/" })
    public String selectDbsTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("dbCureDbs", dbCureDbsService.selectDbCureDbsById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载脑深部刺激治疗树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dbCureDbsService.selectDbCureDbsTree();
        return ztrees;
    }
    /*
     * 查看预览
     * */
    @GetMapping("/preview/{userId}")
    public String preview(@PathVariable("userId") Long userId, ModelMap mmap) throws IOException {
        DbCureDbs dbCureDbs = dbCureDbsService.selectDbCureDbsById(userId);
        String documentAddress = dbCureDbs.getDocumentAddress();
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
