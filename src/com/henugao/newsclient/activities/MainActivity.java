package com.henugao.newsclient.activities;

import com.henugao.newsclient.R;
import com.henugao.newsclient.fragment.ContentFragemnt;
import com.henugao.newsclient.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends SlidingFragmentActivity {
	private static final String FRAGMENT_LEFT_MENU = "fragmet_left_menu";
	public static final String FRAGMENT_CONTENT = "fragment_content";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setBehindContentView(R.layout.left_menu); //设置侧边栏
		SlidingMenu slidingMenu = getSlidingMenu();  //获取侧边栏对象
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); //设置全屏触摸
		slidingMenu.setBehindOffset(200); //设置屏幕预留的宽度
		
		initFragment();
	}
	
	/**
	 * 初始化fragemnt
	 */
	public void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();    //开启事务
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
				FRAGMENT_LEFT_MENU);//用fragment替换left_menu
		transaction.replace(R.id.fl_content, new ContentFragemnt(),
				FRAGMENT_CONTENT);
		transaction.commit();  //提交事务
	
		
	}


}
