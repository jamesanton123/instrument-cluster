package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.InstrumentClusterLayout;

public class Layout1 implements InstrumentClusterLayout{
	private static final Dimension SIZE = InstrumentCluster.SIZE;
	private static final Dimension LEFT_PANE_SIZE = new Dimension(Math.min(SIZE.height, SIZE.width), Math.min(SIZE.height, SIZE.width));
	
	public void initializeAndSetDefaults() {
		RpmMeter.getInstance().setValue(0);
		MphDigitalText.getInstance().setValue(0);
		SpeedometerNeedle.getInstance().setValue(0);	
		GasPanel.getInstance().setValue(0);
		Analog5.getInstance().setValue(0);
		Analog4.getInstance().setValue(0);
		Analog3.getInstance().setValue(0);
		Analog2.getInstance().setValue(0);
		Analog1.getInstance().setValue(0);
		Analog0.getInstance().setValue(0);
		DigitalPins.getInstance().setValue(0);		
		LeftTurnSignal.getInstance().setValue(0);
		RightTurnSignal.getInstance().setValue(0);
		Brights.getInstance().setValue(0);
		CheckEngineLight.getInstance().setValue(0);
		VoltPanel.getInstance().setValue(0);
	}
	
	public JLayeredPane getControlPanel() {
		JLayeredPane layeredPane = new JLayeredPane();		
		// Add components to control panel
		layeredPane.add(RpmMeter.getInstance().getPanel(), new Integer(1));
		layeredPane.add(SpeedometerNeedle.getInstance().getPanel(), new Integer(2));
		layeredPane.add(MphDigitalText.getInstance(), new Integer(3));
		layeredPane.add(GasPanel.getInstance().getPanel(), new Integer(12));
		layeredPane.add(VoltPanel.getInstance().getPanel(), new Integer(13));
		layeredPane.add(Analog5.getInstance().getPanel(), new Integer(14));
		layeredPane.add(Analog4.getInstance().getPanel(), new Integer(15));
		layeredPane.add(Analog3.getInstance().getPanel(), new Integer(16));
		layeredPane.add(Analog2.getInstance().getPanel(), new Integer(17));
		layeredPane.add(Analog1.getInstance().getPanel(), new Integer(18));
		layeredPane.add(Analog0.getInstance().getPanel(), new Integer(19));		
		layeredPane.add(DigitalPins.getInstance().getPanel(), new Integer(20));
		layeredPane.add(LeftTurnSignal.getInstance().getPanel(), new Integer(21));
		layeredPane.add(RightTurnSignal.getInstance().getPanel(), new Integer(22));
		layeredPane.add(Brights.getInstance().getPanel(), new Integer(23));
		layeredPane.add(CheckEngineLight.getInstance().getPanel(), new Integer(24));
		addNumbersForMph(layeredPane, 4);
		
		// set control panel attributes
		layeredPane.setBackground(Color.BLACK);
		layeredPane.setMaximumSize(SIZE);
		layeredPane.setPreferredSize(SIZE);
		layeredPane.setMinimumSize(SIZE);
		
		layeredPane.setLocation(0, 0);
		
		return layeredPane;
	}
	
	private void addNumbersForMph(JLayeredPane layeredPane, int startingJLayeredPaneIndex){
		layeredPane.add(new MphNumber("000", 0, LEFT_PANE_SIZE.height - MphNumber.MPH_NUMBER_SIZE.height), new Integer(startingJLayeredPaneIndex));
		layeredPane.add(new MphNumber("020", 0, LEFT_PANE_SIZE.height / 2 - MphNumber.MPH_NUMBER_SIZE.height / 2), new Integer(startingJLayeredPaneIndex + 1));
		layeredPane.add(new MphNumber("040", 0, 0), new Integer(startingJLayeredPaneIndex + 2));
		
		layeredPane.add(new MphNumber("060", LEFT_PANE_SIZE.width / 2 - MphNumber.MPH_NUMBER_SIZE.width / 2, 0), new Integer(startingJLayeredPaneIndex + 3));
		
		layeredPane.add(new MphNumber("080", LEFT_PANE_SIZE.width - MphNumber.MPH_NUMBER_SIZE.width, 0), new Integer(startingJLayeredPaneIndex + 4));
		layeredPane.add(new MphNumber("100", LEFT_PANE_SIZE.width - MphNumber.MPH_NUMBER_SIZE.width, LEFT_PANE_SIZE.height / 2 - MphNumber.MPH_NUMBER_SIZE.height / 2), new Integer(startingJLayeredPaneIndex + 5));
		layeredPane.add(new MphNumber("120", LEFT_PANE_SIZE.width - MphNumber.MPH_NUMBER_SIZE.width, LEFT_PANE_SIZE.height - MphNumber.MPH_NUMBER_SIZE.height), new Integer(startingJLayeredPaneIndex + 6));
	}

}
