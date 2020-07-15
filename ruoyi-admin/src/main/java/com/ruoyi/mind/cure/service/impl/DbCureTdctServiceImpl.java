package com.ruoyi.mind.cure.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.cure.mapper.DbCureTdctMapper;
import com.ruoyi.mind.cure.domain.DbCureTdct;
import com.ruoyi.mind.cure.service.IDbCureTdctService;
import com.ruoyi.common.core.text.Convert;

/**
 * 经颅直流电治疗Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Service
public class DbCureTdctServiceImpl implements IDbCureTdctService 
{
    @Autowired
    private DbCureTdctMapper dbCureTdctMapper;

    /**
     * 查询经颅直流电治疗
     * 
     * @param id 经颅直流电治疗ID
     * @return 经颅直流电治疗
     */
    @Override
    public DbCureTdct selectDbCureTdctById(Long id)
    {
        return dbCureTdctMapper.selectDbCureTdctById(id);
    }

    /**
     * 查询经颅直流电治疗列表
     * 
     * @param dbCureTdct 经颅直流电治疗
     * @return 经颅直流电治疗
     */
    @Override
    public List<DbCureTdct> selectDbCureTdctList(DbCureTdct dbCureTdct)
    {
        return dbCureTdctMapper.selectDbCureTdctList(dbCureTdct);
    }

    /**
     * 新增经颅直流电治疗
     * 
     * @param dbCureTdct 经颅直流电治疗
     * @return 结果
     */
    @Override
    public int insertDbCureTdct(DbCureTdct dbCureTdct)
    {
        return dbCureTdctMapper.insertDbCureTdct(dbCureTdct);
    }

    /**
     * 修改经颅直流电治疗
     * 
     * @param dbCureTdct 经颅直流电治疗
     * @return 结果
     */
    @Override
    public int updateDbCureTdct(DbCureTdct dbCureTdct)
    {
        return dbCureTdctMapper.updateDbCureTdct(dbCureTdct);
    }

    /**
     * 删除经颅直流电治疗对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbCureTdctByIds(String ids)
    {
        return dbCureTdctMapper.deleteDbCureTdctByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除经颅直流电治疗信息
     * 
     * @param id 经颅直流电治疗ID
     * @return 结果
     */
    @Override
    public int deleteDbCureTdctById(Long id)
    {
        return dbCureTdctMapper.deleteDbCureTdctById(id);
    }

    /**
     * 查询经颅直流电治疗树列表
     * 
     * @return 所有经颅直流电治疗信息
     */
    @Override
    public List<Ztree> selectDbCureTdctTree()
    {
        List<DbCureTdct> dbCureTdctList = dbCureTdctMapper.selectDbCureTdctList(new DbCureTdct());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbCureTdct dbCureTdct : dbCureTdctList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbCureTdct.getId());
            ztree.setpId(dbCureTdct.getFatherId());
            ztree.setName(dbCureTdct.getPatientName());
            ztree.setTitle(dbCureTdct.getPatientName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
