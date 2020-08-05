package com.autoai.myrpc.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhukaishengy
 * @date : 2020/8/4 16:13
 * @Description : 封装rpc传输对象
 * @version : v1.0
 */
@Data
@Builder
public class RpcTransObject implements Serializable {

    private static final long serialVersionUID = 8900435945698699370L;

    private String className;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] params;
    private Object result;
}
