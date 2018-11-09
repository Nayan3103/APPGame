package com.risk.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Logging window to write the phases of the risk game
 * @author Ravneet Singh Brar
 * @version 1.0
 */
public class LoggingWindow {
	/*
	 * this method writes it into loggingwindow file
	 */
	public static void Log(String line) throws IOException {
		String sFileName = "LoggingWindow.txt";
		FileWriter fWriter = new FileWriter(sFileName, true);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		// this method appends date to the file
		fWriter.write(dateFormat.format(cal.getTime())+ "\n");
		// this appends the string to the file
		fWriter.write(line + "\n");
		fWriter.close();
	}

}
