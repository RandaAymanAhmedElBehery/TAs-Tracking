package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TA;
import utils.DateUtils;

public class TARowPanel extends JPanel{

	int width = 1000;
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
		this.setBorder(BorderFactory.createTitledBorder(""));

		JLabel viewTA = new JLabel();
		viewTA.setText(("<html><a href=''>View History</a></html>"));
		viewTA.setForeground(Color.BLUE.darker());
		viewTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(viewTA);
		
		JLabel editTA = new JLabel();
		editTA.setText(("<html><a href=''>Add Event</a></html>"));
		editTA.setForeground(Color.BLUE.darker());
		editTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(editTA);
		
		JLabel yearOfHire = new JLabel(DateUtils.dateToString(ta.getHiringDate()));
////		yearOfHire.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(yearOfHire);
		
		JLabel onVacation = new JLabel(new Boolean(ta.isOnVacation()).toString());
//		yearOfHire.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(onVacation);
		
		JLabel taTitle = new JLabel(ta.getTitle());
//		taTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(taTitle);
		
		
		JLabel taName = new JLabel(ta.getName());
//		taName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(taName);
		
		
		///////////////// Mouse Listeners //////////////////////
		
		viewTA.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				new ViewTAHistory(ta);
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		editTA.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				new AddEventToTAPage(ta);
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
