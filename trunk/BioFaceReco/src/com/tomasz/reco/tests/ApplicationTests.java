package com.tomasz.reco.tests;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import com.tomasz.reco.controller.BioAttributesContainer;
import com.tomasz.reco.controller.FaceFinder;
import com.tomasz.reco.utils.BioPropertiesUtils;

public class ApplicationTests {

	static {
		System.loadLibrary("opencv_java244");
		BasicConfigurator.configure();
	}
	
	@Test
	public void testBioPropertiesUtils() {
		assertNotNull(BioPropertiesUtils.getProperty("harrclassifier"));
		assertNotNull(BioPropertiesUtils.getProperty("database_xml"));
	}

	@Test
	public void testImageRead() {
		Mat img = Highgui.imread("images/picture.jpg");

		assertNotNull(img);
		assertFalse(img.empty());
	}

	@Test
	public void testFaceFinder() {
		FaceFinder ff = new FaceFinder();

		Mat img = Highgui.imread("images/picture.jpg");
		assertNotNull(img);
		
		int cnt = ff.findFaces(img);
		assertTrue(cnt > 0);
	}
	
	@Test
	public void testBioAttributesInfo() {
		BioAttributesContainer bac = new BioAttributesContainer();
		bac.loadAttributes();
		
		assertTrue(bac.getList().size() > 0);
	}

}
