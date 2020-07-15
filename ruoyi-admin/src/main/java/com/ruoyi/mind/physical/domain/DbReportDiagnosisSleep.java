package com.ruoyi.mind.physical.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 睡眠监测报告对象 db_report_diagnosis_sleep
 *
 * @author zheng
 * @date 2020-07-08
 */
public class DbReportDiagnosisSleep extends TreeEntity
        {
private static final long serialVersionUID=1L;

        /** 主键 */
            private Long id;

            /** 病人id */
                                                        @Excel(name = "病人id" )
                        private Long patientId;

                /** 文档地址 */
                                                        @Excel(name = "文档地址" )
                        private String documentAddress;

            /** 主治医生 */
                                                        @Excel(name = "主治医生" )
                        private String visitingStaff;

            /** 操作技师 */
                                                        @Excel(name = "操作技师" )
                        private String technicianName;

            /** 剩余次数 */
                                                        @Excel(name = "剩余次数" )
                        private Long remainingDays;

            /** 是否完成 */
                                                        @Excel(name = "是否完成" )
                        private String isOk;

            /** 病人名称 */
                                                        @Excel(name = "病人名称" )
                        private String patientName;

            /** 父id */
                                                        @Excel(name = "父id" )
                        private Long fatherId;

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
                                public void setDocumentAddress(String documentAddress)
            {
            this.documentAddress = documentAddress;
            }

    public String getDocumentAddress()
            {
            return documentAddress;
            }
                            public void setVisitingStaff(String visitingStaff)
            {
            this.visitingStaff = visitingStaff;
            }

    public String getVisitingStaff()
            {
            return visitingStaff;
            }
                            public void setTechnicianName(String technicianName)
            {
            this.technicianName = technicianName;
            }

    public String getTechnicianName()
            {
            return technicianName;
            }
                            public void setRemainingDays(Long remainingDays)
            {
            this.remainingDays = remainingDays;
            }

    public Long getRemainingDays()
            {
            return remainingDays;
            }
                            public void setIsOk(String isOk)
            {
            this.isOk = isOk;
            }

    public String getIsOk()
            {
            return isOk;
            }
                            public void setPatientName(String patientName)
            {
            this.patientName = patientName;
            }

    public String getPatientName()
            {
            return patientName;
            }
                            public void setFatherId(Long fatherId)
            {
            this.fatherId = fatherId;
            }

    public Long getFatherId()
            {
            return fatherId;
            }
    
@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                                .append("id" ,getId())
                                .append("patientId" ,getPatientId())
                                .append("createTime" ,getCreateTime())
                                .append("documentAddress" ,getDocumentAddress())
                                .append("visitingStaff" ,getVisitingStaff())
                                .append("technicianName" ,getTechnicianName())
                                .append("remainingDays" ,getRemainingDays())
                                .append("isOk" ,getIsOk())
                                .append("patientName" ,getPatientName())
                                .append("fatherId" ,getFatherId())
            .toString();
        }
        }
