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
 * ��������
 * @author henugao
 *
 */
public class NewsCenterPager extends BasePager{

	private ArrayList<BaseMenuDetailPager> mPagers; //�˵�����ҳ�б�
	private NewsData mNewsData;
	public NewsCenterPager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("����");
		setSlidingMenuEnable(true);
		getDataFromServer();
		
		
	}

	/**
	 * �ӷ�������ȡ����
	 */
	private void getDataFromServer() {
		//xutils��һ��ģ��
		HttpUtils utils = new HttpUtils();
		//ʹ��xutils��������
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
	 * ������������
	 * @param result
	 */
	protected void parseData(String result) {
		Gson gson = new Gson();
		mNewsData = gson.fromJson(result, NewsData.class);
		System.out.println("���������"+mNewsData);
		
		//ˢ�²��������
		MainActivity mainUi = (MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment = mainUi.getLeftMenuFragment();
		leftMenuFragment.setMenuData(mNewsData);
		
		//׼��4���˵�����ҳ
		mPagers = new ArrayList<BaseMenuDetailPager>();
		mPagers.add(new NewsMenuDetail(mActivity,mNewsData.data.get(0).children));
		mPagers.add(new TopicMenuDetail(mActivity));
		mPagers.add(new PhotoMenuDetail(mActivity));
		mPagers.add(new InteractMenuDetail(mActivity));
		
		setrCurrentMenuDetailPager(0); //���ò˵�����ҳ��Ĭ��Ϊ��ǰҳ
		
	}
	/**
	 * ���õ�ǰ�˵�����ҳ
	 */
	public void setrCurrentMenuDetailPager(int position) {
		BaseMenuDetailPager pager = mPagers.get(position);  //��ȡ��ǰҪ��ʾ������˵�ҳ
		flContent.removeAllViews();// ���֮ǰ�Ĳ���
		flContent.addView(pager.mRootView); //���˵�����ҳ���ø�֡����
		NewsMenuData newsMenuData = mNewsData.data.get(position);
		tvTitle.setText(newsMenuData.title);
		
		pager.initData();  //��ʼ����ǰҳ�������
	}
	
	

}
