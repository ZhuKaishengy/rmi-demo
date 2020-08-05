package com.autoai.myrpc;

import com.autoai.myrpc.common.RegistryCenter;
import com.autoai.myrpc.common.LocalRegistryCenterImpl;
import com.autoai.myrpc.service.IHiService;
import com.autoai.myrpc.service.impl.HiServiceImpl;

/**
 * @author : zhukaishengy
 * @date : 2020/8/4 11:37
 * @Description : TODO
 * @version : v1.0
 */
public class App {
    public static void main(String[] args) {
        RegistryCenter registerServerCenter = new LocalRegistryCenterImpl();
        registerServerCenter.regist(IHiService.class, HiServiceImpl.class);
        registerServerCenter.start();
    }
}
