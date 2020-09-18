package com.ruoyi.statistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 聊天记录对象 db_chat_message
 *
 * @author zheng
 * @date 2020-08-13
 */
public class DbChatMessage extends BaseEntity
        {
private static final long serialVersionUID=1L;

        /** 主键 */
            private Long id;

            /** 聊天室名称 */
                                                        @Excel(name = "聊天室名称" )
                        private String chatName;

            /** 聊天列表 */
                                                        @Excel(name = "聊天列表" )
                        private String chatList;

            /** 聊天室成员id（推送） */
                                                        @Excel(name = "聊天室成员id" , readConverterExp = "推=送" )
                        private String peopleChatId;

                            public void setId(Long id)
            {
            this.id = id;
            }

    public Long getId()
            {
            return id;
            }
                            public void setChatName(String chatName)
            {
            this.chatName = chatName;
            }

    public String getChatName()
            {
            return chatName;
            }
                            public void setChatList(String chatList)
            {
            this.chatList = chatList;
            }

    public String getChatList()
            {
            return chatList;
            }
                            public void setPeopleChatId(String peopleChatId)
            {
            this.peopleChatId = peopleChatId;
            }

    public String getPeopleChatId()
            {
            return peopleChatId;
            }
    
@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                                .append("id" ,getId())
                                .append("chatName" ,getChatName())
                                .append("chatList" ,getChatList())
                                .append("peopleChatId" ,getPeopleChatId())
            .toString();
        }
        }
