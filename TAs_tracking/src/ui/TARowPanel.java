package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TA;

public class TARowPanel extends JPanel{

	int width = 400;
	int height = 100;
	TA ta;
	String topicName;
	public TARowPanel(TA ta) {
		
		this.ta = ta;
		
		setSize(width, height);
		addTAInfo();
		
		setVisible(true);
	}
	private void addTAInfo() {
		ta.display();
		this.setLayout(new GridLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		JLabel viewTA = new JLabel();
		viewTA.setText(("<html><a href=''>View</a></html>"));
		viewTA.setForeground(Color.BLUE.darker());
		viewTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(viewTA);
		
		JLabel editTA = new JLabel();
		editTA.setText(("<html><a href=''>Edit</a></html>"));
		editTA.setForeground(Color.BLUE.darker());
		editTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(editTA);
		
		JLabel taName = new JLabel(ta.getName());
		taName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(taName);
		
		JLabel taTitle = new JLabel(ta.getTitle());
		taTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(taTitle);
		
		JLabel yearOfHire = new JLabel(ta.getYearOfHiring());
		yearOfHire.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(yearOfHire);
		
	}
}
