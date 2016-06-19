package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1.GraphicalJPanel;

/**
 * @author James
 *
 */
public class Battery extends JPanel implements GraphicalJPanel {

	private static final long serialVersionUID = 1L;
	private static Battery instance = null;
	private static final Dimension SIZE = new Dimension(220, 15);
	private static final Point LOCATION = new Point(20, 190);
	private JPanel innerJPanel = getInnerJPanel();
	private static final int INNER_J_PANEL_MAX_WIDTH = SIZE.width - 4;
	private static Dimension innerJPanelDimension = new Dimension(INNER_J_PANEL_MAX_WIDTH, SIZE.height - 4);
	private static final Color BATTER_OUTLINE_COLOR = new Color(107, 106, 104);
	private static final Color INNER_BATTERY_PROGRESS_COLOR = Color.red;
	
	public static Battery getInstance() {
		if (instance == null) {
			instance = new Battery();
			instance.setProps();
		}
		return instance;
	}
	
	private JPanel getInnerJPanel() {
		JPanel j = new JPanel();
		j.setMaximumSize(innerJPanelDimension);
		j.setPreferredSize(innerJPanelDimension);
		j.setMinimumSize(innerJPanelDimension);
		j.setOpaque(true);
		j.setVisible(true);
		j.setSize(innerJPanelDimension);
		j.setBackground(INNER_BATTERY_PROGRESS_COLOR);
		j.setLayout(new FlowLayout(FlowLayout.LEFT));
		return j;
	}

	public void setProps(){
		
		this.setMaximumSize(SIZE);
		this.setPreferredSize(SIZE);
		this.setMinimumSize(SIZE);
		this.setVisible(true);
		this.setSize(SIZE);
		this.setLocation(LOCATION);
		this.setBorder(BorderFactory.createMatteBorder(2,2,2,2, BATTER_OUTLINE_COLOR));
		this.setBackground(Color.black);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(innerJPanel);
		FlowLayout layout = (FlowLayout)this.getLayout();
		layout.setVgap(0);
		layout.setHgap(0);
	}
	
	/**
	 * A number between 0 and 18 * 100
	 */
	public void setValue(int val) {
		innerJPanelDimension.width = (int) (INNER_J_PANEL_MAX_WIDTH * ((double)val / 1800));
		innerJPanel.setMaximumSize(innerJPanelDimension);
		innerJPanel.setPreferredSize(innerJPanelDimension);
		innerJPanel.setMinimumSize(innerJPanelDimension);
		innerJPanel.setSize(innerJPanelDimension);
	}

	public JPanel getPanel() {
		return this;
	}
	
	
}
