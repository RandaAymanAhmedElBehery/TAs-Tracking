package ui.eventpanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AddEventController;
import controllers.TAController;
import model.Event;
import model.NewTA;
import model.Promotion;
import model.TA;
import utils.DateUtils;
import utils.LabelsConfig;
import utils.TitlesReader;

public class PromotionEventPanel extends JPanel {

	String taName; 
	
	public PromotionEventPanel(String name) {

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
						
						TAController taCtrl = new TAController();
						TA ta = taCtrl.getTAByName(taName);
						String title = ta.getTitle();
						
						if (title.equals(TitlesReader.getArTitle("NewTA"))){
													
							AddEventController ctrl = new AddEventController();
							Promotion event = new Promotion();
							event.setDate(date.getText());
							boolean add = ctrl.addEventToTA(taName, event);
							boolean promote = ctrl.promoteTA(taName, TitlesReader.getArTitle("TA"));
							if (add && promote)
								JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.SUCCESS));
							else
								JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.ERROR));
						} else{
							int response = JOptionPane.showConfirmDialog(null, LabelsConfig.getLabel(LabelsConfig.CONFIRM_DELETE_TA_MSG), LabelsConfig.getLabel(LabelsConfig.CONFIRM), JOptionPane.YES_NO_OPTION);
							if (response == JOptionPane.YES_OPTION){
								taCtrl.removeTA(taName);
								JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.SUCCESS));
							}
						}
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, LabelsConfig.getLabel(LabelsConfig.ERROR));
				}
				
			}
		});
	}

}
