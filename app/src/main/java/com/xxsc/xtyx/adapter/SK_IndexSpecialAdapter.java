package com.xxsc.xtyx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaocoder.android.fw.general.function.adapter.XCBaseAdapter;
import com.xiaocoder.views.view.xc.XCImageView;
import com.xxsc.xtyx.R;
import com.xxsc.xtyx.middle.XTYXImage;
import com.xxsc.xtyx.model.SK_IndexModel;

import java.util.List;

/**
 * @author 赖善琦
 * @description
 */
public class SK_IndexSpecialAdapter extends XCBaseAdapter<SK_IndexModel.DatasEntity.GoodsEntity.SpecialEntity>{

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        bean = list.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sk_l_adapter_index_goods, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.sk_tv_goods_name.setText(bean.getName());
        holder.sk_iv_goods_description.setText(bean.getDescription());
        holder.sk_tv_goods_price.setText(bean.getSell_price() + "元");
        holder.sk_tv_goods_share.setText(bean.getShare());
        XTYXImage.displayImage(bean.getImg(), holder.sk_iv_goods_img);

        return convertView;
    }

    public SK_IndexSpecialAdapter(Context context, List list) {
        super(context, list);
    }

    public class ViewHolder{
        /** 商品名称 */
        TextView sk_tv_goods_name;
        /** 商品图片 */
        XCImageView sk_iv_goods_img;
        /** 商品描述 */
        TextView sk_iv_goods_description;
        /** 商品价格 */
        TextView sk_tv_goods_price;
        /** 商品分享人数 */
        TextView sk_tv_goods_share;

        public ViewHolder(View convertView){
            sk_tv_goods_name = (TextView)convertView.findViewById(R.id.sk_tv_goods_name);
            sk_iv_goods_img = (XCImageView)convertView.findViewById(R.id.sk_iv_goods_img);
            sk_iv_goods_description = (TextView)convertView.findViewById(R.id.sk_iv_goods_description);
            sk_tv_goods_price = (TextView)convertView.findViewById(R.id.sk_tv_goods_price);
            sk_tv_goods_share = (TextView)convertView.findViewById(R.id.sk_tv_goods_share);

        }
    }

}