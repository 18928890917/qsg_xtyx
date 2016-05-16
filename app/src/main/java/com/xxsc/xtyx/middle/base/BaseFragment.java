package com.xxsc.xtyx.middle.base;

import android.os.Bundle;

import com.xiaocoder.android.fw.general.application.XCFragment;

/**
 * Created by xiaocoder on 2015/7/15.
 */
public abstract class BaseFragment extends XCFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initWidgets();
        setListeners();
        setData2Views();

    }

    /**
     * 这里不要用abstract
     */
    protected void initWidgets() {

    }

    /**
     * 这里不要用abstract
     */
    protected void setListeners() {

    }

    /**
     * 这里不要用abstract
     */
    protected void setData2Views() {

    }


}
