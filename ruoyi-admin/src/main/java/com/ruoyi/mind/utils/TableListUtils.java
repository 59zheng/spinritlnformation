package com.ruoyi.mind.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.deepoove.poi.policy.ref.ReplaceOptionalTextPictureRefRenderPolicy;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.mind.diagnosis.domain.DbDiagonsisProject;
import com.ruoyi.mind.diagnosis.service.IDbDiagonsisProjectService;
import com.ruoyi.mind.registered.domain.*;
import com.ruoyi.mind.registered.service.IDbPatientAssociatedService;
import com.ruoyi.mind.registered.service.IDbPatientMessageService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.HashMap;
import java.util.Map;

public class TableListUtils {


    /*
     *
     * 列表查询  未诊断之前
     * */
    public static List<DbPatientMessage> getList(String name) {
        List<DbPatientMessage> list = new ArrayList<>();
        DbPatientAssociated dbPatientAssociated = new DbPatientAssociated();
        dbPatientAssociated.setIsOk("0");
        dbPatientAssociated.setAssociatedTable(name);
        List<DbPatientAssociated> dbPatientAssociateds = SpringUtils.getBean(IDbPatientAssociatedService.class).selectDbPatientAssociatedList(dbPatientAssociated);
        for (DbPatientAssociated patientAssociated : dbPatientAssociateds) {
            DbPatientMessage dbPatientMessage = SpringUtils.getBean(IDbPatientMessageService.class).selectDbPatientMessageById(patientAssociated.getPatientId());
            list.add(dbPatientMessage);
        }
        return list;
    }

    /*
     *
     * 列表查询  未诊断之前   治疗
     * */
    public static List<DbPatientMessageVo2> getListCurc(String name) {
        List<DbPatientMessageVo2> list = new ArrayList<>();
        DbPatientAssociated dbPatientAssociated = new DbPatientAssociated();
        dbPatientAssociated.setIsOk("0");
        dbPatientAssociated.setAssociatedTable(name);
        List<DbPatientAssociated> dbPatientAssociateds = SpringUtils.getBean(IDbPatientAssociatedService.class).selectDbPatientAssociatedList(dbPatientAssociated);
        for (DbPatientAssociated patientAssociated : dbPatientAssociateds) {
            DbPatientMessageVo2 dbPatientMessageVo2 = new DbPatientMessageVo2();
            DbPatientMessage dbPatientMessage = SpringUtils.getBean(IDbPatientMessageService.class).selectDbPatientMessageById(patientAssociated.getPatientId());
            dbPatientMessageVo2.setDbPatientMessage(dbPatientMessage);
            dbPatientMessageVo2.setDbPatientAssociated(patientAssociated);
            list.add(dbPatientMessageVo2);
        }
        return list;
    }

    /*
     *
     * 根据档案id查询主治医生id
     *
     * */

    public static Long getTeamId(long patientId) {
        DbPatientMessage dbPatientMessage = SpringUtils.getBean(IDbPatientMessageService.class).selectDbPatientMessageById(patientId);
        return dbPatientMessage.getTaemId();
    }


    /*
     *
     * 诊断完成添加诊断结果   修改结果
     * */
    public static int updateResult(Long id, String name, Long userId) {
        DbPatientAssociated dbPatientAssociated = new DbPatientAssociated();
        dbPatientAssociated.setPatientId(userId);
        dbPatientAssociated.setIsOk("0");
        dbPatientAssociated.setAssociatedTable(name);
        List<DbPatientAssociated> dbPatientAssociateds = SpringUtils.getBean(IDbPatientAssociatedService.class).selectDbPatientAssociatedList(dbPatientAssociated);
        if (dbPatientAssociateds.size() > 0) {
            DbPatientAssociated dbPatientAssociated1 = dbPatientAssociateds.get(0);
            dbPatientAssociated1.setIsOk("1");
            dbPatientAssociated1.setAssociatedId(id);
            int i = SpringUtils.getBean(IDbPatientAssociatedService.class).updateDbPatientAssociated(dbPatientAssociated1);
            return i;
        }
        return 0;
    }

    /*
     * 绑定表id
     * */
    public static int updateResultId(Long id, String name, Long userId) {
        DbPatientAssociated dbPatientAssociated = new DbPatientAssociated();
        dbPatientAssociated.setPatientId(userId);
        dbPatientAssociated.setIsOk("0");
        dbPatientAssociated.setAssociatedTable(name);
        List<DbPatientAssociated> dbPatientAssociateds = SpringUtils.getBean(IDbPatientAssociatedService.class).selectDbPatientAssociatedList(dbPatientAssociated);
        if (dbPatientAssociateds.size() > 0) {
            DbPatientAssociated dbPatientAssociated1 = dbPatientAssociateds.get(0);
            dbPatientAssociated1.setAssociatedId(id);
            int i = SpringUtils.getBean(IDbPatientAssociatedService.class).updateDbPatientAssociated(dbPatientAssociated1);
            return i;
        }
        return 0;
    }

