package com.tomasz.reco.model;

import java.util.LinkedList;
import java.util.List;

public class BioAttributesContainer {

	private List<BioAttribute> list;
	
	public BioAttributesContainer() {
		list = new LinkedList<BioAttribute>();
	}

	public List<BioAttribute> getList() {
		return list;
	}

	public void setList(List<BioAttribute> list) {
		this.list = list;
	}
	
	public void add(BioAttribute attr) {
		list.add(attr);
	}
	
	public void remove(int index) {
		list.remove(index);
	}
}
