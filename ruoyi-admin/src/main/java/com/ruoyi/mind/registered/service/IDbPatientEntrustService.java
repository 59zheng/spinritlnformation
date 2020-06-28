package com.ruoyi.mind.registered.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.mind.registered.domain.DbPatientEntrust;

/**
 * 医嘱(关联诊断记录 ,关联主治医师)Service接口
 * 
 * @author zheng
 * @date 2020-06-18
 */
public interface IDbPatientEntrustService 
{
    /**
     * 查询医嘱(关联诊断记录 ,关联主治医师)
     * 
     * @param id 医嘱(关联诊断记录 ,关联主治医师)ID
     * @return 医嘱(关联诊断记录 ,关联主治医师)
     */
    public DbPatientEntrust selectDbPatientEntrustById(Long id);

    /**
     * 查询医嘱(关联诊断记录 ,关联主治医师)列表
     * 
     * @param dbPatientEntrust 医嘱(关联诊断记录 ,关联主治医师)
     * @return 医嘱(关联诊断记录 ,关联主治医师)集合
     */
    public List<DbPatientEntrust> selectDbPatientEntrustList(DbPatientEntrust dbPatientEntrust);

    /**
     * 新增医嘱(关联诊断记录 ,关联主治医师)
     * 
     * @param dbPatientEntrust 医嘱(关联诊断记录 ,关联主治医师)
     * @return 结果
     */
    public int insertDbPatientEntrust(DbPatientEntrust dbPatientEntrust);

    /**
     * 修改医嘱(关联诊断记录 ,关联主治医师)
     * 
     * @param dbPatientEntrust 医嘱(关联诊断记录 ,关联主治医师)
     * @return 结果
     */
    public int updateDbPatientEntrust(DbPatientEntrust dbPatientEntrust);

    /**
     * 批量删除医嘱(关联诊断记录 ,关联主治医师)
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbPatientEntrustByIds(String ids);

    /**
     * 删除医嘱(关联诊断记录 ,关联主治医师)信息
     * 
     * @param id 医嘱(关联诊断记录 ,关联主治医师)ID
     * @return 结果
     */
    public int deleteDbPatientEntrustById(Long id);

    Map<String, String> entrustAppendSave(Map map);
}
