package com.ruoyi.mind.physical.service;

import java.util.List;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInfrared;

/**
 * 红外热成像及血流图检查Service接口
 * 
 * @author zheng
 * @date 2020-06-29
 */
public interface IDbReportDiagnosisInfraredService 
{
    /**
     * 查询红外热成像及血流图检查
     * 
     * @param id 红外热成像及血流图检查ID
     * @return 红外热成像及血流图检查
     */
    public DbReportDiagnosisInfrared selectDbReportDiagnosisInfraredById(Long id);

    /**
     * 查询红外热成像及血流图检查列表
     * 
     * @param dbReportDiagnosisInfrared 红外热成像及血流图检查
     * @return 红外热成像及血流图检查集合
     */
    public List<DbReportDiagnosisInfrared> selectDbReportDiagnosisInfraredList(DbReportDiagnosisInfrared dbReportDiagnosisInfrared);

    /**
     * 新增红外热成像及血流图检查
     * 
     * @param dbReportDiagnosisInfrared 红外热成像及血流图检查
     * @return 结果
     */
    public int insertDbReportDiagnosisInfrared(DbReportDiagnosisInfrared dbReportDiagnosisInfrared);

    /**
     * 修改红外热成像及血流图检查
     * 
     * @param dbReportDiagnosisInfrared 红外热成像及血流图检查
     * @return 结果
     */
    public int updateDbReportDiagnosisInfrared(DbReportDiagnosisInfrared dbReportDiagnosisInfrared);

    /**
     * 批量删除红外热成像及血流图检查
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisInfraredByIds(String ids);

    /**
     * 删除红外热成像及血流图检查信息
     * 
     * @param id 红外热成像及血流图检查ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisInfraredById(Long id);
}
