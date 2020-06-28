package com.ruoyi.mind.registered.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医嘱(关联诊断记录 ,关联主治医师)对象 db_patient_entrust
 *
 * @author ruoyi
 * @date 2020-06-19
 */
public class DbPatientEntrust extends BaseEntity
{
    private static final long serialVersionUID=1L;

    /** 主键 */
    private Long id;

    /** 诊断记录表id */
    @Excel(name = "诊断记录表id" )
    private Long patientId;

    /** 主治医师id */
    @Excel(name = "主治医师id" )
    private Long userId;

    /** 医嘱内容 */
    @Excel(name = "医嘱内容" )
    private String entrustContent;

    /** 另行通知医师id(可以指派多人) */
    @Excel(name = "另行通知医师id(可以指派多人)" )
    private String sendId;

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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setEntrustContent(String entrustContent)
    {
        this.entrustContent = entrustContent;
    }

    public String getEntrustContent()
    {
        return entrustContent;
    }
    public void setSendId(String sendId)
    {
        this.sendId = sendId;
    }

    public String getSendId()
    {
        return sendId;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id" ,getId())
                .append("patientId" ,getPatientId())
                .append("userId" ,getUserId())
                .append("entrustContent" ,getEntrustContent())
                .append("sendId" ,getSendId())
                .toString();
    }
}