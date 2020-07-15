package com.ruoyi.mind.registered.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 病人诊断记录(一次诊断记录)对象 db_patient_message
 *
 * @author zheng
 * @date 2020-07-08
 */
public class DbPatientMessage extends BaseEntity
{
    private static final long serialVersionUID=1L;

    /** 主键 */
    private Long id;

    /** 病人姓名 */
    @Excel(name = "病人姓名" )
    private String patientName;

    /** 性别 */
    @Excel(name = "性别" )
    private String patientSex;

    /** 年龄 */
    @Excel(name = "年龄" )
    private String patientAge;

    /** 文化程度 */
    @Excel(name = "文化程度" )
    private String patientCulture;

    /** 职业 */
    @Excel(name = "职业" )
    private String patientProfessional;

    /** 婚姻(已婚0,未婚1) */
    @Excel(name = "婚姻(已婚0,未婚1)" )
    private String patientMarriage;

    /** 出生日期 */
    @Excel(name = "出生日期" , width = 30, dateFormat = "yyyy-MM-dd" )
    private Date patientBirth;

    /** 身高 */
    @Excel(name = "身高" )
    private Long patientHeight;

    /** 体重 */
    @Excel(name = "体重" )
    private Long patientWeight;

    /** 住院号 */
    @Excel(name = "住院号" )
    private String hospitalId;

    /** 是否住院(0住院 1未住院) */
    @Excel(name = "是否住院(0住院 1未住院)" )
    private String isHospital;

    /** 门诊号 */
    @Excel(name = "门诊号" )
    private String outpatientId;

    /** 床号 */
    @Excel(name = "床号" )
    private String bedId;

    /** 临床诊断 */
    @Excel(name = "临床诊断" )
    private String diagnosisCli;

    /** 对应医嘱id */
    @Excel(name = "对应医嘱id" )
    private Long entrustId;

    /** 主治医师团队(json集合) */
    @Excel(name = "主治医师团队(json集合)" )
    private String teamAttending;

    /** 手机号码 */
    @Excel(name = "手机号码" )
    private String patientPhone;

    /** 身份证号 */
    @Excel(name = "身份证号" )
    private String patientIdNumber;

    /** 是否问诊 */
    @Excel(name = "是否问诊" )
    private String isInquiry;

    /** 是否添加检测 */
    @Excel(name = "是否添加检测" )
    private String isAddDetection;

    /** 出院时间 */
    @Excel(name = "出院时间" , width = 30, dateFormat = "yyyy-MM-dd" )
    private Date dischargeTime;

    /** 关联档案id */
    @Excel(name = "关联档案id" )
    private Long archivesId;

    /** 主治医生id */
    @Excel(name = "主治医生id" )
    private Long taemId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public String getPatientName()
    {
        return patientName;
    }
    public void setPatientSex(String patientSex)
    {
        this.patientSex = patientSex;
    }

    public String getPatientSex()
    {
        return patientSex;
    }
    public void setPatientAge(String patientAge)
    {
        this.patientAge = patientAge;
    }

    public String getPatientAge()
    {
        return patientAge;
    }
    public void setPatientCulture(String patientCulture)
    {
        this.patientCulture = patientCulture;
    }

    public String getPatientCulture()
    {
        return patientCulture;
    }
    public void setPatientProfessional(String patientProfessional)
    {
        this.patientProfessional = patientProfessional;
    }

    public String getPatientProfessional()
    {
        return patientProfessional;
    }
    public void setPatientMarriage(String patientMarriage)
    {
        this.patientMarriage = patientMarriage;
    }

    public String getPatientMarriage()
    {
        return patientMarriage;
    }
    public void setPatientBirth(Date patientBirth)
    {
        this.patientBirth = patientBirth;
    }

    public Date getPatientBirth()
    {
        return patientBirth;
    }
    public void setPatientHeight(Long patientHeight)
    {
        this.patientHeight = patientHeight;
    }

