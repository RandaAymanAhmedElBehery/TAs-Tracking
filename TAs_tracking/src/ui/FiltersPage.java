package ui;

import java.awt.Point;

import javax.swing.JFrame;

public class FiltersPage extends JFrame{
	
	int width = 400;
	int height = 300;
	
	public FiltersPage() {
		
		setSize(width, height);
		setTitle("Filters Page");
		setLocation(new Point(500, 300));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addControls();
	}

	private void addControls() {
		// TODO Auto-generated method stub
		
	}

}
