package com.ruoyi.mind.physical.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 诱发电位报告(物理诊断下边)对象 db_report_diagnosis_induced
 *
 * @author zheng
 * @date 2020-06-10
 */
public class DbReportDiagnosisInduced extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 病人id
     */
    @Excel(name = "病人id")
    private Long patientId;

    /**
     * p300结论
     */
    @Excel(name = "p300结论")
    private String impressionP300;

    /**
     * p50结论
     */
    @Excel(name = "p50结论")
    private String impressionP50;

    /**
     * mmn值
     */
    @Excel(name = "mmn值")
    private String mvn;

    /**
     * cnv结论
     */
    @Excel(name = "cnv结论")
    private String impressionCnv;

    /**
     * mmn结论
     */
    @Excel(name = "mmn结论")
    private String impressionMvn;

    /**
     * p300图片
     */
    @Excel(name = "p300图片")
    private String prictureP300;

    /**
     * cnv图片
     */
    @Excel(name = "cnv图片")
    private String pictureCnv;

    /**
     * p50图片
     */
    @Excel(name = "p50图片")
    private String pictureP50;

    /**
     * mmn图片
     */
    @Excel(name = "mmn图片")
    private String pictureMmn;

    /**
     * 技师签名
     */
    @Excel(name = "技师签名")
    private String signatureTechnician;

    /**
     * 医生签名
     */
    @Excel(name = "医生签名")
    private String signatureDoctor;

    /**
     * n1lat
     */
    @Excel(name = "n1lat")
    private String n1Lat;

    /**
     * n1amp
     */
    @Excel(name = "n1amp")
    private String n1Amp;

    /**
     * p2lat
     */
    @Excel(name = "p2lat")
    private String p2Lat;

    /**
     * p2amp
     */
    @Excel(name = "p2amp")
    private String p2Amp;

    /**
     * n2lat
     */
    @Excel(name = "n2lat")
    private String n2Lat;

    /**
     * n2Amp
     */
    @Excel(name = "n2Amp")
    private String n2Amp;

    /**
     * p3alat
     */
    @Excel(name = "p3alat")
    private String p3aLat;

    /**
     * p3aamp
     */
    @Excel(name = "p3aamp")
    private String p3aAmp;

    /**
     * p3blat
     */
    @Excel(name = "p3blat")
    private String p3bLat;

    /**
     * p3bamp
     */
    @Excel(name = "p3bamp")
    private String p3bAmp;

    /**
     * p50las150
     */
    @Excel(name = "p50las150")
    private String p50LaS150;

    /**
     * p50las250
     */
    @Excel(name = "p50las250")
    private String p50LaS250;

    /**
     * p50ams150
     */
    @Excel(name = "p50ams150")
    private String p50AmS150;

    /**
     * p50ams250
     */
    @Excel(name = "p50ams250")
    private String p50AmS250;

    /**
     * cnvlaa
     */
    @Excel(name = "cnvlaa")
    private String cnvLaA;

    /**
     * cnvlab
     */
    @Excel(name = "cnvlab")
    private String cnvLaB;

    /**
     * cnvlas2
     */
    @Excel(name = "cnvlas2")
    private String cnvLaS2;

    /**
     * cnvlac
     */
    @Excel(name = "cnvlac")
    private String cnvLaC;

    /**
     * cnvlas2c
     */
    @Excel(name = "cnvlas2c")
    private String cnvLaS2C;

    /**
     * cnvamb
     */
    @Excel(name = "cnvamb")
    private String cnvAmB;

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

    public void setImpressionP300(String impressionP300) {
        this.impressionP300 = impressionP300;
    }

    public String getImpressionP300() {
        return impressionP300;
    }

    public void setImpressionP50(String impressionP50) {
        this.impressionP50 = impressionP50;
    }

    public String getImpressionP50() {
        return impressionP50;
    }

    public void setMvn(String mvn) {
        this.mvn = mvn;
    }

    public String getMvn() {
        return mvn;
    }

    public void setImpressionCnv(String impressionCnv) {
        this.impressionCnv = impressionCnv;
    }

    public String getImpressionCnv() {
        return impressionCnv;
    }

    public void setImpressionMvn(String impressionMvn) {
        this.impressionMvn = impressionMvn;
    }

    public String getImpressionMvn() {
        return impressionMvn;
    }

    public void setPrictureP300(String prictureP300) {
        this.prictureP300 = prictureP300;
    }

    public String getPrictureP300() {
        return prictureP300;
    }

    public void setPictureCnv(String pictureCnv) {
        this.pictureCnv = pictureCnv;
    }

    public String getPictureCnv() {
        return pictureCnv;
    }

    public void setPictureP50(String pictureP50) {
        this.pictureP50 = pictureP50;
    }

    public String getPictureP50() {
        return pictureP50;
    }

    public void setPictureMmn(String pictureMmn) {
        this.pictureMmn = pictureMmn;
    }

    public String getPictureMmn() {
        return pictureMmn;
    }

    public void setSignatureTechnician(String signatureTechnician) {
        this.signatureTechnician = signatureTechnician;
    }

    public String getSignatureTechnician() {
        return signatureTechnician;
    }

    public void setSignatureDoctor(String signatureDoctor) {
        this.signatureDoctor = signatureDoctor;
    }

    public String getSignatureDoctor() {
        return signatureDoctor;
    }

    public void setN1Lat(String n1Lat) {
        this.n1Lat = n1Lat;
    }

    public String getN1Lat() {
        return n1Lat;
    }

    public void setN1Amp(String n1Amp) {
        this.n1Amp = n1Amp;
    }

    public String getN1Amp() {
        return n1Amp;
    }

    public void setP2Lat(String p2Lat) {
        this.p2Lat = p2Lat;
    }

    public String getP2Lat() {
        return p2Lat;
    }

    public void setP2Amp(String p2Amp) {
        this.p2Amp = p2Amp;
    }

    public String getP2Amp() {
        return p2Amp;
    }

    public void setN2Lat(String n2Lat) {
        this.n2Lat = n2Lat;
    }

    public String getN2Lat() {
        return n2Lat;
    }

    public void setN2Amp(String n2Amp) {
        this.n2Amp = n2Amp;
    }

    public String getN2Amp() {
        return n2Amp;
    }

    public void setP3aLat(String p3aLat) {
        this.p3aLat = p3aLat;
    }

    public String getP3aLat() {
        return p3aLat;
    }

    public void setP3aAmp(String p3aAmp) {
        this.p3aAmp = p3aAmp;
    }

    public String getP3aAmp() {
        return p3aAmp;
    }

    public void setP3bLat(String p3bLat) {
        this.p3bLat = p3bLat;
    }

    public String getP3bLat() {
        return p3bLat;
    }

    public void setP3bAmp(String p3bAmp) {
        this.p3bAmp = p3bAmp;
    }

    public String getP3bAmp() {
        return p3bAmp;
    }

    public void setP50LaS150(String p50LaS150) {
        this.p50LaS150 = p50LaS150;
    }

    public String getP50LaS150() {
        return p50LaS150;
    }

    public void setP50LaS250(String p50LaS250) {
        this.p50LaS250 = p50LaS250;
    }

    public String getP50LaS250() {
        return p50LaS250;
    }

    public void setP50AmS150(String p50AmS150) {
        this.p50AmS150 = p50AmS150;
    }

    public String getP50AmS150() {
        return p50AmS150;
    }

    public void setP50AmS250(String p50AmS250) {
        this.p50AmS250 = p50AmS250;
    }

    public String getP50AmS250() {
        return p50AmS250;
    }

    public void setCnvLaA(String cnvLaA) {
        this.cnvLaA = cnvLaA;
    }

    public String getCnvLaA() {
        return cnvLaA;
    }

    public void setCnvLaB(String cnvLaB) {
        this.cnvLaB = cnvLaB;
    }

    public String getCnvLaB() {
        return cnvLaB;
    }

    public void setCnvLaS2(String cnvLaS2) {
        this.cnvLaS2 = cnvLaS2;
    }

    public String getCnvLaS2() {
        return cnvLaS2;
    }

    public void setCnvLaC(String cnvLaC) {
        this.cnvLaC = cnvLaC;
    }

    public String getCnvLaC() {
        return cnvLaC;
    }

    public void setCnvLaS2C(String cnvLaS2C) {
        this.cnvLaS2C = cnvLaS2C;
    }

    public String getCnvLaS2C() {
        return cnvLaS2C;
    }

    public void setCnvAmB(String cnvAmB) {
        this.cnvAmB = cnvAmB;
    }

    public String getCnvAmB() {
        return cnvAmB;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("patientId", getPatientId())
                .append("impressionP300", getImpressionP300())
                .append("impressionP50", getImpressionP50())
                .append("mvn", getMvn())
                .append("impressionCnv", getImpressionCnv())
                .append("impressionMvn", getImpressionMvn())
                .append("prictureP300", getPrictureP300())
                .append("pictureCnv", getPictureCnv())
                .append("pictureP50", getPictureP50())
                .append("pictureMmn", getPictureMmn())
                .append("signatureTechnician", getSignatureTechnician())
                .append("signatureDoctor", getSignatureDoctor())
                .append("createTime", getCreateTime())
                .append("n1Lat", getN1Lat())
                .append("n1Amp", getN1Amp())
                .append("p2Lat", getP2Lat())
                .append("p2Amp", getP2Amp())
                .append("n2Lat", getN2Lat())
                .append("n2Amp", getN2Amp())
                .append("p3aLat", getP3aLat())
                .append("p3aAmp", getP3aAmp())
                .append("p3bLat", getP3bLat())
                .append("p3bAmp", getP3bAmp())
                .append("p50LaS150", getP50LaS150())
                .append("p50LaS250", getP50LaS250())
                .append("p50AmS150", getP50AmS150())
                .append("p50AmS250", getP50AmS250())
                .append("cnvLaA", getCnvLaA())
                .append("cnvLaB", getCnvLaB())
                .append("cnvLaS2", getCnvLaS2())
                .append("cnvLaC", getCnvLaC())
                .append("cnvLaS2C", getCnvLaS2C())
                .append("cnvAmB", getCnvAmB())
                .toString();
    }
}