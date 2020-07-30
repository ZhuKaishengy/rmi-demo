package com.autoai.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author : zhukaishengy
 * @date : 2020/7/30 16:19
 * @Description : 远程接口，所有的远程接口必须实现 java.rmi.Remote
 * @version : v1.0
 */
public interface IHelloService extends Remote {

    /**
     * 远程方法
     * @param msg 消息
     * @return 结果
     * @throws RemoteException 所有的远程方法必须抛出
     */
    String hello(String msg) throws RemoteException;
}
