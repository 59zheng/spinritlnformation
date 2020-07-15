package com.ruoyi.mind.physical.service;

import java.util.List;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisElectrical;

/**
 * 脑电诊断Service接口
 * 
 * @author zheng
 * @date 2020-07-01
 */
public interface IDbReportDiagnosisElectricalService 
{
    /**
     * 查询脑电诊断
     * 
     * @param id 脑电诊断ID
     * @return 脑电诊断
     */
    public DbReportDiagnosisElectrical selectDbReportDiagnosisElectricalById(Long id);

    /**
     * 查询脑电诊断列表
     * 
     * @param dbReportDiagnosisElectrical 脑电诊断
     * @return 脑电诊断集合
     */
    public List<DbReportDiagnosisElectrical> selectDbReportDiagnosisElectricalList(DbReportDiagnosisElectrical dbReportDiagnosisElectrical);

    /**
     * 新增脑电诊断
     * 
     * @param dbReportDiagnosisElectrical 脑电诊断
     * @return 结果
     */
    public int insertDbReportDiagnosisElectrical(DbReportDiagnosisElectrical dbReportDiagnosisElectrical);

    /**
     * 修改脑电诊断
     * 
     * @param dbReportDiagnosisElectrical 脑电诊断
     * @return 结果
     */
    public int updateDbReportDiagnosisElectrical(DbReportDiagnosisElectrical dbReportDiagnosisElectrical);

    /**
     * 批量删除脑电诊断
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisElectricalByIds(String ids);

    /**
     * 删除脑电诊断信息
     * 
     * @param id 脑电诊断ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisElectricalById(Long id);
}
