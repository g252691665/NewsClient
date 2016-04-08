package com.henugao.newsclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * sharedpreference的封装
 * @author henugao
 *
 */
public class PreUtils {
	
	public static final String PRE_NAME = "config";   //配置文件的名字
	
	public static boolean  getBoolean(Context context,String key,
			boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(PRE_NAME, context.MODE_PRIVATE);
		boolean value = sp.getBoolean(key, defValue);
		return value;
	}
	
	public static void setBoolean(Context context,String key,boolean value) {
		SharedPreferences sp = context.getSharedPreferences(PRE_NAME, context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}


}
