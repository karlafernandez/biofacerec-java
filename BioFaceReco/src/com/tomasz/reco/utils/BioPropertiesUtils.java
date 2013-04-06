package com.tomasz.reco.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BioPropertiesUtils {

	private static final String BIO_PROPERTIES = "bio.properties";
	private static Logger log = Logger.getLogger(BioPropertiesUtils.class);

	public static String getProperty(String key) {

		String value = null;
		FileReader fr = null;
		
		try {
			File f = new File(BIO_PROPERTIES);
			fr = new FileReader(f);
			Properties prop = new Properties();
			prop.load(fr);
			value = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}

		return value;
	}
}
