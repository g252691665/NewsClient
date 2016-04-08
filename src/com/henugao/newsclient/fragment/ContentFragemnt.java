package com.henugao.newsclient.fragment;

import java.util.ArrayList;

import com.henugao.newsclient.R;
import com.henugao.newsclient.base.BasePager;
import com.henugao.newsclient.base.impl.GovAffairsPager;
import com.henugao.newsclient.base.impl.HomePager;
import com.henugao.newsclient.base.impl.NewsCenterPager;
import com.henugao.newsclient.base.impl.SettingPager;
import com.henugao.newsclient.base.impl.SmartServicePager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * 主页Fragemnt
 * @author henugao
 *
 */
public class ContentFragemnt extends BaseFragment {
	
	@ViewInject(R.id.rg_group)
	private RadioGroup rgGroup;
	
	@ViewInject(R.id.vp_content)
	private ViewPager mViewPager;
	
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
//		for (int i = 0; i < 5; i++) {
//			BasePager basePager = new BasePager(mActivity);
//			mPagerList.add(basePager);
//		}
		mPagerList.add(new HomePager(mActivity));
		mPagerList.add(new NewsCenterPager(mActivity));
		mPagerList.add(new SmartServicePager(mActivity));
		mPagerList.add(new GovAffairsPager(mActivity));
		mPagerList.add(new SettingPager(mActivity));
		
		mViewPager.setAdapter(new ContentPager());
		
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
			basePager.initData();
			return basePager.mRootView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}

}
