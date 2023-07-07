package ui;

import java.awt.Point;
import java.lang.reflect.Field;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Event;
import utils.DateUtils;
import utils.EventUtils;
import utils.EventsConfigReader;

public class EventPanel extends JPanel{

	int width = 300;
	int height = 50;
	
	Event event;
	public EventPanel(Event e) {
		
		setSize(width, height);
		setLocation(new Point(500, 300));
		setVisible(true);
		
		this.event = e;
		if(event != null)
			addEvent();
	}
	private void addEvent() {
		
		String eventType = EventsConfigReader.getEventArabicName(event.getType());
		TitledBorder titledBorder = BorderFactory.createTitledBorder(eventType);
		titledBorder.setTitleJustification(TitledBorder.RIGHT);
		setBorder(titledBorder);
		
		Field[] eventFields = EventUtils.getEventFields(event);

		for(Field field : eventFields) {
			field.setAccessible(true);
			try {
//				JLabel label = new JLabel(field.getName());
//				this.add(label);
			
				String valueText = "";
				if(field.getType() == Date.class)
					valueText = DateUtils.dateToString((Date) field.get(event));
				else if(field.getName() != "type")
					valueText = field.get(event).toString();
				JTextField value = new JTextField(valueText);
				value.setEditable(false);
				this.add(value);
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	}
	
}