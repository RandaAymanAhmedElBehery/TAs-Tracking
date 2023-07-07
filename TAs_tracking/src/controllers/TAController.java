package controllers;
// add a new TA or update any data of an existing TA

import java.util.ArrayList;
import java.util.Date;

import Exceptions.TAAlreadyExistsException;
import dao.TADAO;
import model.Event;
import model.NewTA;
import model.TA;
import utils.DateUtils;

public class TAController {
	
	public void addNewTA(String name, String mobileNo, String email, String date, 
			String title, String onVacation) throws TAAlreadyExistsException{
		//must add newTAEvent by default
		//set last event
		
		TADAO taDAO = new TADAO();
		if(taDAO.getTAByName(name) != null)
			throw new TAAlreadyExistsException("TA Already Exists");
		
		Date hiringDate = DateUtils.stringtoDate(date);
		System.out.println("HIRING: " + hiringDate);
		TA ta = new TA(name,  mobileNo,  email,  hiringDate, title,  onVacation);
		
		Event newTA = new NewTA();
		newTA.setDate(hiringDate);
		newTA.setType(NewTA.class.getName());
		
		ta.addEventtoTA(newTA);
		ta.setLastEvent(newTA);
		
		taDAO.addNewTA(ta);
		
	}
	
	public ArrayList<TA> getAllTAs(){
		TADAO tadao = new TADAO();
		return tadao.getAllTAs();
	}
		
	
	public boolean updateTA(TA ta) {
		TADAO taDao = new TADAO();
		return taDao.updateTA(ta, ta);
	}

}
