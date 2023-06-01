package ui;

import javax.swing.JFrame;

import model.TA;

public class TAHistoryPage extends JFrame {
	
	//basic info  + list of event panels
	int width = 400;
	int height = 400;
	TA ta;
	String topicName;
	
	public TAHistoryPage(TA ta) {
		
		this.ta = ta;
		
		setSize(width, height);
		addTAInfo();
		
		setVisible(true);
	}
	
	private void addTAInfo(){
	
		
	}
	

}
