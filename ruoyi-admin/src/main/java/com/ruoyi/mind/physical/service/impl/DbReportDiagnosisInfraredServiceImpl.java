package com.ruoyi.mind.physical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.physical.mapper.DbReportDiagnosisInfraredMapper;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisInfrared;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisInfraredService;
import com.ruoyi.common.core.text.Convert;

/**
 * 红外热成像及血流图检查Service业务层处理
 * 
 * @author zheng
 * @date 2020-06-29
 */
@Service
public class DbReportDiagnosisInfraredServiceImpl implements IDbReportDiagnosisInfraredService 
{
    @Autowired
    private DbReportDiagnosisInfraredMapper dbReportDiagnosisInfraredMapper;

    /**
     * 查询红外热成像及血流图检查
     * 
     * @param id 红外热成像及血流图检查ID
     * @return 红外热成像及血流图检查
     */
    @Override
    public DbReportDiagnosisInfrared selectDbReportDiagnosisInfraredById(Long id)
    {
        return dbReportDiagnosisInfraredMapper.selectDbReportDiagnosisInfraredById(id);
    }

    /**
     * 查询红外热成像及血流图检查列表
     * 
     * @param dbReportDiagnosisInfrared 红外热成像及血流图检查
     * @return 红外热成像及血流图检查
     */
    @Override
    public List<DbReportDiagnosisInfrared> selectDbReportDiagnosisInfraredList(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        return dbReportDiagnosisInfraredMapper.selectDbReportDiagnosisInfraredList(dbReportDiagnosisInfrared);
    }

    /**
     * 新增红外热成像及血流图检查
     * 
     * @param dbReportDiagnosisInfrared 红外热成像及血流图检查
     * @return 结果
     */
    @Override
    public int insertDbReportDiagnosisInfrared(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {

        return dbReportDiagnosisInfraredMapper.insertDbReportDiagnosisInfrared(dbReportDiagnosisInfrared);
    }

    /**
     * 修改红外热成像及血流图检查
     * 
     * @param dbReportDiagnosisInfrared 红外热成像及血流图检查
     * @return 结果
     */
    @Override
    public int updateDbReportDiagnosisInfrared(DbReportDiagnosisInfrared dbReportDiagnosisInfrared)
    {
        return dbReportDiagnosisInfraredMapper.updateDbReportDiagnosisInfrared(dbReportDiagnosisInfrared);
    }

    /**
     * 删除红外热成像及血流图检查对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbReportDiagnosisInfraredByIds(String ids)
    {
        return dbReportDiagnosisInfraredMapper.deleteDbReportDiagnosisInfraredByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除红外热成像及血流图检查信息
     * 
     * @param id 红外热成像及血流图检查ID
     * @return 结果
     */
    @Override
    public int deleteDbReportDiagnosisInfraredById(Long id)
    {
        return dbReportDiagnosisInfraredMapper.deleteDbReportDiagnosisInfraredById(id);
    }
}
