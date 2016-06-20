package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout1;

import com.jamesanton.dashboard.instrument_cluster.communication.DummyDataListener;
import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.InstrumentClusterLayout;

public class DummyDataListenerLayout1 implements DummyDataListener{	
	public DummyDataListenerLayout1(InstrumentClusterLayout instrumentClusterLayout) {
		InstrumentCluster.getInstance(instrumentClusterLayout);
	}
	
	public void pumpData() throws Exception {
		testSpeedAndRpm();
		// Other testable components can be found 
		// in the initialize method of Layout1.java
		System.exit(0);
	}	

	private void testSpeedAndRpm() throws InterruptedException {
		for(int i = 0; i < 7000; i = i + 50){
			int speed = (int)((double)i / 58.33);
			RpmMeter.getInstance().setValue(i);
			MphDigitalText.getInstance().setValue(speed);
			SpeedometerNeedle.getInstance().setValue(speed);
			Thread.sleep(20);
		}
		for(int i = 7000; i >= 0; i = i - 50){
			int speed = (int)((double)i / 58.33);
			RpmMeter.getInstance().setValue(i);
			MphDigitalText.getInstance().setValue(speed);
			SpeedometerNeedle.getInstance().setValue(speed);
			Thread.sleep(20);
		}		
	}
	
	private void pause(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			pumpData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}