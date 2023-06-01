package controllers;
// add a new TA or update any data of an existing TA

import java.util.ArrayList;
import java.util.Date;

import Exceptions.TAAlreadyExistsException;
import dao.TADAO;
import model.Event;
import model.NewTA;
import model.TA;

public class TAController {
	
	public void addNewTA(String name, String mobileNo, String email, String year, 
			String title, String onVacation) throws TAAlreadyExistsException{
		//must add newTAEvent by default
		//set last event
		
		TADAO taDAO = new TADAO();
		if(taDAO.getTAByName(name) != null)
			throw new TAAlreadyExistsException("TA Already Exists");
		
		TA ta = new TA(name,  mobileNo,  email,  year, title,  onVacation);
		
		Event newTA = new NewTA();
		newTA.setDate(new Date());//TODO: to be changed
		newTA.setType(NewTA.class.getName());
		ta.addEventtoTA(newTA);
		ta.setLastEvent(newTA);
		
		
		taDAO.addNewTA(ta);
		
	}
	
	public ArrayList<TA> getAllTAs(){
		TADAO tadao = new TADAO();
		return tadao.getAllTAs();
	}

}
