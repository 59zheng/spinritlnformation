package com.ruoyi.statistics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.statistics.mapper.DbImportTemplateMapper;
import com.ruoyi.statistics.domain.DbImportTemplate;
import com.ruoyi.statistics.service.IDbImportTemplateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 导入模板Service业务层处理
 * 
 * @author zheng
 * @date 2020-08-13
 */
@Service
public class DbImportTemplateServiceImpl implements IDbImportTemplateService 
{
    @Autowired
    private DbImportTemplateMapper dbImportTemplateMapper;

    /**
     * 查询导入模板
     * 
     * @param id 导入模板ID
     * @return 导入模板
     */
    @Override
    public DbImportTemplate selectDbImportTemplateById(Long id)
    {
        return dbImportTemplateMapper.selectDbImportTemplateById(id);
    }

    /**
     * 查询导入模板列表
     * 
     * @param dbImportTemplate 导入模板
     * @return 导入模板
     */
    @Override
    public List<DbImportTemplate> selectDbImportTemplateList(DbImportTemplate dbImportTemplate)
    {
        return dbImportTemplateMapper.selectDbImportTemplateList(dbImportTemplate);
    }

    /**
     * 新增导入模板
     * 
     * @param dbImportTemplate 导入模板
     * @return 结果
     */
    @Override
    public int insertDbImportTemplate(DbImportTemplate dbImportTemplate)
    {
        return dbImportTemplateMapper.insertDbImportTemplate(dbImportTemplate);
    }

    /**
     * 修改导入模板
     * 
     * @param dbImportTemplate 导入模板
     * @return 结果
     */
    @Override
    public int updateDbImportTemplate(DbImportTemplate dbImportTemplate)
    {
        return dbImportTemplateMapper.updateDbImportTemplate(dbImportTemplate);
    }

    /**
     * 删除导入模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbImportTemplateByIds(String ids)
    {
        return dbImportTemplateMapper.deleteDbImportTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除导入模板信息
     * 
     * @param id 导入模板ID
     * @return 结果
     */
    @Override
    public int deleteDbImportTemplateById(Long id)
    {
        return dbImportTemplateMapper.deleteDbImportTemplateById(id);
    }
}
