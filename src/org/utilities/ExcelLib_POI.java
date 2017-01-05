package org.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLib_POI {

	private static final Logger log = Logger.getRootLogger();
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public HashMap<String, Integer> columnIndexMap;
	public HashMap<Integer, String> flaggedMethodsMap;

	public ArrayList<String> columnNamesList;

	public ExcelLib_POI(String excelSheetPath, int sheetNumber) {

		log.debug("constructing ExcelLib object..");
		try {
			FileInputStream file = new FileInputStream(new File(excelSheetPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheetAt(sheetNumber);
			System.out.println(sheet.getSheetName());
			// Create a map of column name and corresponding index
			createColumnNameIndexMap();
			file.close();
			// workbook.close();
		} catch (Exception e) {
			log.error("Could not read excel file ");
			throw new IllegalArgumentException("Exception in reading the excel file...");
		}
	}

	public ExcelLib_POI(String excelSheetPath, String sheetName) {
		log.debug("constructing ExcelLib object..");
		try {
			FileInputStream file = new FileInputStream(new File(excelSheetPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			System.out.println("Sheet selected : " + sheet.getSheetName());
			// Create a map of column name and corresponding index
			createColumnNameIndexMap();
			file.close();
			// workbook.close();
		} catch (Exception e) {
			log.error("Could not read excel file ");
			throw new IllegalArgumentException("Error reading the file (excel)...");
		}
	}

	
	/*************************************************************************
	 * This Private method is to create a map of column value to index. This is
	 * required to get the call value based on columnName
	 ***************************************************************************/
	private void createColumnNameIndexMap() {

		log.debug("inside method to create createColumnNameIndexMap");
		//Create a columnList as well
		columnNamesList = new ArrayList<String>();
		columnIndexMap = new HashMap<String, Integer>();

		int firstRowNum = sheet.getFirstRowNum();
		Row row = sheet.getRow(firstRowNum); // Get first row

		// following is boilerplate from the java doc
		// get the first column index for a row
		short minColIx = row.getFirstCellNum();
		// get the last column index for a row
		short maxColIx = row.getLastCellNum();
		for (short colIx = minColIx; colIx < maxColIx; colIx++) {
			// get the cell
			Cell cell = row.getCell(colIx);
			// add the cell contents and its index to map
			columnIndexMap.put(cell.getStringCellValue(), cell.getColumnIndex());
			
			//Create a column values list as well
			columnNamesList.add(cell.getStringCellValue());
		}
	}

	public HashMap<Integer, String> getFlaggedMethods(String columnFlag, String flagValue,
			ArrayList<String> expectedColValues) {
		log.debug("inside method to get flagged methods");
		int index = 0;
		ListIterator<String> listItr = expectedColValues.listIterator();

		/*
		 * For each matching rows with columnFlag set to flagValue, string value
		 * is created by concatenating cell values (listed in array list)
		 * separated by ";"
		 */
		for (int row = 0; row < getRowCount(); row++) {
			if (getCellValue(row, columnFlag).equalsIgnoreCase(flagValue)) {
				String desiredColValues = "";
				while (listItr.hasNext()) {
					String colValue = getCellValue(row, listItr.next());
					desiredColValues = desiredColValues + ";" + colValue;
				}

				/*
				 * Insert value into flaggedMap along with index Index is
				 * required to get the columnValues directly by treating index
				 * as key. (Since map does not maintain order)
				 */
				flaggedMethodsMap.put(index, desiredColValues);
				index++;
			}
		}

		return flaggedMethodsMap;

	}

	public String getCellValue(int rowNum, int colNum) {
		log.debug("geting cell value of the excel sheet");
		
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(colNum);
		return cell.getStringCellValue();
	}

	public String getCellValue(int rowNum, String colName) {
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(columnIndexMap.get(colName));
		return cell.getStringCellValue();
	}

	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();

		/*
		 * int count = 0; Iterator<Row> rowIterator = sheet.rowIterator(); while
		 * (rowIterator.hasNext()) { Row row = (Row) rowIterator.next(); if(row
		 * != null){ count++; } }
		 * 
		 * return count;
		 */
	}

	public int getColumnCount(int rowIndex) {

		// System.out.println("physical number " +
		// sheet.getRow(rowIndex).getPhysicalNumberOfCells());
		// System.out.println("column count " +
		// (sheet.getRow(rowIndex).getLastCellNum() -
		// sheet.getRow(rowIndex).getFirstCellNum()));
		int colCount = 0;
		try{
			colCount	=  sheet.getRow(rowIndex).getPhysicalNumberOfCells();
		}catch (Exception e){
			System.out.println("Excetion caught. Column is null. returning count as 0");
			//colCount = 0;
		}
		
		return colCount;
	}

	public NameValuePair getColumnValuePair(int colNum, int rowNum) {
		// get column name by row number 0 and colNumber col

		Row row = sheet.getRow(0);
		Cell cell = row.getCell(colNum);
		String colName = cell.getStringCellValue();

		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		String cellValue = cell.getStringCellValue();

		BasicNameValuePair pair = new BasicNameValuePair(colName, cellValue);

		return pair;
	}

	public HashMap<String, Integer> getColumnIndexMap(){
		return columnIndexMap;
	}
	
	public ArrayList<String> getColumnNamesList(){
		return columnNamesList;
	}
	
}
