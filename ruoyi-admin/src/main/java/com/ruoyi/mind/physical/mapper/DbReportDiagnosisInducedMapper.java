package com.ruoyi.mind.physical.mapper;

import java.util.List;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInduced;

/**
 * 诱发电位报告(物理诊断下边)Mapper接口
 *
 * @author zheng
 * @date 2020-06-10
 */
public interface DbReportDiagnosisInducedMapper
{
    /**
     * 查询诱发电位报告(物理诊断下边)
     *
     * @param id 诱发电位报告(物理诊断下边)ID
     * @return 诱发电位报告(物理诊断下边)
     */
    public DbReportDiagnosisInduced selectDbReportDiagnosisInducedById(Long id);

    /**
     * 查询诱发电位报告(物理诊断下边)列表
     *
     * @param dbReportDiagnosisInduced 诱发电位报告(物理诊断下边)
     * @return 诱发电位报告(物理诊断下边)集合
     */
    public List<DbReportDiagnosisInduced> selectDbReportDiagnosisInducedList(DbReportDiagnosisInduced dbReportDiagnosisInduced);

    /**
     * 新增诱发电位报告(物理诊断下边)
     *
     * @param dbReportDiagnosisInduced 诱发电位报告(物理诊断下边)
     * @return 结果
     */
    public int insertDbReportDiagnosisInduced(DbReportDiagnosisInduced dbReportDiagnosisInduced);

    /**
     * 修改诱发电位报告(物理诊断下边)
     *
     * @param dbReportDiagnosisInduced 诱发电位报告(物理诊断下边)
     * @return 结果
     */
    public int updateDbReportDiagnosisInduced(DbReportDiagnosisInduced dbReportDiagnosisInduced);

    /**
     * 删除诱发电位报告(物理诊断下边)
     *
     * @param id 诱发电位报告(物理诊断下边)ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisInducedById(Long id);

    /**
     * 批量删除诱发电位报告(物理诊断下边)
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisInducedByIds(String[] ids);
}