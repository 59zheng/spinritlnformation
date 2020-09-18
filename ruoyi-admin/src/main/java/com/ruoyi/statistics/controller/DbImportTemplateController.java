package com.ruoyi.statistics.controller;

import java.util.*;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.web.controller.demo.domain.UserOperateModel;
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
import com.ruoyi.statistics.domain.DbImportTemplate;
import com.ruoyi.statistics.service.IDbImportTemplateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 导入模板Controller
 *
 * @author zheng
 * @date 2020-08-13
 */
@Controller
@RequestMapping("/statistics/template")
public class DbImportTemplateController extends BaseController {
    private String prefix = "statistics/template";

    @Autowired
    private IDbImportTemplateService dbImportTemplateService;

    @RequiresPermissions("statistics:template:view")
    @GetMapping()
    public String template() {
        return prefix + "/template";
    }

    /**
     * 查询导入模板列表
     */
    @RequiresPermissions("statistics:template:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbImportTemplate dbImportTemplate) {
        startPage();
        List<DbImportTemplate> list = dbImportTemplateService.selectDbImportTemplateList(dbImportTemplate);
        return getDataTable(list);
    }

    /**
     * 导出导入模板列表
     */
    @RequiresPermissions("statistics:template:export")
    @Log(title = "导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbImportTemplate dbImportTemplate) {
        List<DbImportTemplate> list = dbImportTemplateService.selectDbImportTemplateList(dbImportTemplate);
        ExcelUtil<DbImportTemplate> util = new ExcelUtil<DbImportTemplate>(DbImportTemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 新增导入模板
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存导入模板
     */
    @RequiresPermissions("statistics:template:add")
    @Log(title = "导入模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbImportTemplate dbImportTemplate) {
        return toAjax(dbImportTemplateService.insertDbImportTemplate(dbImportTemplate));
    }

    /**
     * 修改导入模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DbImportTemplate dbImportTemplate = dbImportTemplateService.selectDbImportTemplateById(id);
        mmap.put("dbImportTemplate", dbImportTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存导入模板
     */
    @RequiresPermissions("statistics:template:edit")
    @Log(title = "导入模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbImportTemplate dbImportTemplate) {
        return toAjax(dbImportTemplateService.updateDbImportTemplate(dbImportTemplate));
    }

    /**
     * 删除导入模板
     */
    @RequiresPermissions("statistics:template:remove")
    @Log(title = "导入模板", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dbImportTemplateService.deleteDbImportTemplateByIds(ids));
    }


    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importUser(List<DbImportTemplate> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DbImportTemplate dbImportTemplate : userList) {
            int i = dbImportTemplateService.insertDbImportTemplate(dbImportTemplate);
            if (i > 0) {

                successNum++;
            } else {
                failureNum++;
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();

    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<DbImportTemplate> util = new ExcelUtil<DbImportTemplate>(DbImportTemplate.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<DbImportTemplate> util = new ExcelUtil<DbImportTemplate>(DbImportTemplate.class);
        List<DbImportTemplate> userList = util.importExcel(file.getInputStream());
        String message = importUser(userList, updateSupport);
        return AjaxResult.success(message);
    }
    /* 页面统计使用
     * 参数项目名称
     *
     * */
    @GetMapping("/statis")
    @ResponseBody
    public  AjaxResult statis (String name){
        DbImportTemplate dbImportTemplate = new DbImportTemplate();
        dbImportTemplate.setProjectName(name);
        List<DbImportTemplate> dbImportTemplates = dbImportTemplateService.selectDbImportTemplateList(dbImportTemplate);
        Map<String,List<String>> listMap = new HashMap();
        ArrayList<String> objects = new ArrayList<>();
        ArrayList<String> object1 = new ArrayList<>();
        ArrayList<String> object2 = new ArrayList<>();
        dbImportTemplates.forEach(item ->{
//            总价格
            Double totalCost = item.getTotalCost();
            objects.add(totalCost+"");
//            总数量
            Long totalNumber = item.getTotalNumber();
            object1.add(totalNumber+"");
//            医生名称名称
            String doctorName = item.getDoctorName();
            object2.add(doctorName);

        });
        listMap.put("totalCost",objects);
        listMap.put("totalNumber",object1);
        listMap.put("doctorName",object2);
        return   AjaxResult.success(listMap);
    }

    /*
    * 页面统计  项目分组  项目名称
    *
    * */
    @GetMapping("/statisGroup")
    @ResponseBody
    public  AjaxResult statisGroup (){
        DbImportTemplate dbImportTemplate = new DbImportTemplate();
        List<DbImportTemplate> dbImportTemplates = dbImportTemplateService.selectDbImportTemplateList(dbImportTemplate);
        HashMap<String, List<String>> stringListHashMap = new HashMap<>();
        for (DbImportTemplate item : dbImportTemplates) {
            dbImportTemplate.setProjectClassification(item.getProjectClassification());
            List<DbImportTemplate> dbImportTemplates2 = dbImportTemplateService.selectDbImportTemplateList(dbImportTemplate);
            ArrayList<String> strings = new ArrayList<>();
            dbImportTemplates2.forEach(n->{
                strings.add(n.getProjectName());
            });
            ArrayList single = getSingle(strings);
            stringListHashMap.put(item.getProjectClassification(),single
            );
        }
        return   AjaxResult.success(stringListHashMap);
    }

    /**
     * 去重复
     */
    public static ArrayList getSingle(List<String> list) {
        ArrayList tempList = new ArrayList();
        Iterator it = list.iterator();

        while (it.hasNext()) {
            String s = (String) it.next();
            if (!tempList.contains(s)) {
                tempList.add(s);
            }
        }
        return tempList;
    }

}
