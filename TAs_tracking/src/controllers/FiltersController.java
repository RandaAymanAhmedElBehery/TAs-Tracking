package controllers;

import java.util.ArrayList;

import dao.TADAO;
import model.Event;
import model.TA;
import utils.EventsConfigReader;

//view All TAs as list or by any filter
public class FiltersController {
	
	public ArrayList<TA> filterByVacationStatus(String vacationStatus){
		TADAO tadao = new TADAO();
		return tadao.getTAByVacationStatus(vacationStatus.equals(EventsConfigReader.getEventArabicName("OnVacation")));
	}

	public ArrayList<TA> filterByTitle(String title){
		TADAO tadao = new TADAO();
		return tadao.getTAByTitle(title);
	}
	
	public ArrayList<TA> filterByEvent(String eventArabicName){
		
		TADAO tadao = new TADAO();
		ArrayList<TA> allTAs = tadao.getAllTAs();
		ArrayList<TA> tasByEventName = new ArrayList<TA>();

		String eventName = EventsConfigReader.getEventEnglishName(eventArabicName);
		if (eventName == null)
			return new ArrayList<>();
		
		
		for (TA ta: allTAs){
			System.out.println("History"+ ta.getHistory());
			for (Event event: ta.getHistory()){
				if (event != null && event.getClass().getCanonicalName().equals(eventName)){
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
