package com.jamesanton.dashboard.instrument_cluster;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;

public class Manager {
	private static boolean usingArduino = true;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(InstrumentCluster.getInstance());
		if (usingArduino) {
			Thread instrumentClusterUpdaterThread = new Thread(new InstrumentClusterUpdater());
			instrumentClusterUpdaterThread.start();
		} else {
			Thread dummyDataThread = new Thread(new DummyDataListener());
			dummyDataThread.start();
		}
	}
}
