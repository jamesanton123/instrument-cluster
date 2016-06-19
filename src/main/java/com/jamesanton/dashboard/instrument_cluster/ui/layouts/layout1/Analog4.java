package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.ui.util.FontCreator;
public class Analog4 extends JPanel implements GraphicalJPanel{
	
	private static final long serialVersionUID = 1L;
	private static Analog4 instance;
	private static JLabel label = new JLabel();	
	private static final Dimension SIZE = new Dimension(InstrumentCluster.SIZE.width - InstrumentCluster.LEFT_PANE_SIZE.width, 30);
	private static final Point LOCATION = new Point(InstrumentCluster.LEFT_PANE_SIZE.width, 180);

	public Analog4() {				

	}

	public static Analog4 getInstance() {
		if (instance == null) {
			instance = new Analog4();	
			instance.setProps();
		}
		return instance;
	}
	
	public void setProps(){		
		this.setMaximumSize(SIZE);
		this.setPreferredSize(SIZE);
		this.setMinimumSize(SIZE);
		this.setOpaque(true);
		this.setBackground(Color.black);
		label.setFont(new Font(FontCreator.FONT, Font.PLAIN, 20));
		label.setForeground(FontCreator.TEXT_COLOR);
		this.add(label);
		this.setVisible(true);
		this.setSize(SIZE);		
		this.setLocation(LOCATION);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	public void setValue(int value){
		label.setText("Ana4 = " + value);
	}
	
	public JPanel getPanel() {
		return this;
	}
	
}
