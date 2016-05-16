package com.xxsc.xtyx.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaocoder.android.fw.general.http.XCRespType;
import com.xiaocoder.android.fw.general.io.XCLog;
import com.xiaocoder.android.fw.general.util.UtilActivity;
import com.xiaocoder.android.fw.general.util.UtilString;
import com.xiaocoder.views.view.xc.XCTitleCommonLayout;
import com.xxsc.xtyx.MainActivity;
import com.xxsc.xtyx.R;
import com.xxsc.xtyx.middle.XTYXHttp;
import com.xxsc.xtyx.middle.XTYXRespHandler2Model;
import com.xxsc.xtyx.middle.XTYXSP;
import com.xxsc.xtyx.middle.base.BaseActivity;
import com.xxsc.xtyx.middle.config.ConfigUrl;
import com.xxsc.xtyx.model.XL_CheckCodeModel;
import com.xxsc.xtyx.model.XL_RegModel;
import java.util.HashMap;
import java.util.Map;

/**
 * @version xilinch on 2016/4/7.
 * @modifier xilinch 2016/4/7 1:11.
 * @description 注册界面
 */
public class XL_RegisterActivity extends BaseActivity {

    /** title */
    private XCTitleCommonLayout xc_id_model_titlebar;
    /**手机号码 */
    private EditText xl_id_activity_register_et_number;
    /**发送验证码 */
    private TextView xl_id_activity_register_captcha;
    /**手机验证码 */
    private EditText xl_id_activity_register_et_captcha;
    /**密码 */
    private EditText xl_id_activity_register_et_pwd;
    /**注册 */
    private Button xl_id_activity_register_btn_register;

