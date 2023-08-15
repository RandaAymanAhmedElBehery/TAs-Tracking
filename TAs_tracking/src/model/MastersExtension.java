package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.DateUtils;

public class MastersExtension extends Event {
	
	Date endDate;
	int duration;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setEndDate(String date) {
		this.endDate = DateUtils.stringtoDate(date);
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
