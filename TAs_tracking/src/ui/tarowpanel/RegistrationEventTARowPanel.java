package ui.tarowpanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.RegistrationEvent;
import model.TA;
import ui.TARowPanel;
import ui.ViewTAHistory;
import utils.DateUtils;
import utils.LabelsConfig;

public class RegistrationEventTARowPanel extends TARowPanel {

	public RegistrationEventTARowPanel(TA ta) {
		super(ta);
		this.setLocation(0, 0);
		this.setMaximumSize(new Dimension(1000, 50));
	}

	@Override
	protected void addTAInfo() {

		this.setLayout(new GridLayout());
		this.setBorder(BorderFactory.createTitledBorder(""));

		JLabel viewTA = new JLabel();
		viewTA.setText("<html>" + LabelsConfig.getViewHistoryLabel() + "</html>");
		viewTA.setHorizontalAlignment(SwingConstants.RIGHT);
		viewTA.setForeground(Color.BLUE.darker());
		viewTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(viewTA);
		
		RegistrationEvent e = (RegistrationEvent) ta.getLastAcademicEvent();
			
		JLabel title = new JLabel("<html><body><p style = \"word-wrap: normal; width:"+this.getWidth()/5+" \">"+e.getTitle()+"</p></body></html>");
		title.setHorizontalAlignment(SwingConstants.RIGHT);
		title.setAutoscrolls(true);
		title.setVerticalAlignment(SwingConstants.CENTER);
		this.add(title);
				

		StringBuilder supervisorsNames = new StringBuilder("");
		for (String s: e.getSupervisors()) {
			supervisorsNames.append(s);
			supervisorsNames.append(",");
		}
		supervisorsNames.deleteCharAt(supervisorsNames.length()-1);
		String names = new String(supervisorsNames);
		JLabel supervisors = new JLabel("<html><body><p style = \"word-wrap: normal; width:"+this.getWidth()/5+" \">"+names+"</p></body></html>");
		supervisors.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(supervisors);
		
		JLabel startDate = new JLabel(DateUtils.dateToString(e.getDate()));
		startDate.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(startDate);
		
		JLabel taName = new JLabel(ta.getName());
		taName.setHorizontalAlignment(SwingConstants.RIGHT);
		taName.setFont(new Font("Serif", Font.BOLD, 14));
		this.add(taName);

		viewTA.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				new ViewTAHistory(ta);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

}
