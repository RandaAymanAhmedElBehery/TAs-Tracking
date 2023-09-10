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

import model.EndingEvent;
import model.TA;
import ui.TARowPanel;
import ui.ViewTAHistory;
import utils.DateUtils;
import utils.LabelsConfig;

public class EndingAcademicEventTARowPanel extends TARowPanel {

	public EndingAcademicEventTARowPanel(TA ta) {
		super(ta);
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

		EndingEvent e = (EndingEvent) ta.getregistrationStatus();
		JLabel endDate = new JLabel(DateUtils.dateToString(e.getEndDate()));
		endDate.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(endDate);
		
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
