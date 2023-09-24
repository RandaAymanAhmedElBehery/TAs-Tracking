package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controllers.FiltersController;
import model.TA;
import ui.tarowpanel.EndingAcademicEventTARowPanel;
import ui.tarowpanel.RegistrationEventTARowPanel;
import ui.tarowpanel.VacationTARowPanel;
import utils.EventsConfigReader;
import utils.LabelsConfig;
import utils.TitlesReader;

public class FiltersPage extends JFrame{
	
	int width = 1550;
	int height = 800;
	String [] vacationOptions = {EventsConfigReader.getEventArabicName("OnVacation"), EventsConfigReader.getEventArabicName("NotOnVacation")};
	String [] titleOptions = TitlesReader.getTitles();
	String [] normalEventOptions = EventsConfigReader.getNormalEventsArabicList();
	String [] academicEventOptions = EventsConfigReader.getAcademicEventsArabicList();
	String [] registrationStatusOptions = EventsConfigReader.getRegistrationStatusEventsList();

	FiltersController filtersController;
	
	public FiltersPage() {
		setSize(width, height);
		setTitle(LabelsConfig.getLabel("FilterTAs"));
		setLocation(new Point(0, 0));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		filtersController = new FiltersController();	
		
		addControls();
		
		setVisible(true);
	}
	
	private void addControls() {		
		
	    JPanel filtersPanel = new JPanel();

	    JLabel filterTypeLabel = new JLabel(LabelsConfig.getLabel("filterType"));
	    String[] choices = {LabelsConfig.getLabel(LabelsConfig.ON_VACATION),LabelsConfig.getLabel(LabelsConfig.TITLE),LabelsConfig.getLabel(LabelsConfig.REGISTRATION_STATUS),LabelsConfig.getLabel(LabelsConfig.LAST_EVENT),LabelsConfig.getLabel(LabelsConfig.LAST_ACADEMIC_EVENT)};
	    JComboBox<String> filterTypeMenu = new JComboBox<>(choices);
	    filterTypeMenu.setSelectedItem(null);

	    JComboBox<String> filterValueMenu = new JComboBox<>();

	    JButton filterButton = new JButton(LabelsConfig.getLabel("FilterTAs"));
	    JButton mainPageButton = new JButton(LabelsConfig.getHomePageLabel());

	    filtersPanel.add(mainPageButton);
	    filtersPanel.add(filterButton);
	    filtersPanel.add(filterValueMenu);
		filtersPanel.add(filterTypeMenu);
		filtersPanel.add(filterTypeLabel);
		
		JPanel tasPanel = new JPanel();

		JScrollPane pane = new JScrollPane(tasPanel);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);		
		
		this.add(filtersPanel, BorderLayout.NORTH);
		this.add(pane);
			
		
		filterTypeMenu.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				String options [] = null ;
				if (filterTypeMenu.getSelectedItem().equals(LabelsConfig.getLabel(LabelsConfig.ON_VACATION)))
					options = vacationOptions;
				else if (filterTypeMenu.getSelectedItem().equals(LabelsConfig.getLabel(LabelsConfig.TITLE)))
					options = titleOptions;
				else if (filterTypeMenu.getSelectedItem().equals(LabelsConfig.getLabel(LabelsConfig.REGISTRATION_STATUS)))
					options = registrationStatusOptions;
				else if (filterTypeMenu.getSelectedItem().equals(LabelsConfig.getLabel(LabelsConfig.LAST_EVENT)))
					options = normalEventOptions;
				else if (filterTypeMenu.getSelectedItem().equals(LabelsConfig.getLabel(LabelsConfig.LAST_ACADEMIC_EVENT)))	
					options = academicEventOptions;

				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(options);
				filterValueMenu.setModel(model);

				
			}
		});
		
		filterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String filterType = String.valueOf(filterTypeMenu.getSelectedItem());
				String filterValue = String.valueOf(filterValueMenu.getSelectedItem());
				List<TA> filteredTAs = new ArrayList<TA>();
				
				if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.ON_VACATION)))
					filteredTAs = filtersController.filterByVacationStatus(filterValue);
				else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.TITLE)))
					filteredTAs = filtersController.filterByTitle(filterValue);
				else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.REGISTRATION_STATUS)))
					filteredTAs = filtersController.filterByregistrationStatus(filterValue);
				else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.LAST_EVENT)))
					filteredTAs = filtersController.filterByLastEvent(filterValue);
				else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.LAST_ACADEMIC_EVENT)))
					filteredTAs = filtersController.filterByLastAcademicEvent(filterValue);
				
				tasPanel.setLayout(new GridLayout(filteredTAs.size()+1,1));
