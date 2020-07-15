package com.ruoyi.mind.cure.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.cure.mapper.DbCureDbsMapper;
import com.ruoyi.mind.cure.domain.DbCureDbs;
import com.ruoyi.mind.cure.service.IDbCureDbsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 脑深部刺激治疗Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Service
public class DbCureDbsServiceImpl implements IDbCureDbsService 
{
    @Autowired
    private DbCureDbsMapper dbCureDbsMapper;

    /**
     * 查询脑深部刺激治疗
     * 
     * @param id 脑深部刺激治疗ID
     * @return 脑深部刺激治疗
     */
    @Override
    public DbCureDbs selectDbCureDbsById(Long id)
    {
        return dbCureDbsMapper.selectDbCureDbsById(id);
    }

    /**
     * 查询脑深部刺激治疗列表
     * 
     * @param dbCureDbs 脑深部刺激治疗
     * @return 脑深部刺激治疗
     */
    @Override
    public List<DbCureDbs> selectDbCureDbsList(DbCureDbs dbCureDbs)
    {
        return dbCureDbsMapper.selectDbCureDbsList(dbCureDbs);
    }

    /**
     * 新增脑深部刺激治疗
     * 
     * @param dbCureDbs 脑深部刺激治疗
     * @return 结果
     */
    @Override
    public int insertDbCureDbs(DbCureDbs dbCureDbs)
    {
        return dbCureDbsMapper.insertDbCureDbs(dbCureDbs);
    }

    /**
     * 修改脑深部刺激治疗
     * 
     * @param dbCureDbs 脑深部刺激治疗
     * @return 结果
     */
    @Override
    public int updateDbCureDbs(DbCureDbs dbCureDbs)
    {
        return dbCureDbsMapper.updateDbCureDbs(dbCureDbs);
    }

    /**
     * 删除脑深部刺激治疗对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbCureDbsByIds(String ids)
    {
        return dbCureDbsMapper.deleteDbCureDbsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除脑深部刺激治疗信息
     * 
     * @param id 脑深部刺激治疗ID
     * @return 结果
     */
    @Override
    public int deleteDbCureDbsById(Long id)
    {
        return dbCureDbsMapper.deleteDbCureDbsById(id);
    }

    /**
     * 查询脑深部刺激治疗树列表
     * 
     * @return 所有脑深部刺激治疗信息
     */
    @Override
    public List<Ztree> selectDbCureDbsTree()
    {
        List<DbCureDbs> dbCureDbsList = dbCureDbsMapper.selectDbCureDbsList(new DbCureDbs());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbCureDbs dbCureDbs : dbCureDbsList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbCureDbs.getId());
            ztree.setpId(dbCureDbs.getFatherId());
            ztree.setName(dbCureDbs.getPatientName());
            ztree.setTitle(dbCureDbs.getPatientName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
