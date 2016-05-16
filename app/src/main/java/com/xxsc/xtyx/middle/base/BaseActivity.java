package com.xxsc.xtyx.middle.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaocoder.android.fw.general.application.XCActivity;
import com.xiaocoder.android.fw.general.http.IHttp.XCIRespHandler;
import com.xiaocoder.android.fw.general.io.XCLog;
import com.xiaocoder.android.fw.general.util.UtilBroadcast;
import com.xiaocoder.android.fw.general.util.UtilView;
import com.xiaocoder.views.view.open.OPSwipeBackLayout;
import com.xiaocoder.views.view.open.OPSwitchButton;
import com.xiaocoder.views.view.pf.PFHorizontalListView;
import com.xiaocoder.views.view.xc.XCMoveView;
import com.xiaocoder.views.view.xc.XCRecordVoiceButton;
import com.xiaocoder.views.view.xc.XCRecordVoiceButtonPlus;
import com.xxsc.xtyx.R;
import com.xxsc.xtyx.middle.XTYXHttp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaocoder on 2015/7/15.
 * <p/>
 * 无网络的界面样式与重刷新、侧滑销毁activity、状态栏导航栏的样子设置等
 */
public abstract class BaseActivity extends XCActivity implements View.OnClickListener {

    /**
     * showModelLayout会找该id的布局，如果不用该id，不会异常，但是showModelLayout（）就会无效
     */
    public ViewGroup xc_id_model_titlebar;
    /**
     * 1 showModelLayout会找该id的布局，如果不用该id，不会异常，但是showModelLayout（）就会无效
     * 2 无网络背景会默认找该id，如果不用该id，不会异常，但是无网络背景的功能无法自动使用
     */
    public ViewGroup xc_id_model_content;
    /**
     * 向右滑动，销毁activity
     */
    public OPSwipeBackLayout back_layout;
    /**
     * 记录网络失败的请求，待重刷新
     */
    private XCIRespHandler recoderNetFailHandler;
    /**
     * 无网络时显示的界面
     */
    private ViewGroup xc_id_model_no_net;

    /**
     * 是否有网络的回调，可能统一处理应用对网络转换的逻辑
     */
    private BroadcastReceiver mNetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();

            boolean hasConnectivity = info != null && info.isConnected();

