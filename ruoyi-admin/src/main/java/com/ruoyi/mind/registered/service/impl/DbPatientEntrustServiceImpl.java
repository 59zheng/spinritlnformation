package com.ruoyi.mind.registered.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.registered.mapper.DbPatientEntrustMapper;
import com.ruoyi.mind.registered.domain.DbPatientEntrust;
import com.ruoyi.mind.registered.service.IDbPatientEntrustService;
import com.ruoyi.common.core.text.Convert;

/**
 * 医嘱(关联诊断记录 ,关联主治医师)Service业务层处理
 *
 * @author zheng
 * @date 2020-06-18
 */
@Service
public class DbPatientEntrustServiceImpl implements IDbPatientEntrustService {

    @Autowired
    private DbPatientEntrustMapper dbPatientEntrustMapper;

    /**
     * 查询医嘱(关联诊断记录 ,关联主治医师)
     *
     * @param id 医嘱(关联诊断记录 ,关联主治医师)ID
     * @return 医嘱(关联诊断记录, 关联主治医师)
     */
    @Override
    public DbPatientEntrust selectDbPatientEntrustById(Long id) {
        return dbPatientEntrustMapper.selectDbPatientEntrustById(id);
    }

    /**
     * 查询医嘱(关联诊断记录 ,关联主治医师)列表
     *
     * @param dbPatientEntrust 医嘱(关联诊断记录 ,关联主治医师)
     * @return 医嘱(关联诊断记录, 关联主治医师)
     */
    @Override
    public List<DbPatientEntrust> selectDbPatientEntrustList(DbPatientEntrust dbPatientEntrust) {
        return dbPatientEntrustMapper.selectDbPatientEntrustList(dbPatientEntrust);
    }

    /**
     * 新增医嘱(关联诊断记录 ,关联主治医师)
     *
     * @param dbPatientEntrust 医嘱(关联诊断记录 ,关联主治医师)
     * @return 结果
     */
    @Override
    public int insertDbPatientEntrust(DbPatientEntrust dbPatientEntrust) {
        return dbPatientEntrustMapper.insertDbPatientEntrust(dbPatientEntrust);
    }

    /**
     * 修改医嘱(关联诊断记录 ,关联主治医师)
     *
     * @param dbPatientEntrust 医嘱(关联诊断记录 ,关联主治医师)
     * @return 结果
     */
    @Override
    public int updateDbPatientEntrust(DbPatientEntrust dbPatientEntrust) {
        return dbPatientEntrustMapper.updateDbPatientEntrust(dbPatientEntrust);
    }

    /**
     * 删除医嘱(关联诊断记录 ,关联主治医师)对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbPatientEntrustByIds(String ids) {
        return dbPatientEntrustMapper.deleteDbPatientEntrustByIds(Convert.toStrArray(ids));
    }

    /*
     *
     *医生增加处理    月  周  天
     * */
    @Override
    public Map<String, String> entrustAppendSave(Map map) {
        String userCode = (String) map.get("userCode");
        String userName = (String) map.get("userName");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        if (userCode.contains("月")) {
            String substring = userCode.substring(0, userCode.indexOf("月"));
            String s = manageMonth(substring);
            stringStringHashMap.put(s,userName);
        } else if (userCode.contains("周")) {
            String substring = userCode.substring(0, userCode.indexOf("周"));
            String s = manageWeek(substring);
            stringStringHashMap.put(s,userName);
        } else if (userCode.contains("天")) {
            String substring = userCode.substring(0, userCode.indexOf("天"));
            String s = manageDay(substring);
            stringStringHashMap.put(s,userName);
        }else {
            stringStringHashMap.put(userCode,userName);
        }
        return stringStringHashMap;
    }


    /*
     *月处理
     * */
    public static String manageMonth(String indexOf) {

        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int i = Integer.parseInt(indexOf);
        instance.add(Calendar.MONDAY,+i);
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(time);
        return format;
    }
    /*
     *周处理
     * */
    public static String manageWeek(String indexOf) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int i = Integer.parseInt(indexOf);
        instance.add(Calendar.DAY_OF_MONTH,+i*7);
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(time);
        return format;
    }
    /*
     *天处理
     * */
    public static String manageDay(String indexOf) {

        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int i = Integer.parseInt(indexOf);
        instance.add(Calendar.DAY_OF_MONTH,+i);
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(time);
        return format;
    }


    /**
     * 删除医嘱(关联诊断记录 ,关联主治医师)信息
     *
     * @param id 医嘱(关联诊断记录 ,关联主治医师)ID
     * @return 结果
     */
    @Override
    public int deleteDbPatientEntrustById(Long id) {
        return dbPatientEntrustMapper.deleteDbPatientEntrustById(id);
    }
}
