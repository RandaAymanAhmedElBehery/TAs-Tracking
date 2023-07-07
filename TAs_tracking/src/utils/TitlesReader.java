package utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class TitlesReader {
	
	public static String[] getTitles() {
		Properties prop=new Properties();

		try {
			prop.load(ConfigReader.class.getResourceAsStream("/conf/titles.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object[] titlesObjs = prop.values().toArray();
		String[] titles = Arrays.copyOf(titlesObjs, titlesObjs.length, String[].class);
		return titles;
		
	}
	
	public static String getArTitle(String enTitle){
		Properties prop=new Properties();

		try {
			prop.load(ConfigReader.class.getResourceAsStream("/conf/titles.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(enTitle);
	}
	

}
