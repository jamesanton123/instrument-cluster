package com.jamesanton.dashboard.instrument_cluster.ui.util;

/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
/**
 * This program will demonstrate remote exec.
 *   $ CLASSPATH=.:../build javac Exec.java 
 *   $ CLASSPATH=.:../build java Exec
 * You will be asked username, hostname, displayname, passwd and command.
 * If everything works fine, given command will be invoked 
 * on the remote side and outputs will be printed out.
 *
 */
import com.jcraft.jsch.*;

import java.io.*;

public class SSHCommandExecutor {
	
	public String executeCommand(String username, String password, String host, String command) {
		String allResponses = "";
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, host, 22);
			UserInfo ui = new MyUserInfo();
			((MyUserInfo) ui).setPassword(password);
			session.setUserInfo(ui);
			session.connect();
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					allResponses = allResponses + new String(tmp, 0, i);
					System.out.print(allResponses);
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			allResponses = allResponses + e.toString();
			System.out.println(e);
		}
		return allResponses;
	}

	public static class MyUserInfo implements UserInfo, UIKeyboardInteractive {
		String passwd = "raspberry";

		public String getPassword() {
			return passwd;
		}

		public boolean promptYesNo(String str) {
			return true;
		}

		public void setPassword(String password) {
			this.passwd = password;
		}

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			return true;
		}

		public void showMessage(String message) {

		}

		public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt, boolean[] echo) {
			return prompt;
		}
	}
}
