package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1.GraphicalJPanel;
import com.jamesanton.dashboard.instrument_cluster.ui.util.FontCreator;
public class MphDigitalTextL2 extends JPanel implements GraphicalJPanel{
	
	private static final long serialVersionUID = 1L;
	private static MphDigitalTextL2 instance;
	private static JLabel label = new JLabel();		
	private static final Dimension SIZE = new Dimension(800, 100);
	private static final Point LOCATION = new Point(20, 20);

	public MphDigitalTextL2() {				

	}

	public static MphDigitalTextL2 getInstance() {
		if (instance == null) {
			instance = new MphDigitalTextL2();	
			instance.setProps();
		}
		return instance;
	}
	
	public void setProps(){		
		this.setMaximumSize(SIZE);
		this.setPreferredSize(SIZE);
		this.setMinimumSize(SIZE);
		this.setOpaque(false);
		label.setFont(new Font(FontCreator.FONT, Font.PLAIN, FontCreator.MPH_SIZE));
		label.setForeground(FontCreator.TEXT_COLOR);
		this.add(label);
		this.setVisible(true);
		this.setSize(SIZE);		
		this.setLocation(LOCATION);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	public void setValue(int value){
		label.setText("MPH: " + StringUtils.leftPad(String.valueOf(value), 3, "0"));
	}

	public JPanel getPanel() {
		return this;
	}
	
}
