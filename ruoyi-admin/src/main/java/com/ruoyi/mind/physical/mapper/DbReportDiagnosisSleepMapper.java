package com.ruoyi.mind.physical.mapper;

import java.util.List;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisSleep;

/**
 * 睡眠监测报告Mapper接口
 * 
 * @author zheng
 * @date 2020-07-08
 */
public interface DbReportDiagnosisSleepMapper 
{
    /**
     * 查询睡眠监测报告
     * 
     * @param id 睡眠监测报告ID
     * @return 睡眠监测报告
     */
    public DbReportDiagnosisSleep selectDbReportDiagnosisSleepById(Long id);

    /**
     * 查询睡眠监测报告列表
     * 
     * @param dbReportDiagnosisSleep 睡眠监测报告
     * @return 睡眠监测报告集合
     */
    public List<DbReportDiagnosisSleep> selectDbReportDiagnosisSleepList(DbReportDiagnosisSleep dbReportDiagnosisSleep);

    /**
     * 新增睡眠监测报告
     * 
     * @param dbReportDiagnosisSleep 睡眠监测报告
     * @return 结果
     */
    public int insertDbReportDiagnosisSleep(DbReportDiagnosisSleep dbReportDiagnosisSleep);

    /**
     * 修改睡眠监测报告
     * 
     * @param dbReportDiagnosisSleep 睡眠监测报告
     * @return 结果
     */
    public int updateDbReportDiagnosisSleep(DbReportDiagnosisSleep dbReportDiagnosisSleep);

    /**
     * 删除睡眠监测报告
     * 
     * @param id 睡眠监测报告ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisSleepById(Long id);

    /**
     * 批量删除睡眠监测报告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisSleepByIds(String[] ids);
}
