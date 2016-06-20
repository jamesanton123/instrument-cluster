package com.jamesanton.dashboard.instrument_cluster;

import com.jamesanton.dashboard.instrument_cluster.communication.DummyDataListener;
import com.jamesanton.dashboard.instrument_cluster.communication.InstrumentClusterUpdater;
import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.InstrumentClusterLayout;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.DummyDataListenerLayout2;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.Layout2;

public class Manager {
	private static boolean usingArduino = false;
	private static InstrumentClusterLayout layout = new Layout2();
	private static DummyDataListener dummyDataListener = new DummyDataListenerLayout2(layout);
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(InstrumentCluster.getInstance(layout));
		if (usingArduino) {
			Thread instrumentClusterUpdaterThread = new Thread(new InstrumentClusterUpdater());
			instrumentClusterUpdaterThread.start();
		} else {
			Thread dummyDataThread = new Thread(dummyDataListener);
			dummyDataThread.start();
		}
	}
}
