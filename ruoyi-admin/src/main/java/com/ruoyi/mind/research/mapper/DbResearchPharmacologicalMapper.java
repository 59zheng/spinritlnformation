package com.ruoyi.mind.research.mapper;

import java.util.List;
import com.ruoyi.mind.research.domain.DbResearchPharmacological;

/**
 * 药理研究Mapper接口
 * 
 * @author zheng
 * @date 2020-07-09
 */
public interface DbResearchPharmacologicalMapper 
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
     * 删除药理研究
     * 
     * @param id 药理研究ID
     * @return 结果
     */
    public int deleteDbResearchPharmacologicalById(Long id);

    /**
     * 批量删除药理研究
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbResearchPharmacologicalByIds(String[] ids);
}
