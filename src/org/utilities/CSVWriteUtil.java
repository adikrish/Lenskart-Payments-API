package org.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.CSVWriter;

public class CSVWriteUtil {

	private static final Logger log = Logger.getLogger(CSVReadUtil.class);
	private CSVWriter writer;

	public CSVWriteUtil(String csvWriteFile) throws IOException {
		writer = new CSVWriter(new FileWriter(csvWriteFile));
		log.debug("CSV write initialized...");
	}

	public CSVWriteUtil(String csvWriteFile, char seperator) throws IOException {
		writer = new CSVWriter(new FileWriter(csvWriteFile), seperator);
		log.debug("CSV write initialized with a custom seperator..");
	}

	public void addLineToCSV(String[] entries) throws IOException {
		writer.writeNext(entries);
		log.debug("wrote a line to CSV file");
	}
	
	public void addAllToCSV(List<String[]> allLines) throws IOException {
		writer.writeAll(allLines);
		log.debug("wrote multiple lines to CSV file");
	}
	
	public void closeWriter() throws IOException{
		writer.close();
		log.debug("CSV writer closed");
	}
}
