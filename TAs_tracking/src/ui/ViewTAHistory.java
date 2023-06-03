package ui;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.TADAO;
import model.TA;

public class ViewTAHistory extends JFrame{
	
	int width = 600;
	int height = 800;
	
	TA ta;
	
	//basic info  + list of event panels
	public ViewTAHistory(TA ta) {
		setSize(width, height);
		setTitle(ta.getName());
		setLocation(new Point(500, 300));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ta = ta;
		addControls();
		
		setVisible(true);
	}

	private void addControls() {
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
		JTextField year = new JTextField(ta.getYearOfHiring());
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
		
		this.setLayout(null);
		
		this.add(tanameLabel);
		this.add(taname);
		
		this.add(mobileNoLabel);
		this.add(mobileNo);
		
		this.add(emailLabel);
		this.add(email);
		
		this.add(yearLabel);
		this.add(year);
		
		this.add(titleLabel);
		this.add(title);
		
		this.add(onVacationLabel);
		this.add(onVacation);
		
		
	}
	
	public static void main(String[] args) {
		TA ta = new TADAO().getTAByName("n");
		ta.display();
		new ViewTAHistory(ta);
	}

}
