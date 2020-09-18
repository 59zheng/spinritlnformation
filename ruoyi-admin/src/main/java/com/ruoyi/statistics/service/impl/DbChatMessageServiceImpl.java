package com.ruoyi.statistics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.statistics.mapper.DbChatMessageMapper;
import com.ruoyi.statistics.domain.DbChatMessage;
import com.ruoyi.statistics.service.IDbChatMessageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 聊天记录Service业务层处理
 * 
 * @author zheng
 * @date 2020-08-13
 */
@Service
public class DbChatMessageServiceImpl implements IDbChatMessageService 
{
    @Autowired
    private DbChatMessageMapper dbChatMessageMapper;

    /**
     * 查询聊天记录
     * 
     * @param id 聊天记录ID
     * @return 聊天记录
     */
    @Override
    public DbChatMessage selectDbChatMessageById(Long id)
    {
        return dbChatMessageMapper.selectDbChatMessageById(id);
    }

    /**
     * 查询聊天记录列表
     * 
     * @param dbChatMessage 聊天记录
     * @return 聊天记录
     */
    @Override
    public List<DbChatMessage> selectDbChatMessageList(DbChatMessage dbChatMessage)
    {
        return dbChatMessageMapper.selectDbChatMessageList(dbChatMessage);
    }

    /**
     * 新增聊天记录
     * 
     * @param dbChatMessage 聊天记录
     * @return 结果
     */
    @Override
    public int insertDbChatMessage(DbChatMessage dbChatMessage)
    {
        return dbChatMessageMapper.insertDbChatMessage(dbChatMessage);
    }

    /**
     * 修改聊天记录
     * 
     * @param dbChatMessage 聊天记录
     * @return 结果
     */
    @Override
    public int updateDbChatMessage(DbChatMessage dbChatMessage)
    {
        return dbChatMessageMapper.updateDbChatMessage(dbChatMessage);
    }

    /**
     * 删除聊天记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbChatMessageByIds(String ids)
    {
        return dbChatMessageMapper.deleteDbChatMessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除聊天记录信息
     * 
     * @param id 聊天记录ID
     * @return 结果
     */
    @Override
    public int deleteDbChatMessageById(Long id)
    {
        return dbChatMessageMapper.deleteDbChatMessageById(id);
    }
}
