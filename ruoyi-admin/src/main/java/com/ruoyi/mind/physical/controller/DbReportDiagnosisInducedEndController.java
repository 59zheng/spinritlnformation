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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInduced;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisInducedService;
import com.ruoyi.mind.registered.domain.DbPatientMessageVo;
import com.ruoyi.mind.utils.TableListUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 脑电报告(物理诊断下边)Controller
 *
 * @author zheng
 * @date 2020-06-09
 */
@Controller
@RequestMapping("/physical/induced1")
public class DbReportDiagnosisInducedEndController extends BaseController {
    private String prefix = "physical/induced1";

    @Autowired
    private IDbReportDiagnosisInducedService dbReportDiagnosisInducedService;


    @RequiresPermissions("physical:induced:view")
    @GetMapping()
    public String induced() {
        return prefix + "/induced";
    }

    /**
     * 查询脑电报告(物理诊断下边)列表
     */
    @RequiresPermissions("physical:induced:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
        /*
         *
         * 病人信息  <泛型>
         * */
        startPage();
        List<DbPatientMessageVo> induced = TableListUtils.getLisOver("induced");
        return getDataTable(induced);
    }

    /**
     * 导出脑电报告(物理诊断下边)列表
     */
    @RequiresPermissions("physical:induced:export")
    @Log(title = "脑电报告(物理诊断下边)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
        List<DbReportDiagnosisInduced> list = dbReportDiagnosisInducedService.selectDbReportDiagnosisInducedList(dbReportDiagnosisInduced);
        ExcelUtil<DbReportDiagnosisInduced> util = new ExcelUtil<DbReportDiagnosisInduced>(DbReportDiagnosisInduced.class);
        return util.exportExcel(list, "induced");
    }

    /**
     * 新增脑电报告(物理诊断下边)
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存脑电报告(物理诊断下边)
     */
    @RequiresPermissions("physical:induced:add")
    @Log(title = "脑电报告(物理诊断下边)", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
        return toAjax(dbReportDiagnosisInducedService.insertDbReportDiagnosisInduced(dbReportDiagnosisInduced));
    }

    /**
     * 修改脑电报告(物理诊断下边)
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DbReportDiagnosisInduced dbReportDiagnosisInduced = dbReportDiagnosisInducedService.selectDbReportDiagnosisInducedById(id);
        mmap.put("dbReportDiagnosisInduced", dbReportDiagnosisInduced);
        return prefix + "/edit";
    }

    /**
     * 修改保存脑电报告(物理诊断下边)
     */
    @RequiresPermissions("physical:induced:edit")
    @Log(title = "脑电报告(物理诊断下边)", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
        return toAjax(dbReportDiagnosisInducedService.updateDbReportDiagnosisInduced(dbReportDiagnosisInduced));
    }

    /**
     * 删除脑电报告(物理诊断下边)
     */
    @RequiresPermissions("physical:induced:remove")
    @Log(title = "脑电报告(物理诊断下边)", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dbReportDiagnosisInducedService.deleteDbReportDiagnosisInducedByIds(ids));
    }

    /**
     * 表格radio
     */
    @GetMapping("/radio")
    public String radio() {
        return prefix + "/table/radio";
    }


    @GetMapping("/generateWord/{id}")
    public void generateWord(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, URISyntaxException {

        DbReportDiagnosisInduced dbReportDiagnosisInduced = dbReportDiagnosisInducedService.selectDbReportDiagnosisInducedById(id);
        Long patientId = dbReportDiagnosisInduced.getPatientId();
        Map<String, Object> stringObjectMap1 = TableListUtils.patientToMap(patientId);
        Map<String, Object> stringObjectMap = TableListUtils.objectToMap(dbReportDiagnosisInduced);
        stringObjectMap1.putAll(stringObjectMap);
        //图片路径，请注意你是linux还是windows
        /*
         * 图片路径处理
         * */

        File file = ResourceUtils.getFile("classpath:induced.docx");


        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace = TableListUtils.getPictureReplace("prictureP300", (String) stringObjectMap.get("prictureP300"));
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace1 = TableListUtils.getPictureReplace("pictureCnv", (String) stringObjectMap.get("pictureCnv"));
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace2 = TableListUtils.getPictureReplace("pictureP50", (String) stringObjectMap.get("pictureP50"));
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace3 = TableListUtils.getPictureReplace("pictureMmn", (String) stringObjectMap.get("pictureMmn"));
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace4 = TableListUtils.getPictureReplace("signatureTechnician", (String) stringObjectMap.get("signatureTechnician"));
        ReplaceOptionalTextPictureRefRenderPolicy pictureReplace5 = TableListUtils.getPictureReplace("signatureDoctor", (String) stringObjectMap.get("signatureDoctor"));


        ConfigureBuilder configureBuilder = Configure.newBuilder();
        configureBuilder.referencePolicy(pictureReplace);
        configureBuilder.referencePolicy(pictureReplace1);
        configureBuilder.referencePolicy(pictureReplace2);
        configureBuilder.referencePolicy(pictureReplace3);
        configureBuilder.referencePolicy(pictureReplace4);
        configureBuilder.referencePolicy(pictureReplace5);
        Configure configure3 = configureBuilder.build();


        XWPFTemplate template = XWPFTemplate.compile(file, configure3)
                .render(stringObjectMap1);
        // 清空response
        response.reset();
//        response.setContentType("application/octet-stream");
        String patientName = (String) stringObjectMap1.get("patientName");
        String name = "诱发电位报告" + "(" + patientName + ")" + ".docx";

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


}
