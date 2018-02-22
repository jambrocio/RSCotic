package pe.cotic.restCotic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesCache {
	
	private final Properties configProp = new Properties();

	public PropertiesCache() {

		String homeDir = "/usr/share/jboss-eap-6.1/standalone/configuration";
		//String homeDir = "/usr/share/jboss-eap-6.4/standalone/configuration";
		//String homeDir = "C:\\tools\\jboss-eap-6.1\\standalone\\configuration/";

		try {
			//String homeDir = System.getProperty("ruta_fotos");
			FileInputStream fileInput = new FileInputStream(new File(homeDir + "/ayza.properties"));
			configProp.load(fileInput);

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

	public Set<String> getAllPropertyNames() {
		return configProp.stringPropertyNames();
	}

	public boolean containsKey(String key) {
		return configProp.containsKey(key);
	}
}
