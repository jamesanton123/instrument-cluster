package com.jamesanton.dashboard.instrument_cluster.old;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is run in a thread, which all it does is read the serial port data, and populate some variables with the latest updated info
 * @author James
 */
public class OLDSerialEventListener implements SerialPortEventListener {
//	final static Logger LOG = Logger.getLogger(SerialEventListener.class);
	private BufferedReader br;
	public static volatile double ana5 = 0;
	public static volatile double ana4 = 0;
	public static volatile double ana3 = 0;
	public static volatile double ana2 = 0;
	public static volatile double ana1 = 0;
	public static volatile double ana0 = 0;
	
	public static volatile double dig3 = 0;
	public static volatile double dig4 = 0;
	public static volatile double dig5 = 0;
	public static volatile double dig6 = 0;
	public static volatile double dig7 = 0;
	public static volatile double dig8 = 0;
	public static volatile double dig9 = 0;
	public static volatile double dig10 = 0;
	public static volatile double dig11 = 0;
	public static volatile double dig12 = 0;

	
	public void setInputStream(InputStream input) {
		br = new BufferedReader(new InputStreamReader(input));
	}

	public void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {			
			try {
				distributeTheCommand(br.readLine());
			} catch (IOException e1) {
//				LOG.debug("Could not distribute the command.", e1);
			}
		}
	}

	public void distributeTheCommand(String commandData) {	
//		LOG.info("Distributing the command: " + commandData);

		// String commandData = "*mph10#";
		commandData = commandData.replace("*", "").replace("#", "");
		if (commandData.startsWith("ana5")) {
			ana5 = Double.parseDouble(commandData.replace("ana5", ""));
		} else if (commandData.startsWith("ana4")) {
			ana4 = Double.parseDouble(commandData.replace("ana4", ""));
		} else if (commandData.startsWith("ana3")) {
			ana3 = Double.parseDouble(commandData.replace("ana3", ""));
		} else if (commandData.startsWith("ana2")) {
			ana2 = Double.parseDouble(commandData.replace("ana2", ""));
		} else if (commandData.startsWith("ana1")) {
			ana1 = Double.parseDouble(commandData.replace("ana1", ""));
		} else if (commandData.startsWith("ana0")) {
			ana0 = Double.parseDouble(commandData.replace("ana0", ""));
		} else if (commandData.startsWith("dig12")) {
			dig12 = Double.parseDouble(commandData.replace("dig12", ""));
		} else if (commandData.startsWith("dig11")) {
			dig11 = Double.parseDouble(commandData.replace("dig11", ""));
		} else if (commandData.startsWith("dig10")) {
			dig10 = Double.parseDouble(commandData.replace("dig10", ""));
		} else if (commandData.startsWith("dig9")) {
			dig9 = Double.parseDouble(commandData.replace("dig9", ""));
		} else if (commandData.startsWith("dig8")) {
			dig8 = Double.parseDouble(commandData.replace("dig8", ""));
		} else if (commandData.startsWith("dig7")) {
			dig7 = Double.parseDouble(commandData.replace("dig7", ""));
		} else if (commandData.startsWith("dig6")) {
			dig6 = Double.parseDouble(commandData.replace("dig6", ""));
		} else if (commandData.startsWith("dig5")) {
			dig5 = Double.parseDouble(commandData.replace("dig5", ""));
		} else if (commandData.startsWith("dig4")) {
			dig4 = Double.parseDouble(commandData.replace("dig4", ""));
		} else if (commandData.startsWith("dig3")) {
			dig3 = Double.parseDouble(commandData.replace("dig3", ""));
		}
	}

	public static int get(String string) {
		// TODO Auto-generated method stub
		return 0;
	}
}
