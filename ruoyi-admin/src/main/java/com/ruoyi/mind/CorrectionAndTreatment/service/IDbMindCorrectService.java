package com.ruoyi.mind.CorrectionAndTreatment.service;

import java.util.List;
import com.ruoyi.mind.CorrectionAndTreatment.domain.DbMindCorrect;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 心理矫正Service接口
 * 
 * @author zheng
 * @date 2020-07-15
 */
public interface IDbMindCorrectService 
{
    /**
     * 查询心理矫正
     * 
     * @param id 心理矫正ID
     * @return 心理矫正
     */
    public DbMindCorrect selectDbMindCorrectById(Long id);

    /**
     * 查询心理矫正列表
     * 
     * @param dbMindCorrect 心理矫正
     * @return 心理矫正集合
     */
    public List<DbMindCorrect> selectDbMindCorrectList(DbMindCorrect dbMindCorrect);

    /**
     * 新增心理矫正
     * 
     * @param dbMindCorrect 心理矫正
     * @return 结果
     */
    public int insertDbMindCorrect(DbMindCorrect dbMindCorrect);

    /**
     * 修改心理矫正
     * 
     * @param dbMindCorrect 心理矫正
     * @return 结果
     */
    public int updateDbMindCorrect(DbMindCorrect dbMindCorrect);

    /**
     * 批量删除心理矫正
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbMindCorrectByIds(String ids);

    /**
     * 删除心理矫正信息
     * 
     * @param id 心理矫正ID
     * @return 结果
     */
    public int deleteDbMindCorrectById(Long id);

    /**
     * 查询心理矫正树列表
     * 
     * @return 所有心理矫正信息
     */
    public List<Ztree> selectDbMindCorrectTree();
}
