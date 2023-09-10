package ui.eventpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import model.NewTA;
import utils.DateUtils;
import utils.LabelsConfig;

public class NewTAEventPanel extends JPanel {

	String taName; 
	
	public NewTAEventPanel(String name) {

		this.setLayout(null);
		taName = name;
		addControls();
		setVisible(true);
	}

	private void addControls() {
		
		JLabel hiringDateLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.HIRING_DATE) + " (DD/MM/YYYY):");
		JTextField hiringDate = new JTextField();
		hiringDate.setBounds(120,25,200, 25);
		hiringDateLabel.setBounds(330,25,200, 25);
		
		JButton addEventButton = new JButton(LabelsConfig.getLabel(LabelsConfig.ADD_EVENT));
		addEventButton.setBounds(225,75,100, 25);
		
		this.add(hiringDate);
		this.add(hiringDateLabel);
		this.add(addEventButton);
		
		addEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(DateUtils.matchDate(hiringDate.getText())) {
						AddEventController ctrl = new AddEventController();
						NewTA event = new NewTA();
						event.setDate(hiringDate.getText());
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
