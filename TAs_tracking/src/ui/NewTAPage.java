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

import Exceptions.TAAlreadyExistsException;
import controllers.TAController;
import utils.TitlesReader;

public class NewTAPage extends JFrame{
	
	int width = 300;
	int height = 500;
	
	public NewTAPage() {
		
		setSize(width, height);
		setTitle("Add a new TA");
		setLocation(new Point(500, 300));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addControls();
	}

	private void addControls() {
		
		JLabel tanameLabel = new JLabel("TA Name:");
		tanameLabel.setBounds(20, 5, 70, 25);
		JTextField taname = new JTextField("");
		taname.setBounds(20, 30, 200, 25);
		
		JLabel mobileNoLabel = new JLabel("Mobile:");
		mobileNoLabel.setBounds(20, 55, 70, 25);
		JTextField mobileNo = new JTextField("");
		mobileNo.setBounds(20, 80, 200, 25);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(20, 105, 200, 25);
		JTextField email = new JTextField("");
		email.setBounds(20, 130, 200, 25);
		
		JLabel yearLabel = new JLabel("Date of hiring:");
		yearLabel.setBounds(20, 155, 200, 25);
		JTextField year = new JTextField("");
		year.setBounds(20, 180, 200, 25);
		
		String [] titlesArr = TitlesReader.getTitles();
		JLabel currentTitleLabel = new JLabel("Title:");
		currentTitleLabel.setBounds(20, 205, 200, 25);
		JComboBox<String> titles = new JComboBox<String>(titlesArr);
		titles.setBounds(20, 230, 200, 25);
		
		String [] vacArr = {"No", "Yes"};
		JLabel onVacationLabel = new JLabel("TA on Vacation:");
		onVacationLabel.setBounds(20, 255, 200, 25);
		JComboBox<String> onVacation = new JComboBox<String>(vacArr);
		onVacation.setBounds(20, 280, 200, 25);
			
		JButton addTA = new JButton("Add TA");
		addTA.setBounds(20, 320, 200, 25);
		
		JButton homePage = new JButton("Home Page");
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
		
		this.add(addTA);
		this.add(homePage);
		this.add(userMessages);
		
		addTA.addActionListener(new ActionListener() {
			
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
						taController.addNewTA(taname.getText(), mobileNo.getText(), email.getText(), 
								year.getText(),titles.getSelectedItem().toString()
								, onVacation.getSelectedItem().toString());
						
						userMessages.setForeground(Color.GREEN);
						userMessages.setText("TA Added Successfully");
					}
				} catch (TAAlreadyExistsException e1) {
					userMessages.setForeground(Color.RED);
					userMessages.setText(e1.getMessage());
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		homePage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomePage();
				
			}
		});
	}


}
