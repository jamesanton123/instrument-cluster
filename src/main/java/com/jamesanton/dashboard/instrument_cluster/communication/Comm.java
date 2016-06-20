package com.jamesanton.dashboard.instrument_cluster.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Comm implements SerialPortEventListener {
	private String portName = "/dev/ttyS80";
	private SerialPort serialPort;

	private InputStream input;
	private static OutputStream output;
	private static final int DATA_RATE = 57600;
	private static boolean initializeSuccess = false;
	private String messageReceived="";
	private static Comm instance = null;
	
	protected Comm() {
		// Exists only to defeat instantiation.
	}

	public static Comm getInstance() {
		while (initializeSuccess == false) {
			if (instance == null) {
				instance = new Comm();
			}
			instance.initialize();
		}
		return instance;
	}

	private void initialize() {
		CommPortIdentifier portIdentifier = null;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		} catch (NoSuchPortException e1) {
			e1.printStackTrace();
		}
		if (null != portIdentifier && !portIdentifier.isCurrentlyOwned()) {
			try {
				int timeout = 2000;
				CommPort commPort = portIdentifier.open(this.getClass().getName(), timeout);
				if (commPort instanceof SerialPort) {
					SerialPort serialPort = (SerialPort) commPort;
					serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
					input = serialPort.getInputStream();
					output = serialPort.getOutputStream();
					serialPort.addEventListener(this);
					serialPort.notifyOnDataAvailable(true);
				}
			} catch (Exception e) {
				System.err.println(e.toString());
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			initializeSuccess = true;
		}
	}

	/**
	 * Processes a command
	 * @author James
	 */
	public interface OnCommandReceivedCommand{
		public void execute(String message);
	}
	
	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	private synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * This Method is called when data is received.
	 * If the character is a #, that signals to process the message, then reset the message
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String curChar = Character.toString((char) input.read());
				if (curChar.equals("#")) {
					InstrumentClusterUpdater.commandReceived = messageReceived;
					messageReceived = "";
				} else {
					messageReceived = messageReceived + curChar;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Sends a string to the arduino
	 * Appends the character z to the end of the string, because that is how
	 * we are going to tell the arduino program that it needs to process a command.
	 * @param value
	 */
	public void sendString(String value) {
		sendBytes((value + "z").getBytes());		
	}

	/**
	 * Sends an array of bytes to the arduino
	 * @param bytes
	 */
	private void sendBytes(byte[] bytes) {
		for (byte b : bytes) {
			sendByte(b);
		}
	}

	/**
	 * Sends a single byte to the arduino
	 * @param b1
	 */
	private void sendByte(byte b1) {
		try {
			output.write(b1);
			wait(10);			
			output.flush();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	private static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
