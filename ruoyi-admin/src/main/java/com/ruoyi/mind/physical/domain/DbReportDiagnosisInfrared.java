package com.ruoyi.mind.physical.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 红外热成像及血流图检查对象 db_report_diagnosis_infrared
 *
 * @author zheng
 * @date 2020-07-20
 */
public class DbReportDiagnosisInfrared extends BaseEntity
{
    private static final long serialVersionUID=1L;

    /** 主键 */
    private Long id;

    /** 病人id */
    @Excel(name = "病人id" )
    private Long patientId;

    /** Integral值 */
    @Excel(name = "Integral值" )
    private String integralText;

    /** Centroid值 */
    @Excel(name = "Centroid值" )
    private String centroid;

    /** Slope值 */
    @Excel(name = "Slope值" )
    private String slope;

    /** 波普描述 */
    @Excel(name = "波普描述" )
    private String describeText;

    /** 诊断提示 */
    @Excel(name = "诊断提示" )
    private String hint;

    /** 技师签名 */
    @Excel(name = "技师签名" )
    private String signatureTechnician;

    /** 图片地址 */
    @Excel(name = "图片地址" )
    private String pictureCnv;

    /** 是否检测完成 */
    @Excel(name = "是否检测完成" )
    private String isSee;

    @Override
    public String toString() {
        return "DbReportDiagnosisInfrared{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", integralText='" + integralText + '\'' +
                ", centroid='" + centroid + '\'' +
                ", slope='" + slope + '\'' +
                ", describeText='" + describeText + '\'' +
                ", hint='" + hint + '\'' +
                ", signatureTechnician='" + signatureTechnician + '\'' +
                ", pictureCnv='" + pictureCnv + '\'' +
                ", isSee='" + isSee + '\'' +
                ", documentAddress='" + documentAddress + '\'' +
                '}';
    }

    public DbReportDiagnosisInfrared(Long id, Long patientId, String integralText, String centroid, String slope, String describeText, String hint, String signatureTechnician, String pictureCnv, String isSee, String documentAddress) {
        this.id = id;
        this.patientId = patientId;
        this.integralText = integralText;
        this.centroid = centroid;
        this.slope = slope;
        this.describeText = describeText;
        this.hint = hint;
        this.signatureTechnician = signatureTechnician;
        this.pictureCnv = pictureCnv;
        this.isSee = isSee;
        this.documentAddress = documentAddress;
    }

    /** 文档地址 */
    @Excel(name = "文档地址" )
    private String documentAddress;

    public DbReportDiagnosisInfrared() {
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getIntegralText() {
        return integralText;
    }

    public void setIntegralText(String integralText) {
        this.integralText = integralText;
    }

    public String getCentroid() {
        return centroid;
    }

    public void setCentroid(String centroid) {
        this.centroid = centroid;
    }

    public String getSlope() {
        return slope;
    }

    public void setSlope(String slope) {
        this.slope = slope;
    }

    public String getDescribeText() {
        return describeText;
    }

    public void setDescribeText(String describeText) {
        this.describeText = describeText;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSignatureTechnician() {
        return signatureTechnician;
    }

    public void setSignatureTechnician(String signatureTechnician) {
        this.signatureTechnician = signatureTechnician;
    }

    public String getPictureCnv() {
        return pictureCnv;
    }

    public void setPictureCnv(String pictureCnv) {
        this.pictureCnv = pictureCnv;
    }

    public String getIsSee() {
        return isSee;
    }

    public void setIsSee(String isSee) {
        this.isSee = isSee;
    }

    public String getDocumentAddress() {
        return documentAddress;
    }

    public void setDocumentAddress(String documentAddress) {
        this.documentAddress = documentAddress;
    }
}