package com.ruoyi.mind.research.service;

import java.util.List;
import com.ruoyi.mind.research.domain.DbResearchPharmacological;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 药理研究Service接口
 * 
 * @author zheng
 * @date 2020-07-09
 */
public interface IDbResearchPharmacologicalService 
{
    /**
     * 查询药理研究
     * 
     * @param id 药理研究ID
     * @return 药理研究
     */
    public DbResearchPharmacological selectDbResearchPharmacologicalById(Long id);

    /**
     * 查询药理研究列表
     * 
     * @param dbResearchPharmacological 药理研究
     * @return 药理研究集合
     */
    public List<DbResearchPharmacological> selectDbResearchPharmacologicalList(DbResearchPharmacological dbResearchPharmacological);

    /**
     * 新增药理研究
     * 
     * @param dbResearchPharmacological 药理研究
     * @return 结果
     */
    public int insertDbResearchPharmacological(DbResearchPharmacological dbResearchPharmacological);

    /**
     * 修改药理研究
     * 
     * @param dbResearchPharmacological 药理研究
     * @return 结果
     */
    public int updateDbResearchPharmacological(DbResearchPharmacological dbResearchPharmacological);

    /**
     * 批量删除药理研究
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbResearchPharmacologicalByIds(String ids);

    /**
     * 删除药理研究信息
     * 
     * @param id 药理研究ID
     * @return 结果
     */
    public int deleteDbResearchPharmacologicalById(Long id);

    /**
     * 查询药理研究树列表
     * 
     * @return 所有药理研究信息
     */
    public List<Ztree> selectDbResearchPharmacologicalTree();
}
