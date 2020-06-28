package com.ruoyi.mind.physical.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.physical.mapper.DbReportDiagnosisInducedMapper;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInduced;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisInducedService;
import com.ruoyi.common.core.text.Convert;

/**
 * 诱发电位报告(物理诊断下边)Service业务层处理
 *
 * @author zheng
 * @date 2020-06-10
 */
@Service
public class DbReportDiagnosisInducedServiceImpl implements IDbReportDiagnosisInducedService
{
    @Autowired
    private DbReportDiagnosisInducedMapper dbReportDiagnosisInducedMapper;

    /**
     * 查询诱发电位报告(物理诊断下边)
     *
     * @param id 诱发电位报告(物理诊断下边)ID
     * @return 诱发电位报告(物理诊断下边)
     */
    @Override
    public DbReportDiagnosisInduced selectDbReportDiagnosisInducedById(Long id)
    {
        return dbReportDiagnosisInducedMapper.selectDbReportDiagnosisInducedById(id);
    }

    /**
     * 查询诱发电位报告(物理诊断下边)列表
     *
     * @param dbReportDiagnosisInduced 诱发电位报告(物理诊断下边)
     * @return 诱发电位报告(物理诊断下边)
     */
    @Override
    public List<DbReportDiagnosisInduced> selectDbReportDiagnosisInducedList(DbReportDiagnosisInduced dbReportDiagnosisInduced)
    {
        return dbReportDiagnosisInducedMapper.selectDbReportDiagnosisInducedList(dbReportDiagnosisInduced);
    }

    /**
     * 新增诱发电位报告(物理诊断下边)
     *
     * @param dbReportDiagnosisInduced 诱发电位报告(物理诊断下边)
     * @return 结果
     */
    @Override
    public int insertDbReportDiagnosisInduced(DbReportDiagnosisInduced dbReportDiagnosisInduced)
    {
        dbReportDiagnosisInduced.setCreateTime(DateUtils.getNowDate());
        return dbReportDiagnosisInducedMapper.insertDbReportDiagnosisInduced(dbReportDiagnosisInduced);
    }

    /**
     * 修改诱发电位报告(物理诊断下边)
     *
     * @param dbReportDiagnosisInduced 诱发电位报告(物理诊断下边)
     * @return 结果
     */
    @Override
    public int updateDbReportDiagnosisInduced(DbReportDiagnosisInduced dbReportDiagnosisInduced)
    {
        return dbReportDiagnosisInducedMapper.updateDbReportDiagnosisInduced(dbReportDiagnosisInduced);
    }

    /**
     * 删除诱发电位报告(物理诊断下边)对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbReportDiagnosisInducedByIds(String ids)
    {
        return dbReportDiagnosisInducedMapper.deleteDbReportDiagnosisInducedByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除诱发电位报告(物理诊断下边)信息
     *
     * @param id 诱发电位报告(物理诊断下边)ID
     * @return 结果
     */
    @Override
    public int deleteDbReportDiagnosisInducedById(Long id)
    {
        return dbReportDiagnosisInducedMapper.deleteDbReportDiagnosisInducedById(id);
    }
}