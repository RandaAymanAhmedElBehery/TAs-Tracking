package ui.tarowpanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Event;
import model.TA;
import model.Vacation;
import ui.TARowPanel;
import ui.ViewTAHistory;
import utils.DateUtils;
import utils.LabelsConfig;

public class VacationTARowPanel extends TARowPanel {

	public VacationTARowPanel(TA ta) {
		super(ta,1000,75);
	}

	@Override
	protected void addTAInfo() {

		this.setLayout(new GridLayout());
		this.setBorder(BorderFactory.createTitledBorder(""));

		int numItems = 3;

		JLabel viewTA = new JLabel();
		viewTA.setText("<html>" + LabelsConfig.getViewHistoryLabel() + "</html>");
		viewTA.setHorizontalAlignment(SwingConstants.RIGHT);
		viewTA.setForeground(Color.BLUE.darker());
		viewTA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(viewTA);

		if (ta.isOnVacation()) {
			numItems = 6;
			
			Event vacationEvent = null;
			for(int i=ta.getHistory().size()-1; i>=0; i--) {
				if (ta.getHistory().get(i).getType().equals("model.Vacation")) {
					vacationEvent = ta.getHistory().get(i);
					break;
				}
			}
			Vacation v = (Vacation) vacationEvent;
		
			JLabel onVacation = new JLabel("<html><body><p style = \"word-wrap: normal; width:"+this.getWidth()/numItems+" \">"+v.getVacationType()+"</p></body></html>");
			onVacation.setHorizontalAlignment(SwingConstants.RIGHT);
			this.add(onVacation);
			
			JLabel endDate = new JLabel(DateUtils.dateToString(v.getEndDate()));
			endDate.setHorizontalAlignment(SwingConstants.RIGHT);
			this.add(endDate);
			
			JLabel startDate = new JLabel(DateUtils.dateToString(v.getDate()));
			startDate.setHorizontalAlignment(SwingConstants.RIGHT);
			this.add(startDate);
		}

		JLabel taTitle = new JLabel(ta.getTitle());
		taTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(taTitle);

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
