package controllers;

import dao.TADAO;
import model.Event;
import model.TA;

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
		ta.setLastEvent(event);
		ta.addEventtoTA(event);
		
		boolean updated = dao.updateTA(ta, beforeUpdate) ;
		return updated;
	}
	
public boolean promoteTA (String taName , String title){
		
		TADAO dao = new TADAO();
		TA ta = dao.getTAByName(taName);
		TA beforeUpdate = null;
		try {
			beforeUpdate = ta.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ta.setTitle(title);
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
