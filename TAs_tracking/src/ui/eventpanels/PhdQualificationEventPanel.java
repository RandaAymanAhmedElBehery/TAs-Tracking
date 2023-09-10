package ui.eventpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import model.PhdQualification;
import utils.DateUtils;
import utils.LabelsConfig;

public class PhdQualificationEventPanel extends JPanel {
	
	String taName; 
	
	public PhdQualificationEventPanel(String name) {
		this.setLayout(null);
		taName = name;
		addControls();
		setVisible(true);
	}
	
	private void addControls() {
		
		JLabel regisrationDateLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.REGISTRATION_DATE) + " (DD/MM/YYYY):");
		JTextField registrationDate = new JTextField();
		registrationDate.setBounds(120,25,200, 25);
		regisrationDateLabel.setBounds(330,25,200, 25);
		
		JButton addEventButton = new JButton(LabelsConfig.getLabel(LabelsConfig.ADD_EVENT));
		addEventButton.setBounds(225,75,100, 25);
		
		this.add(registrationDate);
		this.add(regisrationDateLabel);
		this.add(addEventButton);
		
		addEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(DateUtils.matchDate(registrationDate.getText())) {
						AddEventController ctrl = new AddEventController();
						PhdQualification event = new PhdQualification();
						event.setDate(registrationDate.getText());
						boolean add = ctrl.addEventToTA(taName, event);
						if (add)
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
