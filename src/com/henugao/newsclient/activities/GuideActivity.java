package com.henugao.newsclient.activities;

import java.util.ArrayList;

import com.henugao.newsclient.R;
import com.henugao.newsclient.utils.PreUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends Activity {
	//引导页的三个页面的资源Id
	private static final int[] mImageIds = new int[]{R.drawable.guide_1,R.drawable.guide_2,
		R.drawable.guide_3};
	private ViewPager vpGuider;
	private LinearLayout llPointsGroup;
	private View vRedPoint;  //红色的小圆点
	private Button btStart;   //开始体验按钮
	
	private ArrayList<ImageView> mImageViewList;
	private int mPointWidth; //两个小圆点的的距离（这里是指，两个小圆点左边之间的距离）

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		vpGuider = (ViewPager) findViewById(R.id.vp_guide);
		llPointsGroup = (LinearLayout) findViewById(R.id.ll_point_group);
		vRedPoint = findViewById(R.id.vred_point);
		btStart = (Button) findViewById(R.id.bt_start);
		btStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PreUtils.setBoolean(GuideActivity.this, "is_user_guide_show", true);
				startActivity(new Intent(GuideActivity.this,MainActivity.class));
				finish();
			}
		});
		initViews();
		vpGuider.setAdapter(new GuiderAdapter());
		vpGuider.setOnPageChangeListener(new GuiderPageLisenter());
	}
	
	/**
	 * 初始化界面
	 */
	private void initViews() {
		mImageViewList = new ArrayList<ImageView>();
		//初始化引导页的三个页面
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(mImageIds[i]); //设置引导页背景
			mImageViewList.add(imageView); 
		}
		
		//初始化引导页的小圆点
		for (int i = 0; i < mImageIds.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.shape_point_gray);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
			if(i > 0) {
				params.leftMargin = 10;    //设置圆点间隔
			} 
			point.setLayoutParams(params);    //设置圆点的大小
			llPointsGroup.addView(point);//将小圆点添加到线性布局中	
		}
		//得到视图树，对layout结束事件进行监听（因为这个方法是在oncreate中调用的，所以此时小圆点不能保证已经绘制完毕，所以为了能够获得小圆点之间有效的距离
		//设置此监听器）
		llPointsGroup.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			//当layout执行结束后回调此方法
			@Override
			public void onGlobalLayout() {
				System.out.println("onlayout结束");
				//由于可能会多次调用，为了防止代码重复运行，所以必须及时的移除掉该监听器
				llPointsGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				mPointWidth = llPointsGroup.getChildAt(1).getLeft() -
						llPointsGroup.getChildAt(0).getLeft();
				System.out.println("得到两个圆点之间的距离"+mPointWidth);
				
			}
		});
	}
	//viewpager的数据适配器
	class GuiderAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageIds.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1 ;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}
		
	}
	
	/**
	 * viewpager的滑动监听
	 * @author henugao
	 *
	 */
	class GuiderPageLisenter implements OnPageChangeListener {

		//滑动状态发生变化
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		//滑动事件  arg0：当前位置 在第几个页面 arg1:滑动的百分比  arg2 滑动的偏移量
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			System.out.println("当前位置："+arg0+"百分比："+arg1+"滑动的偏移量："+arg2);
			int len = (int) (mPointWidth * arg1 + mPointWidth * arg0);
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) 
					vRedPoint.getLayoutParams(); //获得当前小红点的坐标
			layoutParams.leftMargin = len;  //设置左边距
			vRedPoint.setLayoutParams(layoutParams);  //重新设置小红点的布局参数
			
			
			
		}
		
		//某个页面被选中
		@Override
		public void onPageSelected(int arg0) {
			//判断是不是最后一个展示页面
			if(arg0 == mImageIds.length - 1) {
				btStart.setVisibility(View.VISIBLE);
			}else {
				btStart.setVisibility(View.INVISIBLE);
			}
		}
		
	}
	


}
