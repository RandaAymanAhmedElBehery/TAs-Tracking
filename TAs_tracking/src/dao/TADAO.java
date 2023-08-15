package dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

import model.Event;
import model.TA;
import model.Vacation;
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
		
//		System.out.println("TA DB Object: " + ta_dbObject);
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
		allTAs = sortTAsbyName(allTAs);
		return allTAs;
	}
	
	private ArrayList<TA> sortTAsbyName(ArrayList<TA> allTAs) {
		allTAs.sort(new Comparator<TA>() {
		    @Override
		    public int compare(TA lhs, TA rhs) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		        return (lhs.getName().compareTo(rhs.getName()) < 0) ? -1 
		        		: (lhs.getName().compareTo(rhs.getName()) > 0) ? 1 : 0;
		    }
		});
		
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
	
	public void removeTA(TA ta) {
		
		DBCollection collection = database.getCollection(ta_databaseName);
		
		DBObject dbObject = new BasicDBObject();
		dbObject.put("name", ta.getName());
		
		DBObject db_ta = collection.findOne(dbObject);
		collection.remove(db_ta);
		
	}
	
	public void AddEventToTA(Event event, TA ta) {
		removeTA(ta);
		ta.addEventtoTA(event);
		addNewTA(ta);
	}

	public ArrayList<TA> getTAByVacationStatus(boolean vacationStatus) {
		ArrayList<TA> tasByVacation = new ArrayList<TA>();

		DBCollection collection = database.getCollection(ta_databaseName);
		DBObject ta_dbObject = new BasicDBObject();
		ta_dbObject.put("onVacation", vacationStatus);
		
		List<DBObject> result = collection.find(ta_dbObject).toArray();
		if(result != null){
			for(DBObject obj : result) {
				TA ta = DBObjectToTAObject(obj);
				tasByVacation.add(ta);
			}
		}
		
		sortTAsbyName(tasByVacation);
		return tasByVacation;
	}
	
	public ArrayList<TA> getTAByTitle(String title) {

		ArrayList<TA> tasByTitle = new ArrayList<TA>();

		DBCollection collection = database.getCollection(ta_databaseName);
		DBObject ta_dbObject = new BasicDBObject();
		ta_dbObject.put("title", title);
		
		List<DBObject> result = collection.find(ta_dbObject).toArray();
		if(result != null){
			for(DBObject obj : result) {
				TA ta = DBObjectToTAObject(obj);
				tasByTitle.add(ta);
			}
		}
		sortTAsbyName(tasByTitle);
		return tasByTitle;
	}
	
	public boolean updateTA(TA ta , TA beforeUpdate) {

		DBCollection collection = database.getCollection(ta_databaseName);
		DBObject ta_dbObject = TAToDBObject(ta);
		DBObject beforeUpdate_dbObject = TAToDBObject(beforeUpdate);
		DBObject query = new BasicDBObject();
		query.put("name", beforeUpdate.getName());
		WriteResult deleteResult = null;
		
		try {
			deleteResult = collection.remove(query);
			if (deleteResult.getN() == 1){
				collection.insert(ta_dbObject);
				return true;
			}
		}catch (Exception e){
			if (deleteResult.getN() == 1){
				collection.insert(beforeUpdate_dbObject);
				return false;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		
		Event e = new Vacation();
		System.out.println(e.getClass());
		
	}
	
}
