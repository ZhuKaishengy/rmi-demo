package com.autoai.myrpc.common;
/**
 * @author : zhukaishengy
 * @date : 2020/8/4 14:13
 * @Description : 注册中心
 * @version : v1.0
 */
public interface RegistryCenter {

    String LOCAL_IP = "localhost";
    int DEFAULT_PORT = 9000;
    /**
     * 注册服务
     * @param iService
     * @param service
     */
    void regist(Class<?> iService, Class<?> service);

    /**
     * 启动服务
     */
    void start();

    /**
     * 停止服务
     */
    void close() throws Exception;

    /**
     * 获取代理对象
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T getProxy(Class<T> tClass);
}
