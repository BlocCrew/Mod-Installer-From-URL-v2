package com.norway240.mifu;

import javax.swing.JOptionPane;

public class MIFU {
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "This is a test", "MIFU", 1);
		String DLDIR = JOptionPane.showInputDialog(null, "Enter the dir to download to", "MIFU", 1);
		System.out.println(DLDIR);
		
		Download dlObject = new Download();
		dlObject.downloadfile("http://dl.bloccrew.com/test.zip", DLDIR, "/test.zip");

	}

}