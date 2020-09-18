package com.ruoyi.mind.physical.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.policy.ref.ReplaceOptionalTextPictureRefRenderPolicy;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisElectrical;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisElectricalService;
import com.ruoyi.mind.registered.domain.DbPatientMessageVo;
import com.ruoyi.mind.utils.TableListUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * 脑电诊断Controller
 * 
 * @author zheng
 * @date 2020-07-01
 */
@Controller
@RequestMapping("/physical/electrical1")
public class DbReportDiagnosisElectricalEndController extends BaseController
{
    private String prefix = "physical/electrical1";

    @Autowired
    private IDbReportDiagnosisElectricalService dbReportDiagnosisElectricalService;

    @RequiresPermissions("physical:electrical:view")
    @GetMapping()
    public String electrical()
    {
        return prefix + "/electrical";
    }

    /**
     * 查询脑电诊断列表
     */
    @RequiresPermissions("physical:electrical:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        /*
         *
         * 病人信息  <泛型>
         * */
        startPage();
        List<DbPatientMessageVo> electrical = TableListUtils.getLisOver("electrical");
        return getDataTable(electrical);
    }

    /**
     * 导出脑电诊断列表
     */
    @RequiresPermissions("physical:electrical:export")
    @Log(title = "脑电诊断", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        List<DbReportDiagnosisElectrical> list = dbReportDiagnosisElectricalService.selectDbReportDiagnosisElectricalList(dbReportDiagnosisElectrical);
        ExcelUtil<DbReportDiagnosisElectrical> util = new ExcelUtil<DbReportDiagnosisElectrical>(DbReportDiagnosisElectrical.class);
        return util.exportExcel(list, "electrical");
    }

    /**
     * 新增脑电诊断
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存脑电诊断
     */
    @RequiresPermissions("physical:electrical:add")
    @Log(title = "脑电诊断", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        return toAjax(dbReportDiagnosisElectricalService.insertDbReportDiagnosisElectrical(dbReportDiagnosisElectrical));
    }

    /**
     * 修改脑电诊断
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DbReportDiagnosisElectrical dbReportDiagnosisElectrical = dbReportDiagnosisElectricalService.selectDbReportDiagnosisElectricalById(id);
        mmap.put("dbReportDiagnosisElectrical", dbReportDiagnosisElectrical);
        return prefix + "/edit";
    }

    /**
     * 修改保存脑电诊断
     */
    @RequiresPermissions("physical:electrical:edit")
    @Log(title = "脑电诊断", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        return toAjax(dbReportDiagnosisElectricalService.updateDbReportDiagnosisElectrical(dbReportDiagnosisElectrical));
    }

    /**
     * 删除脑电诊断
     */
    @RequiresPermissions("physical:electrical:remove")
    @Log(title = "脑电诊断", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dbReportDiagnosisElectricalService.deleteDbReportDiagnosisElectricalByIds(ids));
    }


    /**
     * 新增脑电报告(物理诊断下边)
     */
    @GetMapping("/addonly/{userId}")
    public String addonly( ModelMap map,@PathVariable("userId") Long userId) {
        map.put("userId",userId);
        return prefix + "/add";
    }


    @GetMapping("/generateWord/{id}")
    public void generateWord(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, URISyntaxException {

        DbReportDiagnosisElectrical dbReportDiagnosisElectrical = dbReportDiagnosisElectricalService.selectDbReportDiagnosisElectricalById(id);
        Long patientId = dbReportDiagnosisElectrical.getPatientId();
        Map<String, Object> stringObjectMap1 = TableListUtils.patientToMap(patientId);
        Map<String, Object> stringObjectMap = TableListUtils.objectToMap(dbReportDiagnosisElectrical);
        stringObjectMap1.putAll(stringObjectMap);
        //图片路径，请注意你是linux还是windows
        /*
         * 图片路径处理
         * */
        File instream = ResourceUtils.getFile("classpath:electrical.docx");
       /* Resource resource2 = new ClassPathResource("/doc/electrical.docx");
        String absolutePath = resource2.getFile().getAbsolutePath();
        String modelName =absolutePath;*/

      /*  String property = System.getProperty("induced.doc");
        String downloadPath = new File(resource.toURI()).getAbsolutePath();*/

        /*
         *
         * 图片处理
         * */
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace = TableListUtils.getPictureReplace("electricalPicture", (String) stringObjectMap.get("electricalPicture"));
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace1 = TableListUtils.getPictureReplace("signatureTechnician", (String) stringObjectMap.get("signatureTechnician"));
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace2 = TableListUtils.getPictureReplace("signatureDoctor", (String) stringObjectMap.get("signatureDoctor"));
        ConfigureBuilder configureBuilder = Configure.newBuilder();
        configureBuilder.referencePolicy(pictureReplace);
        configureBuilder.referencePolicy(pictureReplace1);
        configureBuilder.referencePolicy(pictureReplace2);

        Configure configure3 =configureBuilder.build();


        XWPFTemplate template = XWPFTemplate.compile( instream, configure3)
                .render(stringObjectMap1);

        // 清空response
        response.reset();
//        response.setContentType("application/octet-stream");
        String patientName = (String) stringObjectMap1.get("patientName");
        String name = "脑电报告" + "(" + patientName + ")" + ".docx";

        try {
            // HttpServletResponse response
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(name.getBytes("gbk"),"iso8859-1")+ ".doc" + "\"");

// HttpServletResponse response
            OutputStream out = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            template.write(bos);
            template.close();
            bos.flush();
            bos.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

}
