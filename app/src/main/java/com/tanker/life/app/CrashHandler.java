package com.tanker.life.app;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.tanker.base.util.LogUtils;
import com.tanker.life.action.slide.SlideActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/15
 * @describe : crash捕捉类
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    //文件夹目录
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/crash_log/";
    //文件名
    private static final String FILE_NAME = "crash";
    //文件名后缀
    private static final String FILE_NAME_SUFFIX = ".trace";

    //单例模式
    private static CrashHandler sInstance = new CrashHandler();

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    /**
     * 初始化方法
     */
    void open() {
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 捕获异常回掉
     *
     * @param thread 当前线程
     * @param ex     异常信息
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(ex instanceof NullPointerException){
            LogUtils.i("空指针");
        }
        dumpExceptionToSDCard(ex);
        //延时1秒杀死进程
//        SystemClock.sleep(1000);
        reStartApp();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 导出异常信息到SD卡
     *
     * @param ex
     */
    private void dumpExceptionToSDCard(Throwable ex) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        //创建文件夹
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //获取当前时间
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        //以当前时间创建log文件
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);
        try {
            //输出流操作
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            Context mContext = ApplicationContext.getInstance().getApplicationContext().getApplicationContext();
            //导出手机信息和异常信息
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            pw.println("发生异常时间：" + time);
            pw.println("应用版本：" + pi.versionName);
            pw.println("应用版本号：" + pi.versionCode);
            pw.println("android版本号：" + Build.VERSION.RELEASE);
            pw.println("android版本号API：" + Build.VERSION.SDK_INT);
            pw.println("手机制造商:" + Build.MANUFACTURER);
            pw.println("手机型号：" + Build.MODEL);
            ex.printStackTrace(pw);
            //关闭输出流
            pw.close();
        } catch (Exception e) {
            LogUtils.i(e.getMessage());
        }
    }

    /**
     * 上传异常信息到服务器
     *
     * @param ex
     */
    private void uploadExceptionToServer(Throwable ex) {
        Error error = new Error(ex.getMessage());
        LogUtils.i(error.toString());
    }

    private void reStartApp(){
        Intent intent = new Intent(ApplicationContext.getInstance().getApplicationContext(), SlideActivity.class);
        @SuppressLint("WrongConstant")
        PendingIntent restartIntent = PendingIntent.getActivity(ApplicationContext.getInstance().getApplicationContext(),
                0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        AlarmManager mgr = (AlarmManager) ApplicationContext.getInstance().getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        // 设置1毫秒后重启应
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1, restartIntent);
    }

}