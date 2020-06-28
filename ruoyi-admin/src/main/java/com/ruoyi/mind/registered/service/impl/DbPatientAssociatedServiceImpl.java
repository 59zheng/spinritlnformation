package com.ruoyi.mind.registered.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.registered.mapper.DbPatientAssociatedMapper;
import com.ruoyi.mind.registered.domain.DbPatientAssociated;
import com.ruoyi.mind.registered.service.IDbPatientAssociatedService;
import com.ruoyi.common.core.text.Convert;

/**
 * 病人诊断记录关联检测报告Service业务层处理
 * 
 * @author zheng
 * @date 2020-06-23
 */
@Service
public class DbPatientAssociatedServiceImpl implements IDbPatientAssociatedService 
{
    @Autowired
    private DbPatientAssociatedMapper dbPatientAssociatedMapper;

    /**
     * 查询病人诊断记录关联检测报告
     * 
     * @param id 病人诊断记录关联检测报告ID
     * @return 病人诊断记录关联检测报告
     */
    @Override
    public DbPatientAssociated selectDbPatientAssociatedById(Long id)
    {
        return dbPatientAssociatedMapper.selectDbPatientAssociatedById(id);
    }

    /**
     * 查询病人诊断记录关联检测报告列表
     * 
     * @param dbPatientAssociated 病人诊断记录关联检测报告
     * @return 病人诊断记录关联检测报告
     */
    @Override
    public List<DbPatientAssociated> selectDbPatientAssociatedList(DbPatientAssociated dbPatientAssociated)
    {
        return dbPatientAssociatedMapper.selectDbPatientAssociatedList(dbPatientAssociated);
    }

    /**
     * 新增病人诊断记录关联检测报告
     * 
     * @param dbPatientAssociated 病人诊断记录关联检测报告
     * @return 结果
     */
    @Override
    public int insertDbPatientAssociated(DbPatientAssociated dbPatientAssociated)
    {
        dbPatientAssociated.setCreateTime(DateUtils.getNowDate());
        return dbPatientAssociatedMapper.insertDbPatientAssociated(dbPatientAssociated);
    }

    /**
     * 修改病人诊断记录关联检测报告
     * 
     * @param dbPatientAssociated 病人诊断记录关联检测报告
     * @return 结果
     */
    @Override
    public int updateDbPatientAssociated(DbPatientAssociated dbPatientAssociated)
    {
        return dbPatientAssociatedMapper.updateDbPatientAssociated(dbPatientAssociated);
    }

    /**
     * 删除病人诊断记录关联检测报告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbPatientAssociatedByIds(String ids)
    {
        return dbPatientAssociatedMapper.deleteDbPatientAssociatedByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除病人诊断记录关联检测报告信息
     * 
     * @param id 病人诊断记录关联检测报告ID
     * @return 结果
     */
    @Override
    public int deleteDbPatientAssociatedById(Long id)
    {
        return dbPatientAssociatedMapper.deleteDbPatientAssociatedById(id);
    }
}
