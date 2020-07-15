package com.ruoyi.mind.cure.mapper;

import java.util.List;
import com.ruoyi.mind.cure.domain.DbCureTms;

/**
 * 经颅磁刺激治疗Mapper接口
 * 
 * @author zheng
 * @date 2020-07-09
 */
public interface DbCureTmsMapper 
{
    /**
     * 查询经颅磁刺激治疗
     * 
     * @param id 经颅磁刺激治疗ID
     * @return 经颅磁刺激治疗
     */
    public DbCureTms selectDbCureTmsById(Long id);

    /**
     * 查询经颅磁刺激治疗列表
     * 
     * @param dbCureTms 经颅磁刺激治疗
     * @return 经颅磁刺激治疗集合
     */
    public List<DbCureTms> selectDbCureTmsList(DbCureTms dbCureTms);

    /**
     * 新增经颅磁刺激治疗
     * 
     * @param dbCureTms 经颅磁刺激治疗
     * @return 结果
     */
    public int insertDbCureTms(DbCureTms dbCureTms);

    /**
     * 修改经颅磁刺激治疗
     * 
     * @param dbCureTms 经颅磁刺激治疗
     * @return 结果
     */
    public int updateDbCureTms(DbCureTms dbCureTms);

    /**
     * 删除经颅磁刺激治疗
     * 
     * @param id 经颅磁刺激治疗ID
     * @return 结果
     */
    public int deleteDbCureTmsById(Long id);

    /**
     * 批量删除经颅磁刺激治疗
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbCureTmsByIds(String[] ids);
}
