package com.henugao.newsclient.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * fragment�Ļ���
 * @author henugao
 *
 */
public abstract class BaseFragment extends Fragment{
	public  Activity mActivity;
	
	//fragment����
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}
	
	//������fragment�Ĳ���
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return initView();
	}
	
	//��������activity�������
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initData();
	}
	//�������ʵ�ֳ�ʼ�����ֵķ���
	public abstract View initView();
	
	//��ʼ�����ݣ��÷������п���
	public  void initData() {
		
	}
}
