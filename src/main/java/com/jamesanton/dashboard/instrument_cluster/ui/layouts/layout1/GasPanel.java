package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.ui.util.FontCreator;
public class GasPanel extends JPanel implements GraphicalJPanel{
	
	private static final long serialVersionUID = 1L;
	private static GasPanel instance;
	private static JLabel label = new JLabel();	
	private static final Dimension SIZE = new Dimension(InstrumentCluster.SIZE.width - InstrumentCluster.LEFT_PANE_SIZE.width, 30);
	private static final Point LOCATION = new Point(InstrumentCluster.LEFT_PANE_SIZE.width, 0);

	public GasPanel() {				

	}

	public static GasPanel getInstance() {
		if (instance == null) {
			instance = new GasPanel();	
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
		label.setText("Gas = " + String.valueOf(value));
		int gasPercent = calculatePercentGas(value);
		if(gasPercent < 0){
			label.setText("GAS = " + "ERR");
		}else{
			label.setText("GAS = " + StringUtils.leftPad(String.valueOf(gasPercent), 3, "0"));
		}
	}
	
	public JPanel getPanel() {
		return this;
	}
	
	private int calculatePercentGas(int value){
		// V2 = [R2/(R1+R2) Vin]   ... Analog read voltage = proportion of resistance times the input voltage				
		double emptyTankOhms = 22;
		double fullTankOhms = 145;
		double inputVoltage = 5;
		double r1 = 5;
		double v2 = ((double)value) / ((double)204.6); // 4.68		
		double ratioOfV2ToInput = v2 / inputVoltage; //.936
		double oppositeRatio = ((double)1) - ratioOfV2ToInput; // .064
		double r2 = ((ratioOfV2ToInput) * r1) / (oppositeRatio); // 146.25
		double tankPercent = ((r2 - emptyTankOhms) / (fullTankOhms - emptyTankOhms)) * 100; 
		return (int)tankPercent;
	}
}
