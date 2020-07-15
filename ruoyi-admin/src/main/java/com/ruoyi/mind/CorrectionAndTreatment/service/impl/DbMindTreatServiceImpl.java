package com.ruoyi.mind.CorrectionAndTreatment.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.CorrectionAndTreatment.mapper.DbMindTreatMapper;
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindTreat;
import com.ruoyi.mind.CorrectionAndTreatment.service.IDbMindTreatService;
import com.ruoyi.common.core.text.Convert;

/**
 * 心理治疗Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-15
 */
@Service
public class DbMindTreatServiceImpl implements IDbMindTreatService 
{
    @Autowired
    private DbMindTreatMapper dbMindTreatMapper;

    /**
     * 查询心理治疗
     * 
     * @param id 心理治疗ID
     * @return 心理治疗
     */
    @Override
    public DbMindTreat selectDbMindTreatById(Long id)
    {
        return dbMindTreatMapper.selectDbMindTreatById(id);
    }

    /**
     * 查询心理治疗列表
     * 
     * @param dbMindTreat 心理治疗
     * @return 心理治疗
     */
    @Override
    public List<DbMindTreat> selectDbMindTreatList(DbMindTreat dbMindTreat)
    {
        return dbMindTreatMapper.selectDbMindTreatList(dbMindTreat);
    }

    /**
     * 新增心理治疗
     * 
     * @param dbMindTreat 心理治疗
     * @return 结果
     */
    @Override
    public int insertDbMindTreat(DbMindTreat dbMindTreat)
    {
        return dbMindTreatMapper.insertDbMindTreat(dbMindTreat);
    }

    /**
     * 修改心理治疗
     * 
     * @param dbMindTreat 心理治疗
     * @return 结果
     */
    @Override
    public int updateDbMindTreat(DbMindTreat dbMindTreat)
    {
        return dbMindTreatMapper.updateDbMindTreat(dbMindTreat);
    }

    /**
     * 删除心理治疗对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbMindTreatByIds(String ids)
    {
        return dbMindTreatMapper.deleteDbMindTreatByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除心理治疗信息
     * 
     * @param id 心理治疗ID
     * @return 结果
     */
    @Override
    public int deleteDbMindTreatById(Long id)
    {
        return dbMindTreatMapper.deleteDbMindTreatById(id);
    }

    /**
     * 查询心理治疗树列表
     * 
     * @return 所有心理治疗信息
     */
    @Override
    public List<Ztree> selectDbMindTreatTree()
    {
        List<DbMindTreat> dbMindTreatList = dbMindTreatMapper.selectDbMindTreatList(new DbMindTreat());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbMindTreat dbMindTreat : dbMindTreatList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbMindTreat.getId());
            ztree.setpId(dbMindTreat.getFatherId());
            ztree.setName(dbMindTreat.getPatientName());
            ztree.setTitle(dbMindTreat.getPatientName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
