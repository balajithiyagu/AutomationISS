package com.iss.satellite.testutils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class MyExcelUtil {

	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	
	/*
	 * This method retrieves the list of test cases from the excel sheet
	 * @param moduleName this is the sheet name where the test data is present
	 * @param className based on the class name the corresponding records would be fetched 
	 * @return the list of HashMaps to the method asTwoDimensionalArray()
	 **/
	public static Object[][] readData(String moduleName, String className) throws Exception {
		fis = new FileInputStream("data/Test_Input_Data.xlsx");
		workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(moduleName);
        Iterator<Row> rows = sheet.iterator();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        ArrayList<String> cellHeader = new ArrayList<String>();
        ArrayList<String> cellValue = new ArrayList<String>();
        int j = 0;
        while (rows.hasNext()) {
            Row r = rows.next();
            if (r.getCell(0).getStringCellValue().equalsIgnoreCase("Class"))
            {
                Iterator<Cell> cv = r.cellIterator();
                while (cv.hasNext())
                {
                    Cell c = cv.next();
                    cellHeader.add(c.getStringCellValue());
                }
            }

            if (r.getCell(0).getStringCellValue().equalsIgnoreCase(className)) {
                Map<String, String> data = new HashMap<String, String>();
                Iterator<Cell> cv = r.cellIterator();
                while (cv.hasNext()) {
                    Cell c = cv.next();
                    if (c.getCellTypeEnum() == CellType.STRING)
                    {
                        if (c.getStringCellValue().equalsIgnoreCase("BLANK"))
                        {
                            cellValue.add("");
                        } else
                        {
                            cellValue.add(c.getStringCellValue());
                        }
                    }
                    else
                    {
                        cellValue.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                    }
                }
                for (int i = 0; i < cellHeader.size(); i++) {
                    data.put(cellHeader.get(i), cellValue.get(j));
                    j++;
                }
                list.add(data);
            }

        }
       return asTwoDimensionalArray(list);
    }

	/*
	 * This method fetches the list of records based on the class name 
	 * @param moduleName this is the sheet name where the test data is present
	 * @param className based on the class name the corresponding records would be fetched 
	 * @return the list of HashMaps to the method asTwoDimensionalArray()
	 **/
	public static Map<String, String> readDataWithTestCase(String moduleName, String testCaseName) throws Exception {
		fis = new FileInputStream("data/Test_Input_Data.xlsx");
		workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(moduleName);
		Iterator<Row> rows = sheet.iterator();
		Map<String, String> data = new HashMap<String, String>();
		ArrayList<String> cellHeader = new ArrayList<String>();
		ArrayList<String> cellValue = new ArrayList<String>();
		int j = 0;
		while (rows.hasNext()) 
		{
			Row r = rows.next();
			if (r.getCell(2).getStringCellValue().equalsIgnoreCase("Test_case_name")) 
			{
				Iterator<Cell> cv = r.cellIterator();
				while (cv.hasNext()) 
				{
					Cell c = cv.next();
					cellHeader.add(c.getStringCellValue());
				}
			}

			if (r.getCell(2).getStringCellValue().equalsIgnoreCase(testCaseName)) 
			{
				Iterator<Cell> cv = r.cellIterator();
				while (cv.hasNext()) 
				{
					Cell c = cv.next();
					if (c.getCellTypeEnum() == CellType.STRING) 
					{
						if (c.getStringCellValue().equalsIgnoreCase("BLANK"))
						{
							cellValue.add("");
						} 
						else {
							cellValue.add(c.getStringCellValue());
						}
					} 
					else 
					{
						cellValue.add(NumberToTextConverter.toText(c.getNumericCellValue()));
					}
				}
				for (int i = 0; i < cellHeader.size(); i++) 
				{
					data.put(cellHeader.get(i), cellValue.get(j));
					j++;
				}
				// list.add(data)
			}

		}
		return data;
	}
	
	/*
	 * This method converts the list of HashMap into the ObjectArray 
	 * @param interimResults this holds the list of rows taken from the excel sheet
	 * @return the objectArray which would be passed in the dataProvider of TestFile
	 **/
	private static Object[][] asTwoDimensionalArray(List<Map<String, String>> interimResults) 
	{ 
	  Object[][] results = new Object[interimResults.size()][1];
	  int index = 0; 
	  for (Map<String, String> interimResult : interimResults) 
	  {
	  results[index++] = new Object[] {interimResult};
	  }
	  return results; 
	}
	
}
