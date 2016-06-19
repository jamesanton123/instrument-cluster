package com.jamesanton.dashboard.instrument_cluster.ui.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
public class FontCreator {
	public static final int VOLT_SIZE = 30;
	public static final int MPH_SIZE = 90;
	public static final Color TEXT_COLOR = new Color(170,170,170);

	private static final String[] PATHS = {"font/square_sans_serif_7.ttf", 
		"font/luximb.tff"};
	
//	public static final String FONT = "Square Sans Serif 7";
	public static final String FONT = "Luxi Mono";
	
	public FontCreator(){
		addFonts();
	}
	
	public void addFonts(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		for(String path: PATHS){
			File f = new File(getClass().getClassLoader().getResource(path).getFile());
			Font font = null;
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, f);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ge.registerFont(font);
		}		
	}	
}
