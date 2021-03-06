package com.henugao.newsclient.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.TextView;

import com.henugao.newsclient.base.BasePager;

/**
 * 首页
 * @author henugao
 *
 */
public class SmartServicePager extends BasePager{

	public SmartServicePager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("智能服务");
		TextView textView = new TextView(mActivity);
		textView.setText("智能服务");
		textView.setTextColor(Color.RED);
		textView.setTextSize(25);
		textView.setGravity(Gravity.CENTER);
		setSlidingMenuEnable(true);
		flContent.addView(textView);  //向framentlayout中添加视图
	}

}
