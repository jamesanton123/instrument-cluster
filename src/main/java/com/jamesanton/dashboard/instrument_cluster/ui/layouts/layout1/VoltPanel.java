package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.util.FontCreator;
public class VoltPanel extends JPanel implements GraphicalJPanel{
	
	private static final long serialVersionUID = 1L;
	private static VoltPanel instance;
	private static JLabel label = new JLabel();	
	private static final Dimension SIZE = new Dimension(InstrumentCluster.SIZE.width - InstrumentCluster.LEFT_PANE_SIZE.width, 30);
	private static final Point LOCATION = new Point(InstrumentCluster.LEFT_PANE_SIZE.width, 30);

	public VoltPanel() {				

	}

	public static VoltPanel getInstance() {
		if (instance == null) {
			instance = new VoltPanel();	
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
		double voltage = (double)value / (double)53.6;		
		DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
		String vol = oneDigit.format(voltage);
		label.setText("Volts = " + StringUtils.leftPad(vol, 4, "0"));
		if(voltage < 13){
			CheckEngineLight.getInstance().setValue(1);
		}else{
			CheckEngineLight.getInstance().setValue(0);
		}
	}
	
	public JPanel getPanel() {
		return this;
	}
	
}
