package com.ruoyi.mind.cure.service;

import java.util.List;
import com.ruoyi.mind.cure.domain.DbCureTdct;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 经颅直流电治疗Service接口
 * 
 * @author zheng
 * @date 2020-07-13
 */
public interface IDbCureTdctService 
{
    /**
     * 查询经颅直流电治疗
     * 
     * @param id 经颅直流电治疗ID
     * @return 经颅直流电治疗
     */
    public DbCureTdct selectDbCureTdctById(Long id);

    /**
     * 查询经颅直流电治疗列表
     * 
     * @param dbCureTdct 经颅直流电治疗
     * @return 经颅直流电治疗集合
     */
    public List<DbCureTdct> selectDbCureTdctList(DbCureTdct dbCureTdct);

    /**
     * 新增经颅直流电治疗
     * 
     * @param dbCureTdct 经颅直流电治疗
     * @return 结果
     */
    public int insertDbCureTdct(DbCureTdct dbCureTdct);

    /**
     * 修改经颅直流电治疗
     * 
     * @param dbCureTdct 经颅直流电治疗
     * @return 结果
     */
    public int updateDbCureTdct(DbCureTdct dbCureTdct);

    /**
     * 批量删除经颅直流电治疗
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbCureTdctByIds(String ids);

    /**
     * 删除经颅直流电治疗信息
     * 
     * @param id 经颅直流电治疗ID
     * @return 结果
     */
    public int deleteDbCureTdctById(Long id);

    /**
     * 查询经颅直流电治疗树列表
     * 
     * @return 所有经颅直流电治疗信息
     */
    public List<Ztree> selectDbCureTdctTree();
}
