package com.autoai.myrpc.common;

import lombok.experimental.SuperBuilder;

/**
 * @author : zhukaishengy
 * @date : 2020/8/5 14:58
 * @Description : TODO
 * @version : v1.0
 */
@SuperBuilder
public abstract class AbstractEndPoint implements TCPTransport, AutoCloseable{

    protected String hostName;
    protected int port;
    /**
     * server endpoint or client endpoint
     */
    protected boolean isServerEndPoint;

    public abstract void checkServerEndpoint();
}
