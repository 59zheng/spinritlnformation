package com.ruoyi.mind.lab.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.lab.mapper.DbLabDnaMapper;
import com.ruoyi.mind.lab.domain.DbLabDna;
import com.ruoyi.mind.lab.service.IDbLabDnaService;
import com.ruoyi.common.core.text.Convert;

/**
 * 基因检测Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Service
public class DbLabDnaServiceImpl implements IDbLabDnaService 
{
    @Autowired
    private DbLabDnaMapper dbLabDnaMapper;

    /**
     * 查询基因检测
     * 
     * @param id 基因检测ID
     * @return 基因检测
     */
    @Override
    public DbLabDna selectDbLabDnaById(Long id)
    {
        return dbLabDnaMapper.selectDbLabDnaById(id);
    }

    /**
     * 查询基因检测列表
     * 
     * @param dbLabDna 基因检测
     * @return 基因检测
     */
    @Override
    public List<DbLabDna> selectDbLabDnaList(DbLabDna dbLabDna)
    {
        return dbLabDnaMapper.selectDbLabDnaList(dbLabDna);
    }

    /**
     * 新增基因检测
     * 
     * @param dbLabDna 基因检测
     * @return 结果
     */
    @Override
    public int insertDbLabDna(DbLabDna dbLabDna)
    {
        return dbLabDnaMapper.insertDbLabDna(dbLabDna);
    }

    /**
     * 修改基因检测
     * 
     * @param dbLabDna 基因检测
     * @return 结果
     */
    @Override
    public int updateDbLabDna(DbLabDna dbLabDna)
    {
        return dbLabDnaMapper.updateDbLabDna(dbLabDna);
    }

    /**
     * 删除基因检测对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbLabDnaByIds(String ids)
    {
        return dbLabDnaMapper.deleteDbLabDnaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基因检测信息
     * 
     * @param id 基因检测ID
     * @return 结果
     */
    @Override
    public int deleteDbLabDnaById(Long id)
    {
        return dbLabDnaMapper.deleteDbLabDnaById(id);
    }
}
