package com.henugao.newsclient.base.menudetail;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henugao.newsclient.R;
import com.henugao.newsclient.base.BaseMenuDetailPager;
import com.henugao.newsclient.base.TabDetailPager;
import com.henugao.newsclient.domain.NewsData.NewsTabData;
/**
 * 菜单详情页--新闻
 * @author henugao
 *
 */
public class NewsMenuDetail extends BaseMenuDetailPager {

	private ViewPager mViewPager;
	private ArrayList<TabDetailPager> mPagerList;
	private ArrayList<NewsTabData> mNewsTabData;
	public NewsMenuDetail(Activity activity, ArrayList<NewsTabData> newsTabData) {
		super(activity);
		mNewsTabData = newsTabData;
	}

	@Override
	public View initView() {
		View view  = View.inflate(mActivity, R.layout.news_menu_detail, null);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_menu_detail);
		return view;
	}
	
	@Override
	public void initData() {
		mPagerList = new ArrayList<TabDetailPager>();
		for (int i = 0; i < mNewsTabData.size(); i++) {
			TabDetailPager tabDetailPager = new TabDetailPager(mActivity,mNewsTabData.get(i));
			mPagerList.add(tabDetailPager);
		}
		mViewPager.setAdapter(new MenuDetailAdapter());
	}
	
	
	class MenuDetailAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagerList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TabDetailPager tabDetailPager = mPagerList.get(position);
			container.addView(tabDetailPager.mRootView);
			tabDetailPager.initData();
			return tabDetailPager.mRootView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
		
	}

}
