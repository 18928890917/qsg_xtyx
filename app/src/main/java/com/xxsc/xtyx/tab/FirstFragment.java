/**
 *
 */
package com.xxsc.xtyx.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.xiaocoder.android.fw.general.http.XCRespType;
import com.xiaocoder.android.fw.general.io.XCLog;
import com.xiaocoder.viewpagerslider.XCAutoViewPagerLayout;
import com.xiaocoder.views.view.xc.XCImageView;
import com.xiaocoder.views.view.xc.XCTitleCommonLayout;
import com.xxsc.xtyx.R;
import com.xxsc.xtyx.adapter.SK_IndexNewAdapter;
import com.xxsc.xtyx.adapter.SK_IndexPraiseAdapter;
import com.xxsc.xtyx.adapter.SK_IndexSpecialAdapter;
import com.xxsc.xtyx.middle.XTYXHttp;
import com.xxsc.xtyx.middle.XTYXImage;
import com.xxsc.xtyx.middle.XTYXRespHandler2Model;
import com.xxsc.xtyx.middle.base.BaseFragment;
import com.xxsc.xtyx.middle.config.ConfigUrl;
import com.xxsc.xtyx.model.SK_IndexModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description 首页
 */
public class FirstFragment extends BaseFragment implements View.OnClickListener {
    private ScrollView sk_index_sv;

    private XCTitleCommonLayout xc_id_model_titlebar;
    /**
     * 首页轮播广告栏
     */
    private XCAutoViewPagerLayout sk_vp_banner_viewpager;
    /**
     * 特别推荐listview
     */
    private ListView sk_lv_special;
    /**
     * 最新推荐listview
     */
    private ListView sk_lv_new;
    /**
     * 朋友都赞listview
     */
    private ListView sk_lv_praise;
    /**
     * 图一:左上广告图
     */
    private XCImageView sk_iv_advertisement_01;
    /**
     * 图二:右上广告图
     */
    private XCImageView sk_iv_advertisement_02;
    /**
     * 图三:左下广告图
     */
    private XCImageView sk_iv_advertisement_03;
    /**
     * 图四:右下广告图
     */
    private XCImageView sk_iv_advertisement_04;
    /**
     * 特别推荐数据
     */
    private List<SK_IndexModel.DatasEntity.GoodsEntity.SpecialEntity> mSpecialList = new ArrayList<>();
    /**
     * 最新推荐数据
     */
    private List<SK_IndexModel.DatasEntity.GoodsEntity.NewEntity> mNewList = new ArrayList<>();
    /**
     * 好友都赞数据
     */
    private List<SK_IndexModel.DatasEntity.GoodsEntity.PraiseEntity> mPraiseList = new ArrayList<>();

    private SK_IndexSpecialAdapter mSpecialAdapter;
    private SK_IndexNewAdapter mNewAdapter;
    private SK_IndexPraiseAdapter mPraiseAdapter;

