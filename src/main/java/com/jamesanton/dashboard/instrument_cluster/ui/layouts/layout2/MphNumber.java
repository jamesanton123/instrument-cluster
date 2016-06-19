package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jamesanton.dashboard.instrument_cluster.ui.util.FontCreator;

public class MphNumber  extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel();		
	public static final Dimension MPH_NUMBER_SIZE = new Dimension(50, 17);

	public MphNumber(String text, int x, int y) {		
		Dimension size = new Dimension(MPH_NUMBER_SIZE);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setSize(size.width, size.height);		
		this.setOpaque(false);
		this.add(label);		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setLocation(x, y);
		FlowLayout f = (FlowLayout) getLayout();
		f.setVgap(0);
		f.setHgap(0);
		label.setFont(new Font(FontCreator.FONT, Font.PLAIN, 20));
		label.setForeground(FontCreator.TEXT_COLOR);
		label.setText(text);
		this.setVisible(true);		
	}	
}
