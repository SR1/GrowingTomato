package com.sr1.growingtomato.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	public static String now(){
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdformat.format(new Date());
	}

	// 将剩余时间(毫秒)转换为 mm:ss 格式
	public static String TransToString(long time) {
		StringBuilder s = new StringBuilder();

		int t;
		t = (int) time / 1000 / 60;
		if (t < 10)
			s.append(0);
		s.append(t + ":");
		t = (int) time / 1000 % 60;
		if (t < 10)
			s.append(0);
		s.append(t);

		return s.toString();
	}

}
