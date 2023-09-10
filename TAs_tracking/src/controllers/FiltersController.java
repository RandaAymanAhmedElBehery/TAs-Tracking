package controllers;

import java.util.ArrayList;

import dao.TADAO;
import model.Event;
import model.TA;
import utils.EventsConfigReader;

public class FiltersController {

	public ArrayList<TA> filterByVacationStatus(String vacationStatus) {
		TADAO tadao = new TADAO();
		return tadao.getTAByVacationStatus(vacationStatus.equals(EventsConfigReader.getEventArabicName("OnVacation")));
	}

	public ArrayList<TA> filterByTitle(String title) {
		TADAO tadao = new TADAO();
		return tadao.getTAByTitle(title);
	}

	public ArrayList<TA> filterByEvent(String eventArabicName) {

		TADAO tadao = new TADAO();
		ArrayList<TA> allTAs = tadao.getAllTAs();
		ArrayList<TA> tasByEventName = new ArrayList<TA>();

		String eventName = EventsConfigReader.getEventEnglishName(eventArabicName);
		if (eventName == null)
			return new ArrayList<>();

		for (TA ta : allTAs) {
			for (Event event : ta.getHistory()) {
				if (event != null && event.getClass().getCanonicalName().equals(eventName)) {
					ta.setHistory(new ArrayList<Event>());
					ta.getHistory().add(event);
					tasByEventName.add(ta);
					break;
				}
			}
		}

		return tasByEventName;
	}

	public ArrayList<TA> filterByLastEvent(String lastEventFullName) {

		TADAO tadao = new TADAO();
		ArrayList<TA> allTAs = tadao.getAllTAs();
		ArrayList<TA> tasByLastEventName = new ArrayList<TA>();
		String eventEnName = EventsConfigReader.getEventEnglishName(lastEventFullName);

		for (TA ta : allTAs) {
			if (ta.getLastEvent().getType().equals(eventEnName)) {
				tasByLastEventName.add(ta);
			}
		}

		return tasByLastEventName;
	}

	public ArrayList<TA> filterByLastAcademicEvent(String lastEventFullName) {

		TADAO tadao = new TADAO();
		ArrayList<TA> allTAs = tadao.getAllTAs();
		ArrayList<TA> tasByLastEventName = new ArrayList<TA>();
		String eventEnName = EventsConfigReader.getEventEnglishName(lastEventFullName);

		for (TA ta : allTAs) {
			if (ta.getLastAcademicEvent()!=null && ta.getLastAcademicEvent().getType().equals(eventEnName)) {
				tasByLastEventName.add(ta);
			}
		}

		return tasByLastEventName;
	}
	
	public ArrayList<TA> filterByregistrationStatus(String lastEventFullName) {

		TADAO tadao = new TADAO();
		ArrayList<TA> allTAs = tadao.getAllTAs();
		ArrayList<TA> tasByLastEventName = new ArrayList<TA>();
		String eventEnName = EventsConfigReader.getEventEnglishName(lastEventFullName);

		for (TA ta : allTAs) {
			if (ta.getregistrationStatus()!=null && ta.getregistrationStatus().getType().equals(eventEnName)) {
				tasByLastEventName.add(ta);
			}
		}

		return tasByLastEventName;
	}
}
