package com.zy.common.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelUtils {

	
	
	public static List<List<String>> readJxlXls(String path, int startIdx) throws Exception{
		
		List<List<String>> items = new ArrayList<List<String>>();

		InputStream inputStream = null;
		
		Workbook wb = null;
		
		try {
			inputStream = new FileInputStream(path);
			wb = Workbook.getWorkbook(inputStream);
			
			if(wb.getNumberOfSheets() > 0){
				
				Sheet sheet = wb.getSheet(0);
				
				int rowNum = sheet.getRows();
				
				for(;startIdx < rowNum; startIdx++){
					Cell[] cells = sheet.getRow(startIdx);
					if(cells != null){
						List<String> item = new ArrayList<String>();
						for(Cell c : cells){
							item.add(c.getContents());
						}
						items.add(item);
					}
				}
				
			}
			
		} finally {
			if(wb != null){
				wb.close();
			}
			if(inputStream != null){
				inputStream.close();
			}
		}
		
		
		return items;
	}
	
}
