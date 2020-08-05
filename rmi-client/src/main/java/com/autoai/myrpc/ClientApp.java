package com.autoai.myrpc;

import com.autoai.myrpc.common.RegistryCenter;
import com.autoai.myrpc.common.LocalRegistryCenterImpl;
import com.autoai.myrpc.service.IHiService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : zhukaishengy
 * @date : 2020/8/4 11:37
 * @Description : TODO
 * @version : v1.0
 */
@Slf4j
public class ClientApp {
    public static void main(String[] args) {
        RegistryCenter registryCenter = LocalRegistryCenterImpl.connect(RegistryCenter.LOCAL_IP, RegistryCenter.DEFAULT_PORT);
        IHiService proxy = registryCenter.getProxy(IHiService.class);
        String result = proxy.hi("huhu");
        log.info("result:{}", result);
    }
}
