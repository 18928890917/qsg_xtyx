package com.xxsc.xtyx.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaocoder.android.fw.general.io.XCLog;
import com.xiaocoder.android.fw.general.util.UtilString;
import com.xiaocoder.views.view.xc.XCTitleCommonLayout;
import com.xxsc.xtyx.R;
import com.xxsc.xtyx.middle.base.BaseActivity;

import java.util.HashMap;

/**
 * @version wayne on 5/10/16.
 * @description 重置密码页
 */
public class QSG_GetPasswordActivity extends BaseActivity {


    /**
     * the Title bar
     */
    private XCTitleCommonLayout qsg_activity_get_password_title_bar;

    /**
     * the EditText for phone number
     */
    private EditText qsg_activity_get_password_et_phone_number;

    /**
     * input of the get sms code
     */
    private EditText qsg_activity_get_password_et_sms_code;

    /**
     * TextView display of the sms request
     */
    private TextView qsg_activity_get_password_tv_request_check_code;

    /**
     * reset pwd
     */
    private EditText qsg_activity_get_password_et_pwd;

    /**
     * confirm pwd reset
     */
    private EditText qsg_activity_get_password_confirm_et_pwd;


    /**
     * confirm button
     */
    private Button qsg_activity_get_password_btn_get_pwd;

    /**
     * count down timer
     */
    private CountDownTimer countDownTimer;
    /**
     * 验证码 1注册 2动态密码登录 重置密码？？？
     */
    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.qsg_activity_get_password);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidgets() {
        super.initWidgets();
        qsg_activity_get_password_title_bar = getViewById(R.id.qsg_activity_get_password_title_bar);
        qsg_activity_get_password_et_phone_number = getViewById(R.id.qsg_activity_get_password_et_phone_number);
        qsg_activity_get_password_et_sms_code = getViewById(R.id.qsg_activity_get_password_et_sms_code);
        qsg_activity_get_password_tv_request_check_code = getViewById(R.id.qsg_activity_get_password_tv_request_check_code);
        qsg_activity_get_password_et_pwd = getViewById(R.id.qsg_activity_get_password_et_pwd);

        qsg_activity_get_password_confirm_et_pwd = getViewById(R.id.qsg_activity_get_password_confirm_et_pwd);

        qsg_activity_get_password_btn_get_pwd = getViewById(R.id.qsg_activity_get_password_btn_get_pwd);
        setTitleBar();

    }

    private void setTitleBar(){
        qsg_activity_get_password_title_bar.setBackgroundDrawable(null);
        qsg_activity_get_password_title_bar.setTitleLeft(R.mipmap.fanhui, null);
        qsg_activity_get_password_title_bar.setTitleCenter(true,"找回密码");
        qsg_activity_get_password_title_bar.getXc_id_titlebar_center_textview()
                .setTextColor(getResources().getColor(R.color.black_44484C));
    }

    @Override
    public void setListeners() {
        super.setListeners();

        qsg_activity_get_password_tv_request_check_code.setOnClickListener(this);
        qsg_activity_get_password_btn_get_pwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
//            // 记得密码，重新登陆
//            case R.id.qsg_activity_get_password_rb_back_login:
//                Intent intent_login = new Intent(QSG_GetPasswordActivity.this, XL_LoginActivity.class);
//                startActivity(intent_login);
//                finish();
//                break;
            // 短信验证码
            case R.id.qsg_activity_get_password_tv_request_check_code:
                sendCheckCode();
                break;
            case R.id.qsg_activity_get_password_btn_get_pwd:
                submitRequestRestPWD();
                break;


        }
    }


    /**
     * submit to request the reset password
     */
    private void submitRequestRestPWD(){
        if(!checkConfirmPWD()){
            XCLog.shortToast("密码输入不一致，请重新输入!");
            return;
        }
        // todo call api to reset the password
        XCLog.shortToast("request api");
    }

    /**
     * send the checkcode
     */
    private void sendCheckCode() {
        String phone = qsg_activity_get_password_et_phone_number.getText().toString();
        if (UtilString.isPhoneNum(phone)) {
            qsg_activity_get_password_tv_request_check_code.setEnabled(false);
            requestCheckCode(type, phone);
        } else {
            //请输入合法的电话号码
            XCLog.shortToast("请输入合法的电话号码!");
        }
    }

    /**
     * verify code request
     * what is the get password type????
     * @param type 类型 1注册 2动态密码登录 重置密码？？？
     */
    private void requestCheckCode(int type, String phone) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", phone);
        params.put("type", type);
        // start count down
        uiCountDownTimer();

//        XTYXHttp.getAsyn(true, XTYXConfig.getUrl(XTYXConfig.send_captcha), params, new XTYXRespHandler2Bean<CheckCodeBean>(this, CheckCodeBean.class) {
//
//            @Override
//            public void success(int code, Map<String, Object> headers, byte[] arg2, CheckCodeBean resultBean) {
//                super.success(code, headers, arg2, resultBean);
//                //发送成功
//                String captcha = resultBean.getCaptcha(); // 获得验证码
//                //TODO verify the check code(captcha code) in here
//               // qsg_activity_get_password_et_sms_code.setText(captcha);
//                //测试代码--end
//
//
//            }
//
//            @Override
//            public void finish(XCRespResultType respResultType, CheckCodeBean result_bean) {
//                super.finish(respResultType, result_bean);
//            }
//        });

    }

    /**
     * to check of the confirm password input is the same as the reset password input
     * @return it it is the same
     */
    private boolean checkConfirmPWD(){
        return qsg_activity_get_password_et_pwd.getText().toString().trim().equals(qsg_activity_get_password_confirm_et_pwd.getText().toString().trim())
                && !qsg_activity_get_password_et_pwd.getText().toString().isEmpty();
    }

    /**
     * UI显示倒计时
     */
    private void uiCountDownTimer() {
        //获取验证码以后开始倒计时60秒
        countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                qsg_activity_get_password_tv_request_check_code.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                qsg_activity_get_password_tv_request_check_code.setText("获取验证码");
                qsg_activity_get_password_tv_request_check_code.setEnabled(true);
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}
