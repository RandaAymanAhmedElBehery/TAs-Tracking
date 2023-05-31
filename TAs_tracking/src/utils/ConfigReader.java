package utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static String getConfig(String config) {
		Properties prop=new Properties();

		try {
			prop.load(ConfigReader.class.getResourceAsStream("/conf/conf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop.getProperty(config);
	}
	
	public static String getMongoURI(){
		return getConfig("mongo.uri");
	}
	
	public static String getDBNName(){
		return getConfig("db_name");
	}
	public static String getUserDBNName(){
		return getConfig("user_db");
	}
	
	public static String getTADBNName(){
		return getConfig("ta_db");
	}

}
