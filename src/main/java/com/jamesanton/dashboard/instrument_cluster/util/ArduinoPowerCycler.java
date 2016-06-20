package com.jamesanton.dashboard.instrument_cluster.util;

public class ArduinoPowerCycler {
	public static void main(String[] args) {
		String command = 
						" cd /home/pi/Desktop" +						
						" && sudo ./hub-ctrl -h 1 -P 1 -p 0" + 
						" && sleep 10" +
						" && sudo ./hub-ctrl -h 1 -P 1 -p 1"
						;
		
		SSHCommandExecutor ssh = new SSHCommandExecutor();
		String user = "pi";
		String password = "raspberry";
		String host = StaticData.RPI_IP;
		ssh.executeCommand(user, password, host, command);
	}
}