    /*
     * 确认完成
     * */
    public static int updateResultOk(Long id, String name, Long userId) {
        DbPatientAssociated dbPatientAssociated = new DbPatientAssociated();
        dbPatientAssociated.setPatientId(userId);
        dbPatientAssociated.setIsOk("0");
        dbPatientAssociated.setAssociatedTable(name);
        List<DbPatientAssociated> dbPatientAssociateds = SpringUtils.getBean(IDbPatientAssociatedService.class).selectDbPatientAssociatedList(dbPatientAssociated);
        if (dbPatientAssociateds.size() > 0) {
            DbPatientAssociated dbPatientAssociated1 = dbPatientAssociateds.get(0);
            dbPatientAssociated1.setIsOk("1");
            int i = SpringUtils.getBean(IDbPatientAssociatedService.class).updateDbPatientAssociated(dbPatientAssociated1);
            return i;
        }
        return 0;
    }

    /*
     *
     * 列表查询 诊断之后
     * */
    public static List<DbPatientMessageVo> getLisOver(String name) {
        List<DbPatientMessageVo> list = new ArrayList<>();
        DbPatientAssociated dbPatientAssociated = new DbPatientAssociated();
        dbPatientAssociated.setIsOk("1");
        dbPatientAssociated.setAssociatedTable(name);
        List<DbPatientAssociated> dbPatientAssociateds = SpringUtils.getBean(IDbPatientAssociatedService.class).selectDbPatientAssociatedList(dbPatientAssociated);
        for (DbPatientAssociated patientAssociated : dbPatientAssociateds) {
            DbPatientMessageVo dbPatientMessageVo = new DbPatientMessageVo();
            dbPatientMessageVo.setCreateTime(patientAssociated.getCreateTime());
            dbPatientMessageVo.setAssociatedId(patientAssociated.getAssociatedId());
            dbPatientMessageVo.setDbPatientMessage(SpringUtils.getBean(IDbPatientMessageService.class).selectDbPatientMessageById(patientAssociated.getPatientId()));
            list.add(dbPatientMessageVo);
        }
        return list;
    }

