package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Event;
import model.TA;
import utils.DateUtils;
import utils.EventsConfigReader;
import utils.LabelsConfig;

public class ViewTAHistory extends JFrame{
	
	int width = 800;
	int height = 800;
	
	TA ta;
	
	//basic info  + list of event panels
	public ViewTAHistory(TA ta) {
		setSize(width, height);
		setTitle(ta.getName());
		setLocation(new Point(200, 0));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.ta = ta;
		addControls();
		
		setVisible(true);
	}

	private void addControls() {
		this.setLayout(new GridLayout(2,1));
		
		///////////////////// Adding Basic Info /////////////////////
		JPanel basicInfoPanel = new JPanel();
		
		JLabel tanameLabel = new JLabel(LabelsConfig.getNameLabel());
		tanameLabel.setBounds(700, 5, 70, 25);
		JTextField taname = new JTextField(ta.getName());
		taname.setHorizontalAlignment(JTextField.RIGHT);
		taname.setBounds(520, 30, 200, 25);
		taname.setEditable(false);
		
		JLabel mobileNoLabel = new JLabel(LabelsConfig.getMobileLabel());
		mobileNoLabel.setBounds(690, 55, 70, 25);
		JTextField mobileNo = new JTextField(ta.getMobileNo());
//		mobileNo.setHorizontalAlignment(JTextField.RIGHT);
		mobileNo.setBounds(520, 80, 200, 25);
		mobileNo.setEditable(false);
		
		JLabel emailLabel = new JLabel(LabelsConfig.getEmailLabel());
		emailLabel.setBounds(650, 105, 200, 25);
		JTextField email = new JTextField(ta.getEmail());
//		email.setHorizontalAlignment(JTextField.RIGHT);
		email.setBounds(520, 130, 200, 25);
		email.setEditable(false);
		
		JLabel yearLabel = new JLabel(LabelsConfig.getHiringDateLabel());
		yearLabel.setBounds(670, 155, 200, 25);
		JTextField year = new JTextField(DateUtils.dateToString(ta.getHiringDate()));
//		year.setHorizontalAlignment(JTextField.RIGHT);
		year.setBounds(520, 180, 200, 25);
		year.setEditable(false);
		
		JLabel titleLabel = new JLabel(LabelsConfig.getTitleLabel());
		titleLabel.setBounds(660, 205, 200, 25);
		JTextField title = new JTextField(ta.getTitle());
		title.setHorizontalAlignment(JTextField.RIGHT);
		title.setBounds(520, 230, 200, 25);
		title.setEditable(false);
		
		JLabel onVacationLabel = new JLabel(LabelsConfig.getOnVacationLabel());
		onVacationLabel.setBounds(680, 255, 200, 25);
		JTextField onVacation = new JTextField(EventsConfigReader.getIsOnVacation(ta.isOnVacation()));
		onVacation.setHorizontalAlignment(JTextField.RIGHT);
		onVacation.setBounds(520, 280, 200, 25);
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
		TitledBorder titledBorder = BorderFactory.createTitledBorder(LabelsConfig.getViewHistoryLabel());
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		historyPanel.setBorder(titledBorder);
		historyPanel.setLayout(new GridLayout(history.size(),1));
		for (Event e : history) {
			EventPanel ep = new EventPanel(e);
			historyPanel.add(ep);
			
		}
		
		JScrollPane pane = new JScrollPane(historyPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(pane);
	
		
		
	}
	

}
