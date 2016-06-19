package com.jamesanton.dashboard.instrument_cluster.old;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import org.apache.log4j.Logger;

import com.jamesanton.dashboard.instrument_cluster.ui.InstrumentCluster;

/**
 * This is a working listener of the rxtx port. It is responsible for listening
 * to the com port and setting the values of the InstrumentCluster object
 * 
 * @author James
 *
 */
public class OLDSerialPortListener implements Runnable{
	final static Logger LOG = Logger.getLogger(OLDSerialPortListener.class);

	private String portName = "";
	private OLDSerialEventListener listener;
	
	public OLDSerialPortListener(String portName, OLDSerialEventListener listener) {
		InstrumentCluster.getInstance();
		this.portName = portName;
		this.listener = listener;
	}

	public void connect() throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
		} else {
			int timeout = 2000;
			CommPort commPort = portIdentifier.open(this.getClass().getName(), timeout);
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
				listener.setInputStream(serialPort.getInputStream());
				serialPort.addEventListener(listener);
				serialPort.notifyOnDataAvailable(true);				
			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	public void run() {
		try {
			connect();
		} catch (Exception e) {
			LOG.debug("Could not run.", e);
		}
	}

}