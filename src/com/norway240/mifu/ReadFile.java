package com.norway240.mifu;

import java.io.*;
import java.net.URL;

public class ReadFile {

	public void readfile(String dir) {
		URL url = getClass().getResource("modlist.txt");
		File modlist = new File(url.getPath());
	    Download dlObject = new Download();
	    
		try {
			  BufferedReader cfgFile = new BufferedReader(new FileReader(modlist));
			  String line = null;

			  // Read the file line by line
			  while ((line = cfgFile.readLine()) != null) {
				  line.trim();
			      String [] modlst = line.split(","); 
			      
			      String link = modlst[0];
			      String save = modlst[1];  
			      
			      System.out.println("link: "+link);
			      System.out.println("file: "+save);
			      
			      dlObject.downloadfile(link, dir, save);
			  }

			  cfgFile.close();
			} catch (IOException e) {
				System.out.println("Unexpected File IO Error");
			}
		
	}

}
