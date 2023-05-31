package ui;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

//list all Tas with all options
public class HomePage extends JFrame{
	
	int width = 400;
	int height = 300;
	
	public HomePage() {
		
		setSize(width, height);
		setTitle("Home Page");
		setLocation(new Point(500, 300));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addControls();
	}

	private void addControls() {
		
		JButton newTA = new JButton ("Add a new TA");
		JButton viewAllTAs = new JButton("List All TAs");
		JButton addEvent = new JButton("Add an Event to a TA");
		JButton filters = new JButton ("Custom Search (filters)");
		
		this.setLayout(new GridLayout(4,1));
		this.add(newTA);
		this.add(viewAllTAs);
		this.add(addEvent);
		this.add(filters);
		
		newTA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new NewTAPage();
				
			}
		});
		
		viewAllTAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ViewAllTAsPage();
				
			}
		});
		
		addEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AddEventToTAPage();
				
			}
		});
		
		filters.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FiltersPage();
				
			}
		});
	}

}
