package utils;

import java.lang.reflect.Field;

import model.Event;
import model.MastersDiscussion;
import model.MastersExtension;
import model.MastersPause;
import model.MastersRegistration;
import model.NewTA;
import model.PhdDiscussion;
import model.PhdExtension;
import model.PhdPause;
import model.PhdQualification;
import model.PhdRegistration;
import model.PrePhdRegistration;
import model.PremastersRegistration;
import model.Promotion;
import model.Resignation;
import model.ResumeWork;
import model.Vacation;

public class EventUtils {
	
	public static final String NEW_TA = "model.NewTA";
	public static final String MASETERS_EXTENSION = "model.MastersExtension";
	public static final String PHD_EXTENSION = "model.PhdExtension";
	public static final String MASTERS_REGISTRATION = "model.MastersRegistration";
	public static final String PHD_REGISTRATION = "model.PhdRegistration";
	public static final String MASTERS_DISCUSSION = "model.MastersDiscussion";
	public static final String MASTERS_PAUSE = "model.MastersPause";
	public static final String PHD_DISCUSSION = "model.PhdDiscussion";
	public static final String PHD_PAUSE = "model.PhdPause";
	public static final String PHD_QUALIFICATION = "model.PhdQualification";
	public static final String PREMASTERS_REGISTRATION = "model.PremastersRegistration";
	public static final String PREPHD_REGISTRATION = "model.PrePhdRegistration";
	public static final String PROMOTION = "model.Promotion";
	public static final String RESIGNATION = "model.Resignation";
	public static final String RESUME_WORK = "model.ResumeWork";
	public static final String VACATION = "model.Vacation";
	
	public static Event createEventObject(String eventType) {
		if(eventType.equalsIgnoreCase(NEW_TA))
			return new NewTA();
		else if(eventType.equalsIgnoreCase(MASETERS_EXTENSION))
			return new MastersExtension();
		else if(eventType.equalsIgnoreCase(PHD_EXTENSION))
			return new PhdExtension();
		else if(eventType.equalsIgnoreCase(MASTERS_REGISTRATION))
			return new MastersRegistration();
		else if(eventType.equalsIgnoreCase(PHD_REGISTRATION))
			return new PhdRegistration();
		else if(eventType.equalsIgnoreCase(MASTERS_DISCUSSION))
			return new MastersDiscussion();
		else if(eventType.equalsIgnoreCase(MASTERS_PAUSE))
			return new MastersPause();
		else if(eventType.equalsIgnoreCase(PHD_DISCUSSION))
			return new PhdDiscussion();
		else if(eventType.equalsIgnoreCase(PHD_PAUSE))
			return new PhdPause();
		else if(eventType.equalsIgnoreCase(PHD_QUALIFICATION))
			return new PhdQualification();
		else if(eventType.equalsIgnoreCase(PREMASTERS_REGISTRATION))
			return new PremastersRegistration();
		else if(eventType.equalsIgnoreCase(PROMOTION))
			return new Promotion();
		else if(eventType.equalsIgnoreCase(PREPHD_REGISTRATION))
			return new PrePhdRegistration();
		else if(eventType.equalsIgnoreCase(RESIGNATION))
			return new Resignation();
		else if(eventType.equalsIgnoreCase(RESUME_WORK))
			return new ResumeWork();
		else if(eventType.equalsIgnoreCase(VACATION))
			return new Vacation();
				
		return null;
	}
	
	public static Field[] getEventFields(Event event) {
		Field[] eventInterfaceFields = Event.class.getDeclaredFields();
		Field[] eventFields = event.getClass().getDeclaredFields();
		
		Field[] allFields = new Field[eventInterfaceFields.length + eventFields.length];
		System.arraycopy(eventFields, 0, allFields, 0, eventFields.length);
	    System.arraycopy(eventInterfaceFields, 0, allFields, eventFields.length, eventInterfaceFields.length);
	    
//	    System.out.println("TYPE: "+ event.getClass() + "   ,,   Fields: " + allFields.length);
	    
		return allFields;
	}

}
