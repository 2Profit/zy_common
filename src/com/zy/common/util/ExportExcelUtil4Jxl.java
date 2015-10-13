package com.zy.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 用JXL导出数据到EXCEL工具类
 * 
 * @author pingan  
 * @since  2015-10-14
 */
public class ExportExcelUtil4Jxl {

	/**
	 * 居中的内容
	 */
	public static WritableCellFormat centerFormat;
	public static WritableCellFormat leftFormat;
	public static WritableCellFormat rightFormat;
	
	static {
		if(centerFormat == null){
			centerFormat = new WritableCellFormat();
			try {
				centerFormat.setAlignment(jxl.format.Alignment.CENTRE);
				centerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				centerFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		if(leftFormat == null){
			leftFormat = new WritableCellFormat();
			try {
				leftFormat.setAlignment(jxl.format.Alignment.LEFT);
				leftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				leftFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		if(rightFormat == null){
			rightFormat = new WritableCellFormat();
			try {
				rightFormat.setAlignment(jxl.format.Alignment.RIGHT);
				rightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				rightFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 得到已经写入数据的单元格
	 * @param column
	 * @param row
	 * @param obj
	 * @param cellFormat
	 * @return
	 */
	public static WritableCell getWritableCell(int column, int row, Object obj, CellFormat cellFormat){
		WritableCell cell = null;
		if(obj == null){
			cell = new Label(column, row, "", cellFormat);
		}else{
			if(obj instanceof String){
				cell = new Label(column, row, (String)obj, cellFormat);
			}else if(obj instanceof Integer || obj instanceof Long || obj instanceof Double || obj instanceof Float || obj instanceof BigDecimal){
				cell = new Number(column, row, Double.valueOf(obj.toString()), cellFormat);
			}else if(obj instanceof java.util.Date){
				cell = new Label(column, row, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)obj), cellFormat);
			}else{
				cell = new Label(column, row, obj.toString(), cellFormat);
			}
		}
		return cell;
	}
	
	/**
	 * 直接填充一行数据
	 * @param ws
	 * @param values
	 * @param column 起点列
	 * @param row 起点行
	 * @return
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static WritableSheet addLine(WritableSheet ws, Object[] values, int column, int row, CellFormat cellFormat) 
			throws RowsExceededException, WriteException{
		
		for (int i = 0; i < values.length; i++) {
			ws.addCell(getWritableCell(column+i, row, values[i], cellFormat));
		}
		return ws;
	}
	
	/**
	 * 设置多列的列宽
	 * @param ws
	 * @param width
	 * @param columns
	 */
	public static void setColumnWidth(WritableSheet ws, int width, int[] columns){
		for (int column : columns) {
			ws.setColumnView(column, width);
		}
	}
}
