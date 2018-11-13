package com.tanker.life.manager.thread;

import android.app.Activity;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/08/23
 * @describe : Runnable实现类集合
 */
public abstract class ThreadRunnable implements Runnable {

    /**
     * 线程暂停三秒种
     */
    public static class ThreadSleep extends ThreadRunnable {

        private Activity mActivity;
        private ExecuteCallBack mCallBack;
        private long millis;

        public ThreadSleep(Activity activity, ExecuteCallBack callBack, long millis) {
            this.mActivity = activity;
            this.mCallBack = callBack;
            this.millis = millis;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.callBack();
                    }
                });
            }
        }

    }

    /**
     * 线程返回主线程
     */
    public static class ThreadOnUI extends ThreadRunnable {

        private Activity mActivity;
        private ExecuteCallBack mCallBack;

        public ThreadOnUI(Activity activity, ExecuteCallBack callBack) {
            this.mActivity = activity;
            this.mCallBack = callBack;
        }

        @Override
        public void run() {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mCallBack.callBack();
                }
            });
        }
    }


}