package com.tomasz.reco;

import java.awt.EventQueue;

import com.tomasz.reco.view.MainWindow;

public class MainClass {

	public static void main(String[] args) {

		System.loadLibrary("opencv_java244");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
