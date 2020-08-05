package com.autoai.myrpc.common;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : zhukaishengy
 * @date : 2020/8/5 14:47
 * @Description : tcp传输抽象
 * @version : v1.0
 */
public interface TCPTransport {

    /**
     * 创建server socket
     * @param registryMap
     */
    void createServer(ConcurrentHashMap<String, Class<?>> registryMap);

    /**
     * 连接server socket
     * @param rpcTransObject
     * @return
     */
    RpcTransObject newConnection(RpcTransObject rpcTransObject);
}
