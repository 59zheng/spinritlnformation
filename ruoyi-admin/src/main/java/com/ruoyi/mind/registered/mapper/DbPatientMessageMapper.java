package com.ruoyi.mind.registered.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.mind.registered.domain.DbPatientMessage;
import org.apache.ibatis.annotations.Select;

/**
 * 病人,缴费之后创建Mapper接口
 * 
 * @author zheng
 * @date 2020-06-05
 */
public interface DbPatientMessageMapper 
{
    /**
     * 查询病人,缴费之后创建
     * 
     * @param id 病人,缴费之后创建ID
     * @return 病人,缴费之后创建
     */
    public DbPatientMessage selectDbPatientMessageById(Long id);

    /**
     * 查询病人,缴费之后创建列表
     * 
     * @param dbPatientMessage 病人,缴费之后创建
     * @return 病人,缴费之后创建集合
     */
    public List<DbPatientMessage> selectDbPatientMessageList(DbPatientMessage dbPatientMessage);

    /**
     * 新增病人,缴费之后创建
     * 
     * @param dbPatientMessage 病人,缴费之后创建
     * @return 结果
     */
    public int insertDbPatientMessage(DbPatientMessage dbPatientMessage);

    /**
     * 修改病人,缴费之后创建
     * 
     * @param dbPatientMessage 病人,缴费之后创建
     * @return 结果
     */
    public int updateDbPatientMessage(DbPatientMessage dbPatientMessage);

    /**
     * 删除病人,缴费之后创建
     * 
     * @param id 病人,缴费之后创建ID
     * @return 结果
     */
    public int deleteDbPatientMessageById(Long id);

    /**
     * 批量删除病人,缴费之后创建
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbPatientMessageByIds(String[] ids);

    @Select("select c.create_time as time, COUNT(c.id) num from db_patient_message c GROUP BY c.create_time")
    List<Map<String,Object>> selectListByCreatTime();
}
