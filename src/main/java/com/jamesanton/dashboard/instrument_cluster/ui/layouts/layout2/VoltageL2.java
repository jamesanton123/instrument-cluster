package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import com.jamesanton.dashboard.instrument_cluster.ui.util.FontCreator;

public class VoltageL2 extends JPanel implements GraphicalJPanel {

	private static final long serialVersionUID = 1L;
	private static VoltageL2 instance;
	private static JLabel label = new JLabel();
	private static final Dimension SIZE = new Dimension(800, 70);
	private static final Point LOCATION = new Point(20, 120);

	public VoltageL2() {

	}

	public static VoltageL2 getInstance() {
		if (instance == null) {
			instance = new VoltageL2();
			instance.setProps();
		}
		return instance;
	}

	public void setProps() {
		this.setMaximumSize(SIZE);
		this.setPreferredSize(SIZE);
		this.setMinimumSize(SIZE);
		this.setOpaque(false);
		label.setFont(new Font(FontCreator.FONT, Font.PLAIN, FontCreator.VOLT_SIZE));
		label.setForeground(FontCreator.TEXT_COLOR);
		this.add(label);
		this.setVisible(true);
		this.setSize(SIZE);
		this.setLocation(LOCATION);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public void setValue(int value) {
		double voltage = (double) value / (double) 63;
		DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
		String vol = oneDigit.format(voltage);
		label.setText("Volts: " + StringUtils.leftPad(vol, 4, "0"));
		if (voltage < 12.6) {
			CheckEngineLight.getInstance().setValue(1);
		} else {
			CheckEngineLight.getInstance().setValue(0);
		}
	}

	public JPanel getPanel() {
		return this;
	}

}
