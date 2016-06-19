package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.JPanel;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
public class RpmMeter implements GraphicalJPanel{
	private Color rangeStart = Color.YELLOW;
	private Color rangeEnd = Color.BLUE;
	private int line1Coeffecient = 400;
	private int line2Coefficient = 300;
	private double lineXCoefficient = .07;
	private double verticalDegree = 135;
	public static final int maxRpm = 7000;
	private double barWidthInDegrees = 3;
	private double barSpacerInDegrees = 1;
	private int numBars = (int) ((double) 180 / (barWidthInDegrees + barSpacerInDegrees));
	private Color[] colorsToUse = getColorsForBars();
	private static final int SIDE = Math.min(InstrumentCluster.SIZE.height, InstrumentCluster.SIZE.width);
	private BufferedImage image = new BufferedImage(SIDE, SIDE, BufferedImage.TYPE_INT_RGB);
	private Graphics graphics = image.getGraphics();
	private Graphics2D g2 = (Graphics2D)graphics;
	private double numXValuesToScan = 2560;
	private double width = image.getWidth();
	private double height = image.getHeight();
	private double incrementXScanBy = width / numXValuesToScan;
	private double inverseXScanBy = 1 / incrementXScanBy;
	private Point2D.Double[] eq1Points = getPointsForEquation1();
	private Point2D.Double[] eq2Points = getPointsForEquation2();
	private Polygon[] allPolygons = getAllPolygons();
	private BufferedImage[] allBufferedImages = getAllBufferedImages();
	long totalTime = 0;
	int count = 0;
	long timeOfLastReset = new Date().getTime();
	long timeToReset = 60000;
	private static RpmMeter instance = null;

	protected RpmMeter() {
		// Exists only to defeat instantiation.
	}

	public static RpmMeter getInstance() {
		if (instance == null) {
			instance = new RpmMeter();
		}
		return instance;
	}

	public JPanel getPanel() {
		return imagePanel;
	}	
	
	public void setValue(int rpm) {
		// If time since last reset > 10000 then reset count and reset total
		// time
		if (new Date().getTime() - timeOfLastReset > timeToReset) {
			double average = ((double) totalTime / (double) count);
			System.out.println("Average render of rpm meter: " + average);
			count = 0;
			totalTime = 0;
			timeOfLastReset = new Date().getTime();
		}
		count = count + 1;
		long startTime = new Date().getTime();		
		
		setBufferedImageForRpm(rpm);
		imagePanel.repaint();
		
		long endTime = new Date().getTime();
		totalTime = totalTime + endTime - startTime;
	}

	private void setBufferedImageForRpm(int rpm) {		
		double ratio = (double) rpm / (double) maxRpm;
		int imageToUse = (int) (ratio * (double)numBars * .99);		
		if(allBufferedImages.length > imageToUse && imageToUse >= 0) image = allBufferedImages[imageToUse];
	}
	
	private BufferedImage[] getAllBufferedImages(){
		BufferedImage[] retAry = new BufferedImage[numBars += 1];
		for(int i = 0; i < retAry.length; i++){
			image = new BufferedImage(SIDE, SIDE, BufferedImage.TYPE_INT_RGB);
			graphics = image.getGraphics();
			g2 = (Graphics2D)graphics;
			for(int j = 0; j < i; j++){
				g2.setColor(colorsToUse[j]);
				g2.fillPolygon(allPolygons[j]);
			}
			retAry[i] = image;
		}		
		return retAry;
	}
	
	private Polygon[] getAllPolygons(){
		Polygon[] retPolygons = new Polygon[numBars];
		int width = image.getWidth();
		int height = image.getHeight();
		Point center = new Point(width / 2 - 1, height / 2 - 1);

		// What is the degree for the rpm?
//		double ratio = (double) rpm / (double) maxRpm;
//		int degrees = (int) (ratio * 180);
		int count = 0;
		for (int degree = 0; degree < 180; degree = (int) (degree + barWidthInDegrees + barSpacerInDegrees)) {
			retPolygons[count] = getPolygonForDegree(degree, center);

			count++;
		}
		return retPolygons;
	}
	
	private Color[] getColorsForBars() {

		Color[] colors = new Color[numBars];
		for (int i = 0; i < numBars; i++) {
			double percentOfFirstColorToUse = (double) i / (double) numBars;
			double percentOfSecondColorToUse = 1 - percentOfFirstColorToUse;
			int r = (int) ((double)rangeEnd.getRed() * percentOfFirstColorToUse + (double)rangeStart.getRed() * percentOfSecondColorToUse);
			int g = (int) ((double)rangeEnd.getGreen() * percentOfFirstColorToUse + (double)rangeStart.getGreen() * percentOfSecondColorToUse);
			int b = (int) ((double)rangeEnd.getBlue() * percentOfFirstColorToUse + (double)rangeStart.getBlue() * percentOfSecondColorToUse);
			int val = (r << 16) | (g << 8) | b;
			colors[i] = new Color(val);
		}
		return colors;
	}