    public Long getPatientHeight()
    {
        return patientHeight;
    }
    public void setPatientWeight(Long patientWeight)
    {
        this.patientWeight = patientWeight;
    }

    public Long getPatientWeight()
    {
        return patientWeight;
    }
    public void setHospitalId(String hospitalId)
    {
        this.hospitalId = hospitalId;
    }

    public String getHospitalId()
    {
        return hospitalId;
    }
    public void setIsHospital(String isHospital)
    {
        this.isHospital = isHospital;
    }

    public String getIsHospital()
    {
        return isHospital;
    }
    public void setOutpatientId(String outpatientId)
    {
        this.outpatientId = outpatientId;
    }

    public String getOutpatientId()
    {
        return outpatientId;
    }
    public void setBedId(String bedId)
    {
        this.bedId = bedId;
    }

    public String getBedId()
    {
        return bedId;
    }
    public void setDiagnosisCli(String diagnosisCli)
    {
        this.diagnosisCli = diagnosisCli;
    }

    public String getDiagnosisCli()
    {
        return diagnosisCli;
    }
    public void setEntrustId(Long entrustId)
    {
        this.entrustId = entrustId;
    }

    public Long getEntrustId()
    {
        return entrustId;
    }
    public void setTeamAttending(String teamAttending)
    {
        this.teamAttending = teamAttending;
    }

    public String getTeamAttending()
    {
        return teamAttending;
    }
    public void setPatientPhone(String patientPhone)
    {
        this.patientPhone = patientPhone;
    }

    public String getPatientPhone()
    {
        return patientPhone;
    }
    public void setPatientIdNumber(String patientIdNumber)
    {
        this.patientIdNumber = patientIdNumber;
    }

    public String getPatientIdNumber()
    {
        return patientIdNumber;
    }
    public void setIsInquiry(String isInquiry)
    {
        this.isInquiry = isInquiry;
    }

    public String getIsInquiry()
    {
        return isInquiry;
    }
    public void setIsAddDetection(String isAddDetection)
    {
        this.isAddDetection = isAddDetection;
    }

    public String getIsAddDetection()
    {
        return isAddDetection;
    }
    public void setDischargeTime(Date dischargeTime)
    {
        this.dischargeTime = dischargeTime;
    }

    public Date getDischargeTime()
    {
        return dischargeTime;
    }
    public void setArchivesId(Long archivesId)
    {
        this.archivesId = archivesId;
    }

    public Long getArchivesId()
    {
        return archivesId;
    }
    public void setTaemId(Long taemId)
    {
        this.taemId = taemId;
    }

    public Long getTaemId()
    {
        return taemId;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id" ,getId())
                .append("patientName" ,getPatientName())
                .append("patientSex" ,getPatientSex())
                .append("patientAge" ,getPatientAge())
                .append("patientCulture" ,getPatientCulture())
                .append("patientProfessional" ,getPatientProfessional())
                .append("patientMarriage" ,getPatientMarriage())
                .append("createTime" ,getCreateTime())
                .append("patientBirth" ,getPatientBirth())
                .append("patientHeight" ,getPatientHeight())
                .append("patientWeight" ,getPatientWeight())
                .append("hospitalId" ,getHospitalId())
                .append("isHospital" ,getIsHospital())
                .append("outpatientId" ,getOutpatientId())
                .append("bedId" ,getBedId())
                .append("diagnosisCli" ,getDiagnosisCli())
                .append("entrustId" ,getEntrustId())
                .append("teamAttending" ,getTeamAttending())
                .append("patientPhone" ,getPatientPhone())
                .append("patientIdNumber" ,getPatientIdNumber())
                .append("isInquiry" ,getIsInquiry())
                .append("isAddDetection" ,getIsAddDetection())
                .append("dischargeTime" ,getDischargeTime())
                .append("archivesId" ,getArchivesId())
                .append("taemId" ,getTaemId())
                .toString();
    }
}