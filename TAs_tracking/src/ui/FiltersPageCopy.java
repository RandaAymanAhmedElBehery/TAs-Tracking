package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controllers.FiltersController;
import model.TA;
import ui.tarowpanel.EndingAcademicEventTARowPanel;
import ui.tarowpanel.RegistrationEventTARowPanel;
import ui.tarowpanel.VacationTARowPanel;
import utils.EventsConfigReader;
import utils.LabelsConfig;
import utils.TitlesReader;

public class FiltersPageCopy extends JFrame{
	
	int width = 1550;
	int height = 800;
	String [] vacationOptions = {EventsConfigReader.getEventArabicName("OnVacation"), EventsConfigReader.getEventArabicName("NotOnVacation")};
	String [] titleOptions = TitlesReader.getTitles();
	String [] normalEventOptions = EventsConfigReader.getNormalEventsArabicList();
	String [] academicEventOptions = EventsConfigReader.getAcademicEventsArabicList();
	String [] registrationStatusOptions = EventsConfigReader.getRegistrationStatusEventsList();

	FiltersController filtersController;
	
	public FiltersPageCopy() {
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
				
//				tasPanel.setLayout(new GridLayout(filteredTAs.size(),1));
			/*
				GridBagLayout layout = new GridBagLayout();
				GridBagConstraints constraints = new GridBagConstraints();
				int x = 0 , y = 0;
*/
				
				
				//				tasPanel.setLayout(new BoxLayout(tasPanel, BoxLayout.PAGE_AXIS));
				//tasPanel.setLayout(layout);

				tasPanel.removeAll();
				
				JTable table = new JTable(filteredTAs.size(), 1); 
				
				
				
				String data[][]={ {"101","Amit","670000"},    
                        {"102","Jai","780000"},    
                        {"101","Sachin","700000"}};    
			    String column[]={"ID","NAME","SALARY"};         
			    JTable jt=new JTable(data,column);    
			    jt.setBounds(30,40,200,300);          
			    JScrollPane sp=new JScrollPane(jt);    
			    
				
				
				
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
//					constraints.gridx=0;
//					constraints.weightx=1;
////					constraints.weighty = 1;
//					constraints.fill = GridBagConstraints.HORIZONTAL;
//					constraints.anchor = GridBagConstraints.NORTH;
//					
					
					
//					tasPanel.add(panel,constraints);
//					tasPanel.add(panel);
					
					
				    
	    
	    
					table.add(panel);

					
				}
				tasPanel.add(sp);
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

	private boolean isEndingAcademicEvent(String filterValue) {
		String eventName = EventsConfigReader.getEventEnglishName(filterValue);
		if (eventName.contains("Pause") || eventName.contains("Extension"))
			return true;
		else
			return false;
	}
	
	private boolean isRegistrationEvent(String filterValue) {
		String eventName = EventsConfigReader.getEventEnglishName(filterValue);
		if (eventName.contains("Registration"))
			return true;
		else
			return false;
	}

}
