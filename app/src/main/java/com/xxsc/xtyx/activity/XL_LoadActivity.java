package com.xxsc.xtyx.activity;

import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.xxsc.xtyx.R;
import com.xxsc.xtyx.middle.base.BaseActivity;

/**
 * @version xilinch on 2016/5/12.
 * @modifier xilinch 2016/5/12 16:43.
 * @description
 */
public class XL_LoadActivity extends BaseActivity{
    /**背景图*/
    private ImageView activity_load_iv;
    /**登录*/
    private ImageView activity_load_login;
    /**注册   */
    private ImageView activity_load_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.xl_activity_load);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initWidgets() {
        super.initWidgets();
        activity_load_iv = getViewById(R.id.activity_load_iv);
        activity_load_login = getViewById(R.id.activity_load_login);
        activity_load_register = getViewById(R.id.activity_load_register);


    }

    @Override
    public void setListeners() {
        super.setListeners();
        activity_load_login.setOnClickListener(this);
        activity_load_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent ;
        switch (v.getId()){
            case R.id.activity_load_login:
                intent = new Intent(XL_LoadActivity.this,XL_LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_load_register:
                intent = new Intent(XL_LoadActivity.this,XL_RegisterActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
