package com.ruoyi.mind.cure.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.cure.mapper.DbCureEctMapper;
import com.ruoyi.mind.cure.domain.DbCureEct;
import com.ruoyi.mind.cure.service.IDbCureEctService;
import com.ruoyi.common.core.text.Convert;

/**
 * 电休克治疗Service业务层处理
 * 
 * @author zheng
 * @date 2020-07-13
 */
@Service
public class DbCureEctServiceImpl implements IDbCureEctService 
{
    @Autowired
    private DbCureEctMapper dbCureEctMapper;

    /**
     * 查询电休克治疗
     * 
     * @param id 电休克治疗ID
     * @return 电休克治疗
     */
    @Override
    public DbCureEct selectDbCureEctById(Long id)
    {
        return dbCureEctMapper.selectDbCureEctById(id);
    }

    /**
     * 查询电休克治疗列表
     * 
     * @param dbCureEct 电休克治疗
     * @return 电休克治疗
     */
    @Override
    public List<DbCureEct> selectDbCureEctList(DbCureEct dbCureEct)
    {
        return dbCureEctMapper.selectDbCureEctList(dbCureEct);
    }

    /**
     * 新增电休克治疗
     * 
     * @param dbCureEct 电休克治疗
     * @return 结果
     */
    @Override
    public int insertDbCureEct(DbCureEct dbCureEct)
    {
        return dbCureEctMapper.insertDbCureEct(dbCureEct);
    }

    /**
     * 修改电休克治疗
     * 
     * @param dbCureEct 电休克治疗
     * @return 结果
     */
    @Override
    public int updateDbCureEct(DbCureEct dbCureEct)
    {
        return dbCureEctMapper.updateDbCureEct(dbCureEct);
    }

    /**
     * 删除电休克治疗对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbCureEctByIds(String ids)
    {
        return dbCureEctMapper.deleteDbCureEctByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除电休克治疗信息
     * 
     * @param id 电休克治疗ID
     * @return 结果
     */
    @Override
    public int deleteDbCureEctById(Long id)
    {
        return dbCureEctMapper.deleteDbCureEctById(id);
    }

    /**
     * 查询电休克治疗树列表
     * 
     * @return 所有电休克治疗信息
     */
    @Override
    public List<Ztree> selectDbCureEctTree()
    {
        List<DbCureEct> dbCureEctList = dbCureEctMapper.selectDbCureEctList(new DbCureEct());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbCureEct dbCureEct : dbCureEctList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbCureEct.getId());
            ztree.setpId(dbCureEct.getFatherId());
            ztree.setName(dbCureEct.getPatientName());
            ztree.setTitle(dbCureEct.getPatientName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
