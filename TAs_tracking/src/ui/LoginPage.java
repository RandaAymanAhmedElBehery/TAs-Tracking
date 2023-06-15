package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Exceptions.LoginErrorException;
import controllers.LoginController;
import utils.LabelsConfig;


public class LoginPage extends JFrame{
	
	int width = 400;
	int height = 400;
	
	LoginController loginController;
	
	public LoginPage() {
		setSize(width, height);
		setTitle("Login Page");
		setLocation(new Point(500, 300));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginController = new LoginController();
		
		addControls();
		
		setVisible(true);
		
		
	}

	private void addControls() {
		
		JLabel usernameLabel = new JLabel(LabelsConfig.getNameLabel());
		usernameLabel.setFont(new Font("Serif", Font.BOLD, 14));
		usernameLabel.setBounds(270, 8, 70, 20);
		JTextField username = new JTextField("basma");
		username.setBounds(100, 27, 193, 28);
		JLabel passwordLabel = new JLabel(LabelsConfig.getPasswordLabel());
		passwordLabel.setFont(new Font("Serif", Font.BOLD, 14));
		passwordLabel.setBounds(230, 55, 70, 20);
		JPasswordField password = new JPasswordField("basma");
		password.setBounds(100, 75, 193, 28);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel.add(usernameLabel);
		mainPanel.add(username);
		mainPanel.add(passwordLabel);
		mainPanel.add(password);
		
		JButton loginButton = new JButton(LabelsConfig.getLoginLabel());
		loginButton.setBounds(130, 110, 120, 25);
		JButton signupButton = new JButton(LabelsConfig.getSignUpLabel());
		signupButton.setBounds(130, 150, 120, 25);
		JButton editProfileButton = new JButton(LabelsConfig.getEditProfileLabel());
		editProfileButton.setBounds(130, 190, 120, 25);
		
		JLabel userMessages = new JLabel();
		userMessages.setBounds(10, 250, 400, 25);
		
		mainPanel.add(loginButton);
		mainPanel.add(signupButton);
		mainPanel.add(editProfileButton);
		mainPanel.add(userMessages);
		
		
		this.add(mainPanel);
		
		loginButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					loginController.login(username.getText(), password.getText());
					userMessages.setForeground(Color.GREEN);
					userMessages.setText(username.getText() + " Logged in successfully");
					dispose();
					
					ViewAllTAsPage searchPage = new ViewAllTAsPage();
				} catch (LoginErrorException e1) {
					userMessages.setForeground(Color.RED);
					userMessages.setText("Username and/or Password are incorrect");
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
				
			}  
			
		});
		
		signupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUpPage();
				
			}
		});
		/*
		editProfileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					recommendeAlgorithm.sign_in(username.getText(), password.getText());
					dispose();
					
					new UpdateUserProfilePage(recommendeAlgorithm);
				} catch (LoginErrorException e1) {
					userMessages.setForeground(Color.RED);
					userMessages.setText("Username and/or Password are incorrect");
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		*/
	}

}
