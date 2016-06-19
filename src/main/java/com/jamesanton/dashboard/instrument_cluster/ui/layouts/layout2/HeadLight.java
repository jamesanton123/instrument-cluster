package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HeadLight implements GraphicalJPanel {

	protected static final Dimension SIZE = new Dimension(31, 21);
	protected static final Point LOCATION = new Point(Brights.LOCATION.x + Brights.SIZE.width + 3, Brights.LOCATION.y);

	private static HeadLight instance = null;
	private BufferedImage img = getImage();
	
	private BufferedImage getImage(){
		try {
			return ImageIO.read(HeadLight.class.getResourceAsStream("/images/headlamp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected HeadLight() {
		// Exists only to defeat instantiation.
	}

	public static HeadLight getInstance() {
		if (instance == null) {
			instance = new HeadLight();			
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
