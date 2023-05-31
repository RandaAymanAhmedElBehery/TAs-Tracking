package ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Exceptions.UserNameExistsException;
import controllers.LoginController;


public class SignUpPage extends JFrame{
	
	
	int width = 400;
	int height = 400;
	
	LoginController loginController;
	
	public SignUpPage() {
			
		setSize(width, height);
		setTitle("Login Page");
		setLocation(new Point(500, 300));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		loginController = new LoginController();
		
		addControls();
		setVisible(true);
	}

	private void addControls() {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		JLabel usernameLabel = new JLabel("User Name");
		usernameLabel.setBounds(100, 8, 70, 20);
		JTextField username = new JTextField("");
		username.setBounds(100, 27, 193, 28);
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 55, 70, 20);
		JPasswordField password = new JPasswordField("");
		password.setBounds(100, 75, 193, 28);

		mainPanel.add(usernameLabel);
		mainPanel.add(username);
		mainPanel.add(passwordLabel);
		mainPanel.add(password);
		
		
		JButton signupButton = new JButton("Sign Up");
		signupButton.setBounds(130, 120, 120, 25);
		JButton backButton = new JButton("Login Page");
		backButton.setBounds(130, 150, 120, 25);
		
		JLabel userMessages = new JLabel();
		userMessages.setBounds(100, 200, 400, 25);
		
		mainPanel.add(signupButton);
		mainPanel.add(backButton);
		mainPanel.add(userMessages);
		
		
		this.add(mainPanel);
		
		signupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loginController.sign_up(username.getText(), password.getText());
					userMessages.setForeground(Color.GREEN);
					userMessages.setText(username.getText() + " Signed up successfully");
				} catch (UserNameExistsException e1) {
					userMessages.setForeground(Color.RED);
					userMessages.setText("Username already exists.");
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginPage();
				
			}
		});
		
		
	}
	
}
