package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RightTurnSignal implements GraphicalJPanel {

	protected static final Dimension SIZE = LeftTurnSignal.SIZE;
	protected static final Point LOCATION = new Point(LeftTurnSignal.LOCATION.x + LeftTurnSignal.SIZE.width, LeftTurnSignal.LOCATION.y);
	private static RightTurnSignal instance = null;
	private BufferedImage left = getImage();
	
	private BufferedImage getImage(){
		try {
			return ImageIO.read(LeftTurnSignal.class.getResourceAsStream("/images/arrow-right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected RightTurnSignal() {
		// Exists only to defeat instantiation.
	}

	public static RightTurnSignal getInstance() {
		if (instance == null) {
			instance = new RightTurnSignal();			
		}
		return instance;
	}

	public JPanel getPanel() {
		return imagePanel;
	}
	
	public void setValue(int val) {
		if(val == 1){
			imagePanel.setVisible(true);
		}else{
			imagePanel.setVisible(false);
		}
	}

	private final JPanel imagePanel = new JPanel() {
		private static final long serialVersionUID = 1L;
		{
			setVisible(true);
			this.setMaximumSize(SIZE);
			this.setPreferredSize(SIZE);
			this.setMinimumSize(SIZE);
			this.setOpaque(false);
			this.setVisible(true);
			this.setSize(SIZE);		
			this.setLocation(LOCATION);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(left, 0, 0, null);			
		}
	};
}
