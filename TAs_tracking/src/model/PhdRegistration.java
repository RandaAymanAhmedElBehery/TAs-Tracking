package model;

import java.util.ArrayList;
import java.util.List;

public class PhdRegistration extends Event{
	
	String title;
	List<String> supervisors;
	
	public PhdRegistration() {
		supervisors = new ArrayList<String>();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getSupervisors() {
		return supervisors;
	}
	public void setSupervisors(List<String> supervisors) {
		this.supervisors = supervisors;
	}
}
