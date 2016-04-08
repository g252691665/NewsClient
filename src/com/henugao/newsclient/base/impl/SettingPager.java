package com.henugao.newsclient.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.henugao.newsclient.base.BasePager;

/**
 * 首页
 * @author henugao
 *
 */
public class SettingPager extends BasePager{

	public SettingPager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("设置");
		TextView textView = new TextView(mActivity);
		textView.setText("设置");
		textView.setTextColor(Color.RED);
		textView.setTextSize(25);
		textView.setGravity(Gravity.CENTER);
		btnMenu.setVisibility(View.INVISIBLE);
		setSlidingMenuEnable(false);
		flContent.addView(textView);  //向framentlayout中添加视图
	}

}
