package com.ruoyi.mind.diagnosis.service;

import java.util.List;
import com.ruoyi.mind.diagnosis.domain.DbDiagonsisProject;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 诊断项目Service接口
 * 
 * @author 正
 * @date 2020-06-22
 */
public interface IDbDiagonsisProjectService 
{
    /**
     * 查询诊断项目
     * 
     * @param id 诊断项目ID
     * @return 诊断项目
     */
    public DbDiagonsisProject selectDbDiagonsisProjectById(Long id);

    /**
     * 查询诊断项目列表
     * 
     * @param dbDiagonsisProject 诊断项目
     * @return 诊断项目集合
     */
    public List<DbDiagonsisProject> selectDbDiagonsisProjectList(DbDiagonsisProject dbDiagonsisProject);

    /**
     * 新增诊断项目
     * 
     * @param dbDiagonsisProject 诊断项目
     * @return 结果
     */
    public int insertDbDiagonsisProject(DbDiagonsisProject dbDiagonsisProject);

    /**
     * 修改诊断项目
     * 
     * @param dbDiagonsisProject 诊断项目
     * @return 结果
     */
    public int updateDbDiagonsisProject(DbDiagonsisProject dbDiagonsisProject);

    /**
     * 批量删除诊断项目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbDiagonsisProjectByIds(String ids);

    /**
     * 删除诊断项目信息
     * 
     * @param id 诊断项目ID
     * @return 结果
     */
    public int deleteDbDiagonsisProjectById(Long id);

    /**
     * 查询诊断项目树列表
     * 
     * @return 所有诊断项目信息
     */
    public List<Ztree> selectDbDiagonsisProjectTree();
}
