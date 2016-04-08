package com.henugao.newsclient.activities;

import java.util.ArrayList;

import com.henugao.newsclient.R;
import com.henugao.newsclient.utils.PreUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends Activity {
	//����ҳ������ҳ�����ԴId
	private static final int[] mImageIds = new int[]{R.drawable.guide_1,R.drawable.guide_2,
		R.drawable.guide_3};
	private ViewPager vpGuider;
	private LinearLayout llPointsGroup;
	private View vRedPoint;  //��ɫ��СԲ��
	private Button btStart;   //��ʼ���鰴ť
	
	private ArrayList<ImageView> mImageViewList;
	private int mPointWidth; //����СԲ��ĵľ��루������ָ������СԲ�����֮��ľ��룩

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		vpGuider = (ViewPager) findViewById(R.id.vp_guide);
		llPointsGroup = (LinearLayout) findViewById(R.id.ll_point_group);
		vRedPoint = findViewById(R.id.vred_point);
		btStart = (Button) findViewById(R.id.bt_start);
		btStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PreUtils.setBoolean(GuideActivity.this, "is_user_guide_show", true);
				startActivity(new Intent(GuideActivity.this,MainActivity.class));
				finish();
			}
		});
		initViews();
		vpGuider.setAdapter(new GuiderAdapter());
		vpGuider.setOnPageChangeListener(new GuiderPageLisenter());
	}
	
	/**
	 * ��ʼ������
	 */
	private void initViews() {
		mImageViewList = new ArrayList<ImageView>();
		//��ʼ������ҳ������ҳ��
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(mImageIds[i]); //��������ҳ����
			mImageViewList.add(imageView); 
		}
		
		//��ʼ������ҳ��СԲ��
		for (int i = 0; i < mImageIds.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.shape_point_gray);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
			if(i > 0) {
				params.leftMargin = 10;    //����Բ����
			} 
			point.setLayoutParams(params);    //����Բ��Ĵ�С
			llPointsGroup.addView(point);//��СԲ����ӵ����Բ�����	
		}
		//�õ���ͼ������layout�����¼����м�������Ϊ�����������oncreate�е��õģ����Դ�ʱСԲ�㲻�ܱ�֤�Ѿ�������ϣ�����Ϊ���ܹ����СԲ��֮����Ч�ľ���
		//���ô˼�������
		llPointsGroup.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			//��layoutִ�н�����ص��˷���
			@Override
			public void onGlobalLayout() {
				System.out.println("onlayout����");
				//���ڿ��ܻ��ε��ã�Ϊ�˷�ֹ�����ظ����У����Ա��뼰ʱ���Ƴ����ü�����
				llPointsGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				mPointWidth = llPointsGroup.getChildAt(1).getLeft() -
						llPointsGroup.getChildAt(0).getLeft();
				System.out.println("�õ�����Բ��֮��ľ���"+mPointWidth);
				
			}
		});
	}
	//viewpager������������
	class GuiderAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageIds.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1 ;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}
		
	}
	
	/**
	 * viewpager�Ļ�������
	 * @author henugao
	 *
	 */
	class GuiderPageLisenter implements OnPageChangeListener {

		//����״̬�����仯
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		//�����¼�  arg0����ǰλ�� �ڵڼ���ҳ�� arg1:�����İٷֱ�  arg2 ������ƫ����
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			System.out.println("��ǰλ�ã�"+arg0+"�ٷֱȣ�"+arg1+"������ƫ������"+arg2);
			int len = (int) (mPointWidth * arg1 + mPointWidth * arg0);
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) 
					vRedPoint.getLayoutParams(); //��õ�ǰС��������
			layoutParams.leftMargin = len;  //������߾�
			vRedPoint.setLayoutParams(layoutParams);  //��������С���Ĳ��ֲ���
			
			
			
		}
		
		//ĳ��ҳ�汻ѡ��
		@Override
		public void onPageSelected(int arg0) {
			//�ж��ǲ������һ��չʾҳ��
			if(arg0 == mImageIds.length - 1) {
				btStart.setVisibility(View.VISIBLE);
			}else {
				btStart.setVisibility(View.INVISIBLE);
			}
		}
		
	}
	


}
