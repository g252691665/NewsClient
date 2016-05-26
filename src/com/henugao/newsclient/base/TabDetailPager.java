package com.henugao.newsclient.base;

import com.henugao.newsclient.domain.NewsData.NewsTabData;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
/**
 * Ò³Ç©ÏêÇéÒ³
 * @author henugao
 *
 */
public class TabDetailPager extends BaseMenuDetailPager {

	private TextView tvTitle;
	private NewsTabData mNewsTabData;
	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mNewsTabData = newsTabData;
	}

	@Override
	public View initView() {
		tvTitle = new TextView(mActivity);
		tvTitle.setText("Ò³Ç©ÏêÇéÒ³");
		tvTitle.setTextColor(Color.RED);
		tvTitle.setTextSize(25);
		tvTitle.setGravity(Gravity.CENTER);
		return tvTitle;
	}
	
	@Override
	public void initData() {
		tvTitle.setText(mNewsTabData.title);
	}

}
