package ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TA;
import ui.eventpanels.MastersDiscussionEventPanel;
import ui.eventpanels.MastersExtensionEventPanel;
import ui.eventpanels.MastersPauseEventPanel;
import ui.eventpanels.MastersRegistrationEventPanel;
import ui.eventpanels.NewTAEventPanel;
import ui.eventpanels.PhdDiscussionEventPanel;
import ui.eventpanels.PhdExtensionEventPanel;
import ui.eventpanels.PhdPauseEventPanel;
import ui.eventpanels.PhdQualificationEventPanel;
import ui.eventpanels.PhdRegistrationEventPanel;
import ui.eventpanels.PrePhdRegistrationEventPanel;
import ui.eventpanels.PremastersRegistrationEventPanel;
import ui.eventpanels.PromotionEventPanel;
import ui.eventpanels.ResignationEventPanel;
import ui.eventpanels.ResumeWorkEventPanel;
import ui.eventpanels.VacationEventPanel;
import utils.EventsConfigReader;
import utils.LabelsConfig;

public class AddEventToTAPage extends JFrame{
	
	int width = 600;
	int height = 600;
	String [] eventOptions = EventsConfigReader.getEventsArabicList();

	TA ta;
	JPanel detailsPanel = new JPanel();
		
	//basic info  + list of event panels
	public AddEventToTAPage(TA ta) {
		setSize(width, height);
		setTitle(ta.getName());
		setLocation(new Point(200, 100));
		setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.ta = ta;
		addControls();
		
		setVisible(true);
	}

	private void addControls() {
		
		JPanel mainPanel = new JPanel();
		JLabel eventLabel = new JLabel(LabelsConfig.getLabel("event"));
	    JComboBox<String> eventsMenu = new JComboBox<>(eventOptions);
	    eventsMenu.setSelectedItem(null);

	    mainPanel.setLayout(new BorderLayout());
	    
	    JPanel selectEventPanel = new JPanel();
	    selectEventPanel.add(eventsMenu);
	    selectEventPanel.add(eventLabel);

		mainPanel.add(selectEventPanel, BorderLayout.NORTH);
		this.add(mainPanel);
		
		mainPanel.add(detailsPanel);
		
		eventsMenu.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String selectedEventAR = String.valueOf(eventsMenu.getSelectedItem());
				String eventName = EventsConfigReader.getEventEnglishName(selectedEventAR);

				mainPanel.remove(detailsPanel);
				
				if (eventName.equals("model.NewTA")){
					detailsPanel =new NewTAEventPanel(ta.getName());
				}else if (eventName.equals("model.MastersExtension")){
					detailsPanel =new MastersExtensionEventPanel(ta.getName());
				}else if (eventName.equals("model.PhdExtension")){
					detailsPanel =new PhdExtensionEventPanel(ta.getName());
				}else if (eventName.equals("model.MastersDiscussion")){
					detailsPanel =new MastersDiscussionEventPanel(ta.getName());
				}else if (eventName.equals("model.PhdDiscussion")){
					detailsPanel =new PhdDiscussionEventPanel(ta.getName());
				}else if (eventName.equals("model.MastersPause")){
					detailsPanel =new MastersPauseEventPanel(ta.getName());
				}else if (eventName.equals("model.PhdPause")){
					detailsPanel =new PhdPauseEventPanel(ta.getName());
				}else if (eventName.equals("model.PremastersRegistration")){
					detailsPanel =new PremastersRegistrationEventPanel(ta.getName());
				}else if (eventName.equals("model.PrePhdRegistration")){
					detailsPanel =new PrePhdRegistrationEventPanel(ta.getName());
				}else if (eventName.equals("model.PhdRegistration")){
					detailsPanel =new PhdRegistrationEventPanel(ta.getName());
				}else if (eventName.equals("model.MastersRegistration")){
					detailsPanel =new MastersRegistrationEventPanel(ta.getName());
				}else if (eventName.equals("model.PhdQualification")){
					detailsPanel =new PhdQualificationEventPanel(ta.getName());
				}else if (eventName.equals("model.Promotion")){
					detailsPanel =new PromotionEventPanel(ta.getName());
				}else if (eventName.equals("model.Resignation")){
					detailsPanel =new ResignationEventPanel(ta.getName());
				}else if (eventName.equals("model.ResumeWork")){
					detailsPanel =new ResumeWorkEventPanel(ta.getName());
				}else if (eventName.equals("model.Vacation")){
					detailsPanel =new VacationEventPanel(ta.getName());
				}
				mainPanel.add(detailsPanel);
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
	    
	}

}