    /**
     * 屏幕当前滚动的坐标
     */
    private int scrollX;
    /**
     * 屏幕当前滚动的坐标
     */
    private int scrollY;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 设置布局
        return init(inflater, R.layout.tab_fragment_firstpage);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            sk_index_sv.smoothScrollTo(scrollX, scrollY);
        } else {
            scrollX = sk_index_sv.getScrollX();
            scrollY = sk_index_sv.getScrollY();
        }

    }

    @Override
    protected void initWidgets() {
        super.initWidgets();

        sk_index_sv = getViewById(R.id.sk_index_sv);
        xc_id_model_titlebar = getViewById(R.id.xc_id_model_titlebar);
        sk_vp_banner_viewpager = getViewById(R.id.sk_vp_banner_viewpager);
        sk_lv_special = getViewById(R.id.sk_lv_special);
        sk_lv_new = getViewById(R.id.sk_lv_new);
        sk_lv_praise = getViewById(R.id.sk_lv_praise);
        sk_iv_advertisement_01 = getViewById(R.id.sk_iv_advertisement_01);
        sk_iv_advertisement_02 = getViewById(R.id.sk_iv_advertisement_02);
        sk_iv_advertisement_03 = getViewById(R.id.sk_iv_advertisement_03);
        sk_iv_advertisement_04 = getViewById(R.id.sk_iv_advertisement_04);

        mSpecialAdapter = new SK_IndexSpecialAdapter(getActivity(), mSpecialList);
        mNewAdapter = new SK_IndexNewAdapter(getActivity(), mNewList);
        mPraiseAdapter = new SK_IndexPraiseAdapter(getActivity(), mPraiseList);

        sk_lv_special.setAdapter(mSpecialAdapter);
        sk_lv_new.setAdapter(mNewAdapter);
        sk_lv_praise.setAdapter(mPraiseAdapter);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        sk_iv_advertisement_01.setOnClickListener(this);
        sk_iv_advertisement_02.setOnClickListener(this);
        sk_iv_advertisement_03.setOnClickListener(this);
        sk_iv_advertisement_04.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 图一:左上广告图
            case R.id.sk_iv_advertisement_01:
                XCLog.dShortToast("图一:左上广告图");
                break;
            // 图二:右上广告图
            case R.id.sk_iv_advertisement_02:
                XCLog.dShortToast("图二:右上广告图");
                break;
            // 图三:左下广告图
            case R.id.sk_iv_advertisement_03:
                XCLog.dShortToast("图三:左下广告图");
                break;
            // 图四:右下广告图
            case R.id.sk_iv_advertisement_04:
                XCLog.dShortToast("图四:右下广告图");
                break;
            default:
                break;

        }
    }

    @Override
    protected void setData2Views() {
        super.setData2Views();
        requestData();
        xc_id_model_titlebar.setTitleCenter(true, "新特优选");
        xc_id_model_titlebar.setBackgroundColor(getResources().getColor(R.color.title_color));
    }

    /**
     * 设置数据到控件
     *
     * @param datasEntity 服务器请求回来的数据
     */
    public void setDataToViews(SK_IndexModel.DatasEntity datasEntity) {
        // banner图片资源
        List<SK_IndexModel.DatasEntity.SlideEntity> list = datasEntity.getSlide();
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put("" + i, list.get(i).getImg());
        }
        sk_vp_banner_viewpager.setScale(BaseSliderView.ScaleType.CenterInside);
        // 是否需要描述
        sk_vp_banner_viewpager.setIsNeedDes(false);
        // 设置指示器的动画效果
        sk_vp_banner_viewpager.setAnim(null);
        // 图片的url
        sk_vp_banner_viewpager.setMap(map);


        SK_IndexModel.DatasEntity.GoodsEntity goodsEntity = datasEntity.getGoods();

        // 特别推荐列表的数据
        mSpecialList = goodsEntity.getSpecial();
        mSpecialAdapter.update(mSpecialList);
        mSpecialAdapter.notifyDataSetChanged();

        // 最新推荐列表的数据
        mNewList = goodsEntity.getNewX();
        mNewAdapter.update(mNewList);
        mNewAdapter.notifyDataSetChanged();

        // 朋友都赞列表的数据
        mPraiseList = goodsEntity.getPraise();
        mPraiseAdapter.update(mPraiseList);
        mPraiseAdapter.notifyDataSetChanged();

        // 底部四张广告图
        List<SK_IndexModel.DatasEntity.ActivityEntity> activityEntityList = datasEntity.getActivity();
        if (activityEntityList.size() >= 1)
            XTYXImage.displayImage(activityEntityList.get(0).getImg(), sk_iv_advertisement_01);
        if (activityEntityList.size() >= 2)
            XTYXImage.displayImage(activityEntityList.get(1).getImg(), sk_iv_advertisement_02);
        if (activityEntityList.size() >= 3)
            XTYXImage.displayImage(activityEntityList.get(2).getImg(), sk_iv_advertisement_03);
        if (activityEntityList.size() >= 4)
            XTYXImage.displayImage(activityEntityList.get(3).getImg(), sk_iv_advertisement_04);
    }

    /**
     * 请求首页接口
     */
    public void requestData() {
        Map<String, Object> map = new HashMap<>();
        XTYXHttp.postAsyn(ConfigUrl.getUrl(ConfigUrl.index), map, new XTYXRespHandler2Model<SK_IndexModel>(getXCActivity(), SK_IndexModel.class) {
            @Override
            public void onSuccessAll(int code, Map<String, Object> headers, byte[] arg2, SK_IndexModel resultBean) {
                super.onSuccessAll(code, headers, arg2, resultBean);
                setDataToViews(resultBean.getDatas());
            }

            @Override
            public void onEnd(XCRespType respResultType, SK_IndexModel result_bean) {
                super.onEnd(respResultType, result_bean);
                sk_index_sv.smoothScrollTo(0, 0);
            }
        });

    }
}