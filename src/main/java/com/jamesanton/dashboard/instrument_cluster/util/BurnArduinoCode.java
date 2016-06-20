package com.jamesanton.dashboard.instrument_cluster.util;

public class BurnArduinoCode {
	public static void main(String[] arg) throws InterruptedException {
		BurnArduinoCode burner = new BurnArduinoCode();
		burner.burn();
	}
	// If the programmer is not responding, then disconnect the power on the usb ports of the raspberry pi, disconnect the power on the gpio power pin (turning off the arduino)
	// reconnect the power and retry the burn	
	private void burn() throws InterruptedException{					
			RestartRaspberryPi.main(null);			
			Thread.sleep(50000);
			attemptToBurn();
	}
	
	private String attemptToBurn() {
		String user = "pi";
		String password = "raspberry";
		String host = StaticData.RPI_IP;
		String command = "/usr/share/arduino/hardware/tools/avrdude -V -F -C /etc/avrdude.conf -p atmega328p -P /dev/ttyACM0 -c stk500v1 -b 115200 -U flash:w:Desktop/instrument_cluster.cpp.hex";
		return new SSHCommandExecutor().executeCommand(user, password, host, command);
	}
}
