package ui.eventpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import model.PhdDiscussion;
import utils.DateUtils;
import utils.LabelsConfig;

public class PhdDiscussionEventPanel extends JPanel {

	String taName; 
	
	public PhdDiscussionEventPanel(String name) {

		this.setLayout(null);
		taName = name;
		addControls();
		setVisible(true);
	}

	private void addControls() {
		
		JLabel discussionDateLabel = new JLabel(LabelsConfig.getLabel(LabelsConfig.DISCUSSION_DATE) + " (DD/MM/YYYY):");
		JTextField discussionDate = new JTextField();
		discussionDate.setBounds(120,25,200, 25);
		discussionDateLabel.setBounds(330,25,200, 25);
		
		JButton addEventButton = new JButton(LabelsConfig.getLabel(LabelsConfig.ADD_EVENT));
		addEventButton.setBounds(225,75,100, 25);
		
		this.add(discussionDate);
		this.add(discussionDateLabel);
		this.add(addEventButton);
		
		addEventButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(DateUtils.matchDate(discussionDate.getText())) {
						AddEventController ctrl = new AddEventController();
						PhdDiscussion event = new PhdDiscussion();
						event.setDate(discussionDate.getText());
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
