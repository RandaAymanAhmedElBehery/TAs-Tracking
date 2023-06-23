package ui.eventpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import model.MastersExtension;
import model.MastersPause;
import model.PhdPause;
import utils.DateUtils;
import utils.LabelsConfig;

public class PhdPauseEventPanel extends JPanel {
	
	String taName; 
	
	public PhdPauseEventPanel(String name) {
		this.setLayout(null);
		taName = name;
		addControls();
		setVisible(true);
	}
	
	private void addControls() {
		
		JLabel startDateLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.START_DATE) + " (DD/MM/YYYY):");
		JTextField startDate = new JTextField();
		startDate.setBounds(120,25,200, 25);
		startDateLabel.setBounds(330,25,200, 25);
		
		JLabel endDateLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.END_DATE) + " (DD/MM/YYYY):");
		JTextField endDate = new JTextField();
		endDate.setBounds(120,65,200, 25);
		endDateLabel.setBounds(330,65,200, 25);
		
		JButton addEventButton = new JButton(LabelsConfig.getLabel(LabelsConfig.ADD_EVENT));
		addEventButton.setBounds(225,115,100, 25);
		
		this.add(startDate);
		this.add(startDateLabel);
		this.add(endDate);
		this.add(endDateLabel);
		this.add(addEventButton);
		
		addEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(DateUtils.matchDate(startDate.getText()) && DateUtils.matchDate(endDate.getText())) {
						AddEventController ctrl = new AddEventController();
						PhdPause event = new PhdPause();
						event.setDate(startDate.getText());
						event.setEndDate(endDate.getText());
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
