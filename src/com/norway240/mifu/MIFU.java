package com.norway240.mifu;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MIFU {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		JOptionPane.showMessageDialog(null, "Welcome to the Mod Installer From URL v2!\nThis downloads the mods used in the BlocBin \nautomatically into your MultiMC Instance", "MIFU", 1);
		String DLDIR = JOptionPane.showInputDialog(null, "Enter the location of your\nMultiMC Instance", "MIFU", 1);
		System.out.println(DLDIR);
		JOptionPane.showMessageDialog(null, "You selected:\n"+DLDIR, "MIFU", 1);
		
		JFrame frame = new JFrame();
		JLabel text = new JLabel("Preparing to download..");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout (new BorderLayout ());
	    frame.getContentPane().add (text, BorderLayout.CENTER);
	    frame.pack ();
	    frame.setVisible(true);
	    
	    Thread.sleep(1000);
	    
	    URL url = MIFU.class.getResource("modlist.txt");
		File modlist = new File(url.getPath());
	    Download dlObject = new Download();
		FileReader fr = new FileReader(modlist);
		LineNumberReader lnr = new LineNumberReader(fr);
		int totalmods = 0;
        while (lnr.readLine() != null){
         	totalmods++;
        }
        lnr.close();
        System.out.println("Total number of mods to download: " + totalmods);
	    
		try {
			  BufferedReader cfgFile = new BufferedReader(new FileReader(modlist));
			  String line = null;
			  int currmod = 0;

			  while ((line = cfgFile.readLine()) != null) {
				  line.trim();
			      String [] modlst = line.split(","); 
			      
			      String link = modlst[0];
			      String save = modlst[1];
			      
			      dlObject.downloadfile(link, DLDIR, save);
			      currmod++;
			      
				  System.out.println("Downloaded: " + currmod + "/" + totalmods);
				  text.setText("Downloaded: " + currmod + "/" + totalmods);
			  }
			  cfgFile.close();
			} catch (IOException e) {
				System.out.println("Unexpected File IO Error");
			}
		
		text.setText("Done!");
		JOptionPane.showMessageDialog(null, "Finished downloading mods!\nYou can play now. \nThe Official BlocBin Server\nIP is mod.bloccrew.com", "MIFU", 1);
	}

}