package com.henugao.newsclient.base.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.henugao.newsclient.activities.MainActivity;
import com.henugao.newsclient.base.BaseMenuDetailPager;
import com.henugao.newsclient.base.BasePager;
import com.henugao.newsclient.base.menudetail.InteractMenuDetail;
import com.henugao.newsclient.base.menudetail.NewsMenuDetail;
import com.henugao.newsclient.base.menudetail.PhotoMenuDetail;
import com.henugao.newsclient.base.menudetail.TopicMenuDetail;
import com.henugao.newsclient.domain.NewsData;
import com.henugao.newsclient.domain.NewsData.NewsMenuData;
import com.henugao.newsclient.fragment.LeftMenuFragment;
import com.henugao.newsclient.global.GlobalConstants;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 新闻中心
 * @author henugao
 *
 */
public class NewsCenterPager extends BasePager{

	private ArrayList<BaseMenuDetailPager> mPagers; //菜单详情页列表
	private NewsData mNewsData;
	public NewsCenterPager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("新闻");
		setSlidingMenuEnable(true);
		getDataFromServer();
		
		
	}

	/**
	 * 从服务器获取数据
	 */
	private void getDataFromServer() {
		//xutils的一个模块
		HttpUtils utils = new HttpUtils();
		//使用xutils发送请求
		utils.send(HttpMethod.GET, GlobalConstants.CATEGORIES_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				System.out.println(result);
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println(GlobalConstants.CATEGORIES_URL);
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
			
		});
	}

	/**
	 * 解析网络数据
	 * @param result
	 */
	protected void parseData(String result) {
		Gson gson = new Gson();
		mNewsData = gson.fromJson(result, NewsData.class);
		System.out.println("解析结果："+mNewsData);
		
		//刷新侧边栏数据
		MainActivity mainUi = (MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment = mainUi.getLeftMenuFragment();
		leftMenuFragment.setMenuData(mNewsData);
		
		//准备4个菜单详情页
		mPagers = new ArrayList<BaseMenuDetailPager>();
		mPagers.add(new NewsMenuDetail(mActivity,mNewsData.data.get(0).children));
		mPagers.add(new TopicMenuDetail(mActivity));
		mPagers.add(new PhotoMenuDetail(mActivity));
		mPagers.add(new InteractMenuDetail(mActivity));
		
		setrCurrentMenuDetailPager(0); //设置菜单详情页，默认为当前页
		
	}
	/**
	 * 设置当前菜单详情页
	 */
	public void setrCurrentMenuDetailPager(int position) {
		BaseMenuDetailPager pager = mPagers.get(position);  //获取当前要显示的详情菜单页
		flContent.removeAllViews();// 清除之前的布局
		flContent.addView(pager.mRootView); //将菜单详情页设置给帧布局
		NewsMenuData newsMenuData = mNewsData.data.get(position);
		tvTitle.setText(newsMenuData.title);
		
		pager.initData();  //初始化当前页面的数据
	}
	
	

}
