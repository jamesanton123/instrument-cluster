package com.jamesanton.dashboard.instrument_cluster.util;

public class DeployFontToRaspberryPi {
	public static void main(String[] args) {
		String command = "pscp -pw raspberry "
				+ "C:\\Users\\James\\testworkspace\\instrument-cluster2\\"
				+ "target\\classes\\font\\luximb.ttf pi@" + 
				StaticData.RPI_IP + ":/home/pi/.fonts";
		CommandLineExecutor.executeCommand(command, 20000);
		
		String command2 = "sudo fc-cache -fv";
		String user = "pi";
		String password = "raspberry";
		String host = StaticData.RPI_IP;
		new SSHCommandExecutor().executeCommand(user, password, host, command2);
		
	}
}
