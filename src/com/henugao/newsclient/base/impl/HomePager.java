package com.henugao.newsclient.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.TextView;

import com.henugao.newsclient.base.BasePager;

/**
 * ��ҳ
 * @author henugao
 *
 */
public class HomePager extends BasePager{

	public HomePager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("�ǻ۱���");
		TextView textView = new TextView(mActivity);
		textView.setText("��ҳ");
		textView.setTextColor(Color.RED);
		textView.setTextSize(25);
		textView.setGravity(Gravity.CENTER);
		flContent.addView(textView);  //��framentlayout�������ͼ
	}

}
