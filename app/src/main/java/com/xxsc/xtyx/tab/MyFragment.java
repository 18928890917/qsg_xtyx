/**
 *
 */
package com.xxsc.xtyx.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxsc.xtyx.R;
import com.xxsc.xtyx.middle.base.BaseFragment;


public class MyFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 设置布局
		return init(inflater, R.layout.tab_fragment_my);
	}

}