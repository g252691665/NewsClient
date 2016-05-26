package com.henugao.newsclient.base.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.henugao.newsclient.base.BaseMenuDetailPager;
/**
 * ²Ëµ¥ÏêÇéÒ³--»¥¶¯
 * @author henugao
 *
 */
public class InteractMenuDetail extends BaseMenuDetailPager {

	public InteractMenuDetail(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		TextView text = new TextView(mActivity);
		text.setText("²Ëµ¥ÏêÇéÒ³-»¥¶¯");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		return text;
	}

}
