package controllers;

import dao.TADAO;
import model.Event;
import model.Promotion;
import model.TA;
import utils.EventUtils;

public class AddEventController {
	
	public boolean addEventToTA (String taName , Event event){
		
		TADAO dao = new TADAO();
		TA ta = dao.getTAByName(taName);
		TA beforeUpdate = null;
		try {
			beforeUpdate = ta.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(event.getType().equalsIgnoreCase(EventUtils.NEW_TA)
				|| event.getType().equalsIgnoreCase(EventUtils.PROMOTION) 
				|| event.getType().equalsIgnoreCase(EventUtils.RESIGNATION)
				|| event.getType().equalsIgnoreCase(EventUtils.RESUME_WORK)
				|| event.getType().equalsIgnoreCase(EventUtils.VACATION))
			ta.setLastEvent(event);
		else if (event.getType().contains("Pause") || event.getType().contains("Extension"))
			ta.setregistrationStatus(event);
		else
			ta.setLastAcademicEvent(event);
				
		ta.addEventtoTA(event);
		
		boolean updated = dao.updateTA(ta, beforeUpdate) ;
		return updated;
	}
	
	public boolean promoteTA (String taName , String title, Promotion promotion){
		
		TADAO dao = new TADAO();
		TA ta = dao.getTAByName(taName);
		TA beforeUpdate = null;
		try {
			beforeUpdate = ta.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Promotion : " + promotion);
		ta.setTitle(title);
		ta.setPromotionDate(promotion.getDate());
		
		ta.setLastEvent(promotion);
				
		boolean updated = dao.updateTA(ta, beforeUpdate) ;
		return updated;
	}

public boolean setTAOnVacation (String taName , boolean vacation){
	
	TADAO dao = new TADAO();
	TA ta = dao.getTAByName(taName);
	TA beforeUpdate = null;
	try {
		beforeUpdate = ta.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ta.setOnVacation(vacation);
	boolean updated = dao.updateTA(ta, beforeUpdate) ;
	return updated;
}
}
