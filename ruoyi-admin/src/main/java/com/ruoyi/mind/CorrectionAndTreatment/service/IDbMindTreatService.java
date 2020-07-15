package com.ruoyi.mind.CorrectionAndTreatment.service;

import java.util.List;
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindTreat;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 心理治疗Service接口
 * 
 * @author zheng
 * @date 2020-07-15
 */
public interface IDbMindTreatService 
{
    /**
     * 查询心理治疗
     * 
     * @param id 心理治疗ID
     * @return 心理治疗
     */
    public DbMindTreat selectDbMindTreatById(Long id);

    /**
     * 查询心理治疗列表
     * 
     * @param dbMindTreat 心理治疗
     * @return 心理治疗集合
     */
    public List<DbMindTreat> selectDbMindTreatList(DbMindTreat dbMindTreat);

    /**
     * 新增心理治疗
     * 
     * @param dbMindTreat 心理治疗
     * @return 结果
     */
    public int insertDbMindTreat(DbMindTreat dbMindTreat);

    /**
     * 修改心理治疗
     * 
     * @param dbMindTreat 心理治疗
     * @return 结果
     */
    public int updateDbMindTreat(DbMindTreat dbMindTreat);

    /**
     * 批量删除心理治疗
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbMindTreatByIds(String ids);

    /**
     * 删除心理治疗信息
     * 
     * @param id 心理治疗ID
     * @return 结果
     */
    public int deleteDbMindTreatById(Long id);

    /**
     * 查询心理治疗树列表
     * 
     * @return 所有心理治疗信息
     */
    public List<Ztree> selectDbMindTreatTree();
}
