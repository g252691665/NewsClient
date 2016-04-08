package com.henugao.newsclient.fragment;

import com.henugao.newsclient.R;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Ö÷Ò³Fragemnt
 * @author henugao
 *
 */
public class ContentFragemnt extends BaseFragment {



	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		return view;
	}

}
