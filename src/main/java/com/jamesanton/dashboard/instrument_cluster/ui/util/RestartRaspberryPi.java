package com.jamesanton.dashboard.instrument_cluster.ui.util;

public class RestartRaspberryPi {

	public static void main(String[] arg) throws InterruptedException {		
		String user = "";
		String password = "";
		String host = StaticData.RPI_IP;
		String command = "sudo reboot";
		new SSHCommandExecutor().executeCommand(user, password, host, command);		
	}
}
