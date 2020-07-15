package com.ruoyi.mind.physical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.physical.mapper.DbReportDiagnosisElectricalMapper;
import com.ruoyi.mind.physical.domain.DbReportDiagnosisElectrical;
import com.ruoyi.mind.physical.service.IDbReportDiagnosisElectricalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 脑电诊断Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-01
 */
@Service
public class DbReportDiagnosisElectricalServiceImpl implements IDbReportDiagnosisElectricalService 
{
    @Autowired
    private DbReportDiagnosisElectricalMapper dbReportDiagnosisElectricalMapper;

    /**
     * 查询脑电诊断
     * 
     * @param id 脑电诊断ID
     * @return 脑电诊断
     */
    @Override
    public DbReportDiagnosisElectrical selectDbReportDiagnosisElectricalById(Long id)
    {
        return dbReportDiagnosisElectricalMapper.selectDbReportDiagnosisElectricalById(id);
    }

    /**
     * 查询脑电诊断列表
     * 
     * @param dbReportDiagnosisElectrical 脑电诊断
     * @return 脑电诊断
     */
    @Override
    public List<DbReportDiagnosisElectrical> selectDbReportDiagnosisElectricalList(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        return dbReportDiagnosisElectricalMapper.selectDbReportDiagnosisElectricalList(dbReportDiagnosisElectrical);
    }

    /**
     * 新增脑电诊断
     * 
     * @param dbReportDiagnosisElectrical 脑电诊断
     * @return 结果
     */
    @Override
    public int insertDbReportDiagnosisElectrical(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        return dbReportDiagnosisElectricalMapper.insertDbReportDiagnosisElectrical(dbReportDiagnosisElectrical);
    }

    /**
     * 修改脑电诊断
     * 
     * @param dbReportDiagnosisElectrical 脑电诊断
     * @return 结果
     */
    @Override
    public int updateDbReportDiagnosisElectrical(DbReportDiagnosisElectrical dbReportDiagnosisElectrical)
    {
        return dbReportDiagnosisElectricalMapper.updateDbReportDiagnosisElectrical(dbReportDiagnosisElectrical);
    }

    /**
     * 删除脑电诊断对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbReportDiagnosisElectricalByIds(String ids)
    {
        return dbReportDiagnosisElectricalMapper.deleteDbReportDiagnosisElectricalByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除脑电诊断信息
     * 
     * @param id 脑电诊断ID
     * @return 结果
     */
    @Override
    public int deleteDbReportDiagnosisElectricalById(Long id)
    {
        return dbReportDiagnosisElectricalMapper.deleteDbReportDiagnosisElectricalById(id);
    }
}
