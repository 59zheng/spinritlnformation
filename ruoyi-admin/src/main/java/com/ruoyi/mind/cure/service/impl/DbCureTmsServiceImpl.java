package com.ruoyi.mind.cure.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.cure.mapper.DbCureTmsMapper;
import com.ruoyi.mind.cure.domain.DbCureTms;
import com.ruoyi.mind.cure.service.IDbCureTmsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 经颅磁刺激治疗Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-09
 */
@Service
public class DbCureTmsServiceImpl implements IDbCureTmsService 
{
    @Autowired
    private DbCureTmsMapper dbCureTmsMapper;

    /**
     * 查询经颅磁刺激治疗
     * 
     * @param id 经颅磁刺激治疗ID
     * @return 经颅磁刺激治疗
     */
    @Override
    public DbCureTms selectDbCureTmsById(Long id)
    {
        return dbCureTmsMapper.selectDbCureTmsById(id);
    }

    /**
     * 查询经颅磁刺激治疗列表
     * 
     * @param dbCureTms 经颅磁刺激治疗
     * @return 经颅磁刺激治疗
     */
    @Override
    public List<DbCureTms> selectDbCureTmsList(DbCureTms dbCureTms)
    {
        return dbCureTmsMapper.selectDbCureTmsList(dbCureTms);
    }

    /**
     * 新增经颅磁刺激治疗
     * 
     * @param dbCureTms 经颅磁刺激治疗
     * @return 结果
     */
    @Override
    public int insertDbCureTms(DbCureTms dbCureTms)
    {
        return dbCureTmsMapper.insertDbCureTms(dbCureTms);
    }

    /**
     * 修改经颅磁刺激治疗
     * 
     * @param dbCureTms 经颅磁刺激治疗
     * @return 结果
     */
    @Override
    public int updateDbCureTms(DbCureTms dbCureTms)
    {
        return dbCureTmsMapper.updateDbCureTms(dbCureTms);
    }

    /**
     * 删除经颅磁刺激治疗对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbCureTmsByIds(String ids)
    {
        return dbCureTmsMapper.deleteDbCureTmsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除经颅磁刺激治疗信息
     * 
     * @param id 经颅磁刺激治疗ID
     * @return 结果
     */
    @Override
    public int deleteDbCureTmsById(Long id)
    {
        return dbCureTmsMapper.deleteDbCureTmsById(id);
    }

    /**
     * 查询经颅磁刺激治疗树列表
     * 
     * @return 所有经颅磁刺激治疗信息
     */
    @Override
    public List<Ztree> selectDbCureTmsTree()
    {
        List<DbCureTms> dbCureTmsList = dbCureTmsMapper.selectDbCureTmsList(new DbCureTms());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbCureTms dbCureTms : dbCureTmsList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbCureTms.getId());
            ztree.setpId(dbCureTms.getFatherId());
            ztree.setName(dbCureTms.getPatientName());
            ztree.setTitle(dbCureTms.getPatientName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
