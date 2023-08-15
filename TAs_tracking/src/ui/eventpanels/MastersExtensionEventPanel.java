package ui.eventpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import model.MastersExtension;
import utils.DateUtils;
import utils.LabelsConfig;

public class MastersExtensionEventPanel extends JPanel {
	
	String taName; 
	
	public MastersExtensionEventPanel(String name) {
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
		
		JLabel durationLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.DURATION));
		JTextField duration = new JTextField();
		duration.setBounds(120,65,200, 25);
		durationLabel.setBounds(330,65,200, 25);
		
		JButton addEventButton = new JButton(LabelsConfig.getLabel(LabelsConfig.ADD_EVENT));
		addEventButton.setBounds(225,115,100, 25);
		
		this.add(startDate);
		this.add(startDateLabel);
		this.add(duration);
		this.add(durationLabel);
		this.add(addEventButton);
		
		addEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(DateUtils.matchDate(startDate.getText())) {
						AddEventController ctrl = new AddEventController();
						MastersExtension event = new MastersExtension();
						event.setDate(startDate.getText());
						event.setDuration(Integer.parseInt(duration.getText()));
//						LocalDate endDate = LocalDate.of(event.getDate().getYear(),event.getDate().getMonth(),event.getDate().getDay()).plusMonths(event.getDuration());
//						event.setEndDate(Date.valueOf(endDate));
//						System.out.println(event.getEndDate());
						
						
						boolean add = ctrl.addEventToTA(taName, event);
						if (add)
							JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.SUCCESS));
						else
							JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.ERROR));
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.ERROR));
					ex.printStackTrace();
				}
				
			}
		});
	}

}
