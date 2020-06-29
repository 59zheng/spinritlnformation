package com.ruoyi.mind.physical.mapper;

import java.util.List;
import com.ruoyi.mind.physical.domain.DbDescriptionOrHint;

/**
 * 导出word结果描述与提示对应Mapper接口
 * 
 * @author zheng
 * @date 2020-06-29
 */
public interface DbDescriptionOrHintMapper 
{
    /**
     * 查询导出word结果描述与提示对应
     * 
     * @param id 导出word结果描述与提示对应ID
     * @return 导出word结果描述与提示对应
     */
    public DbDescriptionOrHint selectDbDescriptionOrHintById(Long id);

    /**
     * 查询导出word结果描述与提示对应列表
     * 
     * @param dbDescriptionOrHint 导出word结果描述与提示对应
     * @return 导出word结果描述与提示对应集合
     */
    public List<DbDescriptionOrHint> selectDbDescriptionOrHintList(DbDescriptionOrHint dbDescriptionOrHint);

    /**
     * 新增导出word结果描述与提示对应
     * 
     * @param dbDescriptionOrHint 导出word结果描述与提示对应
     * @return 结果
     */
    public int insertDbDescriptionOrHint(DbDescriptionOrHint dbDescriptionOrHint);

    /**
     * 修改导出word结果描述与提示对应
     * 
     * @param dbDescriptionOrHint 导出word结果描述与提示对应
     * @return 结果
     */
    public int updateDbDescriptionOrHint(DbDescriptionOrHint dbDescriptionOrHint);

    /**
     * 删除导出word结果描述与提示对应
     * 
     * @param id 导出word结果描述与提示对应ID
     * @return 结果
     */
    public int deleteDbDescriptionOrHintById(Long id);

    /**
     * 批量删除导出word结果描述与提示对应
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbDescriptionOrHintByIds(String[] ids);
}
