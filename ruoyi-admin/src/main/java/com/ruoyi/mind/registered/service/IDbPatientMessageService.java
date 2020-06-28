package com.ruoyi.mind.registered.service;

import java.util.List;
import com.ruoyi.mind.registered.domain.DbPatientMessage;

/**
 * 病人,缴费之后创建Service接口
 * 
 * @author zheng
 * @date 2020-06-05
 */
public interface IDbPatientMessageService 
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
     * 批量删除病人,缴费之后创建
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbPatientMessageByIds(String ids);

    /**
     * 删除病人,缴费之后创建信息
     * 
     * @param id 病人,缴费之后创建ID
     * @return 结果
     */
    public int deleteDbPatientMessageById(Long id);
}
