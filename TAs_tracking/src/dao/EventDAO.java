package dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import model.Event;
import utils.EventUtils;

public class EventDAO {
	
	public Event DBObjectToEvent(DBObject dbObject) {
		//must check the type of event to initialize the corresponding correct type of event
		Event event = null;
		String eventType = null;
		try {
			if(dbObject.containsField("type")) {
				
				eventType = dbObject.get("type").toString();
				event = EventUtils.createEventObject(eventType);
	
				//Field[] allFields = EventUtils.getEventFields(event);

				List<Field> f = new ArrayList<Field>();
				Field[] allFields = (Field[]) EventUtils.getEventFields(f,event.getClass()).toArray(new Field[f.size()]);

				int fieldsNum = allFields.length;
		
				int i = 0;
				while(i < fieldsNum){
//					System.out.println(allFields[i]);
					Field field = allFields[i];
					field.setAccessible(true);
					try {
						if(field.getType() == int.class)
							field.setInt(event, Integer.parseInt(dbObject.get(field.getName()).toString()));
						else 
							field.set(event, dbObject.get(field.getName()));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					i++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}
	
	public DBObject EventToDBObject(Event event) {
		if(event != null) {
			DBObject event_dbObject = new BasicDBObject();
			int i =0;
			
			//Field[] allFields = EventUtils.getEventFields(event);
			
			List<Field> f = new ArrayList<Field>();
			Field[] allFields = (Field[]) EventUtils.getEventFields(f,event.getClass()).toArray(new Field[f.size()]);

			int fieldsNum = allFields.length;
	
			while(i < fieldsNum){
				
				allFields[i].setAccessible(true);
				try {
					event_dbObject.put(allFields[i].getName(), allFields[i].get(event));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
				i++;
			}
			
//			System.out.println("Event DB Object: " + event_dbObject);
			
			return event_dbObject;
		}
		return null;
	}
	


}
