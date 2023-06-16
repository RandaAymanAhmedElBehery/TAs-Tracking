package model;

import java.util.List;

public class PostGradStudiesInfo {
	
	String title;
	List<String> supervisors;
	
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
