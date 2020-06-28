package com.ruoyi.mind.registered.mapper;

import java.util.List;
import com.ruoyi.mind.registered.domain.DbPatientEntrust;

/**
 * 医嘱(关联诊断记录 ,关联主治医师)Mapper接口
 * 
 * @author zheng
 * @date 2020-06-18
 */
public interface DbPatientEntrustMapper 
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
     * 删除医嘱(关联诊断记录 ,关联主治医师)
     * 
     * @param id 医嘱(关联诊断记录 ,关联主治医师)ID
     * @return 结果
     */
    public int deleteDbPatientEntrustById(Long id);

    /**
     * 批量删除医嘱(关联诊断记录 ,关联主治医师)
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbPatientEntrustByIds(String[] ids);
}