    /*
     *
     * 列表查询 诊断之后
     * */
    public static List<DbPatientMessageVo2> getLisOverCurc(String name) {
        List<DbPatientMessageVo2> list = new ArrayList<>();
        DbPatientAssociated dbPatientAssociated = new DbPatientAssociated();
        dbPatientAssociated.setIsOk("1");
        dbPatientAssociated.setAssociatedTable(name);
        List<DbPatientAssociated> dbPatientAssociateds = SpringUtils.getBean(IDbPatientAssociatedService.class).selectDbPatientAssociatedList(dbPatientAssociated);
        for (DbPatientAssociated patientAssociated : dbPatientAssociateds) {
            DbPatientMessageVo2 DbPatientMessageVo2 = new DbPatientMessageVo2();
            DbPatientMessageVo2.setDbPatientMessage(SpringUtils.getBean(IDbPatientMessageService.class).selectDbPatientMessageById(patientAssociated.getPatientId()));
            DbPatientMessageVo2.setDbPatientAssociated(patientAssociated);
            list.add(DbPatientMessageVo2);
        }
        return list;
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();

            Object value = StringUtils.nvl(field.get(obj), field.get(obj));
            map.put(fieldName, value);
        }
        return map;
    }

    /*
     *
     * 将病人的基本信息放入map
     *
     * */
    public static Map<String, Object> patientToMap(Long id) throws IllegalAccessException {
        DbPatientMessage dbPatientMessage = SpringUtils.getBean(IDbPatientMessageService.class).selectDbPatientMessageById(id);
        Map<String, Object> stringObjectMap = objectToMap(dbPatientMessage);
        return stringObjectMap;
    }

    /*
     *
     * 图片路径处理
     * */
    public static String getPathString(String path) {
        String replace = path.replace("/profile", "");

        return "D:/ruoyi/uploadPath" + replace;
    }


    public static ReplaceOptionalTextPictureRefRenderPolicy getPictureReplace(String name, String path) throws FileNotFoundException {
        if (path != null && path != "") {
            String path1 = getPath(path);
            ReplaceOptionalTextPictureRefRenderPolicy pictureCnv = new ReplaceOptionalTextPictureRefRenderPolicy(name,
                    new FileInputStream(path1),
                    XWPFDocument.PICTURE_TYPE_PNG);
            return pictureCnv;
        }
        return null;

    }

    /*
     * 路径拼接  完整路径
     * */
    public static String getPath(String path) {
        String profile = Global.getProfile();
        String replace = path.replace("/profile", profile);
        return replace;
    }

    /*
     *   存储地址
     * */
    public static String getPathPage(String path) {
        String profile = Global.getProfile();
//        D:/ruoyi/uploadPath/upload/2020/06/12/804ab1617afd7c40fbe2ad8b83a1413e.png
//        /profile/upload/2020/06/24/747593fb4ef8643f2fc4ec776ba24e22.jpg
        String replace = path.replace("/profile", profile);
        String[] strings = replace.split("/");
        List<String> strings1 = Arrays.asList(strings);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings1.size() - 1; i++) {
            stringBuilder.append(strings1.get(i) + "/");
        }
        return stringBuilder.toString();
    }


    /*
     *替换文件名
     * */
    public static String getPathE(String path, String name) {
        String[] split = path.split("/");
        List<String> strings = Arrays.asList(split);
        String s = strings.get((strings.size() - 1));
        String replace = path.replace(s, name);
        return replace;
    }


    /*
     * 后缀
     * */
    public static String getPathType(String path) {
//        D:/ruoyi/uploadPath/upload/2020/06/12/804ab1617afd7c40fbe2ad8b83a1413e.png
//        /profile/upload/2020/06/24/747593fb4ef8643f2fc4ec776ba24e22.jpg
        String[] strings = path.split("/");
        List<String> strings1 = Arrays.asList(strings);
        String s = strings1.get(strings1.size() - 1);
        String[] split = s.split("\\.");
        return split[1];
    }

    public static String toPath(String s) {

        String profile = Global.getProfile();
//        D:/ruoyi/uploadPath/upload/2020/06/12/804ab1617afd7c40fbe2ad8b83a1413e.png
//        /profile/upload/2020/06/24/747593fb4ef8643f2fc4ec776ba24e22.jpg
        String replace = s.replace(profile, "/profile");
        return replace;
    }


    private static String[] arr = {""};


    public static String getOpenReport(Long userId) {
//    检测报告
        DbPatientAssociated dbDiagonsisProject = new DbPatientAssociated();
        dbDiagonsisProject.setPatientId(userId);
        dbDiagonsisProject.setIsOk("1");
        List<DbPatientAssociated> dbPatientAssociateds = SpringUtils.getBean(IDbPatientAssociatedService.class).selectDbDiagonsisProjectListBycreateTime(dbDiagonsisProject);
        ArrayList<DbOpenReport> objects = new ArrayList<>();
        dbPatientAssociateds.forEach(itm -> {
            DbOpenReport dbOpenReport = new DbOpenReport();
            DbDiagonsisProject dbDiagonsisProject1 = new DbDiagonsisProject();
            String associatedTable = itm.getAssociatedTable();
            dbDiagonsisProject1.setCodeName(associatedTable);
            List<DbDiagonsisProject> dbDiagonsisProjects = SpringUtils.getBean(IDbDiagonsisProjectService.class).selectDbDiagonsisProjectList(dbDiagonsisProject1);
            dbOpenReport.setName(dbDiagonsisProjects.get(0).getName());
            DbDiagonsisProject dbDiagonsisProject2 = SpringUtils.getBean(IDbDiagonsisProjectService.class).selectDbDiagonsisProjectById(dbDiagonsisProjects.get(0).getProductId());
            dbOpenReport.setPorjectName(dbDiagonsisProject2.getName());
            /*
             * 时间处理
             * */
            Date createTime = itm.getCreateTime();
            String s = DateUtils.parseDateToStr("yyyy年MM月dd日HH时MM分", createTime);
            dbOpenReport.setDateTime(s);
            dbOpenReport.setDbPatientAssociated(itm);
            objects.add(dbOpenReport);

        });


        String json = JSON.toJSONString(objects).trim();
        String s = StringEscapeUtils.unescapeHtml(json);


        return s;
    }

    public static String preview(DbPatientAssociated dbDiagonsisProject) {
    //获取对应关联表的文档路径
        DbDiagonsisProject dbDiagonsisProject1 = new DbDiagonsisProject();
        dbDiagonsisProject1.setCodeName(dbDiagonsisProject.getAssociatedTable());
        List<DbDiagonsisProject> dbDiagonsisProjects = SpringUtils.getBean(IDbDiagonsisProjectService.class).selectDbDiagonsisProjectList(dbDiagonsisProject1);
        String tableName = dbDiagonsisProjects.get(0).getTableName();
        String s=SpringUtils.getBean(IDbDiagonsisProjectService.class).selectByTableName(tableName,dbDiagonsisProject.getAssociatedId());


        return s;
    }
}
