package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static boolean matchDate(String date) {
		
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
           return false;
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
		String stringDate= date.getDate()+"/"+date.getMonth()+"/"+date.getYear();
		return stringDate;
	}
	
	public static Date stringtoDate(String date) {
		String[] dateString = date.split("/");
		Date d = new Date();
		d.setDate(Integer.parseInt(dateString[0]));
		d.setMonth(Integer.parseInt(dateString[1]));
		d.setYear(Integer.parseInt(dateString[2]));
		
		
		return d;
	}
}