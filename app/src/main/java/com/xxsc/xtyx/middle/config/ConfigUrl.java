package com.xxsc.xtyx.middle.config;

import com.xiaocoder.android.fw.general.util.UtilString;

/**
 * @author xiaocoder on 2016/5/7.
 * @modifier xiaocoder 2016/5/7 18:36.
 * @description
 */
public class ConfigUrl {

    /**
     * 当前的运行环境，即域名的控制, 上线前，改为ONLINE
     */
    public static RunEnvironment CURRENT_RUN_ENVIRONMENT = RunEnvironment.ONLINE;


    /** 首页*/
    public static final String index = "apisite/index";
    /**登录 */
    public static final String login = "apisite/login";
    /**注册 */
    public static final String reg = "apisite/reg";
    /**发送验证码 */
    public static final String send_captcha = "apisite/send_captcha";
    /**友盟开发key */
    public static final String UMENG_DEV_KEY = "";
    /**友盟在线key */
    public static final String UMENG_ONLINE_KEY = "";
    /**友盟项目使用key */
    public static String UEMNG_KEY = "";

    public enum RunEnvironment {
        DEV, TEST, ONLINE
    }

    /**
     * 域名配置
     */
    public static String ONLINE_HOST = "http://120.25.240.120/shop/";
    public static String ONLINE_PORT = "";
    public static String ONLINE_ADDR = ONLINE_HOST + (UtilString.isBlank(ONLINE_PORT) ? "" : ":") + ONLINE_PORT;

    public static String TEST_HOST = "http://120.25.240.120/shop";
    public static String TEST_PORT = "";
    public static String TEST_ADDR = TEST_HOST + (UtilString.isBlank(TEST_PORT) ? "" : ":") + TEST_PORT;

    public static String DEV_HOST = "http://120.25.240.120/shop/";
    public static String DEV_PORT = "";
    public static String DEV_ADDR = DEV_HOST + (UtilString.isBlank(DEV_PORT) ? "" : ":") + DEV_PORT;

    public static String getUrl(String key) {

        if (CURRENT_RUN_ENVIRONMENT == RunEnvironment.ONLINE) {

            return ONLINE_ADDR + key;

        } else if (CURRENT_RUN_ENVIRONMENT == RunEnvironment.TEST) {

            return TEST_ADDR + key;

        } else if (CURRENT_RUN_ENVIRONMENT == RunEnvironment.DEV) {

            return DEV_ADDR + key;

        } else {
            throw new RuntimeException("QlkConfig中没有找到匹配的url");
        }
    }

}
