package com.autoai.myrpc.service;

import com.autoai.myrpc.interfac.remoteApi;

/**
 * @author : zhukaishengy
 * @date : 2020/8/1 17:42
 * @Description : 用于测试远程传输对象
 * @version : v1.0
 */
@remoteApi
public interface IHiService {

    /**
     * 测试方法
     * @param name 名字
     * @return result
     */
    String hi(String name);
}
