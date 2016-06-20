package com.jamesanton.dashboard.instrument_cluster.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CompileArduinoCode {
	public static void main(String[] args) throws InterruptedException {
		try {
			FileUtils.forceDelete(new File("C:\\Users\\James\\testworkspace\\instrument-cluster2\\src\\"
					+ "main\\resources\\instrument_cluster\\build"));
		} catch (IOException e) {

		}
		
		String command = "cd \\arduino*\\ && arduino --pref build.path=C:\\Users\\James\\testworkspace\\instrument-cluster2\\src\\"
				+ "main\\resources\\instrument_cluster\\build "
				+ "--verify C:\\Users\\James\\testworkspace\\instrument-cluster2\\src\\main\\resources\\instrument_cluster\\instrument_cluster.ino";
		CommandLineExecutor.executeCommand(command, 20000);
	}
}
