package com.henugao.newsclient.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * fragment的基类
 * @author henugao
 *
 */
public abstract class BaseFragment extends Fragment{
	public  Activity mActivity;
	
	//fragment创建
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}
	
	//创建该fragment的布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return initView();
	}
	
	//所依赖的activity创建完成
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initData();
	}
	//子类必须实现初始化布局的方法
	public abstract View initView();
	
	//初始化数据，该方法可有可无
	public  void initData() {
		
	}
}
