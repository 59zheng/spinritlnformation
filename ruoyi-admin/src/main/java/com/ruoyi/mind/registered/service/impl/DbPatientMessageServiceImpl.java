package com.ruoyi.mind.registered.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mind.registered.mapper.DbPatientMessageMapper;
import com.ruoyi.mind.registered.domain.DbPatientMessage;
import com.ruoyi.mind.registered.service.IDbPatientMessageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 病人,缴费之后创建Service业务层处理
 * 
 * @author zheng
 * @date 2020-06-05
 */
@Service
public class DbPatientMessageServiceImpl implements IDbPatientMessageService 
{
    @Autowired
    private DbPatientMessageMapper dbPatientMessageMapper;

    /**
     * 查询病人,缴费之后创建
     * 
     * @param id 病人,缴费之后创建ID
     * @return 病人,缴费之后创建
     */
    @Override
    public DbPatientMessage selectDbPatientMessageById(Long id)
    {
        return dbPatientMessageMapper.selectDbPatientMessageById(id);
    }

    /**
     * 查询病人,缴费之后创建列表
     * 
     * @param dbPatientMessage 病人,缴费之后创建
     * @return 病人,缴费之后创建
     */
    @Override
    public List<DbPatientMessage> selectDbPatientMessageList(DbPatientMessage dbPatientMessage)
    {
        return dbPatientMessageMapper.selectDbPatientMessageList(dbPatientMessage);
    }

    /**
     * 新增病人,缴费之后创建
     * 
     * @param dbPatientMessage 病人,缴费之后创建
     * @return 结果
     */
    @Override
    public int insertDbPatientMessage(DbPatientMessage dbPatientMessage)
    {
        dbPatientMessage.setCreateTime(DateUtils.getNowDate());
        return dbPatientMessageMapper.insertDbPatientMessage(dbPatientMessage);
    }

    /**
     * 修改病人,缴费之后创建
     * 
     * @param dbPatientMessage 病人,缴费之后创建
     * @return 结果
     */
    @Override
    public int updateDbPatientMessage(DbPatientMessage dbPatientMessage)
    {
        return dbPatientMessageMapper.updateDbPatientMessage(dbPatientMessage);
    }

    @Override
    public List<Map<String,Object >> selectListByCreatTime() {
        return dbPatientMessageMapper.selectListByCreatTime();
    }

    /**
     * 删除病人,缴费之后创建对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDbPatientMessageByIds(String ids)
    {
        return dbPatientMessageMapper.deleteDbPatientMessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除病人,缴费之后创建信息
     * 
     * @param id 病人,缴费之后创建ID
     * @return 结果
     */
    @Override
    public int deleteDbPatientMessageById(Long id)
    {
        return dbPatientMessageMapper.deleteDbPatientMessageById(id);
    }
}
