package com.autoai.myrpc.common;

import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @author : zhukaishengy
 * @date : 2020/8/5 14:46
 * @Description : TCP 端点
 * @version : v1.0
 */
@Slf4j
@SuperBuilder
public class TCPBlockingEndPoint extends AbstractEndPoint {

    private final ExecutorService workerThreadPool;

    private ServerSocket serverSocket;

    @Override
    public void createServer(ConcurrentHashMap<String, Class<?>> registryMap) {
        this.checkServerEndpoint();
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(super.hostName, super.port));
            log.info("server启动成功...");
            while (true) {
                Socket socket = serverSocket.accept();
                workerThreadPool.submit(() -> {
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(inputStream);
                    RpcTransObject rpcRequest = (RpcTransObject)ois.readObject();
                    // 解析参数
                    String className = rpcRequest.getClassName();
                    String methodName = rpcRequest.getMethodName();
                    Class<?>[] paramTypes = rpcRequest.getParamTypes();
                    Object[] params = rpcRequest.getParams();
                    // 从注册表中取出对应的类
                    Class<?> aClass = registryMap.get(className);
                    // 反射执行方法
                    Method method = aClass.getMethod(methodName, paramTypes);
                    Object result = method.invoke(aClass.newInstance(), params);
                    // 将结果输出
                    OutputStream outputStream = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                    oos.writeObject(RpcTransObject.builder().result(result).build());
                    return "okay";
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RpcTransObject newConnection(RpcTransObject rpcTransObject) {

        OutputStream outputStream = null;
        ObjectOutputStream oos = null;
        InputStream inputStream = null;
        ObjectInputStream ois = null;
        try (
            Socket socket = new Socket()
        ){
            socket.connect(new InetSocketAddress(this.hostName, this.port));
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(rpcTransObject);

            inputStream = socket.getInputStream();
            ois = new ObjectInputStream(inputStream);
            return (RpcTransObject)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert oos != null;
                oos.close();
                outputStream.close();
                assert ois != null;
                ois.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public void close() throws Exception {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkServerEndpoint() {
        if (!super.isServerEndPoint) {
            log.error("not server endpoint");
            throw new RuntimeException("not server endpoint");
        }
    }
}
