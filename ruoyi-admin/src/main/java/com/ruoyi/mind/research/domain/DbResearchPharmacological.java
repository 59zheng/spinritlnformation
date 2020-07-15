package com.ruoyi.mind.research.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 药理研究对象 db_research_pharmacological
 *
 * @author zheng
 * @date 2020-07-09
 */
public class DbResearchPharmacological extends TreeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 加入时间
     */
    @Excel(name = "加入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinTime;

    /**
     * 主治医生
     */
    @Excel(name = "主治医生")
    private String attendingPhysician;

    /**
     * 临床诊断
     */
    @Excel(name = "临床诊断")
    private String diagnosisCli;

    /**
     * 诊断记录id
     */
    @Excel(name = "诊断记录id")
    private Long patientId;

    /**
     * 组名
     */
    @Excel(name = "组名")
    private String groupName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setAttendingPhysician(String attendingPhysician) {
        this.attendingPhysician = attendingPhysician;
    }

    public String getAttendingPhysician() {
        return attendingPhysician;
    }

    public void setDiagnosisCli(String diagnosisCli) {
        this.diagnosisCli = diagnosisCli;
    }

    public String getDiagnosisCli() {
        return diagnosisCli;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("joinTime", getJoinTime())
                .append("attendingPhysician", getAttendingPhysician())
                .append("diagnosisCli", getDiagnosisCli())
                .append("patientId", getPatientId())
                .append("groupName", getGroupName())
                .append("parentId", getParentId())
                .toString();
    }
}
