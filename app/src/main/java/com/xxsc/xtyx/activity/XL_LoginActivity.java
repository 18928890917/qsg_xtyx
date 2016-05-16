package com.xxsc.xtyx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.xxsc.xtyx.model.XL_LoginModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @version xilinch on 2016/4/7.
 * @modifier xilinch 2016/4/6 0:45.
 * @description 登录界面
 */
public class XL_LoginActivity extends BaseActivity {

    /** title */
    private XCTitleCommonLayout xc_id_model_titlebar;
    /**手机号码 */
    private EditText xl_id_activity_login_et_number;
    /**密码、动态码 */
    private EditText xl_id_activity_login_et_pwd;
    /**忘记密码 */
    private TextView xl_id_activity_login_forget_psd;
    /**登录 */
    private Button xl_id_activity_login_btn_login;
    /**注册新用户 */
    private TextView xl_id_activity_login_tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.xl_l_activity_login);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidgets() {
        super.initWidgets();
        xc_id_model_titlebar = getViewById(R.id.xc_id_model_titlebar);
        xl_id_activity_login_et_number = getViewById(R.id.xl_id_activity_login_et_number);
        xl_id_activity_login_et_pwd = getViewById(R.id.xl_id_activity_login_et_pwd);
        xl_id_activity_login_forget_psd = getViewById(R.id.xl_id_activity_login_forget_psd);
        xl_id_activity_login_btn_login = getViewById(R.id.xl_id_activity_login_btn_login);
        xl_id_activity_login_tv_register = getViewById(R.id.xl_id_activity_login_tv_register);
        xl_id_activity_login_et_number.setText(XTYXSP.getUserPhoneNum());
    }

    @Override
    public void setListeners() {
        super.setListeners();
        xl_id_activity_login_et_pwd.setOnClickListener(this);
        xl_id_activity_login_forget_psd.setOnClickListener(this);
        xl_id_activity_login_btn_login.setOnClickListener(this);
        xl_id_activity_login_tv_register.setOnClickListener(this);
        xc_id_model_titlebar.getXc_id_titlebar_left_imageview().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setData2Views() {
        super.setData2Views();
        xc_id_model_titlebar.getXc_id_titlebar_left_imageview().setBackgroundResource(R.mipmap.fanhui);
        xc_id_model_titlebar.getXc_id_titlebar_center_textview().setText("登录");
        xc_id_model_titlebar.getXc_id_titlebar_center_textview().setTextColor(getResources().getColor(R.color.black_44484C));
        xc_id_model_titlebar.getXc_id_titlebar_common_layout().setBackgroundColor(getResources().getColor(R.color.c_white_ffffff));

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            // 忘记密码
            case R.id.xl_id_activity_login_forget_psd:
                Intent intent_getpwd = new Intent(XL_LoginActivity.this, QSG_GetPasswordActivity.class);
                startActivity(intent_getpwd);
                break;
            // 登录
            case R.id.xl_id_activity_login_btn_login:
                login();
                break;
            // 注册新用户
            case R.id.xl_id_activity_login_tv_register:
                Intent intent_reg = new Intent(XL_LoginActivity.this, XL_RegisterActivity.class);
                startActivity(intent_reg);
                XL_LoginActivity.this.finish();
                break;
            default:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 登录
     */
    private void login() {
        String phone = xl_id_activity_login_et_number.getText().toString().trim();
        if (UtilString.isPhoneNum(phone)) {
            String pwd = xl_id_activity_login_et_pwd.getText().toString().trim();
            //优选账号登录
            if (pwd.length() >= 6) {
                requestLogin(phone, pwd);
            } else {
                XCLog.shortToast("请输入6位以上密码!");
            }

        } else {
            XCLog.shortToast("请输入正确的手机号码!");
        }
    }

    /**
     * 登录
     *
     * @param phone 电话
     * @param pwd   密码
     */
    private void requestLogin(final String phone, String pwd) {
        xl_id_activity_login_btn_login.setEnabled(false);
        Map<String, Object> map = new HashMap<>();
        map.put("username", phone);
        map.put("password", pwd);

        XTYXHttp.getAsyn(ConfigUrl.getUrl(ConfigUrl.login), map, new XTYXRespHandler2Model<XL_LoginModel>(this, XL_LoginModel.class) {

            @Override
            public void onSuccessAll(int code, Map<String, Object> headers, byte[] arg2, XL_LoginModel resultBean) {
                super.onSuccessAll(code, headers, arg2, resultBean);

                String userId = resultBean.getDatas().getUser_id();
                String img = resultBean.getDatas().getImg();
                String user_Token = resultBean.getDatas().getUser_token();
                XTYXSP.setUserPhoneNum(phone);
                XTYXSP.setUserId(userId);
                XTYXSP.setUserToken(user_Token);
                XTYXSP.setHeadimg(img);
                UtilActivity.startActivity(XL_LoginActivity.this, MainActivity.class);
                XL_LoginActivity.this.finish();
            }

            @Override
            public void onEnd(XCRespType respResultType, XL_LoginModel result_bean) {
                super.onEnd(respResultType, result_bean);
                xl_id_activity_login_btn_login.setEnabled(true);
            }
        });
    }
}
