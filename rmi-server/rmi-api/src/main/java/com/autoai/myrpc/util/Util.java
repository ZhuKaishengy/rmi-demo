package com.autoai.myrpc.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zhukaishengy
 * @date : 2020/8/1 18:08
 * @Description : 工具类
 * @version : v1.0
 */
public class Util {

    /**
     * 创建线程池
     * @param corePoolSize
     * @param capacity
     * @return
     */
    public static ExecutorService getExecutorService(int corePoolSize, int capacity) {
        final String namePrefix = "worker-";
        AtomicInteger count = new AtomicInteger(0);
        return new ThreadPoolExecutor(corePoolSize, corePoolSize * 2, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(capacity), r -> {
                    Thread thread = new Thread(r);
                    thread.setName(namePrefix + count.getAndIncrement());
                    return thread;
                });
    }
}
