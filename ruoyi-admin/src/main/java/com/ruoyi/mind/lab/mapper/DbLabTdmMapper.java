package com.ruoyi.mind.lab.mapper;

import java.util.List;
import com.ruoyi.mind.lab.domain.DbLabTdm;

/**
 * 血药浓度检测Mapper接口
 * 
 * @author zheng
 * @date 2020-07-13
 */
public interface DbLabTdmMapper 
{
    /**
     * 查询血药浓度检测
     * 
     * @param id 血药浓度检测ID
     * @return 血药浓度检测
     */
    public DbLabTdm selectDbLabTdmById(Long id);

    /**
     * 查询血药浓度检测列表
     * 
     * @param dbLabTdm 血药浓度检测
     * @return 血药浓度检测集合
     */
    public List<DbLabTdm> selectDbLabTdmList(DbLabTdm dbLabTdm);

    /**
     * 新增血药浓度检测
     * 
     * @param dbLabTdm 血药浓度检测
     * @return 结果
     */
    public int insertDbLabTdm(DbLabTdm dbLabTdm);

    /**
     * 修改血药浓度检测
     * 
     * @param dbLabTdm 血药浓度检测
     * @return 结果
     */
    public int updateDbLabTdm(DbLabTdm dbLabTdm);

    /**
     * 删除血药浓度检测
     * 
     * @param id 血药浓度检测ID
     * @return 结果
     */
    public int deleteDbLabTdmById(Long id);

    /**
     * 批量删除血药浓度检测
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbLabTdmByIds(String[] ids);
}
