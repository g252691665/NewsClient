package com.henugao.newsclient.base;

import com.henugao.newsclient.R;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 主页的五个页面的基类
 * @author henugao
 *
 */
public class BasePager {
	public Activity mActivity;
	public View mRootView;
	public TextView tvTitle;
	public FrameLayout flContent;
	
	public BasePager(Activity mActivity) {
		this.mActivity = mActivity;
		initViews();
	}
	/**
	 * 初始化布局
	 */
	public void initViews() {
		mRootView  =  View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
		flContent = (FrameLayout)mRootView.findViewById(R.id.fl_content);
	}
	
	/**
	 * 初始化数据
	 */
	public void initData() {
		
	}

}
