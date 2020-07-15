package com.ruoyi.mind.registered.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class DbPatientMessageVo2  extends BaseEntity {
    private DbPatientMessage dbPatientMessage;
    private  DbPatientAssociated dbPatientAssociated;

    public DbPatientMessage getDbPatientMessage() {
        return dbPatientMessage;
    }

    public void setDbPatientMessage(DbPatientMessage dbPatientMessage) {
        this.dbPatientMessage = dbPatientMessage;
    }

    public DbPatientAssociated getDbPatientAssociated() {
        return dbPatientAssociated;
    }

    public DbPatientMessageVo2() {
    }

    public void setDbPatientAssociated(DbPatientAssociated dbPatientAssociated) {

        this.dbPatientAssociated = dbPatientAssociated;
    }

    public DbPatientMessageVo2(DbPatientMessage dbPatientMessage, DbPatientAssociated dbPatientAssociated) {

        this.dbPatientMessage = dbPatientMessage;
        this.dbPatientAssociated = dbPatientAssociated;
    }
}
