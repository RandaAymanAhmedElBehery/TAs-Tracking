package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vacation extends Event {
	
	String vacationType;
	Date endDate;
	
	public String getVacationType() {
		return vacationType;
	}
	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setEndDate(String date) {
	    Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		this.endDate = date1;
	}	
	
}
