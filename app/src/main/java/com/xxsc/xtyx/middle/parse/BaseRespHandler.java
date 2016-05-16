package com.xxsc.xtyx.middle.parse;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.xiaocoder.android.fw.general.application.XCConstant;
import com.xiaocoder.android.fw.general.function.helper.XCAppHelper;
import com.xiaocoder.android.fw.general.http.IHttp.XCIHttpClient;
import com.xiaocoder.android.fw.general.http.IHttp.XCIHttpNotify;
import com.xiaocoder.android.fw.general.http.XCRespHandler;
import com.xiaocoder.android.fw.general.http.XCRespType;
import com.xiaocoder.android.fw.general.io.XCLog;
import com.xiaocoder.android.fw.general.js_xl_encryption.md5.UtilMd5;
import com.xiaocoder.android.fw.general.util.UtilString;
import com.xiaocoder.android.fw.general.util.UtilSystem;
import com.xiaocoder.views.dialog.XCSystemHDialog;
import com.xxsc.xtyx.MainActivity;
import com.xxsc.xtyx.middle.XTYXHttp;
import com.xxsc.xtyx.middle.XTYXSP;
import com.xxsc.xtyx.middle.config.ConfigFile;

import java.util.Map;

/**
 * Created by xiaocoder on 2015/8/28.
 * <p/>
 * 已有的一个实现类
 * XTYXRespHandler2Model（gson的解析）
 * <p/>
 * 也可以创建一个实现类，手动解析
 */
public abstract class BaseRespHandler<T> extends XCRespHandler<T> {

    /**
     * 加载中的dialog
     */
    protected Dialog httpDialog;
    /**
     * 后台成功的返回码
     **/
    public static final String REQ_SUCCESS = "1";
    /**
     * android平台
     **/
    public static final String ANDROID = "android";

    public BaseRespHandler(XCIHttpNotify notify, Activity activity, Class<T> result_bean_class) {
        super(notify, activity, result_bean_class);
    }

    public BaseRespHandler(Activity activity, Class<T> result_bean_class) {
        super(activity, result_bean_class);
    }

    public BaseRespHandler(Class<T> result_bean_class) {
        super(result_bean_class);
    }

    public BaseRespHandler() {
        super();
    }

    /**
     * json的状态码
     */
    @Override
    public boolean onMatchAppStatusCode() {

        XCLog.i(XCConstant.TAG_RESP_HANDLER, this.toString() + "---onMatchAppStatusCode()");

        if (getResultBean() instanceof IHttpRespInfo && (getResultBean() instanceof BaseModel)) {

            if (UtilString.equalsStr(((IHttpRespInfo) getResultBean()).getCode(), REQ_SUCCESS)) {
                return true;
            } else {
                XCLog.shortToast(((IHttpRespInfo) getResultBean()).getMsg());
                return false;
            }

        } else {
            XCLog.e("yourCompanyResultRule()中的返回结果不是IHttpResponseInfo, BaseModel 或 BaseBean 类型");
            throw new RuntimeException("yourCompanyResultRule()中的返回结果不是IHttpResponseInfo ,BaseModel 或 BaseBean 类型");
        }

    }

    /**
     * 参数加密
     */
    @Override
    public Map<String, Object> onParamsSecret(Map<String, Object> oParams, boolean needSecret) {

        if (needSecret) {
            oParams.put("user_id", XTYXSP.getUserId());
            oParams.put("user_token", XTYXSP.getUserToken());
            oParams.put("api_sign", UtilMd5.MD5Encode(XTYXSP.getUserId() + ConfigFile.APP_NAME));
            oParams.put("clientype", ANDROID);
        }

        return oParams;
    }

    @Override
    public void onAddHeaders(XCIHttpClient client) {
        super.onAddHeaders(client);
        client.addHeader("_v", UtilSystem.getVersionCode(XCAppHelper.getAppContext()) + "");
        client.addHeader("_m", UtilSystem.getMacAddress(XCAppHelper.getAppContext()));
    }


    @Override
    public void onWillSendRequest() {
        showHttpDialog(new XCSystemHDialog(obtainActivity()));
    }

    public final void showHttpDialog(Dialog yourDialog) {

        if (!getHttpModel().isShowDialog() || obtainActivity() == null) {
            return;
        }

        if (httpDialog == null) {
            httpDialog = yourDialog;
            httpDialog.setCanceledOnTouchOutside(false);
            httpDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        closeHttpDialog();
                        XTYXHttp.resetNetingStatus();
                        if (!(obtainActivity() instanceof MainActivity)) {
                            obtainActivity().finish();
                        }
                    }
                    return false;
                }
            });
        }
        httpDialog.show();
        XCLog.i(XCConstant.TAG_RESP_HANDLER, this.toString() + "---showHttpDialog()");
    }

    public void closeHttpDialog() {
        if (httpDialog != null && httpDialog.isShowing()) {
            httpDialog.cancel();
            XCLog.i(XCConstant.TAG_RESP_HANDLER, this.toString() + "---closeHttpDialog()");
        }
    }

    @Override
    public void onEnd(XCRespType respResultType, T result_bean) {
        XTYXHttp.resetNetingStatus();
        super.onEnd(respResultType, result_bean);
        closeHttpDialog();
    }
}
