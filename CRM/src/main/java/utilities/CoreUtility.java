package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CoreUtility {
	
	public String genNum() {
		
		Random random = new Random();
		return "_" + random.nextInt(Integer.MAX_VALUE);
		
	}
	
	public String genLongNum() {
		
		Random random = new Random();
		return "_" + random.nextLong();
		
	}
	
	public String today() {
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
		
	}
	
	public String today(String format) {
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
		
	}

	public String addDaysToToday(int days) {
		
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		return dateFormat.format(calendar.getTime());
		
	}
	
	public String addDaysToToday(int days, String format) {
		
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
		return dateFormat.format(calendar.getTime());
		
	}

}