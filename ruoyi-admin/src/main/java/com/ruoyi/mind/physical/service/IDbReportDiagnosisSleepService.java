package com.ruoyi.mind.physical.service;

import java.util.List;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisSleep;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 睡眠监测报告Service接口
 * 
 * @author zheng
 * @date 2020-07-08
 */
public interface IDbReportDiagnosisSleepService 
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
     * 批量删除睡眠监测报告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisSleepByIds(String ids);

    /**
     * 删除睡眠监测报告信息
     * 
     * @param id 睡眠监测报告ID
     * @return 结果
     */
    public int deleteDbReportDiagnosisSleepById(Long id);

    /**
     * 查询睡眠监测报告树列表
     * 
     * @return 所有睡眠监测报告信息
     */
    public List<Ztree> selectDbReportDiagnosisSleepTree();
}
