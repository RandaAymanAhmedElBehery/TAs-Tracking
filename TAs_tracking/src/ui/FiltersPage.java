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
import utils.EventsConfigReader;
import utils.TitlesReader;

public class FiltersPage extends JFrame{
	
	int width = 1000;
	int height = 600;
	String [] vacationOptions = {"Yes", "No"};
	String [] titleOptions = TitlesReader.getTitles();
	String [] eventOptions = EventsConfigReader.getEventsArabicList();
	
	FiltersController filtersController;
	
	public FiltersPage() {
		setSize(width, height);
		setTitle("Filter TA ");
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

	    JLabel filterTypeLabel = new JLabel("Filter Type");
//		filterTypeLabel.setBounds(20, 20, 100, 25);
	    String[] choices = { "Vacation","Event", "Title"};
	    JComboBox<String> filterTypeMenu = new JComboBox<>(choices);
	    filterTypeMenu.setSelectedItem(null);
//	    filterTypeMenu.setBounds(90, 20, 150, 25);
	    
		filtersPanel.add(filterTypeLabel);
		filtersPanel.add(filterTypeMenu);
	    
	    JLabel filterValueLabel = new JLabel("Filter Value");
//	    filterValueLabel.setBounds(300, 20, 100, 25);
	    JComboBox<String> filterValueMenu = new JComboBox<>();
//	    filterValueMenu.setBounds(370, 20, 150, 25);

	    JButton filterButton = new JButton("Filter");
//	    filterButton.setBounds(600, 20, 100, 25);

	    filtersPanel.add(filterValueLabel);
		filtersPanel.add(filterValueMenu);
		filtersPanel.add(filterButton);
		
		
		JPanel tasPanel = new JPanel();

		JScrollPane pane = new JScrollPane(tasPanel);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);		
		
		this.add(filtersPanel, BorderLayout.NORTH);
		this.add(pane);
			
		
		filterTypeMenu.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				String options [] = null ;
				if (filterTypeMenu.getSelectedItem().equals("Vacation"))
					options = vacationOptions;
				else if (filterTypeMenu.getSelectedItem().equals("Title"))
					options = titleOptions;
				else if (filterTypeMenu.getSelectedItem().equals("Event"))
					options = eventOptions;
				
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
				
				if (filterType.equals("Vacation"))
					filteredTAs = filtersController.filterByVacationStatus(filterValue.equals("Yes")?true:false);
				else if (filterType.equals("Title"))
					filteredTAs = filtersController.filterByTitle(filterValue);
				else if (filterType.equals("Event"))
					filteredTAs = filtersController.filterByEvent(filterValue);
				
				tasPanel.setLayout(new GridLayout(filteredTAs.size(),1));
				tasPanel.removeAll();
				for(TA ta: filteredTAs) {
					TARowPanel panel = new TARowPanel(ta);
					tasPanel.add(panel);
				}
				tasPanel.revalidate();
				tasPanel.repaint();
				if (filteredTAs.size() == 0)
					JOptionPane.showMessageDialog(null, "No result found with the specified filters");

			}

		});
		
	}

	
}
