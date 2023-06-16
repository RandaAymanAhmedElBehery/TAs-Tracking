package ui;

import java.awt.Point;

import javax.swing.JFrame;

import model.TA;

public class AddEventToTAPage extends JFrame{
	
	int width = 600;
	int height = 600;
	
	TA ta;
	
	//basic info  + list of event panels
	public AddEventToTAPage(TA ta) {
		setSize(width, height);
		setTitle(ta.getName());
		setLocation(new Point(300, 200));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.ta = ta;
		addControls();
		
		setVisible(true);
	}

	private void addControls() {
		
		
	}

}
