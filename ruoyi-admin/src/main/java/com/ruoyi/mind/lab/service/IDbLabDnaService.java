package com.ruoyi.mind.lab.service;

import java.util.List;
import com.ruoyi.mind.lab.domain.DbLabDna;

/**
 * 基因检测Service接口
 * 
 * @author zheng
 * @date 2020-07-13
 */
public interface IDbLabDnaService 
{
    /**
     * 查询基因检测
     * 
     * @param id 基因检测ID
     * @return 基因检测
     */
    public DbLabDna selectDbLabDnaById(Long id);

    /**
     * 查询基因检测列表
     * 
     * @param dbLabDna 基因检测
     * @return 基因检测集合
     */
    public List<DbLabDna> selectDbLabDnaList(DbLabDna dbLabDna);

    /**
     * 新增基因检测
     * 
     * @param dbLabDna 基因检测
     * @return 结果
     */
    public int insertDbLabDna(DbLabDna dbLabDna);

    /**
     * 修改基因检测
     * 
     * @param dbLabDna 基因检测
     * @return 结果
     */
    public int updateDbLabDna(DbLabDna dbLabDna);

    /**
     * 批量删除基因检测
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbLabDnaByIds(String ids);

    /**
     * 删除基因检测信息
     * 
     * @param id 基因检测ID
     * @return 结果
     */
    public int deleteDbLabDnaById(Long id);
}
