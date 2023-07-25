package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.DateUtils;

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
		this.endDate = DateUtils.stringtoDate(date);
	}	
	
}
