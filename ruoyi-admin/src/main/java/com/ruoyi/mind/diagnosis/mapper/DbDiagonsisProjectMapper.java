package com.ruoyi.mind.diagnosis.mapper;

import java.util.List;
import com.ruoyi.mind.diagnosis.domain.DbDiagonsisProject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * 诊断项目Mapper接口
 * 
 * @author 正
 * @date 2020-06-22
 */
public interface DbDiagonsisProjectMapper 
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
     * 删除诊断项目
     * 
     * @param id 诊断项目ID
     * @return 结果
     */
    public int deleteDbDiagonsisProjectById(Long id);

    /**
     * 批量删除诊断项目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbDiagonsisProjectByIds(String[] ids);


    @Select("select r.document_address from ${tableName} r where r.id=#{associatedId}")
    String selectByTableName(@Param("tableName") String tableName,@Param("associatedId") Long associatedId);
}
