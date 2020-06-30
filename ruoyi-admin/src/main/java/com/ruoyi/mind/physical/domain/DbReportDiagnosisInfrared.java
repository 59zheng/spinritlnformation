package com.ruoyi.mind.physical.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 红外热成像及血流图检查对象 db_report_diagnosis_infrared
 *
 * @author zheng
 * @date 2020-06-29
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
    private String integral;

    /** Centroid值 */
    @Excel(name = "Centroid值" )
    private String centroid;

    /** Slope值 */
    @Excel(name = "Slope值" )
    private String slope;

    /** 波普描述 */
    @Excel(name = "波普描述" )
    private String describe;

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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPatientId(Long patientId)
    {
        this.patientId = patientId;
    }

    public Long getPatientId()
    {
        return patientId;
    }
    public void setIntegral(String integral)
    {
        this.integral = integral;
    }

    public String getIntegral()
    {
        return integral;
    }
    public void setCentroid(String centroid)
    {
        this.centroid = centroid;
    }

    public String getCentroid()
    {
        return centroid;
    }
    public void setSlope(String slope)
    {
        this.slope = slope;
    }

    public String getSlope()
    {
        return slope;
    }
    public void setDescribe(String describe)
    {
        this.describe = describe;
    }

    public String getDescribe()
    {
        return describe;
    }
    public void setHint(String hint)
    {
        this.hint = hint;
    }

    public String getHint()
    {
        return hint;
    }
    public void setSignatureTechnician(String signatureTechnician)
    {
        this.signatureTechnician = signatureTechnician;
    }

    public String getSignatureTechnician()
    {
        return signatureTechnician;
    }
    public void setPictureCnv(String pictureCnv)
    {
        this.pictureCnv = pictureCnv;
    }

    public String getPictureCnv()
    {
        return pictureCnv;
    }
    public void setIsSee(String isSee)
    {
        this.isSee = isSee;
    }

    public String getIsSee()
    {
        return isSee;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id" ,getId())
                .append("patientId" ,getPatientId())
                .append("integral" ,getIntegral())
                .append("centroid" ,getCentroid())
                .append("slope" ,getSlope())
                .append("describe" ,getDescribe())
                .append("hint" ,getHint())
                .append("signatureTechnician" ,getSignatureTechnician())
                .append("pictureCnv" ,getPictureCnv())
                .append("isSee" ,getIsSee())
                .toString();
    }
}