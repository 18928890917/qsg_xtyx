package com.xxsc.xtyx;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.xiaocoder.android.fw.general.exception.XCCrashHandler;
import com.xiaocoder.android.fw.general.exception.XCExceptionModelDb;
import com.xiaocoder.android.fw.general.function.helper.XCActivityHelper;
import com.xiaocoder.android.fw.general.io.XCLog;
import com.xxsc.xtyx.middle.base.BaseActivity;
import com.xxsc.xtyx.tab.CartFragment;
import com.xxsc.xtyx.tab.DiscoverFragment;
import com.xxsc.xtyx.tab.FirstFragment;
import com.xxsc.xtyx.tab.MyFragment;
import com.xxsc.xtyx.tab.OrderFragment;

/**
 * 首页继承该activity
 * 1 实现了双击返回键两次退出应用的效果
 * 2 初始化了友盟等第三方的
 * 3 上传异常信息到服务器
 * Created by xiaocoder on 2015/7/15.
 */
public class MainActivity extends BaseActivity {

    public static final int CLICK_QUICK_GAP = 1000;
    /**
     * 双击两次返回键退出应用
     */
    private long back_quit_time;

    private RadioGroup tab_group;
    /**
     * 记录选中了第几个tab
     */
    private int recoder_selected_tab_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.xc_l_activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidgets() {
        super.initWidgets();
        tab_group = getViewById(R.id.xc_id_tab_group);
    }

    @Override
    public void setData2Views() {
        super.setData2Views();
        addFragment(R.id.xc_id_model_content, new FirstFragment());
    }

    @Override
    public void setListeners() {
        super.setListeners();

        tab_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                hideBodyFragment();

                recoder_selected_tab_item = checkedId;

                if (checkedId == R.id.xc_id_tab_item1) {
                    showFragmentByClass(FirstFragment.class, R.id.xc_id_model_content);
                } else if (checkedId == R.id.xc_id_tab_item2) {
                    showFragmentByClass(DiscoverFragment.class, R.id.xc_id_model_content);
                } else if (checkedId == R.id.xc_id_tab_item3) {
                    showFragmentByClass(OrderFragment.class, R.id.xc_id_model_content);
                } else if (checkedId == R.id.xc_id_tab_item4) {
                    showFragmentByClass(MyFragment.class, R.id.xc_id_model_content);
                } else if (checkedId == R.id.xc_id_tab_item5) {
                    showFragmentByClass(CartFragment.class, R.id.xc_id_model_content);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        long this_quit_time = System.currentTimeMillis();
        if (this_quit_time - back_quit_time <= CLICK_QUICK_GAP) {
            XCActivityHelper.appExit();
        } else {
            back_quit_time = this_quit_time;
            XCLog.shortToast("快速再按一次退出");
        }
    }

    /**
     * 进入首页时或闪屏页，把未上传的异常信息上传到服务器
     */
    protected void uploadException() {
        XCExceptionModelDb exceptionModelDb = XCCrashHandler.getInstance().getExceptionModelDb();

        if (exceptionModelDb != null) {
            // TODO 每次启动时，将上一次的异常信息上传到服务器，每上传成功一条，更新model中的uploadSuccess为“1”
            XCLog.itemp(exceptionModelDb.queryCount());
            XCLog.itemp(exceptionModelDb.queryUploadFail(XCExceptionModelDb.SORT_DESC));
        }
    }
}
