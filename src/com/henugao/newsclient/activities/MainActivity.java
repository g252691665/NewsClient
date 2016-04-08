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
		
		setBehindContentView(R.layout.left_menu); //���ò����
		SlidingMenu slidingMenu = getSlidingMenu();  //��ȡ���������
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); //����ȫ������
		slidingMenu.setBehindOffset(200); //������ĻԤ���Ŀ��
		
		initFragment();
	}
	
	/**
	 * ��ʼ��fragemnt
	 */
	public void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();    //��������
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
				FRAGMENT_LEFT_MENU);//��fragment�滻left_menu
		transaction.replace(R.id.fl_content, new ContentFragemnt(),
				FRAGMENT_CONTENT);
		transaction.commit();  //�ύ����
	
		
	}


}
