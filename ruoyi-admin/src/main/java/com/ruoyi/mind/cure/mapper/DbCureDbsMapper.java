package com.ruoyi.mind.cure.mapper;

import java.util.List;
import com.ruoyi.mind.cure.domain.DbCureDbs;

/**
 * 脑深部刺激治疗Mapper接口
 * 
 * @author zheng
 * @date 2020-07-13
 */
public interface DbCureDbsMapper 
{
    /**
     * 查询脑深部刺激治疗
     * 
     * @param id 脑深部刺激治疗ID
     * @return 脑深部刺激治疗
     */
    public DbCureDbs selectDbCureDbsById(Long id);

    /**
     * 查询脑深部刺激治疗列表
     * 
     * @param dbCureDbs 脑深部刺激治疗
     * @return 脑深部刺激治疗集合
     */
    public List<DbCureDbs> selectDbCureDbsList(DbCureDbs dbCureDbs);

    /**
     * 新增脑深部刺激治疗
     * 
     * @param dbCureDbs 脑深部刺激治疗
     * @return 结果
     */
    public int insertDbCureDbs(DbCureDbs dbCureDbs);

    /**
     * 修改脑深部刺激治疗
     * 
     * @param dbCureDbs 脑深部刺激治疗
     * @return 结果
     */
    public int updateDbCureDbs(DbCureDbs dbCureDbs);

    /**
     * 删除脑深部刺激治疗
     * 
     * @param id 脑深部刺激治疗ID
     * @return 结果
     */
    public int deleteDbCureDbsById(Long id);

    /**
     * 批量删除脑深部刺激治疗
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbCureDbsByIds(String[] ids);
}
