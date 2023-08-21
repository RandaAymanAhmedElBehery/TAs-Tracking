package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.FiltersController;
import model.TA;
import ui.tarowpanel.EndingAcademicEventTARowPanel;
import ui.tarowpanel.VacationTARowPanel;
import utils.EventsConfigReader;
import utils.LabelsConfig;
import utils.TitlesReader;

public class FiltersPage extends JFrame{
	
	int width = 1000;
	int height = 600;
	String [] vacationOptions = {EventsConfigReader.getEventArabicName("OnVacation"), EventsConfigReader.getEventArabicName("NotOnVacation")};
	String [] titleOptions = TitlesReader.getTitles();
	String [] eventOptions = EventsConfigReader.getEventsArabicList();
	String [] normalEventOptions = EventsConfigReader.getNormalEventsArabicList();
	String [] academicEventOptions = EventsConfigReader.getAcademicEventsArabicList();

	FiltersController filtersController;
	
	public FiltersPage() {
		setSize(width, height);
		setTitle(LabelsConfig.getLabel("FilterTAs"));
		setLocation(new Point(100, 50));
//		this.setLayout(new BorderLayout());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		filtersController = new FiltersController();	
		
		addControls();
		
		setVisible(true);
	}
	
	private void addControls() {		
		
	    JPanel filtersPanel = new JPanel();

//	    filtersPanel.setLayout(null);

	    JLabel filterTypeLabel = new JLabel(LabelsConfig.getLabel("filterType"));
//		filterTypeLabel.setBounds(20, 20, 100, 25);
	    String[] choices = {LabelsConfig.getLabel(LabelsConfig.ON_VACATION),LabelsConfig.getLabel(LabelsConfig.TITLE),LabelsConfig.getLabel(LabelsConfig.LAST_EVENT),LabelsConfig.getLabel(LabelsConfig.LAST_ACADEMIC_EVENT)};
	    JComboBox<String> filterTypeMenu = new JComboBox<>(choices);
	    filterTypeMenu.setSelectedItem(null);
//	    filterTypeMenu.setBounds(90, 20, 150, 25);
	    

	    
//	    JLabel filterValueLabel = new JLabel(LabelsConfig.getLabel("filterValue"));
//	    filterValueLabel.setBounds(300, 20, 100, 25);
	    JComboBox<String> filterValueMenu = new JComboBox<>();
//	    filterValueMenu.setBounds(370, 20, 150, 25);

	    JButton filterButton = new JButton(LabelsConfig.getLabel("FilterTAs"));
	    JButton mainPageButton = new JButton(LabelsConfig.getHomePageLabel());
//	    filterButton.setBounds(600, 20, 100, 25);

//	    filtersPanel.add(filterValueLabel);
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
				else if (filterTypeMenu.getSelectedItem().equals(LabelsConfig.getLabel(LabelsConfig.EVENT)))
					options = eventOptions;
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
//				else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.EVENT)))
//					filteredTAs = filtersController.filterByEvent(filterValue);
				else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.LAST_EVENT)))
					filteredTAs = filtersController.filterByLastEvent(filterValue);
				else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.LAST_ACADEMIC_EVENT)))
					filteredTAs = filtersController.filterByLastAcademicEvent(filterValue);
				
				tasPanel.setLayout(new GridLayout(filteredTAs.size(),1));
				tasPanel.removeAll();
				
				for(TA ta: filteredTAs) {
					TARowPanel panel;
					if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.ON_VACATION)))
						panel = new VacationTARowPanel(ta);
					else if (filterType.equals(LabelsConfig.getLabel(LabelsConfig.LAST_ACADEMIC_EVENT))
							&& isEndingAcademicEvent(filterValue))
						panel = new EndingAcademicEventTARowPanel(ta);
					else 
						panel = new TARowPanel(ta);
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

	private boolean isEndingAcademicEvent(String filterValue) {
		String eventName = EventsConfigReader.getEventEnglishName(filterValue);
		if (eventName.contains("Pause") || eventName.contains("Extension"))
			return true;
		else
			return false;
	}
}
