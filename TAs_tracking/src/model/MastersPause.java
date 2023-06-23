package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MastersPause extends Event {

	Date endDate;

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
