package com.ruoyi.mind.physical.controller;

import java.io.*;
import java.util.*;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.policy.ref.ReplaceOptionalTextPictureRefRenderPolicy;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.mind.diagnosis.service.IDbDiagonsisProjectService;
import com.ruoyi.mind.registered.domain.DbPatientAssociated;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.registered.service.IDbPatientAssociatedService;
import com.ruoyi.mind.registered.service.IDbPatientMessageService;
import com.ruoyi.mind.utils.TableListUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInduced;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisInducedService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.System.out;

/**
 * 脑电报告(物理诊断下边)Controller
 *
 * @author zheng
 * @date 2020-06-09
 */
@Controller
@RequestMapping("/physical/induced")
public class DbReportDiagnosisInducedController extends BaseController {
    private String prefix = "physical/induced";

    @Autowired
    private IDbReportDiagnosisInducedService dbReportDiagnosisInducedService;


    @Autowired
    private ISysUserService sysUserService;



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
        List<DbPatientMessage> induced = TableListUtils.getList("induced");
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
    @GetMapping("/add/{userId}")
    public String add( ModelMap map,@PathVariable("userId") Long userId) {

        map.put("userId",userId);
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
     * 新增保存脑电报告(物理诊断下边)
     */
    @RequiresPermissions("physical:induced:add")
    @Log(title = "脑电报告(物理诊断下边)", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DbReportDiagnosisInduced dbReportDiagnosisInduced) {
        /*
        * 新增保存  关联表id给中间表  标记完成
        * */
        SysUser sysUser = ShiroUtils.getSysUser();
//        技师签名
        dbReportDiagnosisInduced.setSignatureTechnician(sysUser.getSignatureURL());
//        医生签名
        SysUser sysUser1 = sysUserService.selectUserById(TableListUtils.getTeamId(dbReportDiagnosisInduced.getPatientId()));
        dbReportDiagnosisInduced.setSignatureDoctor(sysUser1.getSignatureURL());
        int i = dbReportDiagnosisInducedService.insertDbReportDiagnosisInduced(dbReportDiagnosisInduced);

        Long id = dbReportDiagnosisInduced.getId();
        Long patientId = dbReportDiagnosisInduced.getPatientId();
        int induced = TableListUtils.updateResult(id, "induced", patientId);
        return toAjax(induced);
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


    @GetMapping("/generateWord")
    public void generateWord(String data, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //图片路径，请注意你是linux还是windows
        String wordPath = "C:\\Users\\Administrator\\Desktop\\";
        String modelName = "induced.docx";
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                //本地图片
                put("patientName", "鞠婧祎");
                put("patientSex", "男");
                put("patientAge", "15");
                put("hospitalId", "19940618");
//				//网路图片
                put("pictureCnv", new PictureRenderData(500, 140, "C:\\Users\\Administrator\\Desktop\\map.png"));
                // 网络图片"D:/ruoyi/uploadPath/upload/2020/06/12/53827f606acc614abf772e56344575d1.png
                String path = "D:/ruoyi/uploadPath" + "/profile/upload/2020/06/12/1591952105(1).jpg";
                put("signatureTechnician", new PictureRenderData(100, 50, "D:/ruoyi/uploadPath/upload/2020/06/12/1591952105(1).jpg"));
            }
        };
        Configure configure = Configure.newBuilder()
                .referencePolicy(
                        new ReplaceOptionalTextPictureRefRenderPolicy("signaturetwo",
                                new FileInputStream("D:/ruoyi/uploadPath/upload/2020/06/12/804ab1617afd7c40fbe2ad8b83a1413e.png"),
                                XWPFDocument.PICTURE_TYPE_PNG))
                .build();


        XWPFTemplate template = XWPFTemplate.compile(wordPath + modelName, configure)
                .render(datas);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=\"" + "out_template.docx" + "\"");


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
