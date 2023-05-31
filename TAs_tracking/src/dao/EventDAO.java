package dao;

import java.lang.reflect.Field;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import model.Event;

public class EventDAO {
	
	public Event DBObjectToEvent(DBObject dbObject) {
		//must check the type of event to initialize the corresponding correct type of event
		Event event = null;
		
		
		return event;
	}
	
	public DBObject EventToDBObject(Event event) {
		DBObject event_dbObject = new BasicDBObject();
		int i =0;
		
		Field[] eventFields = Event.class.getDeclaredFields();
		int fieldsNum = eventFields.length;
		System.out.println("EVENT: " + event);

		while(i < fieldsNum){
			
			eventFields[i].setAccessible(true);
			try {
				event_dbObject.put(eventFields[i].getName(), eventFields[i].get(event));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			i++;
		}
		
		System.out.println("Event DB Object: " + event_dbObject);
		
		return event_dbObject;
	}

}