            if (hasConnectivity) {
                XCLog.dLongToast("有网");
                if (mNetListener != null) {
                    mNetListener.onNetNormal();
                }
            } else {
                XCLog.dLongToast("无网");
                if (mNetListener != null) {
                    mNetListener.onNetLoss();
                }
            }
        }
    };

    interface OnNetChangeListener {
        void onNetLoss();

        void onNetNormal();
    }

    OnNetChangeListener mNetListener;

    public void setOnNetChangeListener(OnNetChangeListener listener) {
        mNetListener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 注册网络广播
        initReceiver();
        // 侧滑销毁activity的设置
        // initSlideDestroyActivity();
        // 无网络背景
        initNoNetBackground();
        // title 和 content
        initModelLayout();

        initWidgets();
        setListeners();
        setData2Views();

        // 显示title 和 content，默认效果是一创建activity就显示布局
        // 如果需求是网络没有返回之前，不显示布局，则oncreate里不调用该方法，待网络成功后再调用，可以统一在http的基类里面加
        showModelLayout();

    }

    @Override
    public void addFragment(int layout_id, Fragment fragment, String tag, boolean isToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 增加了动画效果
        ft.setCustomAnimations(R.anim.xc_anim_alpha_in, R.anim.xc_anim_alpha);
        ft.add(layout_id, fragment, tag);
        if (isToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }

    // 之前必须有add
    @Override
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 增加了动画效果
        ft.setCustomAnimations(R.anim.xc_anim_alpha_in, R.anim.xc_anim_alpha);
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }


    private void initReceiver() {
        UtilBroadcast.register(this, 1000, ConnectivityManager.CONNECTIVITY_ACTION, mNetReceiver);
    }

    private void unbindReceiver() {
        UtilBroadcast.unRegister(this, mNetReceiver);
    }

    public void showTitleLayout(boolean isShow) {
        if (xc_id_model_titlebar != null) {
            UtilView.setGone(isShow, xc_id_model_titlebar);
        }
    }

    public void showContentLayout(boolean isShow) {
        if (xc_id_model_content != null) {
            UtilView.setGone(isShow, xc_id_model_content);
        }
    }

    /**
     * 在onCreate()中调用了， 显示title和content的布局
     * <p/>
     * 如果需要在网络没有返回时，不显示content布局，则重写showModelLayout
     */
    public void showModelLayout() {
        showTitleLayout(true);
        showContentLayout(true);
    }

    private void initModelLayout() {
        xc_id_model_titlebar = getViewById(R.id.xc_id_model_titlebar);
        xc_id_model_content = getViewById(R.id.xc_id_model_content);
    }

    /**
     * 这里不要用abstract
     */
    public void initWidgets() {

    }

    /**
     * 这里不要用abstract
     */
    public void setListeners() {

    }

    /**
     * 这里不要用abstract
     */
    public void setData2Views() {

    }

    /**
     * 无网络背景控件
     */
    protected void initNoNetBackground() {
        xc_id_model_no_net = getViewById(R.id.xc_id_model_no_net);

        if (xc_id_model_no_net != null) {
            xc_id_model_no_net.setOnClickListener(this);
        }
    }

    /**
     * 无网络的背景
     */
    public void showNoNetLayout(boolean visiable) {
        if (xc_id_model_no_net != null) {
            UtilView.setGone(visiable, xc_id_model_no_net);
        }
    }

    /**
     * 网络访问失败时, 回调该方法
     */
    public void onNetFail(XCIRespHandler resHandler, boolean show_background_when_net_fail) {

        setRecoderNetFailHandler(resHandler);

        if (show_background_when_net_fail) {
            // 隐藏内容的背景
            showContentLayout(false);
            // 显示网络失败的背景
            showNoNetLayout(true);
        } else {
            // 不显示网络失败的背景
            showNoNetLayout(false);
            // 显示内容的背景
            showContentLayout(true);
        }
    }

    /**
     * 网络访问成功时，回调该方法
     */
    public void onNetSuccess(XCIRespHandler resHandler) {
        showNoNetLayout(false);
        showContentLayout(true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.xc_id_model_no_net) {
            // 点击无网络界面刷新
            refreshNetFailHandler();
        }
    }

    /**
     * 手势滑动退出activity的基类布局
     */
    private void initSlideDestroyActivity() {

        if (interceptSlideDestroyActivityFunction(this)) {
            return;
        }

        back_layout = ((OPSwipeBackLayout) LayoutInflater.from(this).inflate(R.layout.baseactivity_swipe_back, null));

        // 把你项目中与侧滑控件的事件有冲突控件的名字加到该集合中
        List<String> list = new LinkedList<>();
        list.add(XCMoveView.class.getSimpleName());
        list.add(OPSwitchButton.class.getSimpleName());
        list.add(ViewPager.class.getSimpleName());
        list.add(XCRecordVoiceButtonPlus.class.getSimpleName());
        list.add(XCRecordVoiceButton.class.getSimpleName());
        list.add(PFHorizontalListView.class.getSimpleName());

        back_layout.setInterceptClassNames(list);

        back_layout.attachToActivity(this);

    }

    /**
     * 有的页面可能不需要滑动销毁activity的功能,基类里统一控制
     *
     * @param baseActivity
     * @return
     */
    protected boolean interceptSlideDestroyActivityFunction(BaseActivity baseActivity) {
        return true;
    }

    /**
     * 刷新上一次网络失败的请求
     */
    public void refreshNetFailHandler() {
        XTYXHttp.sendHttpRequest(recoderNetFailHandler);
    }

    public XCIRespHandler getRecoderNetFailHandler() {
        return recoderNetFailHandler;
    }

    /**
     * 记录最近一次网络失败的请求
     */
    public void setRecoderNetFailHandler(XCIRespHandler recoderNetFailHandler) {
        this.recoderNetFailHandler = recoderNetFailHandler;
    }

    @Override
    protected void onDestroy() {
        recoderNetFailHandler = null;
        unbindReceiver();
        XTYXHttp.resetNetingStatus();
        super.onDestroy();
    }

    /**
     * activity进入动画
     */
    public void activityStartAnimation() {
        // overridePendingTransition(R.anim.xc_anim_right_in, R.anim.xc_anim_left_out);  //此为自定义的动画效果，下面两个为系统的动画效果
        overridePendingTransition(com.xiaocoder.android_fw_general.R.anim.baseactivity_slide_right_in, com.xiaocoder.android_fw_general.R.anim.baseactivity_slide_remain);
        //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    /**
     * activity的退出动画
     */
    public void activityEndAnimation() {
        overridePendingTransition(0, com.xiaocoder.android_fw_general.R.anim.baseactivity_slide_right_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        activityEndAnimation();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        activityStartAnimation();
    }
}
