package com.ruoyi.mind.registered.service;

import java.util.List;
import com.ruoyi.mind.registered.domain.DbPatientAssociated;

/**
 * 病人诊断记录关联检测报告Service接口
 * 
 * @author zheng
 * @date 2020-06-23
 */
public interface IDbPatientAssociatedService 
{
    /**
     * 查询病人诊断记录关联检测报告
     * 
     * @param id 病人诊断记录关联检测报告ID
     * @return 病人诊断记录关联检测报告
     */
    public DbPatientAssociated selectDbPatientAssociatedById(Long id);

    /**
     * 查询病人诊断记录关联检测报告列表
     * 
     * @param dbPatientAssociated 病人诊断记录关联检测报告
     * @return 病人诊断记录关联检测报告集合
     */
    public List<DbPatientAssociated> selectDbPatientAssociatedList(DbPatientAssociated dbPatientAssociated);

    /**
     * 新增病人诊断记录关联检测报告
     * 
     * @param dbPatientAssociated 病人诊断记录关联检测报告
     * @return 结果
     */
    public int insertDbPatientAssociated(DbPatientAssociated dbPatientAssociated);

    /**
     * 修改病人诊断记录关联检测报告
     * 
     * @param dbPatientAssociated 病人诊断记录关联检测报告
     * @return 结果
     */
    public int updateDbPatientAssociated(DbPatientAssociated dbPatientAssociated);

    /**
     * 批量删除病人诊断记录关联检测报告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbPatientAssociatedByIds(String ids);

    /**
     * 删除病人诊断记录关联检测报告信息
     * 
     * @param id 病人诊断记录关联检测报告ID
     * @return 结果
     */
    public int deleteDbPatientAssociatedById(Long id);
}
