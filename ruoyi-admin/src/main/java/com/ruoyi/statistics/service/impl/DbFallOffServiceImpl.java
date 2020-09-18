package com.ruoyi.statistics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.statistics.mapper.DbFallOffMapper;
import com.ruoyi.statistics.domain.DbFallOff;
import com.ruoyi.statistics.service.IDbFallOffService;
import com.ruoyi.common.core.text.Convert;

/**
 * 脱落列Service业务层处理
 * 
 * @author zheng
 * @date 2020-08-11
 */
@Service
public class DbFallOffServiceImpl implements IDbFallOffService 
{
    @Autowired
    private DbFallOffMapper dbFallOffMapper;

    /**
     * 查询脱落列
     * 
     * @param id 脱落列ID
     * @return 脱落列
     */
    @Override
    public DbFallOff selectDbFallOffById(Long id)
    {
        return dbFallOffMapper.selectDbFallOffById(id);
    }

    /**
     * 查询脱落列列表
     * 
     * @param dbFallOff 脱落列
     * @return 脱落列
     */
    @Override
    public List<DbFallOff> selectDbFallOffList(DbFallOff dbFallOff)
    {
        return dbFallOffMapper.selectDbFallOffList(dbFallOff);
    }

    /**
     * 新增脱落列
     * 
     * @param dbFallOff 脱落列
     * @return 结果
     */
    @Override
    public int insertDbFallOff(DbFallOff dbFallOff)
    {
        return dbFallOffMapper.insertDbFallOff(dbFallOff);
    }

    /**
     * 修改脱落列
     * 
     * @param dbFallOff 脱落列
     * @return 结果
     */
    @Override
    public int updateDbFallOff(DbFallOff dbFallOff)
    {
        return dbFallOffMapper.updateDbFallOff(dbFallOff);
    }

    /**
     * 删除脱落列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbFallOffByIds(String ids)
    {
        return dbFallOffMapper.deleteDbFallOffByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除脱落列信息
     * 
     * @param id 脱落列ID
     * @return 结果
     */
    @Override
    public int deleteDbFallOffById(Long id)
    {
        return dbFallOffMapper.deleteDbFallOffById(id);
    }
}
