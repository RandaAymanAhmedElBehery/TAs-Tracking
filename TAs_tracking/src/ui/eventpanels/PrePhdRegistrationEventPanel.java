package ui.eventpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import model.PrePhdRegistration;
import utils.DateUtils;
import utils.LabelsConfig;

public class PrePhdRegistrationEventPanel extends JPanel {
	
	String taName; 
	
	public PrePhdRegistrationEventPanel(String name) {
		this.setLayout(null);
		taName = name;
		addControls();
		setVisible(true);
	}
	
	private void addControls() {
		
		JLabel registrationDateLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.REGISTRATION_DATE) + " (DD/MM/YYYY):");
		JTextField registrationDate = new JTextField();
		registrationDate.setBounds(120,25,200, 25);
		registrationDateLabel.setBounds(330,25,200, 25);
		
		JButton addEventButton = new JButton(LabelsConfig.getLabel(LabelsConfig.ADD_EVENT));
		addEventButton.setBounds(225,75,100, 25);
		
		this.add(registrationDate);
		this.add(registrationDateLabel);
		this.add(addEventButton);
		
		addEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(DateUtils.matchDate(registrationDate.getText())) {
						AddEventController ctrl = new AddEventController();
						PrePhdRegistration event = new PrePhdRegistration();
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
