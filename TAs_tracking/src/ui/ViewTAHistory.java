package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Event;
import model.TA;
import utils.DateUtils;

public class ViewTAHistory extends JFrame{
	
	int width = 1200;
	int height = 800;
	
	TA ta;
	
	//basic info  + list of event panels
	public ViewTAHistory(TA ta) {
		setSize(width, height);
		setTitle(ta.getName());
		setLocation(new Point(500, 200));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.ta = ta;
		addControls();
		
		setVisible(true);
	}

	private void addControls() {
		this.setLayout(new GridLayout(2,1));
		
		///////////////////// Adding Basic Info /////////////////////
		JPanel basicInfoPanel = new JPanel();
		
		JLabel tanameLabel = new JLabel("TA Name:");
		tanameLabel.setBounds(20, 5, 70, 25);
		JTextField taname = new JTextField(ta.getName());
		taname.setBounds(20, 30, 200, 25);
		taname.setEditable(false);
		
		JLabel mobileNoLabel = new JLabel("Mobile:");
		mobileNoLabel.setBounds(20, 55, 70, 25);
		JTextField mobileNo = new JTextField(ta.getMobileNo());
		mobileNo.setBounds(20, 80, 200, 25);
		mobileNo.setEditable(false);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(20, 105, 200, 25);
		JTextField email = new JTextField(ta.getEmail());
		email.setBounds(20, 130, 200, 25);
		email.setEditable(false);
		
		JLabel yearLabel = new JLabel("Date of hiring:");
		yearLabel.setBounds(20, 155, 200, 25);
		JTextField year = new JTextField(DateUtils.dateToString(ta.getHiringDate()));
		year.setBounds(20, 180, 200, 25);
		year.setEditable(false);
		
		JLabel titleLabel = new JLabel("Title:");
		titleLabel.setBounds(20, 205, 200, 25);
		JTextField title = new JTextField(ta.getTitle());
		title.setBounds(20, 230, 200, 25);
		title.setEditable(false);
		
		JLabel onVacationLabel = new JLabel("On Vacation:");
		onVacationLabel.setBounds(20, 255, 200, 25);
		JTextField onVacation = new JTextField(new Boolean(ta.isOnVacation()).toString());
		onVacation.setBounds(20, 280, 200, 25);
		onVacation.setEditable(false);
		
		basicInfoPanel.setLayout(null);
		
		basicInfoPanel.add(tanameLabel);
		basicInfoPanel.add(taname);
		
		basicInfoPanel.add(mobileNoLabel);
		basicInfoPanel.add(mobileNo);
		
		basicInfoPanel.add(emailLabel);
		basicInfoPanel.add(email);
		
		basicInfoPanel.add(yearLabel);
		basicInfoPanel.add(year);
		
		basicInfoPanel.add(titleLabel);
		basicInfoPanel.add(title);
		
		basicInfoPanel.add(onVacationLabel);
		basicInfoPanel.add(onVacation);
		
		this.add(basicInfoPanel,BorderLayout.NORTH);
		
		/////////////////// Adding Events /////////////////////
		List<Event> history = ta.getHistory();

		JPanel historyPanel = new JPanel();
		historyPanel.setLayout(new GridLayout(history.size(),1,0,0));
		for (Event e : history) {
			EventPanel ep = new EventPanel(e);
			historyPanel.add(ep);
			
		}
		this.add(historyPanel);
		
	}
	

}
