package com.jamesanton.dashboard.instrument_cluster.util;

public class DeployArduinoCodeToRaspberryPi {
	public static void main(String[] args) {
		String command = "pscp -pw raspberry C:\\Users\\James\\testworkspace\\instrument-cluster2\\src\\"
				+ "main\\resources\\instrument_cluster\\build\\instrument_cluster.cpp.hex pi@" + StaticData.RPI_IP + ":/home/pi/Desktop";
		CommandLineExecutor.executeCommand(command, 20000);
	}
}
