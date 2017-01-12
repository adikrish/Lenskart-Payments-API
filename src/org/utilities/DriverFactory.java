package org.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

	private static Properties masterProp = null;
	private String flavour = null;
	String fileloc = "";


	public DriverFactory() {
	}

	public DriverFactory(String flavour) throws IOException {
		this.setFlavour(flavour);
		loadProperties(flavour);
	}

	public void loadProperties(String flavor) throws IOException {
		masterProp = new Properties();
		fileloc = System.getProperty("user.dir") + "//ConfigFiles//" + flavor + ".properties";
		System.out.println("Property file path : " + fileloc);
		File f = new File(fileloc);
		FileInputStream fis = new FileInputStream(f);
		masterProp.load(fis);

	}

	public static String getProperty(String propValue) {

		// System.out.println(masterProp.getProperty(propValue));
		return masterProp.getProperty(propValue);
	}

	void loadMasterProperties(String flavour) {
		masterProp = new Properties();

		// Set config file path here..
		String fileLoc = System.getProperty("user.dir") + "/configFiles/" + flavour + ".properties";
		System.out.println("Property file path : " + fileloc);
		try {
			File f = new File(fileLoc);
			FileInputStream fis = new FileInputStream(f);
			masterProp.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

}
