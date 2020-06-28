package com.ruoyi.mind.registered.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class DbPatientMessageVo extends BaseEntity {


    private  DbPatientMessage dbPatientMessage;

    public DbPatientMessageVo() {
    }

    public DbPatientMessageVo(DbPatientMessage dbPatientMessage, Long associatedId) {

        this.dbPatientMessage = dbPatientMessage;
        this.associatedId = associatedId;
    }

    @Override
    public String toString() {
        return "DbPatientMessageVo{" +
                "dbPatientMessage=" + dbPatientMessage +
                ", associatedId=" + associatedId +
                '}';
    }

    public DbPatientMessage getDbPatientMessage() {
        return dbPatientMessage;
    }

    public void setDbPatientMessage(DbPatientMessage dbPatientMessage) {
        this.dbPatientMessage = dbPatientMessage;
    }

    public Long getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Long associatedId) {
        this.associatedId = associatedId;
    }

    /**


     * 关联相关报告id
     */
    private Long associatedId;


}
