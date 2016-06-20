package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CheckEngineLight implements GraphicalJPanel {

	protected static final Dimension SIZE = new Dimension(42, 42);
	protected static final Point LOCATION = new Point(Layout2.LOCATION_ICONS.x, Layout2.LOCATION_ICONS.y);
	private static CheckEngineLight instance = null;
	private BufferedImage img = getImage();
	
	private BufferedImage getImage(){
		try {
			return ImageIO.read( CheckEngineLight.class.getResourceAsStream("/images/check-engine-light.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected CheckEngineLight() {
		// Exists only to defeat instantiation.
	}

	public static CheckEngineLight getInstance() {
		if (instance == null) {
			instance = new CheckEngineLight();			
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
			g.drawImage(img, 0, 0, null);			
		}
	};
}
