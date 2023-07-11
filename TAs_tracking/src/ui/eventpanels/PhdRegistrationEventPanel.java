package ui.eventpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import model.PhdRegistration;
import utils.DateUtils;
import utils.LabelsConfig;

public class PhdRegistrationEventPanel extends JPanel {
	
	String taName; 
	
	public PhdRegistrationEventPanel(String name) {
		this.setLayout(null);
		taName = name;
		addControls();
		setVisible(true);
	}
	
	private void addControls() {
		
		JLabel startDateLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.REGISTRATION_DATE) + " (DD/MM/YYYY):");
		JTextField startDate = new JTextField();
		startDate.setBounds(120,25,200, 25);
		startDateLabel.setBounds(330,25,200, 25);
		
		JLabel thesisTitleLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.THESIS_TITLE));
		JTextField thesisTitle = new JTextField();
		thesisTitle.setBounds(120,65,200, 25);
		thesisTitleLabel.setBounds(330,65,200, 25);
		
		JLabel supervisor1Label = new JLabel(LabelsConfig.getLabel(LabelsConfig.SUPERVISOR_1));
		JTextField supervisor1 = new JTextField();
		supervisor1.setBounds(120,105,200, 25);
		supervisor1Label.setBounds(330,105,200, 25);
		
		JLabel supervisor2Label = new JLabel(LabelsConfig.getLabel(LabelsConfig.SUPERVISOR_2));
		JTextField supervisor2 = new JTextField();
		supervisor2.setBounds(120,145,200, 25);
		supervisor2Label.setBounds(330,145,200, 25);
		
		JLabel supervisor3Label = new JLabel(LabelsConfig.getLabel(LabelsConfig.SUPERVISOR_3));
		JTextField supervisor3 = new JTextField();
		supervisor3.setBounds(120,185,200, 25);
		supervisor3Label.setBounds(330,185,200, 25);
		
		JButton addEventButton = new JButton(LabelsConfig.getLabel(LabelsConfig.ADD_EVENT));
		addEventButton.setBounds(225,235,100, 25);
		
		this.add(startDate);
		this.add(startDateLabel);
		this.add(thesisTitle);
		this.add(thesisTitleLabel);
		this.add(supervisor1);
		this.add(supervisor1Label);
		this.add(supervisor2);
		this.add(supervisor2Label);
		this.add(supervisor3);
		this.add(supervisor3Label);
		this.add(addEventButton);
		
		addEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(DateUtils.matchDate(startDate.getText())&& !supervisor1.getText().isEmpty()) {
						List<String> supervisors = new ArrayList<String>();
						supervisors.add(supervisor1.getText());
						if (!supervisor2.getText().isEmpty())
							supervisors.add(supervisor2.getText());
						if (!supervisor3.getText().isEmpty())
							supervisors.add(supervisor3.getText());
						AddEventController ctrl = new AddEventController();
						PhdRegistration event = new PhdRegistration();
						event.setDate(startDate.getText());
						event.setTitle(thesisTitle.getText());
						event.setSupervisors(supervisors);
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
