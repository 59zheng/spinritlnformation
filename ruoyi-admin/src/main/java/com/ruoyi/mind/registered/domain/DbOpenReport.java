package com.ruoyi.mind.registered.domain;

public class DbOpenReport {

    private DbPatientAssociated dbPatientAssociated;
    /*
     * 项目名字
     * x*/
    private String porjectName;

/*
* 显示日期
* */
private  String dateTime;

    @Override
    public String toString() {
        return "DbOpenReport{" +
                "dbPatientAssociated=" + dbPatientAssociated +
                ", porjectName='" + porjectName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /*
     *
     * 检测名字
     * */
    private String name;

    public DbOpenReport() {
    }

    public DbOpenReport(DbPatientAssociated dbPatientAssociated, String porjectName, String name) {

        this.dbPatientAssociated = dbPatientAssociated;
        this.porjectName = porjectName;
        this.name = name;
    }

    public DbPatientAssociated getDbPatientAssociated() {

        return dbPatientAssociated;
    }

    public void setDbPatientAssociated(DbPatientAssociated dbPatientAssociated) {
        this.dbPatientAssociated = dbPatientAssociated;
    }

    public String getPorjectName() {
        return porjectName;
    }

    public void setPorjectName(String porjectName) {
        this.porjectName = porjectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
