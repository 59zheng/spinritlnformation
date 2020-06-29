package com.ruoyi.mind.physical.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 导出word结果描述与提示对应对象 db_description_or_hint
 *
 * @author zheng
 * @date 2020-06-29
 */
public class DbDescriptionOrHint extends BaseEntity
        {
private static final long serialVersionUID=1L;

        /** 主键 */
            private Long id;

            /** 属性编号 */
                                                        @Excel(name = "属性编号" )
                        private String typeCode;

            /** 名称 */
                                                        @Excel(name = "名称" )
                        private String name;

            /** 描述 */
                                                        @Excel(name = "描述" )
                        private String describe;

            /** 提示 */
                                                        @Excel(name = "提示" )
                        private String hint;

                            public void setId(Long id)
            {
            this.id = id;
            }

    public Long getId()
            {
            return id;
            }
                            public void setTypeCode(String typeCode)
            {
            this.typeCode = typeCode;
            }

    public String getTypeCode()
            {
            return typeCode;
            }
                            public void setName(String name)
            {
            this.name = name;
            }

    public String getName()
            {
            return name;
            }
                            public void setDescribe(String describe)
            {
            this.describe = describe;
            }

    public String getDescribe()
            {
            return describe;
            }
                            public void setHint(String hint)
            {
            this.hint = hint;
            }

    public String getHint()
            {
            return hint;
            }
    
@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                                .append("id" ,getId())
                                .append("typeCode" ,getTypeCode())
                                .append("name" ,getName())
                                .append("describe" ,getDescribe())
                                .append("hint" ,getHint())
            .toString();
        }
        }
