package com.tomasz.reco.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.tomasz.reco.model.BioAttributeInfo;
import com.tomasz.reco.utils.BioPropertiesUtils;

public class BioAttributesContainer {

	private static Logger log = Logger.getLogger(BioAttributesContainer.class);
	
	private List<BioAttributeInfo> list;
	
	public BioAttributesContainer() {
		list = new LinkedList<BioAttributeInfo>();
	}

	public List<BioAttributeInfo> getList() {
		return list;
	}

	public void setList(List<BioAttributeInfo> list) {
		this.list = list;
	}
	
	public void add(BioAttributeInfo attr) {
		list.add(attr);
	}
	
	public void remove(int index) {
		list.remove(index);
	}
	
	public void clear() {
		list.clear();
	}
	
	public void loadAttributes() {
		
		list.clear();
		
		try {
			File xml = new File(BioPropertiesUtils.getProperty("keymap_xml"));
			SAXBuilder sax = new SAXBuilder();
			Document doc = (Document)sax.build(xml);
			Element root = doc.getRootElement();
			
			List<Element> attributes = root.getChildren("attribute");
			
			for(Element e : attributes) {
				BioAttributeInfo bioAttInfo = parseAttributeElement(e);
				log.info(bioAttInfo);
				list.add(bioAttInfo);
			}
		} catch (JDOMException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		
		
	}

	private BioAttributeInfo parseAttributeElement(Element e) {
		String attrib = e.getAttributeValue("value");
		Map<String, Integer> attribs = new HashMap<String, Integer>();
		
		for(Element value : e.getChildren("value")) {
			String v = value.getValue();
			Integer i = Integer.valueOf(value.getAttributeValue("index"));
			
			attribs.put(v, i);
		}
		
		return new BioAttributeInfo(attrib, attribs);
		
	}
}
