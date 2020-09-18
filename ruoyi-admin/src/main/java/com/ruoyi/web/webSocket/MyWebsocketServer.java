package com.ruoyi.web.webSocket;

import com.ruoyi.common.json.JSON;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gjing
 **/
@ServerEndpoint(value = "/test/one/{userId}")
@Component
public class MyWebsocketServer {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MyWebsocketServer.class);

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        //将新用户存入在线的组
//        获取当前在线用户id 当key
        log.info("有新的客户端连接了: {}", userId);
        clients.put(userId, session);
//        发送公告信息
//        开启一个定时


    }



    ;

    /**
     * 客户端关闭
     *
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("有用户断开了, id为:{}", session.getId());
        //将掉线的用户移除在线的组里
        clients.remove(session.getId());
    }

    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     *
     * @param message 消息对象
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("服务端收到客户端发来的消息: {}", message);
        this.sendAll(message);
    }

    /**
     * 群发消息
     *
     * @param message 消息内容
     */
    public void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }


/*
*
*
* */
    public void HearbeatRun(String userId) throws InterruptedException {
        Thread.sleep(1000);
        SysNotice notice1 = new SysNotice();
        notice1.setNoticeType("2");
        notice1.setStatus("0");
        SysNotice notice = SpringUtils.getBean(ISysNoticeService.class).selectNoticeList(notice1).get(0);
        System.out.println("给"+userId+"发送消息");
        String s = com.alibaba.fastjson.JSON.toJSONString(notice);
        clients.get(userId).getAsyncRemote().sendText(s);
    }
}

