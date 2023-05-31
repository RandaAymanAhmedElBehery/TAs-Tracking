package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import utils.ConfigReader;

public class UserProfileDAO {
	
	MongoClient mongoClient;
	DB database;
	String user_databaseName;
	
	public UserProfileDAO() {
		
		mongoClient = new MongoClient(new MongoClientURI(ConfigReader.getMongoURI()));	
		database = mongoClient.getDB(ConfigReader.getDBNName());

		user_databaseName = ConfigReader.getUserDBNName();
		
		System.out.println("Successfully connected to User DB .");
	}

	public boolean login(String username, String password) {
		
		DBCollection collection = database.getCollection(user_databaseName);
		
		//build the db object
		DBObject dbObject = new BasicDBObject();
		dbObject.put("username", username);
		dbObject.put("password", password);
		
		DBObject dp_userprofile = collection.findOne(dbObject);
		
		//System.out.println("DBBBB : " + dp_userprofile);
		if(dp_userprofile == null)// user doesn't exist
			return false;
		return true;
	}

	public boolean sign_up(String username, String password) {
		//build the db object
		DBObject dbObject = new BasicDBObject();
		dbObject.put("username", username);
		dbObject.put("password", password);
		
		DBCollection collection = database.getCollection(user_databaseName);
		
		if(collection.find(dbObject).toArray().size() > 0)
			return false;
		else {
			collection.insert(dbObject);
			return true;
		}
	}


}
