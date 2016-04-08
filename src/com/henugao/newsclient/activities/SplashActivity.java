package com.henugao.newsclient.activities;

import com.henugao.newsclient.R;
import com.henugao.newsclient.R.layout;
import com.henugao.newsclient.R.menu;
import com.henugao.newsclient.utils.PreUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	
	private LinearLayout ll_root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUi();
	}

	private void initUi() {
		setContentView(R.layout.activity_splash);
		ll_root = (LinearLayout) findViewById(R.id.ll_root);
		startAnim();
	}
	
	/**
	 * ��������
	 */
	public void startAnim() {
		AnimationSet as = new AnimationSet(false);
		//��ת����
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(2000);//������תʱ��
		ra.setFillAfter(true);// ������ת���״̬
		as.addAnimation(ra);
		//���Ŷ���
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF,
				0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setFillAfter(true);
		as.addAnimation(sa);
		//͸������
		AlphaAnimation aa = new AlphaAnimation(0, 1);
		aa.setDuration(2000);
		aa.setFillAfter(true);
		as.addAnimation(aa);
		as.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			//����ִ�н���
			@Override
			public void onAnimationEnd(Animation animation) {
				jumpNextPage();
			}
		});
		ll_root.startAnimation(as); //��������
	}
	//��ת����һ��ҳ��
	public void jumpNextPage() {
		//�ж�֮ǰ�ǲ��ǽ��й���������ҳ
		boolean userGuide = PreUtils.getBoolean(SplashActivity.this, "is_user_guide_show", false);
		if(!userGuide) {
			//��ת����������ҳ
			startActivity(new Intent(SplashActivity.this,GuideActivity.class));
		} else {
			startActivity(new Intent(SplashActivity.this,MainActivity.class));
		}
		finish();
		
	}
	


}
