package com.jamesanton.dashboard.instrument_cluster.util;

public class DeployJarToRaspberryPi {
	public static void main(String[] args){
		while(true){
			String command = "pscp -pw raspberry C:\\Users\\James\\projects-and-workspaces\\projects\\instrument-cluster2\\target\\i.jar pi@" + StaticData.RPI_IP + ":/home/pi/Desktop";
			CommandLineExecutor.executeCommand(command, 30000);
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
