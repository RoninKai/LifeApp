package com.tanker.life.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @author : Tanker
 * @e-mail : zhoukai@zto.cn
 * @time   : 2018/07/11
 * @desc   : eventbus工具类
 */
public class EventBusUtil {

    /**
     * 注册
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        if (isRegistered(subscriber)) {
            return;
        }
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 反注册
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        if (!isRegistered(subscriber)) {
            return;
        }
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送事件
     *
     * @param event
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送黏性事件
     *
     * @param event
     */
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

    /**
     * 是否已注册
     *
     * @param subscriber
     * @return
     */
    private static boolean isRegistered(Object subscriber) {
        return EventBus.getDefault().isRegistered(subscriber);
    }

}