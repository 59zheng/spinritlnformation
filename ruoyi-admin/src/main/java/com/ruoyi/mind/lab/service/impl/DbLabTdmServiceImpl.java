package com.ruoyi.mind.lab.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.lab.mapper.DbLabTdmMapper;
import com.ruoyi.mind.lab.domain.DbLabTdm;
import com.ruoyi.mind.lab.service.IDbLabTdmService;
import com.ruoyi.common.core.text.Convert;

/**
 * 血药浓度检测Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Service
public class DbLabTdmServiceImpl implements IDbLabTdmService 
{
    @Autowired
    private DbLabTdmMapper dbLabTdmMapper;

    /**
     * 查询血药浓度检测
     * 
     * @param id 血药浓度检测ID
     * @return 血药浓度检测
     */
    @Override
    public DbLabTdm selectDbLabTdmById(Long id)
    {
        return dbLabTdmMapper.selectDbLabTdmById(id);
    }

    /**
     * 查询血药浓度检测列表
     * 
     * @param dbLabTdm 血药浓度检测
     * @return 血药浓度检测
     */
    @Override
    public List<DbLabTdm> selectDbLabTdmList(DbLabTdm dbLabTdm)
    {
        return dbLabTdmMapper.selectDbLabTdmList(dbLabTdm);
    }

    /**
     * 新增血药浓度检测
     * 
     * @param dbLabTdm 血药浓度检测
     * @return 结果
     */
    @Override
    public int insertDbLabTdm(DbLabTdm dbLabTdm)
    {
        return dbLabTdmMapper.insertDbLabTdm(dbLabTdm);
    }

    /**
     * 修改血药浓度检测
     * 
     * @param dbLabTdm 血药浓度检测
     * @return 结果
     */
    @Override
    public int updateDbLabTdm(DbLabTdm dbLabTdm)
    {
        return dbLabTdmMapper.updateDbLabTdm(dbLabTdm);
    }

    /**
     * 删除血药浓度检测对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbLabTdmByIds(String ids)
    {
        return dbLabTdmMapper.deleteDbLabTdmByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除血药浓度检测信息
     * 
     * @param id 血药浓度检测ID
     * @return 结果
     */
    @Override
    public int deleteDbLabTdmById(Long id)
    {
        return dbLabTdmMapper.deleteDbLabTdmById(id);
    }
}
