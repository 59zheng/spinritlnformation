package com.ruoyi.mind.CorrectionAndTreatment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindCorrect;
import com.ruoyi.mind.registered.domain.DbPatientMessageVo2;
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
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindTreat;
import com.ruoyi.mind.CorrectionAndTreatment.service.IDbMindTreatService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 心理治疗Controller
 * 
 * @author zheng
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/CorrectionAndTreatment/treat")
public class DbMindTreatController extends BaseController
{
    private String prefix = "CorrectionAndTreatment/treat";

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
        List<DbPatientMessageVo2> tms = TableListUtils.getListCurc("treat");
        List<DbMindTreat> list = new ArrayList<>();
        tms.forEach(item -> {
//            判断是否完成
            if (item.getDbPatientAssociated().getAssociatedId() != null) {
//                  绑定过
                DbMindTreat dbMindTreat1 = dbMindTreatService.selectDbMindTreatById(item.getDbPatientAssociated().getAssociatedId());
//                是否完成
                if (dbMindTreat1.getHowMany() == 0) {
//                    已完成
                    TableListUtils.updateResultOk(item.getDbPatientAssociated().getAssociatedId(), "treat", item.getDbPatientAssociated().getPatientId());

                }else {
                    DbMindTreat dbCureTms3 = new DbMindTreat();
                    dbCureTms3.setFatherId(item.getDbPatientAssociated().getAssociatedId());

                    List<DbMindTreat> dbMindTreats = dbMindTreatService.selectDbMindTreatList(dbCureTms3);
                    list.add(dbMindTreat1);
                    list.addAll(dbMindTreats);
                }

            } else {
                DbMindTreat dbCureTms1 = new DbMindTreat();
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
                List<DbMindTreat> dbMindTreats = dbMindTreatService.selectDbMindTreatList(dbCureTms1);
                if (dbMindTreats == null || dbMindTreats.size() == 0) {
                    dbCureTms1.setExecutionTime(new Date());
                    dbMindTreatService.insertDbMindTreat(dbCureTms1);
                    list.add(dbCureTms1);
                }else {
                    list.addAll(dbMindTreats);
                }
            }

        });
        return list;
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
}
