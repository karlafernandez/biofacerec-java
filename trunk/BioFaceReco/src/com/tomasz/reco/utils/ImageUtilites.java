package com.tomasz.reco.utils;

import java.awt.image.BufferedImage;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageUtilites {

	/**
	 * Converts/writes a Mat into a BufferedImage.
	 * 
	 * @param matrix
	 *            Mat of type CV_8UC3 or CV_8UC1
	 * @return BufferedImage of type TYPE_3BYTE_BGR or TYPE_BYTE_GRAY
	 */
	public static BufferedImage matToBufferedImage(Mat matrix) {
		int cols = matrix.cols();
		int rows = matrix.rows();
		int elemSize = (int) matrix.elemSize();
		byte[] data = new byte[cols * rows * elemSize];
		int type;

		matrix.get(0, 0, data);

		switch (matrix.channels()) {
		case 1:
			type = BufferedImage.TYPE_BYTE_GRAY;
			break;

		case 3:
			type = BufferedImage.TYPE_3BYTE_BGR;

			// bgr to rgb
			byte b;
			for (int i = 0; i < data.length; i = i + 3) {
				b = data[i];
				data[i] = data[i + 2];
				data[i + 2] = b;
			}
			break;

		default:
			return null;
		}

		BufferedImage image = new BufferedImage(cols, rows, type);
		image.getRaster().setDataElements(0, 0, cols, rows, data);

		return image;
	}

	public static Mat cropFace(Mat image, Point eyeLeft, Point eyeRight,
			Point offsetPct, Size destSize) {

		double offsetH = Math.floor(offsetPct.x * destSize.width);
		double offsetV = Math.floor(offsetPct.y * destSize.height);

		Point eyeDirection = new Point((eyeRight.x - eyeLeft.x),
				(eyeRight.y - eyeLeft.y));

		double rotation = -Math.atan2(eyeDirection.y, eyeDirection.x);
		double dist = distance(eyeLeft, eyeRight);

		double reference = destSize.width - 2.0 * offsetH;
		double scale = dist / reference;

		Mat newImg = scaleRotateTranslate(image, rotation, eyeLeft, null, 0,
				Imgproc.INTER_CUBIC);

		Point cropXY = new Point(eyeLeft.x - scale * offsetH, eyeLeft.y - scale
				* offsetV);
		Size cropSize = new Size(destSize.width * scale, destSize.height
				* scale);

		newImg = crop(newImg, (int) cropXY.x, (int) cropXY.y,
				(int) ((cropXY.x + cropSize.width)),
				(int) ((cropXY.y + cropSize.height)));
		newImg = resize(newImg, destSize, Imgproc.INTER_LINEAR);
		return newImg;

	}

	private static Mat resize(Mat newImg, Size destSize, int interpolation) {
		Mat resized = new Mat();
		Imgproc.resize(newImg, resized, destSize, 0, 0, interpolation);
		return resized;
	}

	private static Mat crop(Mat img, int x, int y, int width, int height) {
		Rect roi = new Rect(x, y, width, height);
		return img.submat(roi);
	}

	private static double distance(Point p1, Point p2) {
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		return Math.sqrt(dx * dx - dy * dy);
	}

	private static Mat scaleRotateTranslate(Mat image, double angle,
			Point center, Point newCenter, double scale, int resample) {

		if (scale == 0 && center == null) {
			return imageRotate(image, new Point(0, 0), angle, 1.0, resample);
		}

		double nx = center.x;
		double ny = center.y;
		double x = center.x;
		double y = center.y;
		double sx = 1.0;
		double sy = 1.0;

		if (newCenter != null) {
			nx = newCenter.x;
			ny = newCenter.y;
		}

		if (scale != 0) {
			sx = scale;
			sy = scale;
		}

		double cosine = Math.cos(angle);
		double sine = Math.sin(angle);
		double a = cosine / sx;
		double b = sine / sx;
		double c = x - nx * a - ny * b;
		double d = -sine / sy;
		double e = cosine / sy;
		double f = y - nx * d - ny * e;

		Mat transform = new Mat(2, 3, CvType.CV_32FC1);
		transform.put(0, 0, a);
		transform.put(0, 1, b);
		transform.put(0, 2, c);
		transform.put(1, 0, d);
		transform.put(1, 1, e);
		transform.put(1, 2, f);

		Mat trans = transform(image, image.size(), transform, resample);
		return trans;
	}

	private static Mat transform(Mat image, Size size, Mat transform,
			int resample) {
		Mat dst = new Mat(image.size(), image.type());
		Imgproc.warpAffine(image, dst, transform, size);
		return null;
	}

	private static Mat imageRotate(Mat image, Point center, double angle,
			double scale, int resample) {
		Mat rot_mat = Imgproc.getRotationMatrix2D(center, angle, scale);
		Mat dst = new Mat(image.size(), image.type());
		Imgproc.warpAffine(image, dst, rot_mat, image.size());
		return dst;
	}
}
