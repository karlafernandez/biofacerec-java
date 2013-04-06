package com.tomasz.reco.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.core.Mat;

public class FaceData {
	
	private List<BioAttribute> characteristic;
	private Mat image;
	
	public FaceData() {
		characteristic = new ArrayList<BioAttribute>();
		image = null;
	}

	public FaceData(List<BioAttribute> characteristic, Mat image) {
		this.characteristic = characteristic;
		this.image = image;
	}

	public List<BioAttribute> getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(List<BioAttribute> characteristic) {
		this.characteristic = characteristic;
	}

	public Mat getImage() {
		return image;
	}

	public void setImage(Mat image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((characteristic == null) ? 0 : characteristic.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FaceData other = (FaceData) obj;
		if (characteristic == null) {
			if (other.characteristic != null)
				return false;
		} else if (!characteristic.equals(other.characteristic))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FaceData [characteristic=" + characteristic + ", image="
				+ image + "]";
	}

}