	private final JPanel imagePanel = new JPanel() {
		private static final long serialVersionUID = 1L;
		{
			setBackground(Color.blue);
			setVisible(true);
			setSize(SIDE, SIDE);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(image, 0, 0, null); // see javadoc for more info on the
											// parameters
		}
	};



	/**
	 * Specify a degree between 0 and 180
	 * 
	 * @return
	 */
	private Polygon getPolygonForDegree(int degree, Point center) {
		int[] xs = new int[4];
		int[] ys = new int[4];

		double slope1 = getSlopeForPercent(degree);
		double slope2 = getSlopeForPercent(degree + barWidthInDegrees);

		Point2D.Double line1Intersection1 = getIntersectingPointOnLineBetweenCenterAndLine(slope1, center, degree, true);
		Point2D.Double line1Intersection2 = getIntersectingPointOnLineBetweenCenterAndLine(slope2, center, degree, true);
		Point2D.Double line2Intersection1 = getIntersectingPointOnLineBetweenCenterAndLine(slope1, center, degree, false);
		Point2D.Double line2Intersection2 = getIntersectingPointOnLineBetweenCenterAndLine(slope2, center, degree, false);

		xs[0] = (int) line1Intersection1.x;
		xs[1] = (int) line1Intersection2.x;
		xs[2] = (int) line2Intersection2.x;
		xs[3] = (int) line2Intersection1.x;
		ys[0] = (int) line1Intersection1.y;
		ys[1] = (int) line1Intersection2.y;
		ys[2] = (int) line2Intersection2.y;
		ys[3] = (int) line2Intersection1.y;

		return new Polygon(xs, ys, 4);
	}

	private Point2D.Double[] getPointsForEquation1() {
		Point2D.Double[] retAry = new Point2D.Double[(int) numXValuesToScan];
		for (int i = 0; i < numXValuesToScan; i++) {
			double x = (double) i * incrementXScanBy;
			retAry[i] = new Point2D.Double(x, getYForFirstEquation(x, (int) height));
		}
		return retAry;
	}

	private Point2D.Double[] getPointsForEquation2() {
		Point2D.Double[] retAry = new Point2D.Double[(int) numXValuesToScan];
		for (int i = 0; i < numXValuesToScan; i++) {
			double x = (double) i * incrementXScanBy;
			retAry[i] = new Point2D.Double(x, getYForSecondEquation(x, (int) height));
		}
		return retAry;
	}

	private Point2D.Double getIntersectingPointOnLineBetweenCenterAndLine(double slope, Point center, double degree, boolean dataIsForLine1) {
		Point2D.Double[] lineData = dataIsForLine1 ? eq1Points : eq2Points;
		// Which x has the least distance
		double minDist = Double.MAX_VALUE;
		Point2D.Double minPoint = null;

		// y = mx + b

		// we know y, and x are the center
		// calculate b
		// center.y = slope * center.x + b
		double yIntercept = slope * (-1 * center.x) + center.y; // y = m(x - x1)
																// + y1

		// Given each x value possible, find the point for that x on the line
		// which has the smallest distance, we will return that number
		for (double x = 0; x < width; x = x + incrementXScanBy) {
			Point2D.Double p1 = lineData[(int) (x * inverseXScanBy)];
			Point2D.Double p2 = new Point2D.Double(x, height - ((slope * x) + yIntercept));
			double distance = Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
			if (distance < minDist) {
				if (degree <= verticalDegree - barWidthInDegrees - barSpacerInDegrees) {
					// Only choose from points where x is less than or equal to
					// center.x
					if (p1.x <= center.x) {
						minDist = distance;
						minPoint = p1;
					}
				} else if (degree >= verticalDegree + barWidthInDegrees + barSpacerInDegrees) {
					// Only choose from points where x is greater than center.x
					if (p1.x > center.x) {
						minDist = distance;
						minPoint = p1;
					}
				} else {
					minDist = distance;
					minPoint = p1;
				}
			}
		}
		return minPoint;
	}

	private double getYForFirstEquation(double x, int height) {
		return height - (line1Coeffecient * Math.sin((lineXCoefficient * Math.sqrt(x))));
	}

	private double getYForSecondEquation(double x, int height) {
		return height - (line2Coefficient * Math.sin((lineXCoefficient * Math.sqrt(x))));
	}

	/**
	 * 0 percent will correspond to a line between lower left corner and center
	 * 
	 * @return
	 */
	private double getSlopeForPercent(double percent) {
		double degrees = percent + 225;
		// 0 degrees is to the right of the center
		// 0 percent is bottom left of the center

		double radians = degrees * Math.PI / (double) 180;
		Double xPoint = Math.cos(radians);
		Double yPoint = Math.sin(radians);
		return (0 - xPoint) / (0 - yPoint);
	}
}
