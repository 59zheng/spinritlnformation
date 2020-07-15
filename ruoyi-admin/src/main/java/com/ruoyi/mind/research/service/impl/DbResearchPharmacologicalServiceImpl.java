package com.ruoyi.mind.research.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.research.mapper.DbResearchPharmacologicalMapper;
import com.ruoyi.mind.research.domain.DbResearchPharmacological;
import com.ruoyi.mind.research.service.IDbResearchPharmacologicalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 药理研究Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-09
 */
@Service
public class DbResearchPharmacologicalServiceImpl implements IDbResearchPharmacologicalService 
{
    @Autowired
    private DbResearchPharmacologicalMapper dbResearchPharmacologicalMapper;

    /**
     * 查询药理研究
     * 
     * @param id 药理研究ID
     * @return 药理研究
     */
    @Override
    public DbResearchPharmacological selectDbResearchPharmacologicalById(Long id)
    {
        return dbResearchPharmacologicalMapper.selectDbResearchPharmacologicalById(id);
    }

    /**
     * 查询药理研究列表
     * 
     * @param dbResearchPharmacological 药理研究
     * @return 药理研究
     */
    @Override
    public List<DbResearchPharmacological> selectDbResearchPharmacologicalList(DbResearchPharmacological dbResearchPharmacological)
    {
        return dbResearchPharmacologicalMapper.selectDbResearchPharmacologicalList(dbResearchPharmacological);
    }

    /**
     * 新增药理研究
     * 
     * @param dbResearchPharmacological 药理研究
     * @return 结果
     */
    @Override
    public int insertDbResearchPharmacological(DbResearchPharmacological dbResearchPharmacological)
    {
        return dbResearchPharmacologicalMapper.insertDbResearchPharmacological(dbResearchPharmacological);
    }

    /**
     * 修改药理研究
     * 
     * @param dbResearchPharmacological 药理研究
     * @return 结果
     */
    @Override
    public int updateDbResearchPharmacological(DbResearchPharmacological dbResearchPharmacological)
    {
        return dbResearchPharmacologicalMapper.updateDbResearchPharmacological(dbResearchPharmacological);
    }

    /**
     * 删除药理研究对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbResearchPharmacologicalByIds(String ids)
    {
        return dbResearchPharmacologicalMapper.deleteDbResearchPharmacologicalByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除药理研究信息
     * 
     * @param id 药理研究ID
     * @return 结果
     */
    @Override
    public int deleteDbResearchPharmacologicalById(Long id)
    {
        return dbResearchPharmacologicalMapper.deleteDbResearchPharmacologicalById(id);
    }

    /**
     * 查询药理研究树列表
     * 
     * @return 所有药理研究信息
     */
    @Override
    public List<Ztree> selectDbResearchPharmacologicalTree()
    {
        List<DbResearchPharmacological> dbResearchPharmacologicalList = dbResearchPharmacologicalMapper.selectDbResearchPharmacologicalList(new DbResearchPharmacological());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbResearchPharmacological dbResearchPharmacological : dbResearchPharmacologicalList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbResearchPharmacological.getId());
            ztree.setpId(dbResearchPharmacological.getParentId());
            ztree.setName(dbResearchPharmacological.getGroupName());
            ztree.setTitle(dbResearchPharmacological.getGroupName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
