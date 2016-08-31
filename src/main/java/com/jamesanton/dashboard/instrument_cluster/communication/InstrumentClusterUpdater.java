package com.jamesanton.dashboard.instrument_cluster.communication;

import org.apache.commons.lang.StringUtils;

import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.Battery;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.Brights;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.DigitalPins;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.LeftTurnSignal;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.MphDigitalTextL2;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.RightTurnSignal;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.SpeedometerNeedle;
import com.jamesanton.dashboard.instrument_cluster.ui.layouts.layout2.VoltageL2;

/**
 * This class is a class responsible for requesting values from the arduino.
 * Since the performance of the java swing application is not as good as the
 * rate which the arduino can send bytes, to optimize the ui performance, we
 * only want to update components on an as needed basis.
 * 
 * @author James
 *
 */
public class InstrumentClusterUpdater implements Runnable {
	public static volatile String commandReceived = "";
	Comm c = Comm.getInstance();

	public void run() {
		while (true) {
			updateSpeed();
			updateLeftTurnSignal();
			updateRightTurnSignal();
			updateBrights();
			updateVoltage();
			updateDebugTable();
		}
	}

	private void updateDebugTable() {
		DigitalPins.getInstance().setStringValue(getDebugTable());
	}

	private void updateVoltage() {
		int ana0 = retrievePinValue("ana0");
		if (ana0 != 111111) {
			VoltageL2.getInstance().setValue(ana0);
			Battery.getInstance().setValue((int) ((double)ana0 * (double).63));
		}
	}

	private void updateBrights() {
		int dig6 = retrievePinValue("dig6");
		if (dig6 != 111111) {
			Brights.getInstance().setValue(dig6);
		}
	}

	private void updateRightTurnSignal() {
		int dig11 = retrievePinValue("dig11");
		if (dig11 != 111111) {
			RightTurnSignal.getInstance().setValue(dig11);
		}
	}

	private void updateLeftTurnSignal() {
		int dig12 = retrievePinValue("dig12");
		if (dig12 != 111111) {
			LeftTurnSignal.getInstance().setValue(dig12);
		}
	}

	private void updateSpeed() {
		int ana1 = retrievePinValue("ana1");
		if (ana1 != 111111) {
			int speed = calculateSpeed(ana1);			
			if(speed >= 30){
				MphDigitalTextL2.getInstance().setValue(speed);
				SpeedometerNeedle.getInstance().setValue(speed);
			}else{
				// Else, my rectifier circuit is not very accurate at low speeds for
				// whatever reason.
				MphDigitalTextL2.getInstance().setValue(0);
				SpeedometerNeedle.getInstance().setValue(0);
			}
		}
	}

	private int calculateSpeed(int ana1) {
		return Double.valueOf(.0000000000148 * Math.pow(ana1, 4.7)).intValue();
	}
	
	private int retrievePinValue(String pin) {
		String response = sendValue(pin);
		if (response.contains("bad")) {
			return 10;
		} else {
			return intVal(response);
		}
	}

	private String getDebugTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("MPH  = " + pad(sendValue("ana1")));
		sb.append("</td>");		
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</html>");

		// sb.append("<td>");sb.append("dig5 = "+
		// pad(sendValue("dig5")));sb.append("</td>");
		// sb.append("<td>");sb.append("dig11 = "+
		// pad(sendValue("dig11")));sb.append("</td>"); // Right turn signal
		// sb.append("<td>");sb.append("ana2 = "+
		// pad(sendValue("ana2")));sb.append("</td>");
		// sb.append("<td>");sb.append("dig6 = "+
		// pad(sendValue("dig6")));sb.append("</td>"); // Brights
		// sb.append("<td>");sb.append("dig12 = "+
		// pad(sendValue("dig12")));sb.append("</td>"); // Left turn signal
		// sb.append("<td>");sb.append("ana3 = " +
		// pad(sendValue("ana3")));sb.append("</td>");
		// sb.append("<td>");sb.append("dig7 = "+
		// pad(sendValue("dig7")));sb.append("</td>");
		// sb.append("<td>");sb.append("");sb.append("</td>");
		// sb.append("<td>");sb.append("dig8 = "+
		// pad(sendValue("dig8")));sb.append("</td>");
		// sb.append("<td>");sb.append("");sb.append("</td>");
		// sb.append("<td>");sb.append("ana5 = " +
		// pad(sendValue("ana5")));sb.append("</td>");
		return sb.toString();
	}

	private int intVal(String in) {
		try {
			return Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return 1;
		}
	}

	private String pad(String in) {
		return StringUtils.leftPad(in, 4, "0");
	}

	private String sendValue(String string) {
		commandReceived = "";
		c.sendString(string);
		return waitForResponse();
	}

	private String waitForResponse() {
		while (commandReceived.equals("")) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return commandReceived;
	}
}
