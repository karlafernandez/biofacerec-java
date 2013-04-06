package com.tomasz.reco.model;

import java.util.HashMap;
import java.util.Map;

public class BioAttributeInfo {

	private String name;
	private Map<String, Integer> attributesMap;

	public BioAttributeInfo(String name, Map<String, Integer> attributesMap) {
		this.name = name;
		this.attributesMap = attributesMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Integer> getAttributesMap() {
		return attributesMap;
	}

	public void setAttributesMap(Map<String, Integer> attributesMap) {
		this.attributesMap = attributesMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributesMap == null) ? 0 : attributesMap.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		BioAttributeInfo other = (BioAttributeInfo) obj;
		if (attributesMap == null) {
			if (other.attributesMap != null)
				return false;
		} else if (!attributesMap.equals(other.attributesMap))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BioAttributeInfo [name=" + name + ", attributesMap="
				+ attributesMap + "]";
	}
	
	
}
