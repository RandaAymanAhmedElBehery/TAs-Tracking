package ui;

import java.awt.Point;
import java.lang.reflect.Field;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Event;
import utils.DateUtils;

public class EventPanel extends JPanel{

	int width = 400;
	int height = 200;
	
	Event event;
	public EventPanel(Event e) {
		
		setSize(width, height);
		setLocation(new Point(500, 300));
		setVisible(true);
		
		this.event = e;
		addEvent();
	}
	private void addEvent() {
		
		setBorder(BorderFactory.createTitledBorder(event.getType()));
		
		Field[] eventFields = Event.class.getDeclaredFields();

		for(Field field : eventFields) {
			field.setAccessible(true);
			try {
				JLabel label = new JLabel(field.getName());
				this.add(label);
			
				String valueText = "";
				if(field.getType() == Date.class)
					valueText = DateUtils.dateToString((Date) field.get(event));
				else 
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