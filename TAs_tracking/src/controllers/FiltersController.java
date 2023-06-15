package controllers;

import java.util.ArrayList;

import dao.TADAO;
import model.Event;
import model.TA;

//view All TAs as list or by any filter
public class FiltersController {
	
	public ArrayList<TA> filterByVacationStatus(boolean vacationStatus){
		TADAO tadao = new TADAO();
		return tadao.getTAByVacationStatus(vacationStatus);
	}

	public ArrayList<TA> filterByTitle(String title){
		TADAO tadao = new TADAO();
		return tadao.getTAByTitle(title);
	}
	
	public ArrayList<TA> filterByEvent(String eventFullName){
		
		TADAO tadao = new TADAO();
		ArrayList<TA> allTAs = tadao.getAllTAs();
		ArrayList<TA> tasByEventName = new ArrayList<TA>();
		String eventNameSplit[] = eventFullName.split("\\.");	// eventFullName ex: model.MastersExtension
		String eventName = eventNameSplit[eventNameSplit.length-1];
		
		for (TA ta: allTAs){
			for (Event event: ta.getHistory()){
				if (event.getClass().getSimpleName().equals(eventName)){
					ta.setHistory(new ArrayList<Event>());
					ta.getHistory().add(event);
					tasByEventName.add(ta);
					break;
				}
			}
		}
		
		return tasByEventName;
	}
	
public ArrayList<TA> filterByLastEvent(String lastEventFullName){
		
		TADAO tadao = new TADAO();
		ArrayList<TA> allTAs = tadao.getAllTAs();
		ArrayList<TA> tasByLastEventName = new ArrayList<TA>();
		String lastEventNameSplit[] = lastEventFullName.split("\\.");	// eventFullName ex: model.MastersExtension
		String lastEventName = lastEventNameSplit[lastEventNameSplit.length-1];
		
		for (TA ta: allTAs){
			if (ta.getLastEvent().getClass().getSimpleName().equals(lastEventName)){
				tasByLastEventName.add(ta);
			}
		}
		
		return tasByLastEventName;
	}
}
