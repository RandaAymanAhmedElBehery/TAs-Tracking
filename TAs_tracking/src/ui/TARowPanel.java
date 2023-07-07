package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.TA;
import utils.DateUtils;
import utils.EventsConfigReader;
import utils.LabelsConfig;

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
		viewTA.setText("<html>" +  LabelsConfig.getViewHistoryLabel() + "</html>");
		viewTA.setHorizontalAlignment(SwingConstants.RIGHT);
		viewTA.setForeground(Color.BLUE.darker());
		viewTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(viewTA);
		
		JLabel AddEventToTA = new JLabel();
		AddEventToTA.setText(LabelsConfig.getAddEventLabel());
		AddEventToTA.setHorizontalAlignment(SwingConstants.RIGHT);
		AddEventToTA.setForeground(Color.BLUE.darker());
		AddEventToTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(AddEventToTA);
		
		JLabel editTA = new JLabel();
		editTA.setText(LabelsConfig.getEditTALabel());
		editTA.setHorizontalAlignment(SwingConstants.RIGHT);
		editTA.setForeground(Color.BLUE.darker());
		editTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(editTA);
		
		
		JLabel yearOfHiring = new JLabel(DateUtils.dateToString(ta.getHiringDate()));
////		yearOfHire.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		yearOfHiring.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(yearOfHiring);
		
		JLabel onVacation = new JLabel(EventsConfigReader.getIsOnVacation(ta.isOnVacation()));
//		yearOfHire.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		onVacation.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(onVacation);
		
		JLabel taTitle = new JLabel(ta.getTitle());
//		taTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		taTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(taTitle);
		
		
		JLabel taName = new JLabel(ta.getName());
//		taName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		taName.setHorizontalAlignment(SwingConstants.RIGHT);
		taName.setFont(new Font("Serif", Font.BOLD, 14));
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
		
		AddEventToTA.addMouseListener(new MouseListener() {
			
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
		
		editTA.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				new EditTAPage(ta);
				
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
