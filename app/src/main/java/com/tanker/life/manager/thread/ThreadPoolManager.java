package com.tanker.life.manager.thread;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/07/25
 * @describe : 线程管理
 */
public class ThreadPoolManager {

    /**
     * 核心线程池的数量，同时执行的线程数
     */
    private int corePoolSize;
    /**
     * 最大线程池数量
     */
    private int maximumpollSize;
    /**
     * 存活时间
     */
    private long keepAliveTime = 1;
    private TimeUnit timeUnit = TimeUnit.HOURS;
    private ThreadPoolExecutor poolExecutor;

    private static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }

    private ThreadPoolManager() {
        //当前设备可用处理器核心数 * 2 + 1，能够让cpu效率最大程度执行(maximumpollSize未使用但需赋值，否则报错)
        maximumpollSize = corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("zto-best-pool-name");
                return thread;
            }
        };
        //使用ThreadFactoryBuilder需要依赖Guava包(阿里Java规范)
//        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("zto-best-pool-%d").build();
        poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumpollSize, keepAliveTime, timeUnit,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 执行任务
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        poolExecutor.execute(runnable);
    }

    /**
     * 执行任务(有返回值)
     *
     * @param callback
     * @return
     */
    public Future submit(Callable callback) {
        if (callback == null) {
            return null;
        }
        return poolExecutor.submit(callback);
    }

    /**
     * 移除任务
     *
     * @param runnable
     */
    public void remove(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        poolExecutor.remove(runnable);
    }

}