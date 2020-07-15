package com.ruoyi.mind.CorrectionAndTreatment.mapper;

import java.util.List;
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindTreat;

/**
 * 心理治疗Mapper接口
 * 
 * @author zheng
 * @date 2020-07-15
 */
public interface DbMindTreatMapper 
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
     * 删除心理治疗
     * 
     * @param id 心理治疗ID
     * @return 结果
     */
    public int deleteDbMindTreatById(Long id);

    /**
     * 批量删除心理治疗
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbMindTreatByIds(String[] ids);
}
