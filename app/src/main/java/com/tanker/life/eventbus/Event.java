package com.tanker.life.eventbus;

/**
 * author : zhoukai
 * e-mail : zhoukai@zto.cn
 * time   : 2018/07/11
 * desc   : eventbus事件传递通用类
 */
public class Event<T> {

    private int code;
    private T data;

    public Event(int code) {
        this.code = code;
    }

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public static final class EventCode {

        public static final int UPDATE_MOTTOM_CONTENT = 0x001;

        /**
         * 添加待办事项
         */
        public static final int MEMO_CHANGE = 0x002;

    }

}