package com.norway240.mifu;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;

public class ReadFile {

	public void readfile(String dir) throws IOException {
		URL url = getClass().getResource("modlist.txt");
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
			      
			      dlObject.downloadfile(link, dir, save);
			      currmod++;
			      
				  System.out.println("Downloaded: " + currmod + "/" + totalmods);
			  }

			  cfgFile.close();
			} catch (IOException e) {
				System.out.println("Unexpected File IO Error");
			}
		
	}

}