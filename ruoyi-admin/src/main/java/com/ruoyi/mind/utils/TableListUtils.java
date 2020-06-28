package com.ruoyi.mind.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.deepoove.poi.policy.ref.ReplaceOptionalTextPictureRefRenderPolicy;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.mind.registered.domain.DbPatientAssociated;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.registered.domain.DbPatientMessageVo;
import com.ruoyi.mind.registered.service.IDbPatientAssociatedService;
import com.ruoyi.mind.registered.service.IDbPatientMessageService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public static String getPathString(String path){
        String replace = path.replace("/profile", "");

        return "D:/ruoyi/uploadPath"+replace;
    }


    public  static  ReplaceOptionalTextPictureRefRenderPolicy  getPictureReplace(String name , String path) throws FileNotFoundException {
        if (path!=null&&path!=""){
            String path1 = getPath(path);
            ReplaceOptionalTextPictureRefRenderPolicy pictureCnv = new ReplaceOptionalTextPictureRefRenderPolicy(name,
                    new FileInputStream(path1),
                    XWPFDocument.PICTURE_TYPE_PNG);
            return pictureCnv;
        }return  null;

    }

    /*
    * 路径拼接
    * */
    public static String  getPath(String path) {
        String profile = Global.getProfile();
//        D:/ruoyi/uploadPath/upload/2020/06/12/804ab1617afd7c40fbe2ad8b83a1413e.png
//        /profile/upload/2020/06/24/747593fb4ef8643f2fc4ec776ba24e22.jpg
        String replace = path.replace("/profile", "D:/ruoyi/uploadPath");
        return replace;

    }
}
