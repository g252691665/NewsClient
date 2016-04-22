package com.henugao.newsclient.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.henugao.newsclient.activities.MainActivity;
import com.henugao.newsclient.base.BasePager;
import com.henugao.newsclient.domain.NewsData;
import com.henugao.newsclient.fragment.LeftMenuFragment;
import com.henugao.newsclient.global.GlobalConstants;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * ��ҳ
 * @author henugao
 *
 */
public class NewsCenterPager extends BasePager{

	public NewsCenterPager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("��������");
		TextView textView = new TextView(mActivity);
		textView.setText("��������");
		textView.setTextColor(Color.RED);
		textView.setTextSize(25);
		textView.setGravity(Gravity.CENTER);
		setSlidingMenuEnable(true);
		flContent.addView(textView);  //��framentlayout�������ͼ
		
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
		NewsData mNewsData = gson.fromJson(result, NewsData.class);
		System.out.println("���������"+mNewsData);
		
		
		MainActivity mainUi = (MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment = mainUi.getLeftMenuFragment();
		leftMenuFragment.setMenuData(mNewsData);
		
	}
	
	

}
