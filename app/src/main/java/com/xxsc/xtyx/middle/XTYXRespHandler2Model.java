package com.xxsc.xtyx.middle;

import android.app.Activity;

import com.xiaocoder.android.fw.general.application.XCConstant;
import com.xiaocoder.android.fw.general.http.IHttp.XCIHttpNotify;
import com.xiaocoder.android.fw.general.io.XCLog;
import com.xiaocoder.android.fw.general.json.SXGsonParse;
import com.xxsc.xtyx.middle.parse.BaseModel;
import com.xxsc.xtyx.middle.parse.BaseRespHandler;

/**
 * gson 解析
 */
public class XTYXRespHandler2Model<T extends BaseModel> extends BaseRespHandler<T> {

    public XTYXRespHandler2Model(XCIHttpNotify notify, Activity activity, Class<T> result_bean_class) {
        super(notify, activity, result_bean_class);
    }

    public XTYXRespHandler2Model(Activity activity, Class<T> result_bean_class) {
        super(activity, result_bean_class);
    }

    public XTYXRespHandler2Model(Class<T> result_bean_class) {
        super(result_bean_class);
    }

    public XTYXRespHandler2Model() {
    }

    @Override
    public T onParse2Model(String responseStr, byte[] response_bytes) {

        XCLog.i(XCConstant.TAG_RESP_HANDLER, this.toString() + "-----parseWay()");

        return SXGsonParse.fromJson(responseStr, getResultBeanClass());
    }

}
