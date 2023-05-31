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
	
	public List<TA> getAllTAs(){
		List<TA> allTAs = new ArrayList<TA>();
		DBCollection collection = database.getCollection(ta_databaseName);
		
		List<DBObject> dbObjects = collection.find().toArray();
		for(DBObject obj : dbObjects) {
			TA ta = DBObjectToTAObject(obj);
			allTAs.add(ta);
		}
		
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
		System.out.println(taDAO.getAllTAs().get(0));
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
