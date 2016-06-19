package com.jamesanton.dashboard.instrument_cluster.ui.util;

import java.io.IOException;
import java.util.Date;

public class CommandLineExecutor {

	public static void executeCommand(String command, long timeoutMillis) {
		try {
			Runtime rt = Runtime.getRuntime();
			String fullCommand = "cmd /c start cmd.exe /k \"" + command + "\"";
			System.out.println(fullCommand);
			rt.exec(fullCommand);
			long start = new Date().getTime();
			while((new Date().getTime() - start) < timeoutMillis){				
			}
			rt.exec("taskkill /f /im cmd.exe");
		} catch (IOException e) {			
			throw new RuntimeException(e);
		}
	}
}
