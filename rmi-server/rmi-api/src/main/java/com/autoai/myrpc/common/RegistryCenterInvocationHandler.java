package com.autoai.myrpc.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author : zhukaishengy
 * @date : 2020/8/5 13:46
 * @Description : 动态代理处理器
 * @version : v1.0
 */
@Data
@AllArgsConstructor
public class RegistryCenterInvocationHandler implements InvocationHandler {

    private AbstractEndPoint serverEndPoint;
    private final Class<?> tClass;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = tClass.getName();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        RpcTransObject rpcReq = RpcTransObject.builder()
                .className(className)
                .methodName(methodName)
                .paramTypes(parameterTypes)
                .params(args)
                .build();
        RpcTransObject rpcResp = serverEndPoint.newConnection(rpcReq);
        return rpcResp.getResult();
    }
}
