package com.jamesanton.dashboard.instrument_cluster.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.jamesanton.dashboard.instrument_cluster.ui.layouts.Layout2;
public class InstrumentCluster extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	public static InstrumentCluster newContentPane = null;
	public static Color background = new Color(0, 80, 0);
	public static final Dimension SIZE = new Dimension(660, 480);
	public static final Dimension LEFT_PANE_SIZE = new Dimension(Math.min(SIZE.height, SIZE.width), Math.min(SIZE.height, SIZE.width));
	private static InstrumentCluster instance = null;
	
	public InstrumentCluster() {
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		this.getContentPane().setCursor(blankCursor);
	}

	public static InstrumentCluster getInstance() {
		if (instance == null) {
			instance = new InstrumentCluster();
			instance.add(Layout2.getControlPanel());
			Layout2.initializeAndSetDefaults();
			instance.setVisible(true);
			System.out.println("Finished initializing instrument cluster");
		}
		return instance;
	}
	
	public void run() {
		createAndShowGUI();
	}
	
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private void createAndShowGUI() {
		GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (d.isFullScreenSupported()) {
			this.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent arg0) {
					setAlwaysOnTop(true);
				}

				public void focusLost(FocusEvent arg0) {
					setAlwaysOnTop(false);
				}
			});
			d.setFullScreenWindow(this);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(false);
			this.dispose();
			this.setMaximumSize(SIZE);
			this.setPreferredSize(SIZE);
			this.setMinimumSize(SIZE);
			this.setBackground(background);
			this.setUndecorated(true);
			this.pack();
			this.setVisible(true);
		}
	}
}
