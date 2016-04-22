package com.henugao.newsclient.domain;

import java.util.ArrayList;

/**
 * 网络信息分类的封装
 * 字段名字必须和服务器返回的字段名一致，方便gson解析
 * @author henugao
 *
 */
public class NewsData {
	public int retcode;
	public ArrayList<NewsMenuData> data;
	
	public class NewsMenuData {
		public String id;
		public String title;
		public int type;
		public String url;
		
		public ArrayList<NewsTabData> children;

		@Override
		public String toString() {
			return "NewsMenuData [children=" + children + "]";
		}

		
	}
	
	public class NewsTabData {
		public String id;
		public String title;
		public int type;
		public String url;
		@Override
		public String toString() {
			return "NewsTabData [title=" + title + "]";
		}
		
		
	}

	@Override
	public String toString() {
		return "NewsData [data=" + data + "]";
	}
	


}
