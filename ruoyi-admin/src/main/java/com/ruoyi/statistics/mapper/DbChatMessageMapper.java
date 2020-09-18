package com.ruoyi.statistics.mapper;

import java.util.List;
import com.ruoyi.statistics.domain.DbChatMessage;

/**
 * 聊天记录Mapper接口
 * 
 * @author zheng
 * @date 2020-08-13
 */
public interface DbChatMessageMapper 
{
    /**
     * 查询聊天记录
     * 
     * @param id 聊天记录ID
     * @return 聊天记录
     */
    public DbChatMessage selectDbChatMessageById(Long id);

    /**
     * 查询聊天记录列表
     * 
     * @param dbChatMessage 聊天记录
     * @return 聊天记录集合
     */
    public List<DbChatMessage> selectDbChatMessageList(DbChatMessage dbChatMessage);

    /**
     * 新增聊天记录
     * 
     * @param dbChatMessage 聊天记录
     * @return 结果
     */
    public int insertDbChatMessage(DbChatMessage dbChatMessage);

    /**
     * 修改聊天记录
     * 
     * @param dbChatMessage 聊天记录
     * @return 结果
     */
    public int updateDbChatMessage(DbChatMessage dbChatMessage);

    /**
     * 删除聊天记录
     * 
     * @param id 聊天记录ID
     * @return 结果
     */
    public int deleteDbChatMessageById(Long id);

    /**
     * 批量删除聊天记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDbChatMessageByIds(String[] ids);
}
