package com.xxsc.xtyx.middle;

import com.xiaocoder.android.fw.general.http.IHttp.XCIHttpClient;
import com.xiaocoder.android.fw.general.http.IHttp.XCIRespHandler;
import com.xiaocoder.android.fw.general.http.XCHttpProxy;

import java.util.Map;

/**
 * Created by xiaocoder on 2015/11/12.
 */
public class XTYXHttp {

    private static XCHttpProxy httpProxy;

    public static XCHttpProxy getHttpProxy() {
        return httpProxy;
    }

    public static void setHttpProxy(XCHttpProxy httpProxy) {
        XTYXHttp.httpProxy = httpProxy;
    }

    public static void initXTYXHttp(XCIHttpClient client) {
        httpProxy = new XCHttpProxy(client);
    }

    public static void resetNetingStatus() {
        httpProxy.resetNetingStatus();
    }

    public static void postAsyn(boolean needSecret, boolean isFrequentlyClick, boolean isShowDialog,
                                String urlString, Map<String, Object> map, XCIRespHandler res) {
        if (res == null) {
            res = new XTYXRespHandler2Model();
        }
        httpProxy.postAsyn(needSecret, isFrequentlyClick, isShowDialog, urlString, map, res);
    }

    public static void postAsyn(boolean isShowDialog, String urlString, Map<String, Object> map, XCIRespHandler res) {
        postAsyn(true, true, isShowDialog, urlString, map, res);
    }

    public static void postAsyn(String urlString, Map<String, Object> map, XCIRespHandler res) {
        postAsyn(true, true, true, urlString, map, res);
    }

    public static void getAsyn(boolean needSecret, boolean isFrequentlyClick, boolean isShowDialog,
                               String urlString, Map<String, Object> map, XCIRespHandler res) {
        if (res == null) {
            res = new XTYXRespHandler2Model();
        }
        httpProxy.getAsyn(needSecret, isFrequentlyClick, isShowDialog, urlString, map, res);
    }

    public static void getAsyn(boolean isShowDialog, String urlString, Map<String, Object> map, XCIRespHandler res) {
        getAsyn(true, true, isShowDialog, urlString, map, res);
    }

    public static void getAsyn(String urlString, Map<String, Object> map, XCIRespHandler res) {
        getAsyn(true, true, true, urlString, map, res);
    }

    public static void sendHttpRequest(XCIRespHandler res) {
        if (res == null) {
            res = new XTYXRespHandler2Model();
        }
        httpProxy.sendAsyn(res);
    }

}
