package com.ruoyi.mind.physical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.physical.mapper.DbDescriptionOrHintMapper;
import com.ruoyi.mind.physical.domain.DbDescriptionOrHint;
import com.ruoyi.mind.physical.service.IDbDescriptionOrHintService;
import com.ruoyi.common.core.text.Convert;

/**
 * 导出word结果描述与提示对应Service业务层处理
 * 
 * @author zheng
 * @date 2020-06-29
 */
@Service
public class DbDescriptionOrHintServiceImpl implements IDbDescriptionOrHintService 
{
    @Autowired
    private DbDescriptionOrHintMapper dbDescriptionOrHintMapper;

    /**
     * 查询导出word结果描述与提示对应
     * 
     * @param id 导出word结果描述与提示对应ID
     * @return 导出word结果描述与提示对应
     */
    @Override
    public DbDescriptionOrHint selectDbDescriptionOrHintById(Long id)
    {
        return dbDescriptionOrHintMapper.selectDbDescriptionOrHintById(id);
    }

    /**
     * 查询导出word结果描述与提示对应列表
     * 
     * @param dbDescriptionOrHint 导出word结果描述与提示对应
     * @return 导出word结果描述与提示对应
     */
    @Override
    public List<DbDescriptionOrHint> selectDbDescriptionOrHintList(DbDescriptionOrHint dbDescriptionOrHint)
    {
        return dbDescriptionOrHintMapper.selectDbDescriptionOrHintList(dbDescriptionOrHint);
    }

    /**
     * 新增导出word结果描述与提示对应
     * 
     * @param dbDescriptionOrHint 导出word结果描述与提示对应
     * @return 结果
     */
    @Override
    public int insertDbDescriptionOrHint(DbDescriptionOrHint dbDescriptionOrHint)
    {
        return dbDescriptionOrHintMapper.insertDbDescriptionOrHint(dbDescriptionOrHint);
    }

    /**
     * 修改导出word结果描述与提示对应
     * 
     * @param dbDescriptionOrHint 导出word结果描述与提示对应
     * @return 结果
     */
    @Override
    public int updateDbDescriptionOrHint(DbDescriptionOrHint dbDescriptionOrHint)
    {
        return dbDescriptionOrHintMapper.updateDbDescriptionOrHint(dbDescriptionOrHint);
    }

    /**
     * 删除导出word结果描述与提示对应对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbDescriptionOrHintByIds(String ids)
    {
        return dbDescriptionOrHintMapper.deleteDbDescriptionOrHintByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除导出word结果描述与提示对应信息
     * 
     * @param id 导出word结果描述与提示对应ID
     * @return 结果
     */
    @Override
    public int deleteDbDescriptionOrHintById(Long id)
    {
        return dbDescriptionOrHintMapper.deleteDbDescriptionOrHintById(id);
    }
}
