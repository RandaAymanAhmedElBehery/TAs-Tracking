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
//		Map<String, String> eventsList = new HashMap<String, String>();
		
		Properties prop=new Properties();

		try {
			
			prop.load(ConfigReader.class.getResourceAsStream("/conf/events.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		prop.list(System.out);
		return prop.entrySet();
	}
	
	public static void main(String[] args) {
//		System.out.println(EventsConfigReader.getEventsList());
		for(Entry<Object, Object> e : EventsConfigReader.getEventsList()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
	}
	
}
