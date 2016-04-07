package com.henugao.newsclient.activities;

import com.henugao.newsclient.R;
import com.henugao.newsclient.R.layout;
import com.henugao.newsclient.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
	 * 开启动画
	 */
	public void startAnim() {
		AnimationSet as = new AnimationSet(false);
		//旋转动画
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(2000);//设置旋转时间
		ra.setFillAfter(true);// 保持旋转后的状态
		as.addAnimation(ra);
		//缩放动画
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF,
				0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setFillAfter(true);
		as.addAnimation(sa);
		//透明动画
		AlphaAnimation aa = new AlphaAnimation(0, 1);
		aa.setDuration(2000);
		aa.setFillAfter(true);
		as.addAnimation(aa);
		ll_root.startAnimation(as); //开启动画
	}
	


}
