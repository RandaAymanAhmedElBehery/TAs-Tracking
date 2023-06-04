package ui;

import java.awt.Point;

import javax.swing.JFrame;

import controllers.FiltersController;

public class FiltersPage extends JFrame{
	
	int width = 400;
	int height = 400;
	
	FiltersController filtersController;

	
	public FiltersPage() {
		setSize(width, height);
		setTitle("Filter TA ");
		setLocation(new Point(500, 300));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		filtersController = new FiltersController();
		
		addControls();
		
		setVisible(true);
	}
	
	private void addControls() {
		
		
		
	}
}
