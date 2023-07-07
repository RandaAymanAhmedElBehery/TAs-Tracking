package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DateUtils {

	public static boolean matchDate(String date) throws ParseException {
		
		DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        sdf.setLenient(false);
        try {
            System.out.println(sdf.parse(date));
        } catch (ParseException e) {
        	e.printStackTrace();
        	throw e;
        } 
        return true;
		/*
//		String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
		String regex = "^[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		 
		Matcher matcher = pattern.matcher(date);
		System.out.println("MATCH : "+ matcher.matches());
		
		return matcher.matches();
	*/
	}
	
	public static String dateToString(Date date) {
		if(date == null)
			return "";
		//the +1 in the month because java.util.date stores the 12 months from 0 to 11
		int month = date.getMonth()+1;
		String stringDate= date.getDate()+"/"+month+"/"+date.getYear();
		return stringDate;
	}
	
	public static Date stringtoDate(String date) {
		String[] dateString = date.split("/");
		
		//the -1 in the month because java.util.date stores the 12 months from 0 to 11
		int day = Integer.parseInt(dateString[0]);
		int month = Integer.parseInt(dateString[1])-1;
		int year = Integer.parseInt(dateString[2]);
		
		Date d = new Date();
		d.setDate(day);
		d.setMonth(month);
		d.setYear(year);
		System.out.println(Arrays.toString(dateString));
		System.out.println("DATE:"+ d.getDay()+".."+ d.getMonth() + " .."+d.getYear());
		
		return d;
		
	}
}
