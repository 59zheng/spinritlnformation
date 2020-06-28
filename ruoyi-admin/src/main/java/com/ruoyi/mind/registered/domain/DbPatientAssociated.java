package com.ruoyi.mind.registered.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 病人诊断记录关联检测报告对象 db_patient_associated
 *
 * @author zheng
 * @date 2020-06-23
 */
public class DbPatientAssociated extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 病人_id
     */
    @Excel(name = "病人_id")
    private Long patientId;

    /**
     * 关联相关报告id
     */
    @Excel(name = "关联相关报告id")
    private Long associatedId;

    /**
     * 关联表名称
     */
    @Excel(name = "关联表名称")
    private String associatedTable;

    /**
     * 是否完成
     */
    @Excel(name = "是否完成")
    private String isOk;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setAssociatedId(Long associatedId) {
        this.associatedId = associatedId;
    }

    public Long getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedTable(String associatedTable) {
        this.associatedTable = associatedTable;
    }

    public String getAssociatedTable() {
        return associatedTable;
    }

    public void setIsOk(String isOk) {
        this.isOk = isOk;
    }

    public String getIsOk() {
        return isOk;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("patientId", getPatientId())
                .append("associatedId", getAssociatedId())
                .append("createTime", getCreateTime())
                .append("associatedTable", getAssociatedTable())
                .append("isOk", getIsOk())
                .toString();
    }
}
