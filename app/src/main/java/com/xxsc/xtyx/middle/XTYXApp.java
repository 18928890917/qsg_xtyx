package com.xxsc.xtyx.middle;

import android.app.Application;
import android.content.Context;

import com.xiaocoder.android.fw.general.application.XCConstant;
import com.xiaocoder.android.fw.general.exception.XCCrashHandler;
import com.xiaocoder.android.fw.general.exception.XCExceptionModel;
import com.xiaocoder.android.fw.general.exception.XCExceptionModelDb;
import com.xiaocoder.android.fw.general.exception.XCIException2Server;
import com.xiaocoder.android.fw.general.function.helper.XCAppHelper;
import com.xiaocoder.android.fw.general.function.thread.XCExecutor;
import com.xiaocoder.android.fw.general.http.asynchttp.XCAsyncClient;
import com.xiaocoder.android.fw.general.imageloader.XCAsynLoader;
import com.xiaocoder.android.fw.general.io.XCIOAndroid;
import com.xiaocoder.android.fw.general.io.XCLog;
import com.xiaocoder.android.fw.general.io.XCSP;
import com.xxsc.xtyx.middle.config.ConfigFile;
import com.xxsc.xtyx.middle.config.ConfigImages;
import com.xxsc.xtyx.middle.config.ConfigLog;
import com.xxsc.xtyx.middle.config.ConfigThread;

/**
 * Created by xiaocoder on 2015/7/14.
 * <p/>
 * 初始化的顺序不要去改动
 */
public class XTYXApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        initAppHelper();

        createDir();

        initLog();

        initSp();

        initHttp();

        initNumThreadPool();

        initImageLoader();

        initCrash();

    }

    private void initAppHelper() {
        XCAppHelper.init(this);
    }


    /**
     * sp保存文件名 与 模式
     */
    private void initSp() {
        XCSP.initXCSP(getApplicationContext(), ConfigFile.SP_FILE, Context.MODE_APPEND);// Context.MODE_MULTI_PROCESS
    }

    private void initHttp() {
        XTYXHttp.initXTYXHttp(new XCAsyncClient());
    }

    /**
     * log(可以打印日志到控制台和文件中) 与 toast
     */
    private void initLog() {

        XCLog.initXCLog(getApplicationContext(),
                ConfigLog.IS_DTOAST, ConfigLog.IS_OUTPUT, ConfigLog.IS_PRINTLOG,
                ConfigFile.APP_ROOT, ConfigFile.LOG_FILE, XCConstant.ENCODING_UTF8);
    }

    /**
     * http解析时用到该固定线程池
     */
    private void initNumThreadPool() {
        XCExecutor.initXCExecutor(ConfigThread.FIX_THREAD_NUM, ConfigThread.SCHEDULE_THREAD_NUM);
    }

    private void createDir() {
        // 应用存储日志 缓存等信息的顶层文件夹
        XCIOAndroid.createDirInAndroid(getApplicationContext(), ConfigFile.APP_ROOT);
        // 图片视频等缓存的文件夹
        XCIOAndroid.createDirInAndroid(getApplicationContext(), ConfigFile.CHAT_MOIVE_DIR);
        XCIOAndroid.createDirInAndroid(getApplicationContext(), ConfigFile.CHAT_VIDEO_DIR);
        XCIOAndroid.createDirInAndroid(getApplicationContext(), ConfigFile.CHAT_PHOTO_DIR);
        // crash文件夹
        XCIOAndroid.createDirInAndroid(getApplicationContext(), ConfigFile.CRASH_DIR);
        // cache文件夹
        XCIOAndroid.createDirInAndroid(getApplicationContext(), ConfigFile.CACHE_DIR);
    }

    private void initImageLoader() {

        XTYXImage.initImager(new XCAsynLoader(ConfigImages.getImageloader(getApplicationContext()),
                ConfigImages.default_image_options
        ));
    }

    private void initCrash() {

        XCCrashHandler.getInstance().init(ConfigLog.IS_INIT_CRASH_HANDLER,
                getApplicationContext(), ConfigFile.CRASH_DIR, ConfigLog.IS_SHOW_EXCEPTION_ACTIVITY);

        XCCrashHandler.getInstance().setUploadServer(new XCIException2Server() {
            @Override
            public void uploadException2Server(String info, Throwable ex, Thread thread,
                                               XCExceptionModel model, XCExceptionModelDb db) {
                if (db != null) {
                    model.setUserId(XTYXSP.getUserId());
                    db.updateByUniqueId(model, model.getUniqueId());
                }
            }
        });
    }
}
