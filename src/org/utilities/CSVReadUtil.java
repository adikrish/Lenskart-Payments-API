package org.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;

public class CSVReadUtil {

	private static final Logger log = Logger.getLogger(CSVReadUtil.class);
	private CSVReader reader;
	
	public CSVReadUtil(String csvReadFile) throws IOException {
		reader = new CSVReader(new FileReader(csvReadFile));
		log.debug("CSV reader initialized");
	}

	public List<String[]> getEntriesAsList() throws IOException {
		List<String[]> myEntries = reader.readAll();
		log.debug("Converted CSV file into List of String arrays");
		return myEntries;
	}
	
	
}
