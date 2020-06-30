package com.ruoyi.mind.physical.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.policy.ref.ReplaceOptionalTextPictureRefRenderPolicy;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInduced;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInfrared;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisInfraredService;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.registered.domain.DbPatientMessageVo;
import com.ruoyi.mind.utils.TableListUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 红外热成像及血流图检查Controller
 * 
 * @author zheng
 * @date 2020-06-29
 */
@Controller
@RequestMapping("/physical/infrared1")
public class DbReportDiagnosisInfraredEndController extends BaseController
{
    private String prefix = "physical/infrared1";

    @Autowired
    private IDbReportDiagnosisInfraredService dbReportDiagnosisInfraredService;

    @RequiresPermissions("physical:infrared1:view")
    @GetMapping()
    public String infrared()
    {
        return prefix + "/infrared";
    }

    /**
     * 查询红外热成像及血流图检查列表
     */
    @RequiresPermissions("physical:infrared1:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        /*
         *
         * 病人信息  <泛型>
         * */
        startPage();
        List<DbPatientMessageVo> induced = TableListUtils.getLisOver("infrared");
        return getDataTable(induced);
    }

    /**
     * 导出红外热成像及血流图检查列表
     */
    @RequiresPermissions("physical:infrared1:export")
    @Log(title = "红外热成像及血流图检查", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        List<DbReportDiagnosisInfrared> list = dbReportDiagnosisInfraredService.selectDbReportDiagnosisInfraredList(dbReportDiagnosisInfrared);
        ExcelUtil<DbReportDiagnosisInfrared> util = new ExcelUtil<DbReportDiagnosisInfrared>(DbReportDiagnosisInfrared.class);
        return util.exportExcel(list, "infrared");
    }

    /**
     * 新增红外热成像及血流图检查
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增脑电报告(物理诊断下边)
     */
    @GetMapping("/addonly/{userId}")
    public String addonly( ModelMap map,@PathVariable("userId") Long userId) {

        map.put("userId",userId);
        return prefix + "/add";
    }


    /**
     * 新增保存红外热成像及血流图检查
     */
    @RequiresPermissions("physical:infrared1:add")
    @Log(title = "红外热成像及血流图检查", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        return toAjax(dbReportDiagnosisInfraredService.insertDbReportDiagnosisInfrared(dbReportDiagnosisInfrared));
    }

    /**
     * 修改红外热成像及血流图检查
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbReportDiagnosisInfrared dbReportDiagnosisInfrared = dbReportDiagnosisInfraredService.selectDbReportDiagnosisInfraredById(id);
        mmap.put("dbReportDiagnosisInfrared", dbReportDiagnosisInfrared);
        return prefix + "/edit";
    }

    /**
     * 修改保存红外热成像及血流图检查
     */
    @RequiresPermissions("physical:infrared1:edit")
    @Log(title = "红外热成像及血流图检查", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        return toAjax(dbReportDiagnosisInfraredService.updateDbReportDiagnosisInfrared(dbReportDiagnosisInfrared));
    }

    /**
     * 删除红外热成像及血流图检查
     */
    @RequiresPermissions("physical:infrared1:remove")
    @Log(title = "红外热成像及血流图检查", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbReportDiagnosisInfraredService.deleteDbReportDiagnosisInfraredByIds(ids));
    }


    @GetMapping("/generateWord/{id}")
    public void generateWord(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, URISyntaxException {

        DbReportDiagnosisInfrared dbReportDiagnosisInfrared = dbReportDiagnosisInfraredService.selectDbReportDiagnosisInfraredById(id);
        Long patientId = dbReportDiagnosisInfrared.getPatientId();
        dbReportDiagnosisInfrared.setDescribe(dbReportDiagnosisInfrared.getDescribe().replaceAll("\\s*",""));
        Map<String, Object> stringObjectMap1 = TableListUtils.patientToMap(patientId);
        stringObjectMap1.put("createTime", DateUtils.getDate());
        Map<String, Object> stringObjectMap = TableListUtils.objectToMap(dbReportDiagnosisInfrared);
        stringObjectMap1.putAll(stringObjectMap);
        //图片路径，请注意你是linux还是windows
        /*
         * 图片路径处理
         * */
        Resource resource2 = new ClassPathResource("/doc/infrared.docx");
        String absolutePath = resource2.getFile().getAbsolutePath();

        String modelName =absolutePath;


        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace5 = TableListUtils.getPictureReplace("signatureTechnician", (String) stringObjectMap.get("signatureTechnician"));
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace6 = TableListUtils.getPictureReplace("pictureCnv", (String) stringObjectMap.get("pictureCnv"));


        ConfigureBuilder configureBuilder = Configure.newBuilder();
        configureBuilder.referencePolicy(pictureReplace5);
        configureBuilder.referencePolicy(pictureReplace6);
        Configure configure3 =configureBuilder.build();


        XWPFTemplate template = XWPFTemplate.compile( modelName, configure3)
                .render(stringObjectMap1);

        response.setContentType("application/octet-stream");
        String patientName = (String) stringObjectMap1.get("patientName");
        String name="近红外结果报告"+"("+patientName+")"+".docx";
        String s = new String(name.getBytes(), "iso-8859-1");

        response.setHeader("Content-disposition", "attachment;filename=\"" + s + "\"");

        try {
            // HttpServletResponse response
            ServletOutputStream out = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            template.write(bos);
            template.close();
            out.flush();
            out.close();
            template.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
