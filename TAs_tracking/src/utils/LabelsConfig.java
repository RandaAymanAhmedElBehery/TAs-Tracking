package utils;

import java.io.IOException;
import java.util.Properties;

public class LabelsConfig {
	
	public static final String DATE = "date";
	public static final String HIRING_DATE = "hiringDate";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	public static final String DISCUSSION_DATE = "discussionDate";
	public static final String REGISTRATION_DATE = "registrationDate";
	public static final String ADD_EVENT = "addEvent";
	public static final String THESIS_TITLE = "thesisTitle";
	public static final String SUPERVISOR_1 = "supervisor_1";
	public static final String SUPERVISOR_2 = "supervisor_2";
	public static final String SUPERVISOR_3 = "supervisor_3";
	public static final String CONFIRM = "confirm";
	public static final String CONFIRM_DELETE_TA_MSG = "confirmDeleteTA";
	public static final String STATUS = "status";
	public static final String SUCCESS = "sucessMsg";
	public static final String ERROR = "errorMsg";
	public static final String EDIT_TA = "editTA";
	public static final String REASON = "reason";
	

	private static String getConfig(String config) {
		Properties prop=new Properties();

		try {
			prop.load(ConfigReader.class.getResourceAsStream("/conf/labels.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop.getProperty(config);
	}
	
	public static String getNameLabel() {
		return getConfig("name");
		
	}
	
	public static String getOnVacationLabel() {
		return getConfig("onVacation");
		
	}
	public static String getTitleLabel() {
		return getConfig("title");
		
	}
	public static String getHiringDateLabel() {
		return getConfig("hiringDate");
		
	}
	public static String getDateLabel() {
		return getConfig("date");
		
	}
	public static String getTypeLabel() {
		return getConfig("type");
		
	}
	public static String getEmailLabel() {
		return getConfig("email");
		
	}
	
	public static String getPasswordLabel() {
		return getConfig("password");
		
	}
	
	public static String getMobileLabel() {
		return getConfig("mobile");
		
	}
	
	public static String getAddTALabel() {
		return getConfig("AddTA");
		
	}
	
	public static String getHomePageLabel() {
		return getConfig("HomePage");
		
	}
	
	public static String getFilterTAsLabel() {
		return getConfig("FilterTAs");
		
	}
	
	public static String getAddEventLabel() {
		return getConfig("addEvent");
		
	}
	public static String getViewHistoryLabel() {
		return getConfig("viewHistory");
		
	}
	
	public static String getLoginLabel() {
		return getConfig("login");
		
	}
	public static String getSignUpLabel() {
		return getConfig("signup");
		
	}
	public static String getEditProfileLabel() {
		return getConfig("editprofile");
		
	}
	
	
	
	public static String getLabel(String label) {
		return getConfig(label);
		
	}

	public static String getEditTALabel() {
		return getConfig(EDIT_TA);
	}
}
