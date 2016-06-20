package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.util.FontCreator;
public class MphDigitalText extends JPanel implements GraphicalJPanel{
	
	private static final long serialVersionUID = 1L;
	private static MphDigitalText instance;
	private static JLabel label = new JLabel();		
	private static final Dimension SIZE = new Dimension(300, 40);
	private static final Point LOCATION = new Point(
			InstrumentCluster.LEFT_PANE_SIZE.width / 2 - SIZE.width / 2, 
			(InstrumentCluster.LEFT_PANE_SIZE.height / 2 - SIZE.height / 2) + 100
			);

	public MphDigitalText() {				

	}

	public static MphDigitalText getInstance() {
		if (instance == null) {
			instance = new MphDigitalText();	
			instance.setProps();
		}
		return instance;
	}
	
	public void setProps(){		
		this.setMaximumSize(SIZE);
		this.setPreferredSize(SIZE);
		this.setMinimumSize(SIZE);
		this.setOpaque(false);
		label.setFont(new Font(FontCreator.FONT, Font.PLAIN, 40));
		label.setForeground(FontCreator.TEXT_COLOR);
		this.add(label);
		this.setVisible(true);
		this.setSize(SIZE);		
		this.setLocation(LOCATION);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	public void setValue(int value){
		label.setText("MPH = " + StringUtils.leftPad(String.valueOf(value), 3, "0"));
	}

	public JPanel getPanel() {
		return this;
	}
	
}
