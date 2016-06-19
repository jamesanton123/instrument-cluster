package com.jamesanton.dashboard.instrument_cluster.ui.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLayeredPane;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.Battery;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.Brights;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.CheckEngineLight;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.DigitalPins;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.HeadLight;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.LeftTurnSignal;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.MphDigitalTextL2;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.MphNumber;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.RightTurnSignal;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.SpeedometerNeedle;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.VoltageL2;

public class Layout2 {
	private static final Dimension SIZE = InstrumentCluster.SIZE;
	public static final Point LOCATION_ICONS = new Point(540, 300);
	private static final Dimension LEFT_PANE_SIZE = new Dimension(Math.min(SIZE.height, SIZE.width), Math.min(SIZE.height, SIZE.width));

	
	/**
	 * This is just setting defaults for our components
	 */
	public static void initializeAndSetDefaults() {		
		SpeedometerNeedle.getInstance().setValue(0);
		MphDigitalTextL2.getInstance().setValue(0);
		VoltageL2.getInstance().setValue(0);
		LeftTurnSignal.getInstance().setValue(0);
		RightTurnSignal.getInstance().setValue(0);
		Brights.getInstance().setValue(0);
		CheckEngineLight.getInstance().setValue(0);
		HeadLight.getInstance().setValue(0);
		DigitalPins.getInstance().setStringValue("");
		Battery.getInstance().setValue(0);
	}
	
	/**
	 * The layered pane is is going to allow us to overlap components if need be.
	 * This layered pane has most of our components on it.
	 * @return
	 */
	public static JLayeredPane getControlPanel() {
		JLayeredPane layeredPane = new JLayeredPane();		
		// Add components to control panel
		layeredPane.add(Battery.getInstance().getPanel(), new Integer(1));
		layeredPane.add(SpeedometerNeedle.getInstance().getPanel(), new Integer(2));
		layeredPane.add(MphDigitalTextL2.getInstance().getPanel(), new Integer(3));
		layeredPane.add(VoltageL2.getInstance().getPanel(), new Integer(4));
		layeredPane.add(LeftTurnSignal.getInstance().getPanel(), new Integer(5));
		layeredPane.add(RightTurnSignal.getInstance().getPanel(), new Integer(6));
		layeredPane.add(Brights.getInstance().getPanel(), new Integer(7));
		layeredPane.add(CheckEngineLight.getInstance().getPanel(), new Integer(8));
		layeredPane.add(DigitalPins.getInstance().getPanel(), new Integer(9));
		layeredPane.add(HeadLight.getInstance().getPanel(), new Integer(10));
		addNumbersForMph(layeredPane, 11);		
		// set control panel attributes
		layeredPane.setOpaque(true);
		layeredPane.setBackground(Color.BLACK);
		layeredPane.setMaximumSize(SIZE);
		layeredPane.setPreferredSize(SIZE);
		layeredPane.setMinimumSize(SIZE);		
		layeredPane.setLocation(0, 0);		
		return layeredPane;
	}
	
	
	private static void addNumbersForMph(JLayeredPane layeredPane, int startingJLayeredPaneIndex){
		layeredPane.add(new MphNumber("000", 0, LEFT_PANE_SIZE.height - MphNumber.MPH_NUMBER_SIZE.height), new Integer(startingJLayeredPaneIndex));
		layeredPane.add(new MphNumber("020", 0, LEFT_PANE_SIZE.height / 2 - MphNumber.MPH_NUMBER_SIZE.height / 2), new Integer(startingJLayeredPaneIndex + 1));
		layeredPane.add(new MphNumber("040", 0, 0), new Integer(startingJLayeredPaneIndex + 2));
		
		layeredPane.add(new MphNumber("060", LEFT_PANE_SIZE.width / 2 - MphNumber.MPH_NUMBER_SIZE.width / 2, 0), new Integer(startingJLayeredPaneIndex + 3));
		
		layeredPane.add(new MphNumber("080", LEFT_PANE_SIZE.width - MphNumber.MPH_NUMBER_SIZE.width, 0), new Integer(startingJLayeredPaneIndex + 4));
		layeredPane.add(new MphNumber("100", LEFT_PANE_SIZE.width - MphNumber.MPH_NUMBER_SIZE.width, LEFT_PANE_SIZE.height / 2 - MphNumber.MPH_NUMBER_SIZE.height / 2), new Integer(startingJLayeredPaneIndex + 5));
		layeredPane.add(new MphNumber("120", LEFT_PANE_SIZE.width - MphNumber.MPH_NUMBER_SIZE.width, LEFT_PANE_SIZE.height - MphNumber.MPH_NUMBER_SIZE.height), new Integer(startingJLayeredPaneIndex + 6));
	}
}
