package com.ruoyi.mind.physical.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 脑电诊断对象 db_report_diagnosis_electrical
 *
 * @author zheng
 * @date 2020-07-02
 */
public class DbReportDiagnosisElectrical extends BaseEntity {
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
     * 脑电图号
     */
    @Excel(name = "脑电图号")
    private String electricalNo;

    /**
     * 左右利手
     */
    @Excel(name = "左右利手")
    private String handedness;

    /**
     * 节律
     */
    @Excel(name = "节律")
    private String rhythmText;

    /**
     * 慢波
     */
    @Excel(name = "慢波")
    private String slowWave;

    /**
     * 快波
     */
    @Excel(name = "快波")
    private String dastWave;

    /**
     * 病理波
     */
    @Excel(name = "病理波")
    private String pathologyWave;

    /**
     * 诱发实验
     */
    @Excel(name = "诱发实验")
    private String inducedExperimental;

    /**
     * 临床发作
     */
    @Excel(name = "临床发作")
    private String clinicalSeizures;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String noteText;

    /**
     * 结果
     */
    @Excel(name = "结果")
    private String resultsText;

    /**
     * 建议
     */
    @Excel(name = "建议")
    private String adviceText;

    /**
     * 是否完成
     */
    @Excel(name = "是否完成")
    private String isSee;

    /**
     * 脑电图片
     */
    @Excel(name = "脑电图片")
    private String electricalPicture;

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

    public void setElectricalNo(String electricalNo) {
        this.electricalNo = electricalNo;
    }

    public String getElectricalNo() {
        return electricalNo;
    }

    public void setHandedness(String handedness) {
        this.handedness = handedness;
    }

    public String getHandedness() {
        return handedness;
    }

    public void setRhythmText(String rhythmText) {
        this.rhythmText = rhythmText;
    }

    public String getRhythmText() {
        return rhythmText;
    }

    public void setSlowWave(String slowWave) {
        this.slowWave = slowWave;
    }

    public String getSlowWave() {
        return slowWave;
    }

    public void setDastWave(String dastWave) {
        this.dastWave = dastWave;
    }

    public String getDastWave() {
        return dastWave;
    }

    public void setPathologyWave(String pathologyWave) {
        this.pathologyWave = pathologyWave;
    }

    public String getPathologyWave() {
        return pathologyWave;
    }

    public void setInducedExperimental(String inducedExperimental) {
        this.inducedExperimental = inducedExperimental;
    }

    public String getInducedExperimental() {
        return inducedExperimental;
    }

    public void setClinicalSeizures(String clinicalSeizures) {
        this.clinicalSeizures = clinicalSeizures;
    }

    public String getClinicalSeizures() {
        return clinicalSeizures;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setResultsText(String resultsText) {
        this.resultsText = resultsText;
    }

    public String getResultsText() {
        return resultsText;
    }

    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }

    public String getAdviceText() {
        return adviceText;
    }

    public void setIsSee(String isSee) {
        this.isSee = isSee;
    }

    public String getIsSee() {
        return isSee;
    }

    public void setElectricalPicture(String electricalPicture) {
        this.electricalPicture = electricalPicture;
    }

    public String getElectricalPicture() {
        return electricalPicture;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("patientId", getPatientId())
                .append("signatureTechnician", getSignatureTechnician())
                .append("signatureDoctor", getSignatureDoctor())
                .append("electricalNo", getElectricalNo())
                .append("handedness", getHandedness())
                .append("rhythmText", getRhythmText())
                .append("slowWave", getSlowWave())
                .append("dastWave", getDastWave())
                .append("pathologyWave", getPathologyWave())
                .append("inducedExperimental", getInducedExperimental())
                .append("clinicalSeizures", getClinicalSeizures())
                .append("noteText", getNoteText())
                .append("resultsText", getResultsText())
                .append("adviceText", getAdviceText())
                .append("isSee", getIsSee())
                .append("electricalPicture", getElectricalPicture())
                .toString();
    }
}