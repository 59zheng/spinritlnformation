package com.ruoyi.statistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 导入模板对象 db_import_template
 *
 * @author zheng
 * @date 2020-08-13
 */
public class DbImportTemplate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 月份
     */
    @Excel(name = "月份")
    private String performMonth;

    /**
     * 开单科室
     */
    @Excel(name = "开单科室")
    private String openDepartment;

    /**
     * 执行科室
     */
    @Excel(name = "执行科室")
    private String executiveDepartment;

    /**
     * 医生
     */
    @Excel(name = "医生")
    private String doctorName;

    /**
     * 项目分类
     */
    @Excel(name = "项目分类")
    private String projectClassification;

    /**
     * 项目编码
     */
    @Excel(name = "项目编码")
    private String projectCode;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 总数
     */
    @Excel(name = "总数")
    private Long totalNumber;

    /**
     * 总费用
     */
    @Excel(name = "总费用")
    private Double totalCost;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPerformMonth(String performMonth) {
        this.performMonth = performMonth;
    }

    public String getPerformMonth() {
        return performMonth;
    }

    public void setOpenDepartment(String openDepartment) {
        this.openDepartment = openDepartment;
    }

    public String getOpenDepartment() {
        return openDepartment;
    }

    public void setExecutiveDepartment(String executiveDepartment) {
        this.executiveDepartment = executiveDepartment;
    }

    public String getExecutiveDepartment() {
        return executiveDepartment;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setProjectClassification(String projectClassification) {
        this.projectClassification = projectClassification;
    }

    public String getProjectClassification() {
        return projectClassification;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("performMonth", getPerformMonth())
                .append("openDepartment", getOpenDepartment())
                .append("executiveDepartment", getExecutiveDepartment())
                .append("doctorName", getDoctorName())
                .append("projectClassification", getProjectClassification())
                .append("projectCode", getProjectCode())
                .append("projectName", getProjectName())
                .append("totalNumber", getTotalNumber())
                .append("totalCost", getTotalCost())
                .toString();
    }
}
