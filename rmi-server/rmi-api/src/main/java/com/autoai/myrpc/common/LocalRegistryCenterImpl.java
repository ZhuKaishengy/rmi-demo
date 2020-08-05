package com.autoai.myrpc.common;

import com.autoai.myrpc.util.Util;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : zhukaishengy
 * @date : 2020/8/4 14:18
 * @Description : 注册中心实现
 * @version : v1.0
 */
@Slf4j
public class LocalRegistryCenterImpl implements RegistryCenter {

    private final AbstractEndPoint endPoint;
    public static final ConcurrentHashMap<String, Class<?>> REGISTRY_MAP = new ConcurrentHashMap<>();

    public LocalRegistryCenterImpl(String hostName, int port, int corePoolSize, int capacity) {
        endPoint = TCPBlockingEndPoint.builder()
                .hostName(hostName)
                .port(port)
                .isServerEndPoint(true)
                .workerThreadPool(Util.getExecutorService(corePoolSize, capacity))
                .build();
    }

    public LocalRegistryCenterImpl() {
        this(LOCAL_IP, DEFAULT_PORT, 5, Integer.MAX_VALUE);
    }

    private LocalRegistryCenterImpl(String hostName, int port) {
        endPoint = TCPBlockingEndPoint.builder()
                .hostName(hostName)
                .port(port)
                .isServerEndPoint(false)
                .build();
    }

    @Override
    public void regist(Class<?> iService, Class<?> service) {
        this.checkServerEndpoint();
        REGISTRY_MAP.put(iService.getName(), service);
        log.info("注册成功！");
    }

    @Override
    public void start() {
        this.endPoint.createServer(REGISTRY_MAP);
    }

    @Override
    public void close() throws Exception {
        this.endPoint.close();
    }

    /**
     * 连接到注册中心
     * @param hostName
     * @param port
     */
    public static RegistryCenter connect(String hostName, int port) {
        return new LocalRegistryCenterImpl(hostName, port);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> tClass) {
        return (T)Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, new RegistryCenterInvocationHandler(endPoint, tClass));
    }

    private void checkServerEndpoint() {
        this.endPoint.checkServerEndpoint();
    }
}
