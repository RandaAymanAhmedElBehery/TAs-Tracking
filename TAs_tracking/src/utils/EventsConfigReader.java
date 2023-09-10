package utils;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class EventsConfigReader {

	public static String getEventArabicName(String event) {
		Properties prop=new Properties();

		try {
			
			prop.load(ConfigReader.class.getResourceAsStream("/conf/events.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop.getProperty(event);
	}
	
	public static Set<Entry<Object,Object>> getEventsList(){
		
		Properties prop=new Properties();

		try {
			
			prop.load(ConfigReader.class.getResourceAsStream("/conf/events.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		prop.entrySet().removeIf(p -> !p.toString().startsWith("model"));
//		prop.list(System.out);
		return prop.entrySet();
	}
	

	public static String[] getEventsArabicList(){
		
		Properties prop=new Properties();

		try {
			
			prop.load(ConfigReader.class.getResourceAsStream("/conf/events.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.entrySet().removeIf(p -> !p.toString().startsWith("model"));

		String arEvents[] = new String[prop.size()];
		Object [] obj = prop.values().toArray();
		int i=0;
		for (Object x : obj){
			arEvents[i++] = String.valueOf(x);
		}
		return arEvents ;
	}
	
	public static String[] getNormalEventsArabicList() {

		Properties prop = new Properties();

		try {

			prop.load(ConfigReader.class.getResourceAsStream("/conf/normalEvents.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String arEvents[] = new String[prop.size()];
		Object[] obj = prop.values().toArray();
		int i = 0;
		for (Object x : obj) {
			arEvents[i++] = String.valueOf(x);
		}
		return arEvents;
	}
	
	public static String[] getAcademicEventsArabicList() {

		Properties prop = new Properties();

		try {

			prop.load(ConfigReader.class.getResourceAsStream("/conf/academicEvents.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String arEvents[] = new String[prop.size()];
		Object[] obj = prop.values().toArray();
		int i = 0;
		for (Object x : obj) {
			arEvents[i++] = String.valueOf(x);
		}
		return arEvents;
	}
	
	public static String[] getRegistrationStatusEventsList() {

		Properties prop = new Properties();

		try {

			prop.load(ConfigReader.class.getResourceAsStream("/conf/registrationStatusEvents.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String arEvents[] = new String[prop.size()];
		Object[] obj = prop.values().toArray();
		int i = 0;
		for (Object x : obj) {
			arEvents[i++] = String.valueOf(x);
		}
		return arEvents;
	}
	
	public static String getEventEnglishName(String event) {
		Set<Entry<Object,Object>> allEvents = getEventsList();
		for (Entry<Object, Object> eventObject : allEvents){
			if (eventObject.getValue().equals(event))
				return (String) eventObject.getKey();
		}

		return null;
	}
	
	
	public static String getIsOnVacation(boolean onVacation) {
		if(onVacation)
			return getEventArabicName("OnVacation");
		else
			return getEventArabicName("NotOnVacation");
		
	}
	
	
	public static void main(String[] args) {
//		System.out.println(EventsConfigReader.getEventsList());
		for(Entry<Object, Object> e : EventsConfigReader.getEventsList()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		
		System.out.println(getEventArabicName("NotOnVacation"));
	}
	
}
