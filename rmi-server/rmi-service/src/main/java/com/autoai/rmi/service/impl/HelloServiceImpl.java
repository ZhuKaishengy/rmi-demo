package com.autoai.rmi.service.impl;

import com.autoai.rmi.service.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author : zhukaishengy
 * @date : 2020/7/30 16:59
 * @Description : 远程接口的服务端实现
 * @version : v1.0
 */
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String hello(String msg) {
        return "hello " + msg;
    }
}
