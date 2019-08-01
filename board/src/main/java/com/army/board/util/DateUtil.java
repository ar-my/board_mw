package com.army.board.util;

import java.text.SimpleDateFormat;

public class DateUtil {
	
	public String yyyyMMdd() {
		
		SimpleDateFormat sf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String regdate = sf.format (System.currentTimeMillis());
		System.out.println(regdate);
		return regdate;
	}

	
}
