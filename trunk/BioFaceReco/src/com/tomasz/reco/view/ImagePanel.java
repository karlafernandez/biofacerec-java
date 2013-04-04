package com.tomasz.reco.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private BufferedImage image;
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Create the panel.
	 */
	public ImagePanel() {
		image = null;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if(image != null) {
			g2d.drawImage(image, 0, 0, null);
		}
	}

}
