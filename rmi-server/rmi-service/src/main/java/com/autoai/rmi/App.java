package com.autoai.rmi;

import com.autoai.rmi.service.IHelloService;
import com.autoai.rmi.service.impl.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author : zhukaishengy
 * @date : 2020/7/30 17:05
 * @Description : server服务
 * @version : v1.0
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        try {
            IHelloService helloService = new HelloServiceImpl();
            // 创建注册中心
            LocateRegistry.createRegistry(1099);
            // 绑定服务
            Naming.bind("rmi://127.0.0.1/hello", helloService);
            log.info("server start...");
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