    /**
     * 计时器
     */
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.xl_l_activity_register);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initWidgets() {
        super.initWidgets();
        xc_id_model_titlebar = getViewById(R.id.xc_id_model_titlebar);
        xc_id_model_titlebar.getXc_id_titlebar_left_imageview().setBackgroundResource(R.mipmap.fanhui);
        xc_id_model_titlebar.getXc_id_titlebar_center_textview().setText("注册");
        xc_id_model_titlebar.getXc_id_titlebar_center_textview().setTextColor(getResources().getColor(R.color.black_44484C));
        xc_id_model_titlebar.getXc_id_titlebar_common_layout().setBackgroundColor(getResources().getColor(R.color.c_white_ffffff));


        xl_id_activity_register_et_number = getViewById(R.id.xl_id_activity_register_et_number);
        xl_id_activity_register_captcha = getViewById(R.id.xl_id_activity_register_captcha);
        xl_id_activity_register_et_captcha = getViewById(R.id.xl_id_activity_register_et_captcha);
        xl_id_activity_register_et_pwd = getViewById(R.id.xl_id_activity_register_et_pwd);
        xl_id_activity_register_btn_register = getViewById(R.id.xl_id_activity_register_btn_register);


    }

    @Override
    public void setListeners() {
        super.setListeners();
        xl_id_activity_register_et_number.setOnClickListener(this);
        xl_id_activity_register_captcha.setOnClickListener(this);
        xl_id_activity_register_et_captcha.setOnClickListener(this);
        xl_id_activity_register_et_pwd.setOnClickListener(this);
        xl_id_activity_register_btn_register.setOnClickListener(this);
        xc_id_model_titlebar.getXc_id_titlebar_left_imageview().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            // 短信验证码
            case R.id.xl_id_activity_register_captcha:
                sendCheckCode();
                break;
            // 注册并且登录
            case R.id.xl_id_activity_register_btn_register:
                registerAndLogin();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    /**
     * 注册且登录
     * 1.手机号码11位
     * 2.验证码6位
     * 3.密码6位以上含
     */
    private void registerAndLogin() {
        String phone = xl_id_activity_register_et_number.getText().toString().trim();
        if (UtilString.isPhoneNum(phone)) {
            String captCha = xl_id_activity_register_captcha.getText().toString().trim();
            if (captCha.length() == 6) {
                String pwd = xl_id_activity_register_et_pwd.getText().toString().trim();
                if (pwd.length() >= 6) {
                    requestReg(phone, pwd, captCha);
                } else {
                    XCLog.shortToast("请输入6位以上密码!");
                }
            } else {
                XCLog.shortToast("请输入6位验证码!");
            }
        } else {
            XCLog.shortToast("请输入正确的手机号码!");
        }
    }

    /**
     * 发送手机注册验证码
     */
    private void sendCheckCode() {
        String phone = xl_id_activity_register_et_number.getText().toString();
        if (UtilString.isPhoneNum(phone)) {
            xl_id_activity_register_captcha.setEnabled(false);
            requestCheckCode(1, phone);
        } else {
            //请输入合法的电话号码
            XCLog.shortToast("请输入合法的电话号码!");
        }
    }

    /**
     * 注册
     *
     * @param type 类型 1注册 2动态密码登录
     */
    private void requestCheckCode(int type, String phone) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", phone);
        params.put("type", type);
        XTYXHttp.getAsyn(true, ConfigUrl.getUrl(ConfigUrl.send_captcha), params, new XTYXRespHandler2Model<XL_CheckCodeModel>(this, XL_CheckCodeModel.class) {

            @Override
            public void onSuccessAll(int code, Map<String, Object> headers, byte[] arg2, XL_CheckCodeModel resultBean) {
                super.onSuccessAll(code, headers, arg2, resultBean);
                //发送成功
                int captcha = resultBean.getDatas().getCaptcha();
                //TODO 测试代码
                xl_id_activity_register_et_captcha.setText(captcha + "");
                //测试代码--end
                uiCountDownTimer();

            }

            @Override
            public void onEnd(XCRespType respResultType, XL_CheckCodeModel result_bean) {
                super.onEnd(respResultType, result_bean);
            }
        });

    }


    /**
     * UI显示倒计时
     */
    private void uiCountDownTimer() {
        //获取验证码以后开始倒计时60秒
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                xl_id_activity_register_captcha.setText(getString(R.string.get_captcha) +
                        "("+ millisUntilFinished / 1000 + "秒" + ")");
            }

            @Override
            public void onFinish() {
                xl_id_activity_register_captcha.setText(getString(R.string.get_captcha));
                xl_id_activity_register_captcha.setEnabled(true);
            }
        };
        countDownTimer.start();
    }

    /**
     * 注册请求
     *
     * @param phone   电话
     * @param pwd     密码
     * @param captcha 验证码
     */
    private void requestReg(final String phone, String pwd, String captcha) {
        xl_id_activity_register_btn_register.setEnabled(false);
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("password", pwd);
        map.put("captcha", captcha);
        XTYXHttp.getAsyn(true, ConfigUrl.getUrl(ConfigUrl.reg), map, new XTYXRespHandler2Model<XL_RegModel>(this, XL_RegModel.class) {

            @Override
            public void onSuccessAll(int code, Map<String, Object> headers, byte[] arg2, XL_RegModel resultBean) {
                super.onSuccessAll(code, headers, arg2, resultBean);
                String userId = resultBean.getDatas().getUser_id();
                String img = resultBean.getDatas().getImg();
                String user_Token = resultBean.getDatas().getUser_token();
                XTYXSP.setUserPhoneNum(phone);
                XTYXSP.setUserId(userId);
                XTYXSP.setUserToken(user_Token);
                XTYXSP.setHeadimg(img);
                UtilActivity.startActivity(XL_RegisterActivity.this, MainActivity.class);
                XL_RegisterActivity.this.finish();
            }

            @Override
            public void onSuccessButCodeWrong(int httpCode, Map<String, Object> headers, byte[] bytes, XL_RegModel resultBean) {
                super.onSuccessButCodeWrong(httpCode, headers, bytes, resultBean);

            }

            @Override
            public void onSuccessButParseWrong(int httpCode, Map<String, Object> headers, byte[] bytes) {
                super.onSuccessButParseWrong(httpCode, headers, bytes);

            }

            @Override
            public void onEnd(XCRespType respResultType, XL_RegModel result_bean) {
                super.onEnd(respResultType, result_bean);
                xl_id_activity_register_btn_register.setEnabled(true);
            }

            @Override
            public void onFailure(int code, Map<String, Object> headers, byte[] bytes, Throwable e) {
                super.onFailure(code, headers, bytes, e);
            }
        });
    }
}
