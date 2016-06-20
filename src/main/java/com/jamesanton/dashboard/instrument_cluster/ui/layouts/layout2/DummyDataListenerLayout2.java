package com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2;

import org.apache.commons.lang.StringUtils;

import com.jamesanton.dashboard.instrument_cluster.communication.DummyDataListener;
import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.InstrumentClusterLayout;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.Battery;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.Brights;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.CheckEngineLight;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.HeadLight;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.LeftTurnSignal;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.MphDigitalTextL2;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.RightTurnSignal;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.SpeedometerNeedle;

public class DummyDataListenerLayout2 implements DummyDataListener{	
	public DummyDataListenerLayout2(InstrumentClusterLayout instrumentClusterLayout) {
		InstrumentCluster.getInstance(instrumentClusterLayout);
	}

	public void pumpData() throws Exception {
		testSpeed();
		testVoltage();
		testSignals();
	}	
	
	private void testSignals() throws InterruptedException {
		int sleepTimeWarnings = 100;
		for(int i = 0; i < 2; i++){				
			CheckEngineLight.getInstance().setValue(intVal("0001"));
			Thread.sleep(sleepTimeWarnings);
	//		CheckEngineLight.getInstance().setValue(intVal("0000"));
			Thread.sleep(sleepTimeWarnings);
			LeftTurnSignal.getInstance().setValue(intVal("0001"));
			Thread.sleep(sleepTimeWarnings);
	//		LeftTurnSignal.getInstance().setValue(intVal("0000"));
			Thread.sleep(sleepTimeWarnings);
			RightTurnSignal.getInstance().setValue(intVal("0001"));
			Thread.sleep(sleepTimeWarnings);
	//		RightTurnSignal.getInstance().setValue(intVal("0000"));
			Thread.sleep(sleepTimeWarnings);
			Brights.getInstance().setValue(intVal("0001"));
			Thread.sleep(sleepTimeWarnings);
	//		Brights.getInstance().setValue(intVal("0000"));
			Thread.sleep(sleepTimeWarnings);
			HeadLight.getInstance().setValue(intVal("0001"));
			Thread.sleep(sleepTimeWarnings);
	//		HeadLight.getInstance().setValue(intVal("0000"));
			Thread.sleep(sleepTimeWarnings);		
		}
	}

	private void testVoltage() throws InterruptedException {
		for(int i = 0; i < 1800; i+=5){
			Battery.getInstance().setValue(i);
			Thread.sleep(10);
		}
		for(int i = 1800; i >= 0; i-=5){
			Battery.getInstance().setValue(i);
			Thread.sleep(10);
		}	
	}

	private void testSpeed() throws InterruptedException {
		for(int i = 0; i < 7000; i = i + 50){
			int speed = (int)((double)i / 58.33);
			MphDigitalTextL2.getInstance().setValue(speed);
			SpeedometerNeedle.getInstance().setValue(speed);
			Thread.sleep(20);
		}
		for(int i = 7000; i >= 0; i = i - 50){
			int speed = (int)((double)i / 58.33);
			MphDigitalTextL2.getInstance().setValue(speed);
			SpeedometerNeedle.getInstance().setValue(speed);
			Thread.sleep(20);
		}	
		
	}

	private int intVal(String in){	
		try{
			return Integer.parseInt(in);
		}catch(NumberFormatException e){
			return -1;
		}
	}
	
	private static String getDebuggingTable(int i) {
		String val = StringUtils.leftPad(String.valueOf(i), 4, "0");
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<table>");				
		
		sb.append("<tr>");
		sb.append("<td>");sb.append("dig3 = "+ val);sb.append("</td>");
		sb.append("<td>");sb.append("dig9_ = "+ val);sb.append("</td>"); 
		sb.append("<td>");sb.append("ana0 = "+ val);sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");sb.append("dig4 = "+ val);sb.append("</td>");
		sb.append("<td>");sb.append("dig10 = "+ val);sb.append("</td>"); 
		sb.append("<td>");sb.append("ana1 = "+ val);sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");sb.append("dig5 = "+ val);sb.append("</td>");
		sb.append("<td>");sb.append("dig11 = "+ val);sb.append("</td>"); 
		sb.append("<td>");sb.append("ana2 = "+ val);sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");sb.append("dig6 = "+ val);sb.append("</td>");
		sb.append("<td>");sb.append("dig12 = "+ val);sb.append("</td>"); 
		sb.append("<td>");sb.append("ana3 = " + val);sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");sb.append("dig7 = "+ val);sb.append("</td>");
		sb.append("<td>");sb.append("");sb.append("</td>"); 
		sb.append("<td>");sb.append("ana4 = " + val);sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");sb.append("dig8 = "+ val);sb.append("</td>");
		sb.append("<td>");sb.append("");sb.append("</td>"); 
		sb.append("<td>");sb.append("ana5 = " + val);sb.append("</td>");
		sb.append("</tr>");	
		sb.append("</table>");
		return sb.toString();
	}

	public void run() {
		try {
			pumpData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}