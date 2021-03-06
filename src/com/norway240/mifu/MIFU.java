package com.norway240.mifu;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class MIFU {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String currdir = System.getProperty("user.dir");
		JOptionPane.showMessageDialog(null, "Welcome to the Mod Installer From URL v2!\nThis downloads the mods \nautomatically into your MultiMC Instance", "MIFU", 1);
		String DLDIR = JOptionPane.showInputDialog(null, "Enter the location of your\nMultiMC Instance\nLeave blank for the current dir", "MIFU", 1);
		if(DLDIR == null){DLDIR = currdir;}
		
		JFrame gui = new JFrame("MIFU");
		JLabel text = new JLabel("Preparing to download...            ");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.getContentPane().setLayout(new BorderLayout ());
		gui.getContentPane().add(text, BorderLayout.CENTER);
		//gui.pack();
		
		//JFrame logwindow = new JFrame("MIFU LOG");
		JTextArea log = new JTextArea(20,30);
		//logwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		log.setEditable(false);
		JScrollPane scroll = new JScrollPane(log);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//logwindow.add(scroll);
		//logwindow.pack();
		gui.getContentPane().add(scroll, BorderLayout.PAGE_END);		
		//logwindow.setVisible(true);
		gui.pack();
		gui.setVisible(true);
	    
		Thread.sleep(1000);
	    Download dlObject = new Download();
	    boolean modlistcheck = new File(currdir + "modlist.txt").isFile();
	    if(!modlistcheck){
	    	text.setText("Downloading modlist.txt");
	    	log.append("Downloading modlist.txt\n");
	    	dlObject.downloadfile("http://dl.bloccrew.com/modlist.txt", currdir, "/modlist.txt");
	    	Thread.sleep(1000);
	    }
	    
	    File modlist = new File(currdir + "/modlist.txt");
		FileReader fr = new FileReader(modlist);
		LineNumberReader lnr = new LineNumberReader(fr);
		int totalmods = 0;
        while (lnr.readLine() != null){
         	totalmods++;
        }
        lnr.close();
        text.setText("Reading modlist");
		log.append("Reading modlist\n");
        System.out.println("Total number of mods to download: " + totalmods);
	    log.append("Mods to download: " + totalmods + "\n");
        
		try {
			BufferedReader cfgFile = new BufferedReader(new FileReader(modlist));
			String line = null;
			int currmod = 0;
			while ((line = cfgFile.readLine()) != null) {
				line.trim();
			    String [] modlst = line.split(","); 
			    String link = modlst[0];
			    String save = modlst[1];
			    log.append("Downloading: " + save + "\n");
			    log.setCaretPosition(log.getDocument().getLength());
			    dlObject.downloadfile(link, DLDIR, save);
			    currmod++;
				System.out.println("Downloaded: " + currmod + "/" + totalmods);
				text.setText("Downloaded: " + currmod + "/" + totalmods);
				log.append("Downloaded: ["+currmod+"/"+totalmods+"] " + save + "\n");
				log.setCaretPosition(log.getDocument().getLength());
			}
				cfgFile.close();
			} catch (IOException e) {
				System.out.println("Unexpected File IO Error");
				log.append("Unexpected File IO Error\n");
			}

		boolean configcheck = new File(DLDIR + "/config.zip").isFile();
	    if(configcheck) {
	    	text.setText("Extracting config.zip");
	    	log.append("Extracting config.zip\n");
	    	File configfolder = new File(DLDIR + "/minecraft/config");
	    	configfolder.mkdir();
	    	Extract extractObj = new Extract();
	    	extractObj.ExtractZipFile(DLDIR + "/config.zip", DLDIR + "/minecraft/config/");
	    	Thread.sleep(1000);
	    }
		
		text.setText("Done!");
		log.append("Done!\n");
		log.setCaretPosition(log.getDocument().getLength());
		Thread.sleep(1000);
		gui.dispose();
		//logwindow.dispose();
		if(!modlistcheck){JOptionPane.showMessageDialog(null, "Finished downloading mods!\nYou can play now. \nThe Official BlocBin Server\nIP is mod.bloccrew.com", "MIFU", 1);
		}else{JOptionPane.showMessageDialog(null, "Your download is now complete!", "MIFU", 1);}
	}

}