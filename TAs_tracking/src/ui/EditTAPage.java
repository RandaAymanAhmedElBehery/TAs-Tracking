package ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllers.TAController;
import model.TA;
import utils.DateUtils;
import utils.EventsConfigReader;
import utils.LabelsConfig;
import utils.TitlesReader;

public class EditTAPage extends JFrame{
	
	int width = 300;
	int height = 500;
	
	TA ta;
	
	public EditTAPage(TA ta) {
		
		this.ta = ta;
		setSize(width, height);
		setTitle(ta.getName());
		setLocation(new Point(500, 300));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addControls();
	}

	private void addControls() {
		
		JLabel tanameLabel = new JLabel(LabelsConfig.getNameLabel());
		tanameLabel.setBounds(200, 5, 70, 25);
		JTextField taname = new JTextField("");
		taname.setBounds(20, 30, 200, 25);
		taname.setText(ta.getName());
		taname.setEditable(false);
		
		JLabel mobileNoLabel = new JLabel(LabelsConfig.getMobileLabel());
		mobileNoLabel.setBounds(180, 55, 70, 25);
		JTextField mobileNo = new JTextField("");
		mobileNo.setBounds(20, 80, 200, 25);
		mobileNo.setText(ta.getMobileNo());
		
		JLabel emailLabel = new JLabel(LabelsConfig.getEmailLabel());
		emailLabel.setBounds(150, 105, 200, 25);
		JTextField email = new JTextField("");
		email.setBounds(20, 130, 200, 25);
		email.setText(ta.getEmail());
		
		JLabel yearLabel = new JLabel(LabelsConfig.getHiringDateLabel() + " (DD/MM/YYYY):");
		yearLabel.setBounds(80, 155, 200, 25);
		JTextField year = new JTextField("");
		year.setBounds(20, 180, 200, 25);
		year.setText(DateUtils.dateToString(ta.getHiringDate()));
		
		String [] titlesArr = TitlesReader.getTitles();
		JLabel currentTitleLabel = new JLabel(LabelsConfig.getTitleLabel());
		currentTitleLabel.setBounds(160, 205, 200, 25);
		JComboBox<String> titles = new JComboBox<String>(titlesArr);
		titles.setBounds(20, 230, 200, 25);
		titles.setSelectedItem(ta.getTitle());
		
		String [] vacArr = {EventsConfigReader.getIsOnVacation(false), EventsConfigReader.getIsOnVacation(true)};
		JLabel onVacationLabel = new JLabel(LabelsConfig.getOnVacationLabel());
		onVacationLabel.setBounds(180, 255, 200, 25);
		JComboBox<String> onVacation = new JComboBox<String>(vacArr);
		onVacation.setBounds(20, 280, 200, 25);
		onVacation.setSelectedItem(ta.isOnVacation());
			
		JButton editTA = new JButton(LabelsConfig.getEditTALabel());
		editTA.setBounds(20, 320, 200, 25);
		
		JButton homePage = new JButton(LabelsConfig.getHomePageLabel());
		homePage.setBounds(20, 360, 200, 25);
		
		JLabel userMessages = new JLabel();
		userMessages.setBounds(20, 400, 200, 25);
		
		this.setLayout(null);
		
		this.add(tanameLabel);
		this.add(taname);
		
		this.add(mobileNoLabel);
		this.add(mobileNo);
		
		this.add(emailLabel);
		this.add(email);
		
		this.add(yearLabel);
		this.add(year);
		
		this.add(currentTitleLabel);
		this.add(titles);
		
		this.add(onVacationLabel);
		this.add(onVacation);
		
		this.add(editTA);
		this.add(homePage);
		this.add(userMessages);
		
		editTA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TAController taController = new TAController();
				try {
					if(taname.getText().equals("") || mobileNo.getText().equals("")
							|| email.getText().equals("")
							|| year.getText().equals("")) {
						userMessages.setForeground(Color.RED);
						userMessages.setText("All fields are required");
					}else {
						if(DateUtils.matchDate(year.getText())) {
							
							ta.setMobileNo(mobileNo.getText());
							ta.setEmail(email.getText());
							System.out.println(year.getText());
							System.out.println("Hiring date before: " + ta.getHiringDate());
							ta.setHiringDate(DateUtils.stringtoDate(year.getText()));
							System.out.println("Hiring date after: " + ta.getHiringDate());
							ta.setTitle(titles.getSelectedItem().toString());
							if(onVacation.getSelectedItem().toString().equalsIgnoreCase(EventsConfigReader.getIsOnVacation(false)))
								ta.setOnVacation(false);
							else
								ta.setOnVacation(true);
									
							boolean updated = taController.updateTA(ta);
							if(updated) {
								userMessages.setForeground(Color.GREEN);
								userMessages.setText("TA Edited Successfully");
							}else {
								userMessages.setForeground(Color.RED);
								userMessages.setText("Error Updating TA");
							}
						}else {
							userMessages.setForeground(Color.RED);
							userMessages.setText("Please Enter a valid date");
						}
					}
				} catch (Exception e1) {
					userMessages.setForeground(Color.RED);
					userMessages.setText(e1.getMessage());
				}
				
				
			}
		});
		
		homePage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ViewAllTAsPage();
				
			}
		});
	}

	
}
