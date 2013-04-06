package com.tomasz.reco;

import org.apache.log4j.BasicConfigurator;

public class MainClass {

	public static void main(String[] args) {

		System.loadLibrary("opencv_java244");
		BasicConfigurator.configure();
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow frame = new MainWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

	}
}
