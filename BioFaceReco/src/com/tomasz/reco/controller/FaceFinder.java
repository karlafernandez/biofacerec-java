package com.tomasz.reco.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import com.tomasz.reco.utils.BioPropertiesUtils;

public class FaceFinder {

	private static final String HARRCLASSIFIER_PROPERTY = "harrclassifier";

	private static Logger log = Logger.getLogger(FaceFinder.class);

	private List<Mat> faces;
	private List<Mat> grayFaces;
	private List<Rect> rects;

	private CascadeClassifier harrClassifier;

	public FaceFinder() {
		faces = new ArrayList<Mat>();
		grayFaces = new ArrayList<Mat>();
		harrClassifier = new CascadeClassifier();
		rects = new ArrayList<Rect>();
	}

	public int findFaces(Mat image) {

		faces.clear();
		grayFaces.clear();

		if (rects != null) {
			rects.clear();
		}

		boolean success = false;
		String classifier = BioPropertiesUtils.getProperty(HARRCLASSIFIER_PROPERTY);

		success = harrClassifier.load(classifier);

		if (success) {

			if (!image.empty()) {
				Mat gray = new Mat();
				Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

				if (!gray.empty()) {
					MatOfRect tmpRects = new MatOfRect();
					harrClassifier.detectMultiScale(gray, tmpRects);

					rects = tmpRects.toList();

					for (Rect r : rects) {
						Mat ROI = image.submat(r);
						Mat grayROI = gray.submat(r);
						faces.add(ROI);
						grayFaces.add(grayROI);
					}
				}
			}

		} else {
			log.error("Problem with loading classifier file");
		}

		return faces.size();
	}

	public List<Mat> getFaces() {
		return faces;
	}

	public void setFaces(List<Mat> faces) {
		this.faces = faces;
	}

	public List<Mat> getGrayFaces() {
		return grayFaces;
	}

	public void setGrayFaces(List<Mat> grayFaces) {
		this.grayFaces = grayFaces;
	}

	public List<Rect> getRects() {
		return rects;
	}

	public void setRects(List<Rect> rects) {
		this.rects = rects;
	}

	@Override
	public String toString() {
		return "FaceFinder [faces=" + faces + ", rects=" + rects
				+ ", harrClassifier=" + harrClassifier + "]";
	}

}
