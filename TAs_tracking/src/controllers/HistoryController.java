package controllers;

import dao.TADAO;
import model.TA;

//view history of certain TA
public class HistoryController {
	
	TA viewHistoryOfTA(String taName) {
		TADAO taDAO = new TADAO();
		
		return taDAO.getTAByName(taName);
	}

}
