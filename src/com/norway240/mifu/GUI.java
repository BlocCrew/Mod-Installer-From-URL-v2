package com.norway240.mifu;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel text;
	private JTextArea log;
	
	public GUI(String dir) {
		super("MIFU");
		setLayout(new FlowLayout());
		
		text = new JLabel("Downloading...");
		log = new JTextArea("Starting Download\n",7,15);
		
		add(text);
		add(log);
		
		log.append("THIS IS STILL A TEST\n");
		
	}
	
}