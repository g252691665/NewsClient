package com.henugao.newsclient.fragment;

import java.util.ArrayList;

import com.henugao.newsclient.R;
import com.henugao.newsclient.activities.MainActivity;
import com.henugao.newsclient.base.impl.NewsCenterPager;
import com.henugao.newsclient.domain.NewsData;
import com.henugao.newsclient.domain.NewsData.NewsMenuData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * �����fragment
 * @author henugao
 *
 */
public class LeftMenuFragment extends BaseFragment {
	@ViewInject(R.id.lv_list)
	private ListView lvList;
	private ArrayList<NewsMenuData> mMenuList;
	
	private int mCurrentPosition; // ��ǰ������Ĳ˵���
	private MenuAdapter mMenuAdapter;   //�������������
	

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
		ViewUtils.inject(this, view);
		return view;
	}
	
	@Override
	public void initData() {
		lvList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("initData"+position);
				mCurrentPosition = position;
				System.out.println("mCurrentPosition"+mCurrentPosition);
				mMenuAdapter.notifyDataSetChanged();
				setCurrentMenuDetailPager(position);
				
				toggleSlidingmenu();
			}
		});
	
	}
	
	/**
	 * �л�slidingmenu��״̬
	 * @param b
	 */
	protected void toggleSlidingmenu() {
		MainActivity mainUi = (MainActivity)mActivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();
		slidingMenu.toggle(); //�л�slidingmenu��״̬����ʾʱ���أ�����ʱ��ʾ
	}

	//���õ�ǰ�˵�����ҳ
	public void setCurrentMenuDetailPager(int position) {
		MainActivity mainUi = (MainActivity)mActivity;
		ContentFragemnt contentFragment = mainUi.getContentFragment();  //��ȡ��ҳ��
		NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager(); //��ȡ��������
		newsCenterPager.setrCurrentMenuDetailPager(position); //���ò˵�����ҳ
	}
	//������������
	public void setMenuData(NewsData data) {
		System.out.println("������õ����ݣ�"+data);
		mMenuList = data.data;
		mMenuAdapter = new MenuAdapter();
		lvList.setAdapter(mMenuAdapter);
	}
	
	//�������������
	class MenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mMenuList.size();
		}

		@Override
		public Object getItem(int position) {
			return mMenuList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			System.out.println("notifydatasetchanged");
			View view = View.inflate(mActivity, R.layout.list_menu_item, null);
			TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
			NewsMenuData newsMenuData = mMenuList.get(position);
			tvTitle.setText(newsMenuData.title);
			
			if(mCurrentPosition == position) {
				System.out.println("red position:"+position);
				//��ɫ
				tvTitle.setEnabled(true);
			}else {
				//��ɫ
				System.out.println("white position:"+position);
				tvTitle.setEnabled(false);
			}
			return view;
		}
		
	}
	

}
