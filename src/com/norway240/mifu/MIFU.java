package com.norway240.mifu;

import javax.swing.JOptionPane;

public class MIFU {
	
	public static void main(String[] args) {
		
		JOptionPane.showMessageDialog(null, "Welcome to the Mod Installer From URL v2!\nThis downloads the mods used in the BlocBin \nautomatically into your MultiMC Instance", "MIFU", 1);
		String DLDIR = JOptionPane.showInputDialog(null, "Enter the location of your\nMultiMC Instance", "MIFU", 1);
		System.out.println(DLDIR);
		
		ReadFile rfObj = new ReadFile();
		rfObj.readfile(DLDIR);
		
		JOptionPane.showMessageDialog(null, "Finished downloading mods!\nYou can play now. \nThe Official BlocBin Server\nIP is mod.bloccrew.com", "MIFU", 1);
	}

}