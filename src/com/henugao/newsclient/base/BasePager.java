package com.henugao.newsclient.base;

import com.henugao.newsclient.R;
import com.henugao.newsclient.activities.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * ��ҳ�����ҳ��Ļ���
 * @author henugao
 *
 */
public class BasePager {
	public Activity mActivity;
	public View mRootView;
	public TextView tvTitle;
	public FrameLayout flContent;
	public ImageButton btnMenu;
	
	public BasePager(Activity mActivity) {
		this.mActivity = mActivity;
		initViews();
	}
	/**
	 * ��ʼ������
	 */
	public void initViews() {
		mRootView  =  View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
		flContent = (FrameLayout)mRootView.findViewById(R.id.fl_content);
		btnMenu = (ImageButton)mRootView.findViewById(R.id.btn_menu);
	}
	
	/**
	 * ��ʼ������
	 */
	public void initData() {
		
	}
	
	/**
	 * ���ò����������ر�
	 * @param enable
	 */
	public void setSlidingMenuEnable(boolean enable) {
		MainActivity mainUi = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();
		if(enable)
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		else 
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	}

}
