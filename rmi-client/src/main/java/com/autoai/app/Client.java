package com.autoai.app;

import com.autoai.service.IHelloService;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author : zhukaishengy
 * @date : 2020/7/30 17:13
 * @Description : 客户端
 * @version : v1.0
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        try {
            // 从注册中心取服务
            IHelloService helloService = (IHelloService)Naming.lookup("rmi://127.0.0.1/hello");
            String result = helloService.hello("zks");
            log.info("response:{}", result);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
