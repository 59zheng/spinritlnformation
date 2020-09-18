package com.ruoyi.statistics.mapper;

import java.util.List;
import com.ruoyi.statistics.domain.DbImportTemplate;

/**
 * 导入模板Mapper接口
 * 
 * @author zheng
 * @date 2020-08-13
 */
public interface DbImportTemplateMapper 
{
    /**
     * 查询导入模板
     * 
     * @param id 导入模板ID
     * @return 导入模板
     */
    public DbImportTemplate selectDbImportTemplateById(Long id);

    /**
     * 查询导入模板列表
     * 
     * @param dbImportTemplate 导入模板
     * @return 导入模板集合
     */
    public List<DbImportTemplate> selectDbImportTemplateList(DbImportTemplate dbImportTemplate);

    /**
     * 新增导入模板
     * 
     * @param dbImportTemplate 导入模板
     * @return 结果
     */
    public int insertDbImportTemplate(DbImportTemplate dbImportTemplate);

    /**
     * 修改导入模板
     * 
     * @param dbImportTemplate 导入模板
     * @return 结果
     */
    public int updateDbImportTemplate(DbImportTemplate dbImportTemplate);

    /**
     * 删除导入模板
     * 
     * @param id 导入模板ID
     * @return 结果
     */
    public int deleteDbImportTemplateById(Long id);

    /**
     * 批量删除导入模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbImportTemplateByIds(String[] ids);
}
