package com.ruoyi.mind.diagnosis.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.diagnosis.mapper.DbDiagonsisProjectMapper;
import com.ruoyi.mind.diagnosis.domain.DbDiagonsisProject;
import com.ruoyi.mind.diagnosis.service.IDbDiagonsisProjectService;
import com.ruoyi.common.core.text.Convert;

/**
 * 诊断项目Service业务层处理
 * 
 * @author 正
 * @date 2020-06-22
 */
@Service
public class DbDiagonsisProjectServiceImpl implements IDbDiagonsisProjectService 
{
    @Autowired
    private DbDiagonsisProjectMapper dbDiagonsisProjectMapper;

    /**
     * 查询诊断项目
     * 
     * @param id 诊断项目ID
     * @return 诊断项目
     */
    @Override
    public DbDiagonsisProject selectDbDiagonsisProjectById(Long id)
    {
        return dbDiagonsisProjectMapper.selectDbDiagonsisProjectById(id);
    }

    /**
     * 查询诊断项目列表
     * 
     * @param dbDiagonsisProject 诊断项目
     * @return 诊断项目
     */
    @Override
    public List<DbDiagonsisProject> selectDbDiagonsisProjectList(DbDiagonsisProject dbDiagonsisProject)
    {
        return dbDiagonsisProjectMapper.selectDbDiagonsisProjectList(dbDiagonsisProject);
    }

    /**
     * 新增诊断项目
     * 
     * @param dbDiagonsisProject 诊断项目
     * @return 结果
     */
    @Override
    public int insertDbDiagonsisProject(DbDiagonsisProject dbDiagonsisProject)
    {
        return dbDiagonsisProjectMapper.insertDbDiagonsisProject(dbDiagonsisProject);
    }

    /**
     * 修改诊断项目
     * 
     * @param dbDiagonsisProject 诊断项目
     * @return 结果
     */
    @Override
    public int updateDbDiagonsisProject(DbDiagonsisProject dbDiagonsisProject)
    {
        return dbDiagonsisProjectMapper.updateDbDiagonsisProject(dbDiagonsisProject);
    }

    /**
     * 删除诊断项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbDiagonsisProjectByIds(String ids)
    {
        return dbDiagonsisProjectMapper.deleteDbDiagonsisProjectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除诊断项目信息
     * 
     * @param id 诊断项目ID
     * @return 结果
     */
    @Override
    public int deleteDbDiagonsisProjectById(Long id)
    {
        return dbDiagonsisProjectMapper.deleteDbDiagonsisProjectById(id);
    }

    /**
     * 查询诊断项目树列表
     * 
     * @return 所有诊断项目信息
     */
    @Override
    public List<Ztree> selectDbDiagonsisProjectTree()
    {
        List<DbDiagonsisProject> dbDiagonsisProjectList = dbDiagonsisProjectMapper.selectDbDiagonsisProjectList(new DbDiagonsisProject());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (DbDiagonsisProject dbDiagonsisProject : dbDiagonsisProjectList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(dbDiagonsisProject.getId());
            ztree.setpId(dbDiagonsisProject.getProductId());
            ztree.setName(dbDiagonsisProject.getName());
            ztree.setTitle(dbDiagonsisProject.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
