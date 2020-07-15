package com.ruoyi.mind.lab.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 血药浓度检测对象 db_lab_tdm
 *
 * @author zheng
 * @date 2020-07-13
 */
public class DbLabTdm extends BaseEntity
        {
private static final long serialVersionUID=1L;

        /** 主键 */
            private Long id;

            /** 病人名称 */
                                                        @Excel(name = "病人名称" )
                        private String patientName;

            /** 诊断记录表id */
                                                        @Excel(name = "诊断记录表id" )
                        private Long patientId;

            /** 主治医生 */
                                                        @Excel(name = "主治医生" )
                        private String attendingPhysician;

            /** 执行技师 */
                                                        @Excel(name = "执行技师" )
                        private String technicianName;

            /** 执行时间 */
                                                        @Excel(name = "执行时间" , width = 30, dateFormat = "yyyy-MM-dd" )
                        private Date executionTime;

            /** 剩余次数 */
                                                        @Excel(name = "剩余次数" )
                        private Long howMany;

            /** 文档地址 */
                                                        @Excel(name = "文档地址" )
                        private String documentAddress;

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
                            public void setPatientId(Long patientId)
            {
            this.patientId = patientId;
            }

    public Long getPatientId()
            {
            return patientId;
            }
                            public void setAttendingPhysician(String attendingPhysician)
            {
            this.attendingPhysician = attendingPhysician;
            }

    public String getAttendingPhysician()
            {
            return attendingPhysician;
            }
                            public void setTechnicianName(String technicianName)
            {
            this.technicianName = technicianName;
            }

    public String getTechnicianName()
            {
            return technicianName;
            }
                            public void setExecutionTime(Date executionTime)
            {
            this.executionTime = executionTime;
            }

    public Date getExecutionTime()
            {
            return executionTime;
            }
                            public void setHowMany(Long howMany)
            {
            this.howMany = howMany;
            }

    public Long getHowMany()
            {
            return howMany;
            }
                            public void setDocumentAddress(String documentAddress)
            {
            this.documentAddress = documentAddress;
            }

    public String getDocumentAddress()
            {
            return documentAddress;
            }
    
@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                                .append("id" ,getId())
                                .append("patientName" ,getPatientName())
                                .append("patientId" ,getPatientId())
                                .append("attendingPhysician" ,getAttendingPhysician())
                                .append("technicianName" ,getTechnicianName())
                                .append("executionTime" ,getExecutionTime())
                                .append("howMany" ,getHowMany())
                                .append("documentAddress" ,getDocumentAddress())
            .toString();
        }
        }
