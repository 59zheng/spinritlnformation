package com.ruoyi.mind.cure.mapper;

import java.util.List;
import com.ruoyi.mind.cure.domain.DbCureEct;

/**
 * 电休克治疗Mapper接口
 * 
 * @author zheng
 * @date 2020-07-13
 */
public interface DbCureEctMapper 
{
    /**
     * 查询电休克治疗
     * 
     * @param id 电休克治疗ID
     * @return 电休克治疗
     */
    public DbCureEct selectDbCureEctById(Long id);

    /**
     * 查询电休克治疗列表
     * 
     * @param dbCureEct 电休克治疗
     * @return 电休克治疗集合
     */
    public List<DbCureEct> selectDbCureEctList(DbCureEct dbCureEct);

    /**
     * 新增电休克治疗
     * 
     * @param dbCureEct 电休克治疗
     * @return 结果
     */
    public int insertDbCureEct(DbCureEct dbCureEct);

    /**
     * 修改电休克治疗
     * 
     * @param dbCureEct 电休克治疗
     * @return 结果
     */
    public int updateDbCureEct(DbCureEct dbCureEct);

    /**
     * 删除电休克治疗
     * 
     * @param id 电休克治疗ID
     * @return 结果
     */
    public int deleteDbCureEctById(Long id);

    /**
     * 批量删除电休克治疗
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbCureEctByIds(String[] ids);
}
