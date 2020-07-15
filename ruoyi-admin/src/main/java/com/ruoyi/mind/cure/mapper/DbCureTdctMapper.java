package com.ruoyi.mind.cure.mapper;

import java.util.List;
import com.ruoyi.mind.cure.domain.DbCureTdct;

/**
 * 经颅直流电治疗Mapper接口
 * 
 * @author zheng
 * @date 2020-07-13
 */
public interface DbCureTdctMapper 
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
     * 删除经颅直流电治疗
     * 
     * @param id 经颅直流电治疗ID
     * @return 结果
     */
    public int deleteDbCureTdctById(Long id);

    /**
     * 批量删除经颅直流电治疗
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbCureTdctByIds(String[] ids);
}
