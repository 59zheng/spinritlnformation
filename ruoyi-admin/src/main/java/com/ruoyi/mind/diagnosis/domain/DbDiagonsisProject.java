package com.ruoyi.mind.diagnosis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 诊断项目对象 db_diagonsis_project
 *
 * @author zheng
 * @date 2020-07-20
 */
public class DbDiagonsisProject extends BaseEntity
{
    private static final long serialVersionUID=1L;

    /** 主键 */
    private Long id;

    /** 诊断项目名称 */
    @Excel(name = "诊断项目名称" )
    private String name;

    /** 父级id */
    @Excel(name = "父级id" )
    private Long productId;

    /** 代号 */
    @Excel(name = "代号" )
    private String codeName;

    /** 表名称 */
    @Excel(name = "表名称" )
    private String tableName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setCodeName(String codeName)
    {
        this.codeName = codeName;
    }

    public String getCodeName()
    {
        return codeName;
    }
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getTableName()
    {
        return tableName;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id" ,getId())
                .append("name" ,getName())
                .append("productId" ,getProductId())
                .append("codeName" ,getCodeName())
                .append("tableName" ,getTableName())
                .toString();
    }
}