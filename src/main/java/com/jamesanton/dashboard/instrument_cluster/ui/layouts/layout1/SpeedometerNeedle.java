package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.JPanel;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;

/**
 * Performance = 31.8
 * 
 * @author James
 *
 */
public class SpeedometerNeedle implements GraphicalJPanel {
	private int width = InstrumentCluster.LEFT_PANE_SIZE.width;
	private int height = InstrumentCluster.LEFT_PANE_SIZE.height;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	private static SpeedometerNeedle instance = null;
	private double radius = 350;
	private int maxMph = 120;
	private static final Point center = new Point(InstrumentCluster.LEFT_PANE_SIZE.width / 2, InstrumentCluster.LEFT_PANE_SIZE.height / 2);
	private Color color = Color.YELLOW;
	private int needleThickness = 3;
	private double totalDegrees = 270;
	private Graphics2D g2;
	private double percent;
	private Point p2;
	private double degreesNeeded;
	private double radians;
	private Point untransformedPoint;	
	private final JPanel imagePanel = new JPanel() {
		private static final long serialVersionUID = 1L;
		{
			setSize(width, height);
			setOpaque(false);
			setBackground(new Color(0, 0, 0, 0));
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	};
	long totalTime = 0;
	int count = 0;
	long timeOfLastReset = new Date().getTime();
	long timeToReset = 60000;

	public static SpeedometerNeedle getInstance() {
		if (instance == null) {
			instance = new SpeedometerNeedle();
		}
		return instance;
	}

	public void setValue(int mph) {
		image = createBufferedImageForSpeed(mph);
		imagePanel.repaint();
	}

	private BufferedImage createBufferedImageForSpeed(int mph) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);		
		g2 = (Graphics2D) image.getGraphics();
		percent = (double) mph / (double) maxMph;
		p2 = getPointForPercent(percent);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(needleThickness));
		g2.drawLine(center.x, center.y, p2.x, p2.y);
		return image;
	}

	private Point getPointForPercent(double percent) {
		degreesNeeded = 360 - (totalDegrees * percent - 225);
		radians = (degreesNeeded * Math.PI / (double) 180);
		untransformedPoint = new Point((int) (center.x + (double) radius * Math.cos(radians)), (int) (center.y + (double) radius * Math.sin(radians)));
		return new Point(untransformedPoint.x, (height - untransformedPoint.y));
	}

	public JPanel getPanel() {
		return imagePanel;
	}

}
