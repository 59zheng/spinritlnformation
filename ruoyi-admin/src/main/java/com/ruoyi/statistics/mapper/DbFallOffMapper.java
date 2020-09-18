package com.ruoyi.statistics.mapper;

import java.util.List;
import com.ruoyi.statistics.domain.DbFallOff;

/**
 * 脱落列Mapper接口
 * 
 * @author zheng
 * @date 2020-08-11
 */
public interface DbFallOffMapper 
{
    /**
     * 查询脱落列
     * 
     * @param id 脱落列ID
     * @return 脱落列
     */
    public DbFallOff selectDbFallOffById(Long id);

    /**
     * 查询脱落列列表
     * 
     * @param dbFallOff 脱落列
     * @return 脱落列集合
     */
    public List<DbFallOff> selectDbFallOffList(DbFallOff dbFallOff);

    /**
     * 新增脱落列
     * 
     * @param dbFallOff 脱落列
     * @return 结果
     */
    public int insertDbFallOff(DbFallOff dbFallOff);

    /**
     * 修改脱落列
     * 
     * @param dbFallOff 脱落列
     * @return 结果
     */
    public int updateDbFallOff(DbFallOff dbFallOff);

    /**
     * 删除脱落列
     * 
     * @param id 脱落列ID
     * @return 结果
     */
    public int deleteDbFallOffById(Long id);

    /**
     * 批量删除脱落列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbFallOffByIds(String[] ids);
}
