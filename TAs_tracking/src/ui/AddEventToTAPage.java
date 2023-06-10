package ui;

import java.awt.Point;

import javax.swing.JFrame;

import model.TA;

public class AddEventToTAPage extends JFrame{
	
	int width = 600;
	int height = 800;
	
	TA ta;
	
	//basic info  + list of event panels
	public AddEventToTAPage(TA ta) {
		setSize(width, height);
		setTitle(ta.getName());
		setLocation(new Point(500, 200));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.ta = ta;
		addControls();
		
		setVisible(true);
	}

	private void addControls() {
		// TODO Auto-generated method stub
		
	}

}
