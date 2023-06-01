package ui;

import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.TAController;
import model.TA;

public class ViewAllTAsPage extends JFrame{
	
	int width = 600;
	int height = 400;
	
	int headersNum = 5;
	
	public ViewAllTAsPage() {
		setSize(width, height);
		setTitle("View All TAs Page");
		setLocation(new Point(500, 300));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addControls();
		
		setVisible(true);
		
	}

	private void addControls() {
		JPanel mainPanel = new JPanel();
		
		TAController taController = new TAController();
		ArrayList<TA> allTAs = taController.getAllTAs();
		
		int size = allTAs.size();
		
		/*
		String[] headers = {"<html><b>#</b></html>",
				"<html><b>Name</b></html>", 
				"<html><b>Title</b></html>", 
				"<html><b>View</b></html>", 
				"<html><b>Edit</b></html>"};
		
		
		JTable tasTable = new JTable(getTAsDataAsStrings(allTAs), headers);
		*/
		mainPanel.setLayout(new GridLayout(size, 1));
		TARowPanel[] tasPanels = new TARowPanel[size];
		int i = 0;
		for(TARowPanel p: tasPanels) {
			p = new TARowPanel(allTAs.get(i));
			i++;
			mainPanel.add(p);
		}
		
		JScrollPane pane = new JScrollPane(mainPanel);
		this.add(pane);
	}

	private Object[][] getTAsDataAsStrings(ArrayList<TA> allTAs) {
		
		Object[][]allTAsData = new Object[allTAs.size()][headersNum];
		int i = 0;
		for(TA ta : allTAs) {
			JButton view = new JButton("View");
			JButton edit = new JButton("Edit");
			Object [] taData = {i+1, ta.getName(),ta.getTitle(),view ,  edit};
			allTAsData[i] = taData;
			i++;
		}
		return allTAsData;
	}

}
