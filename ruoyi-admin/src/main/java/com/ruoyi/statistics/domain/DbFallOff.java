package com.ruoyi.statistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 脱落列对象 db_fall_off
 *
 * @author zheng
 * @date 2020-08-11
 */
public class DbFallOff extends BaseEntity
        {
private static final long serialVersionUID=1L;

        /** 主键 */
            private Long id;

            /** 医生名称 */
                                                        @Excel(name = "医生名称" )
                        private String doctorName;

            /** 脱落类型（急性期，非急性期） */
                                                        @Excel(name = "脱落类型" , readConverterExp = "急=性期，非急性期" )
                        private String offClass;

            /** 说明 */
                                                        @Excel(name = "说明" )
                        private String offState;

            /** 是否取消脱落（1，脱落） */
                                                        @Excel(name = "是否取消脱落" , readConverterExp = "1=，脱落" )
                        private String isOff;

            /** 关联的诊断id */
                                                        @Excel(name = "关联的诊断id" )
                        private Long messageId;

            /** 操作人姓名 */
                                                        @Excel(name = "操作人姓名" )
                        private String updateName;

                                public void setId(Long id)
            {
            this.id = id;
            }

    public Long getId()
            {
            return id;
            }
                            public void setDoctorName(String doctorName)
            {
            this.doctorName = doctorName;
            }

    public String getDoctorName()
            {
            return doctorName;
            }
                            public void setOffClass(String offClass)
            {
            this.offClass = offClass;
            }

    public String getOffClass()
            {
            return offClass;
            }
                            public void setOffState(String offState)
            {
            this.offState = offState;
            }

    public String getOffState()
            {
            return offState;
            }
                            public void setIsOff(String isOff)
            {
            this.isOff = isOff;
            }

    public String getIsOff()
            {
            return isOff;
            }
                            public void setMessageId(Long messageId)
            {
            this.messageId = messageId;
            }

    public Long getMessageId()
            {
            return messageId;
            }
                            public void setUpdateName(String updateName)
            {
            this.updateName = updateName;
            }

    public String getUpdateName()
            {
            return updateName;
            }
        
@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                                .append("id" ,getId())
                                .append("doctorName" ,getDoctorName())
                                .append("offClass" ,getOffClass())
                                .append("offState" ,getOffState())
                                .append("isOff" ,getIsOff())
                                .append("messageId" ,getMessageId())
                                .append("updateName" ,getUpdateName())
                                .append("updateBy" ,getUpdateBy())
            .toString();
        }
        }
