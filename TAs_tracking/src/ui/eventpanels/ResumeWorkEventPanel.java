package ui.eventpanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import model.Event;
import model.NewTA;
import model.ResumeWork;
import utils.DateUtils;
import utils.LabelsConfig;

public class ResumeWorkEventPanel extends JPanel {

	String taName; 
	
	public ResumeWorkEventPanel(String name) {

		this.setLayout(null);
		taName = name;
		addControls();
		setVisible(true);
	}

	private void addControls() {
		
		JLabel dateLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.DATE) + " (DD/MM/YYYY):");
		JTextField date = new JTextField();
		date.setBounds(120,25,200, 25);
		dateLabel.setBounds(330,25,200, 25);
		
		JButton addEventButton = new JButton(LabelsConfig.getLabel(LabelsConfig.ADD_EVENT));
		addEventButton.setBounds(225,75,100, 25);
		
		this.add(date);
		this.add(dateLabel);
		this.add(addEventButton);
		
		addEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(DateUtils.matchDate(date.getText())) {
						AddEventController ctrl = new AddEventController();
						ResumeWork event = new ResumeWork();
						event.setDate(date.getText());
						boolean onVacation = ctrl.setTAOnVacation(taName, false);
						boolean add = ctrl.addEventToTA(taName, event);
						if (add && onVacation)
							JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.SUCCESS));
						else
							JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.ERROR));
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.ERROR));
				}
				
			}
		});
	}

}
