package com.ruoyi.mind.physical.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.physical.mapper.DbReportDiagnosisSleepMapper;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisSleep;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisSleepService;
import com.ruoyi.common.core.text.Convert;

/**
 * 睡眠监测报告Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-08
 */
@Service
public class DbReportDiagnosisSleepServiceImpl implements IDbReportDiagnosisSleepService 
{
    @Autowired
    private DbReportDiagnosisSleepMapper dbReportDiagnosisSleepMapper;

    /**
     * 查询睡眠监测报告
     * 
     * @param id 睡眠监测报告ID
     * @return 睡眠监测报告
     */
    @Override
    public DbReportDiagnosisSleep selectDbReportDiagnosisSleepById(Long id)
    {
        return dbReportDiagnosisSleepMapper.selectDbReportDiagnosisSleepById(id);
    }

    /**
     * 查询睡眠监测报告列表
     * 
     * @param dbReportDiagnosisSleep 睡眠监测报告
     * @return 睡眠监测报告
     */
    @Override
    public List<DbReportDiagnosisSleep> selectDbReportDiagnosisSleepList(DbReportDiagnosisSleep dbReportDiagnosisSleep)
    {
        return dbReportDiagnosisSleepMapper.selectDbReportDiagnosisSleepList(dbReportDiagnosisSleep);
    }

    /**
     * 新增睡眠监测报告
     * 
     * @param dbReportDiagnosisSleep 睡眠监测报告
     * @return 结果
     */
    @Override
    public int insertDbReportDiagnosisSleep(DbReportDiagnosisSleep dbReportDiagnosisSleep)
    {
        dbReportDiagnosisSleep.setCreateTime(DateUtils.getNowDate());
        return dbReportDiagnosisSleepMapper.insertDbReportDiagnosisSleep(dbReportDiagnosisSleep);
    }

    /**
     * 修改睡眠监测报告
     * 
     * @param dbReportDiagnosisSleep 睡眠监测报告
     * @return 结果
     */
    @Override
    public int updateDbReportDiagnosisSleep(DbReportDiagnosisSleep dbReportDiagnosisSleep)
    {
        return dbReportDiagnosisSleepMapper.updateDbReportDiagnosisSleep(dbReportDiagnosisSleep);
    }

    /**
     * 删除睡眠监测报告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbReportDiagnosisSleepByIds(String ids)
    {
        return dbReportDiagnosisSleepMapper.deleteDbReportDiagnosisSleepByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除睡眠监测报告信息
     * 
     * @param id 睡眠监测报告ID
     * @return 结果
     */
    @Override
    public int deleteDbReportDiagnosisSleepById(Long id)
    {
        return dbReportDiagnosisSleepMapper.deleteDbReportDiagnosisSleepById(id);
    }

    /**
     * 查询睡眠监测报告树列表
     * 
     * @return 所有睡眠监测报告信息
     */
    @Override
    public List<Ztree> selectDbReportDiagnosisSleepTree()
    {
        List<DbReportDiagnosisSleep> dbReportDiagnosisSleepList = dbReportDiagnosisSleepMapper.selectDbReportDiagnosisSleepList(new DbReportDiagnosisSleep());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbReportDiagnosisSleep dbReportDiagnosisSleep : dbReportDiagnosisSleepList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbReportDiagnosisSleep.getId());
            ztree.setpId(dbReportDiagnosisSleep.getFatherId());
            ztree.setName(dbReportDiagnosisSleep.getPatientName());
            ztree.setTitle(dbReportDiagnosisSleep.getPatientName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