//				GridBagLayout layout = new GridBagLayout();
//				GridBagConstraints constraints = new GridBagConstraints();
//				int x = 0 , y = 0;
//				tasPanel.setLayout(new BoxLayout(tasPanel, BoxLayout.PAGE_AXIS));
//				tasPanel.setLayout(layout);

				tasPanel.removeAll();
				if (filteredTAs.size() != 0)
					addTableHeader(tasPanel,filterType, filterValue);
				for(TA ta: filteredTAs) {
					TARowPanel panel;
					if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.ON_VACATION)))
						panel = new VacationTARowPanel(ta);
					else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.REGISTRATION_STATUS)))
						panel = new EndingAcademicEventTARowPanel(ta);
					else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.LAST_ACADEMIC_EVENT))
							&& isRegistrationEvent(filterValue))
						panel = new RegistrationEventTARowPanel(ta);
					else 
						panel = new TARowPanel(ta);
//					constraints.gridy = y++;
//					constraints.fill = GridBagConstraints.HORIZONTAL;
					
//					System.out.println(constraints.gridx);
//					tasPanel.add(panel,constraints);

					tasPanel.add(panel);
					
				}
				tasPanel.revalidate();
				tasPanel.repaint();
				if (filteredTAs.size() == 0)
					JOptionPane.showMessageDialog(null, LabelsConfig.getLabel("noResultsFoundMsg"));

			}

		});
		
		mainPageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ViewAllTAsPage();
				
			}
		});
		
	}

	private boolean isRegistrationEvent(String filterValue) {
		String eventName = EventsConfigReader.getEventEnglishName(filterValue);
		if (eventName.equals("model.MastersRegistration")||eventName.equals("model.PhdRegistration"))
			return true;
		else
			return false;
	}
	
	private void addTableHeader (JPanel tasPanel, String filterType , String filterValue) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		
		if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.ON_VACATION))) {
			
			JLabel l1 = new JLabel(" ");
			l1.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l1);
			
			if (filterValue.equals(EventsConfigReader.getEventArabicName("OnVacation")))
			{
				JLabel l2 = new JLabel(LabelsConfig.getLabel(LabelsConfig.REASON));
				l2.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(l2);
				
				JLabel l3 = new JLabel(LabelsConfig.getLabel(LabelsConfig.END_DATE));
				l3.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(l3);
				
				JLabel l4 = new JLabel(LabelsConfig.getLabel(LabelsConfig.START_DATE));
				l4.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(l4);
			}
			
			JLabel l5 = new JLabel(LabelsConfig.getLabel(LabelsConfig.TITLE));
			l5.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l5);
			
			JLabel l6 = new JLabel(LabelsConfig.getLabel(LabelsConfig.NAME));
			l6.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l6);
		
		}
		else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.REGISTRATION_STATUS))) {

			JLabel l1 = new JLabel(" ");
			l1.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l1);
			
			JLabel l2 = new JLabel(LabelsConfig.getLabel(LabelsConfig.END_DATE));
			l2.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l2);
			
			JLabel l3 = new JLabel(LabelsConfig.getLabel(LabelsConfig.START_DATE));
			l3.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l3);
			
			JLabel l4 = new JLabel(LabelsConfig.getLabel(LabelsConfig.NAME));
			l4.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l4);
		}
		else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.LAST_ACADEMIC_EVENT))
				&& isRegistrationEvent(filterValue)) {

			JLabel l1 = new JLabel(" ");
			l1.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l1);
			
			JLabel l2 = new JLabel(LabelsConfig.getLabel(LabelsConfig.THESIS_TITLE));
			l2.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l2);
			
			JLabel l3 = new JLabel(LabelsConfig.getLabel(LabelsConfig.SUPERVISORS));
			l3.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l3);
			
			JLabel l4 = new JLabel(LabelsConfig.getLabel(LabelsConfig.REGISTRATION_DATE));
			l4.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l4);
			
			JLabel l5 = new JLabel(LabelsConfig.getLabel(LabelsConfig.NAME));
			l5.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l5);

		}
		else {

			JLabel l1 = new JLabel(" ");
			l1.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l1);
			
			JLabel l2 = new JLabel(" ");
			l2.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l2);
			
			JLabel l3 = new JLabel(" ");
			l3.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l3);
			
			JLabel l4 = new JLabel(LabelsConfig.getLabel(LabelsConfig.HIRING_DATE));
			l4.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l4);
			
			JLabel l5 = new JLabel(LabelsConfig.getLabel(LabelsConfig.ON_VACATION));
			l5.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l5);
			
			JLabel l6 = new JLabel(LabelsConfig.getLabel(LabelsConfig.TITLE));
			l6.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l6);
			
			JLabel l7 = new JLabel(LabelsConfig.getLabel(LabelsConfig.NAME));
			l7.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(l7);

		}
	
		 
		 tasPanel.add(panel);
	
	}
}
