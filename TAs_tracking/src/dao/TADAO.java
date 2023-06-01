package dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import model.Event;
import model.TA;
import utils.ConfigReader;

public class TADAO {
	
	MongoClient mongoClient;
	DB database;
	String ta_databaseName;
	
	public TADAO() {
		
		mongoClient = new MongoClient(new MongoClientURI(ConfigReader.getMongoURI()));	
		database = mongoClient.getDB(ConfigReader.getDBNName());

		ta_databaseName = ConfigReader.getTADBNName();
		
		System.out.println("Successfully connected to TA DB .");
	}
	
	public void addNewTA(TA ta) {
		//this function shall check that the id doesn't already exists
		//it should call TAToDBObject to insert the TA to DB
		
		DBCollection collection = database.getCollection(ta_databaseName);
		DBObject ta_dbObject = TAToDBObject(ta);
		
		collection.insert(ta_dbObject);
		
		System.out.println("TA inserted successfully");
	}
	
	public TA DBObjectToTAObject(DBObject dbObject) {
		TA ta = new TA();
		Field[] taFields = TA.class.getDeclaredFields();
		int fieldsNum = taFields.length;

		int i = 0;
		while(i < fieldsNum){
			Field field = taFields[i];
			field.setAccessible(true);
			try {
				if(field.getType() == int.class)
					field.setInt(ta, Integer.parseInt(dbObject.get(field.getName()).toString()));
				else if(field.getName() != "lastEvent" && field.getName() != "history")
					field.set(ta, dbObject.get(field.getName()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			i++;
		}
		
		EventDAO eventDAO = new EventDAO();
		//setting the lastEvent
		DBObject lastEventObject = (DBObject) dbObject.get("lastEvent");
		if(lastEventObject != null) {
			Event lastEvent = eventDAO.DBObjectToEvent(lastEventObject);
			ta.setLastEvent(lastEvent);
		}
		
		//setting History
		ArrayList<DBObject> historyDB = new ArrayList<DBObject>();
		historyDB = (ArrayList<DBObject>) dbObject.get("history");
		
		if(historyDB != null) {
			ArrayList<Event> taHistory = new ArrayList<Event>();
			if(historyDB != null) {
				for (DBObject db : historyDB) {
					Event e = eventDAO.DBObjectToEvent(db);
					taHistory.add(e);
				}
			}
			ta.setHistory(taHistory);
		}
		
		return ta;
	}
	
	public DBObject TAToDBObject(TA ta) {
		DBObject ta_dbObject = new BasicDBObject();
		
		int i =0;
		
		Field[] taFields = TA.class.getDeclaredFields();
		int fieldsNum = taFields.length;
		
		EventDAO eventDao = new EventDAO();

		while(i < fieldsNum){
			
			taFields[i].setAccessible(true);
			try {
				if(taFields[i].getName().equalsIgnoreCase("history")) {
					List<DBObject> ta_events = new ArrayList<DBObject>();
					for(Event e: ta.getHistory()) {
						DBObject event_dbObject = eventDao.EventToDBObject(e);
						ta_events.add(event_dbObject);
						//ta_dbObject.put(event_dbObject);
					}
					ta_dbObject.put("history", ta_events);
				}else if(taFields[i].getName().equalsIgnoreCase("lastEvent")) {
					DBObject event_dbObject = eventDao.EventToDBObject((Event) taFields[i].get(ta));
					ta_dbObject.put("lastEvent", event_dbObject);
				}
				else {
					ta_dbObject.put(taFields[i].getName(), taFields[i].get(ta));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			i++;
		}
		
		System.out.println("TA DB Object: " + ta_dbObject);
		return ta_dbObject;
	}
	
	public ArrayList<TA> getAllTAs(){
		ArrayList<TA> allTAs = new ArrayList<TA>();
		DBCollection collection = database.getCollection(ta_databaseName);
		
		List<DBObject> dbObjects = collection.find().toArray();
		for(DBObject obj : dbObjects) {
			TA ta = DBObjectToTAObject(obj);
			allTAs.add(ta);
		}
//		System.out.println(allTAs);
		return allTAs;
	}
	
	public TA getTAByName(String name) {
		TA foundTA = null;
		DBCollection collection = database.getCollection(ta_databaseName);
		DBObject ta_dbObject = new BasicDBObject();
		ta_dbObject.put("name", name);
		
		DBCursor results = collection.find(ta_dbObject);
//		System.out.println(results.toArray());
		if(results.toArray()!= null && results.toArray().size() == 1)
			foundTA = DBObjectToTAObject(results.toArray().get(0));
		
		return foundTA;
	}

	public static void main(String[] args) {
		
		TADAO taDAO = new TADAO();
//		System.out.println(taDAO.getTAByName("nn"));
		taDAO.getAllTAs().get(0).display();
		/*
		TA ta = new TA();
		ta.setName("basma");
		List<Event> events = new ArrayList<Event>();
		MastersExtension me = new MastersExtension();
		me.setType("masters");
		events.add(me);
		events.add(new PhdExtension());
		
		ta.setHistory(events);
		
		TADAO dao = new TADAO();
		dao.addNewTA(ta);
		*/
	}
}
