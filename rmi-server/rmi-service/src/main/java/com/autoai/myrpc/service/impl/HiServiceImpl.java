package com.autoai.myrpc.service.impl;

import com.autoai.myrpc.interfac.remoteObject;
import com.autoai.myrpc.service.IHiService;

/**
 * @author : zhukaishengy
 * @date : 2020/8/1 17:53
 * @Description : 远程对象的实现
 * @version : v1.0
 */
@remoteObject
public class HiServiceImpl implements IHiService {

    @Override
    public String hi(String name) {
        return "hi, " + name;
    }
}
