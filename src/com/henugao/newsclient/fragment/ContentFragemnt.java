package com.henugao.newsclient.fragment;

import java.util.ArrayList;

import com.henugao.newsclient.R;
import com.henugao.newsclient.base.BasePager;
import com.henugao.newsclient.base.impl.GovAffairsPager;
import com.henugao.newsclient.base.impl.HomePager;
import com.henugao.newsclient.base.impl.NewsCenterPager;
import com.henugao.newsclient.base.impl.SettingPager;
import com.henugao.newsclient.base.impl.SmartServicePager;
import com.henugao.newsclient.view.NoScrollViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 主页Fragemnt
 * @author henugao
 *
 */
public class ContentFragemnt extends BaseFragment {
	
	@ViewInject(R.id.rg_group)
	private RadioGroup rgGroup;
	
	@ViewInject(R.id.vp_content)
	private NoScrollViewPager mViewPager;
	
	private ArrayList<BasePager> mPagerList;
	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		//rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		ViewUtils.inject(this, view);   //注入view和事件
		return view;
	}
	
	@Override
	public void initData() {
		rgGroup.check(R.id.rb_home); //默认勾选首页
		//初始化五个子页面
		mPagerList = new ArrayList<BasePager>();
		mPagerList.add(new HomePager(mActivity));
		mPagerList.add(new NewsCenterPager(mActivity));
		mPagerList.add(new SmartServicePager(mActivity));
		mPagerList.add(new GovAffairsPager(mActivity));
		mPagerList.add(new SettingPager(mActivity));
		
		mViewPager.setAdapter(new ContentPager());
		mPagerList.get(0).initData();
		//监听radiogroup的选择事件
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					mViewPager.setCurrentItem(0,false); //去掉切换页面的动画
					break;
				case R.id.rb_news:
					mViewPager.setCurrentItem(1,false);
					break;
				case R.id.rb_smart:
					mViewPager.setCurrentItem(2,false);
					break;
				case R.id.rb_gov:
					mViewPager.setCurrentItem(3,false);
					break;
				case R.id.rb_setting:
					mViewPager.setCurrentItem(4,false);
					break;	
				default:
					break;
				}
			}
		});
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				mPagerList.get(arg0).initData();   //获得当前被选中的页面，初始化此页面的数据
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	class ContentPager extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPagerList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager basePager = mPagerList.get(position);
			container.addView(basePager.mRootView);
			return basePager.mRootView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}
	
	/**
	 * 获取新闻中心页面
	 * @return
	 */
	public NewsCenterPager getNewsCenterPager() {
		return (NewsCenterPager)mPagerList.get(1);
	}

}
