package com.ruoyi.mind.CorrectionAndTreatment.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.CorrectionAndTreatment.mapper.DbMindCorrectMapper;
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindCorrect;
import com.ruoyi.mind.CorrectionAndTreatment.service.IDbMindCorrectService;
import com.ruoyi.common.core.text.Convert;

/**
 * 心理矫正Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-15
 */
@Service
public class DbMindCorrectServiceImpl implements IDbMindCorrectService 
{
    @Autowired
    private DbMindCorrectMapper dbMindCorrectMapper;

    /**
     * 查询心理矫正
     * 
     * @param id 心理矫正ID
     * @return 心理矫正
     */
    @Override
    public DbMindCorrect selectDbMindCorrectById(Long id)
    {
        return dbMindCorrectMapper.selectDbMindCorrectById(id);
    }

    /**
     * 查询心理矫正列表
     * 
     * @param dbMindCorrect 心理矫正
     * @return 心理矫正
     */
    @Override
    public List<DbMindCorrect> selectDbMindCorrectList(DbMindCorrect dbMindCorrect)
    {
        return dbMindCorrectMapper.selectDbMindCorrectList(dbMindCorrect);
    }

    /**
     * 新增心理矫正
     * 
     * @param dbMindCorrect 心理矫正
     * @return 结果
     */
    @Override
    public int insertDbMindCorrect(DbMindCorrect dbMindCorrect)
    {
        return dbMindCorrectMapper.insertDbMindCorrect(dbMindCorrect);
    }

    /**
     * 修改心理矫正
     * 
     * @param dbMindCorrect 心理矫正
     * @return 结果
     */
    @Override
    public int updateDbMindCorrect(DbMindCorrect dbMindCorrect)
    {
        return dbMindCorrectMapper.updateDbMindCorrect(dbMindCorrect);
    }

    /**
     * 删除心理矫正对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbMindCorrectByIds(String ids)
    {
        return dbMindCorrectMapper.deleteDbMindCorrectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除心理矫正信息
     * 
     * @param id 心理矫正ID
     * @return 结果
     */
    @Override
    public int deleteDbMindCorrectById(Long id)
    {
        return dbMindCorrectMapper.deleteDbMindCorrectById(id);
    }

    /**
     * 查询心理矫正树列表
     * 
     * @return 所有心理矫正信息
     */
    @Override
    public List<Ztree> selectDbMindCorrectTree()
    {
        List<DbMindCorrect> dbMindCorrectList = dbMindCorrectMapper.selectDbMindCorrectList(new DbMindCorrect());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbMindCorrect dbMindCorrect : dbMindCorrectList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbMindCorrect.getId());
            ztree.setpId(dbMindCorrect.getFatherId());
            ztree.setName(dbMindCorrect.getPatientName());
            ztree.setTitle(dbMindCorrect.getPatientName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
