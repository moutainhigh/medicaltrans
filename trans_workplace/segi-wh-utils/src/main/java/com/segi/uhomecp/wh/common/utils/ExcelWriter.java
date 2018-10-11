package com.segi.uhomecp.wh.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.utils.DateUtil;
import com.segi.uhomecp.utils.ExcelUtils;
import com.segi.uhomecp.utils.ObjNumberUtils;

/**
 * Excel导出工具类 zhaow
 */
public class ExcelWriter {
	private static Logger log = LoggerFactory.getLogger(ExcelWriter.class);

	private final static String RSPAN = "#rspan";

	private final static String CSPAN = "#cspan";
	
	/**
	 * 创建简单的Excel
	 * @param title 文件名和sheet名
	 * @param columnTitles Excel表头
	 * @param data Excel数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static File simpleCreate(String title, String[] columnTitles, Collection data) throws Exception {
		return simpleCreate(getTitile(title), columnTitles, data, null, null);
	}
	
	/**
	 * @Title: mulitCreate   
	 *  创建简单的多表头 Excel
	 * @author zhaoqing  
	 * @param title
	 * @param columnTitles
	 * @param data
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static File mulitCreate(String title, List<String[]> columnTitles, Collection data) throws Exception {
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put("MainTitle1Style", "宋体,18,center,60,B");
		configMap.put("MainTitle2Style", "华文新魏,11,right,20,B#I");
		configMap.put("TitleStyle", "宋体,11,center,20");
		configMap.put("FootTitleStyle", "宋体,11,left,20,B#I");
		configMap.put("DataStyle", "宋体,10,center");
		configMap.put("DataWidth", "");
		return mulitHeaderCreate(getTitile(title), null, null, columnTitles, data, configMap);
	}
	
	/**
	 * 创建简单的Excel
	 * @param title 文件名和sheet名
	 * @param columnTitles Excel表头
	 * @param data Excel数据
	 * @param rowCelIndexList 行列索引集合 List<List<int[]>> int[0] 行索引, int[1] 列索引
	 * @param backgroudColrList 背景颜色 List<颜色代码（如HSSFColor.GREY_25_PERCENT.index）>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static File simpleCreate(String title, String[] columnTitles, Collection data, List<List<int[]>> rowCelIndexList, List<Short> backgroudColrList) throws Exception {
		HSSFWorkbook book = null;
		FileOutputStream fis = null;
		File file = null;
		try {
            if (file == null) {
                String path = ExcelUtils.class.getClassLoader().getResource("/").getPath() + title + ".xls";
                path = path.replace("%20", " ");
                file = new File(path);
                book = createSheets(title, columnTitles, data, book);
                fis = new FileOutputStream(file);
                HSSFSheet sheet = book.getSheetAt(0);
                setCellBackgroundColor(sheet, rowCelIndexList, backgroudColrList);
                book.write(fis);
                fis.flush();
            } 
			return file;
		} catch (Exception e) {
			log.error("", e);
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	/**
	 * 创建sheet
	 * @param sheetName
	 * @param columnTitles
	 * @param data
	 * @param book
	 */
	@SuppressWarnings("rawtypes")
	private static HSSFWorkbook createSheets(String sheetName, String[] columnTitles, Collection data, HSSFWorkbook book) {
	    HSSFSheet sheet = null;
	    if (book == null) {
	        book = new HSSFWorkbook();
	        sheet = book.createSheet(sheetName);
        }else {
            sheet = book.getSheetAt(0);
        }
		HSSFCellStyle titleCellStyle = getTitleCellStyle(book);
		HSSFCellStyle cellStyle = getCellStyle(book);
		Cell cell = null;
		// 如果表头不为空，则写入表头
		Row excelRow = null;
		if (columnTitles != null) {
			excelRow = sheet.createRow(0);
			for (int i = 0; i < columnTitles.length; i++) {
				cell = excelRow.createCell(i);
				initCell(cell, columnTitles[i], titleCellStyle);
			}
		}
		// 写入数据
		int rowIndex = 1;
        if (sheet.getLastRowNum() > 0) {
            rowIndex = sheet.getLastRowNum() + 1;
        }
		Iterator it = data.iterator();
		while (it.hasNext()) {
			Object[] row = (Object[]) it.next();
			excelRow = sheet.createRow(rowIndex++);

			int colIndex = 0;
			for (Object cellValue : row) {
				cell = excelRow.createCell(colIndex);
				setColumnWidth(sheet, colIndex, cellValue);
				initCell(cell, cellValue, cellStyle);
				
				colIndex++;
			}
		}
        return book;
	}

	/**
	 * 创建多个sheet的Excel表格
	 * @param title	Excel文件名
	 * @param sheetNames sheet名称
	 * @param columnTitleList 列名
	 * @param dataList	表格数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static File anySheetsCreate(String title,String[] sheetNames,List<String[]> columnTitleList, List<Collection> dataList){
		File file = null;
		HSSFWorkbook book = null;
		FileOutputStream fis = null;
		try {
			String path = ExcelUtils.class.getClassLoader().getResource("/").getPath() + title + ".xls";
			path = path.replace("%20", " ");
			file = new File(path);
			book = new HSSFWorkbook();
			if(sheetNames!=null){
				for (int i = 0; i < sheetNames.length; i++) {
					createSheets(sheetNames[i], columnTitleList.get(i), dataList.get(i), book);
				}
			}
			fis = new FileOutputStream(file);
			book.write(fis);
			fis.flush();
		} catch (Exception e) {
			log.error("",e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					log.error("",e);
				}
			}
		}
		return file;
	}
	
	/**
	 * 创建多表头
	 * @param title
	 * @param mainTitleList 标题文字集合
	 * @param footTitle 页脚文字
	 * @param columnTitles 多表头
	 * @param data 数据
	 * @param configMap 配置信息， 可为空
	 * 		  	MainTitle1Style(主标题样式):宋体(字体样式),11(字体大小),right(对齐方式),20(行高),B(粗体)#I(斜体)#U(下划线)
	 * 			MainTitle2Style(副标题1样式)
	 * 			MainTitle3Style(副标题2样式)
	 * 			TitleStyle(表头样式)
	 * 			FootTitleStyle(页脚样式)
	 * 			DataStyle(数据样式):宋体(字体样式),11(字体大小),20(行高)
	 * 			DataWidth(数据宽度):20,10,25,30
	 * 			DataAlign(数据对齐方式):center,left,right,center
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static File mulitHeaderCreate(String title, List<String> mainTitleList, String footTitle, List<String[]> columnTitles, Collection data, Map<String, Object> configMap) {
		File file = null;
		HSSFWorkbook book = null;
		FileOutputStream fis = null;
		try {
			String path = ExcelUtils.class.getClassLoader().getResource("/").getPath() + title + ".xls";
			path = path.replace("%20", " ");
			file = new File(path);			
			book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet(title);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			int rowIndex = 0;
			Row excelRow = null;
			Cell cell = null;
			CellRangeAddress cra = null;
			HSSFCellStyle titleCellStyle = null;
			
			//15.625 表示行高为一个像素
			//35.7 表示宽度度为一个像素
			short h = (short) 15.625;
			
			// 如果表头不为空，则写入表头
			if (columnTitles != null) {
				String[] ttHeader = columnTitles.get(0);
				int columnLen = ttHeader.length; //列个数
				
				//添加主标题
				if (mainTitleList != null && !mainTitleList.isEmpty()) {
					for (int i=0; i<mainTitleList.size(); i++) {
						String titleStyle = (String) configMap.get("MainTitle" + (i+1) + "Style");
						titleCellStyle = getTitleCellStyle(book, titleStyle);
						Integer titleHeight = ObjNumberUtils.toInteger(getConfigStr(titleStyle, 3));
						excelRow = sheet.createRow(rowIndex);
						if (titleHeight != null) {
							excelRow.setHeight((short) ((short) h*titleHeight));
						}
						cra = new CellRangeAddress(rowIndex, rowIndex, 0, columnLen-1);
						sheet.addMergedRegion(cra);
						
						cell = excelRow.createCell(0);
						initCell(cell, mainTitleList.get(i), titleCellStyle);
						for (int j=1; j<=columnLen-1; j++) {
							cell = excelRow.createCell(j);
							initCell(cell, "", titleCellStyle);
						}
						rowIndex ++;
					}
				}
				
				String titleStyle = (String) configMap.get("TitleStyle");
				Integer titleHeight = ObjNumberUtils.toInteger(getConfigStr(titleStyle, 3));
				titleCellStyle = getTitleCellStyle(book, titleStyle);
				String dataWidthStr = (String) configMap.get("DataWidth");
				if (dataWidthStr != null && !"".equals(dataWidthStr)) {
					String[] dsArr = dataWidthStr.split(",");
					if (dsArr.length >= columnLen) {
						int ssWidth = 0;
						for (int i=0; i<columnLen; i++) {
							ssWidth = ObjNumberUtils.toInt(dsArr[i]);
							if (ssWidth > 0) {
								sheet.setColumnWidth(i, (int) (35.7*ssWidth));
								//sheet.setColumnWidth(i, (int) (1*ssWidth));
							} else {
								sheet.setColumnWidth(i, (int) (35.7*120));
							}
						}
					} 
				}
				if (StringUtils.isEmpty(dataWidthStr) || dataWidthStr.split(",").length < columnLen) {
					// 设置默认宽度
					for (int i=0; i<columnLen; i++) {
						sheet.setColumnWidth(i, (int) (35.7*120));
					}
				}
				
				// 当标题行只有一行时，不需要解析，直接顺序显示即可
				if (columnTitles.size() == 1) {
					String[] tempHeads = columnTitles.get(0);
					excelRow = sheet.createRow(rowIndex);
					if (titleHeight != null) {
						excelRow.setHeight((short) ((short) h*titleHeight));
					}
					for (int i = 0; i < tempHeads.length; i++) {
						cell = excelRow.createCell(i);
						initCell(cell, tempHeads[i], titleCellStyle);
					}
					rowIndex++;
				} else {// 如果是多表头的时候，就需要解析合并表头
					for (int i = 0; i < columnTitles.size(); i++) {
						excelRow = sheet.createRow(rowIndex);
						if (titleHeight != null) {
							excelRow.setHeight((short) ((short) h*titleHeight));
						}
						String[] tempHeads = columnTitles.get(i);
						for (int j = 0; j < tempHeads.length; j++) {
							cell = excelRow.createCell(j);
							if (CSPAN.equals(tempHeads[j])) {
								int preColIndex = j - 1;
								preColIndex = preColIndex < 0 ? 0 : preColIndex;
								if (!CSPAN.equals(tempHeads[preColIndex])) {
									int nextColIndex = j;
									while (nextColIndex < tempHeads.length && CSPAN.equals(tempHeads[nextColIndex])) {
										nextColIndex++;
									}
									cra = new CellRangeAddress(rowIndex+i, rowIndex+i, preColIndex, nextColIndex - 1);
									sheet.addMergedRegion(cra);
									initCell(cell, tempHeads[j], titleCellStyle);
								}
							} else if (RSPAN.equals(tempHeads[j])) {
								int preRowIndex = i - 1;
								preRowIndex = preRowIndex < 0 ? 0 : preRowIndex;
								if (!RSPAN.equals(columnTitles.get(preRowIndex)[j])) {
									int nextRowIndex = i;
									while (nextRowIndex < columnTitles.size() && RSPAN.equals(columnTitles.get(nextRowIndex)[j])) {
										nextRowIndex++;
									}
									cra = new CellRangeAddress(rowIndex+preRowIndex-1, rowIndex+nextRowIndex-2, j, j);
									sheet.addMergedRegion(cra);
									
									initCell(cell, tempHeads[j], titleCellStyle);
								}
							} else {
								initCell(cell, tempHeads[j], titleCellStyle);
							}
						}
						rowIndex++;
					}
				}
			}
			
			// 写入数据
			String dataConfigStr = (String) configMap.get("DataStyle");
			String dataFont = getConfigStr(dataConfigStr, 0);
			Integer dataFontSize = ObjNumberUtils.toInteger(getConfigStr(dataConfigStr, 1));
			Integer dataHeight = ObjNumberUtils.toInteger(getConfigStr(dataConfigStr, 2));			
			String vertical = getConfigStr(dataConfigStr, 5);
			HSSFCellStyle dataCellStyle = null;
			Iterator it = data.iterator();
			dataCellStyle = getCellStyle(book, dataFont, dataFontSize, null);			
			if(!StringUtils.isEmpty(vertical) && NumberUtils.isNumber(vertical)){
				dataCellStyle.setVerticalAlignment(Short.parseShort(vertical));
			}
			while (it.hasNext()) {
				Object[] row = (Object[]) it.next();
				excelRow = sheet.createRow(rowIndex++);
				if (dataHeight != null) {
					excelRow.setHeight((short) ((short) h*dataHeight));
				}
				int colIndex = 0;
				for (Object cellValue : row) {
					cell = excelRow.createCell(colIndex);
					initCell(cell, cellValue, dataCellStyle);

					colIndex++;
				}
			}
			if (columnTitles != null && footTitle != null && !"".equals(footTitle)) {
				String[] ttHeader = columnTitles.get(0);
				int columnLen = ttHeader.length; //列个数
				String footTitleStyle = (String) configMap.get("FootTitleStyle");
				titleCellStyle = getTitleCellStyle(book, footTitleStyle);
				//添加页脚
				Integer titleHeight = ObjNumberUtils.toInteger(getConfigStr(footTitleStyle, 3));
				excelRow = sheet.createRow(rowIndex);
				if (titleHeight != null) {
					excelRow.setHeight((short) ((short) h*titleHeight));
				}
				cra = new CellRangeAddress(rowIndex, rowIndex, 0, columnLen-1);
				sheet.addMergedRegion(cra);

				cell = excelRow.createCell(0);
				initCell(cell, footTitle, titleCellStyle);
				for (int j=1; j<=columnLen-1; j++) {
					cell = excelRow.createCell(j);
					initCell(cell, "", titleCellStyle);
				}
				rowIndex ++;
			}
			fis = new FileOutputStream(file);
			book.write(fis);
			fis.flush();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					log.error("",e);
				}
			}
		}
		return file;
	}
	
	/**
	 * 创建Excel单元格，根据数据的不同类型，创建不同类型的单元格
	 * @param cell
	 * @param value
	 * @param cellStyle
	 */
	public static void initCell(Cell cell, Object value, HSSFCellStyle cellStyle) {
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		if (value instanceof Number) {
			if (value != null) {
				cell.setCellValue(String.valueOf(value));
			}
		}
		if (value instanceof Date || value instanceof Timestamp || value instanceof java.sql.Date) {
			if (value != null) {
				cell.setCellValue(DateUtil.formatDateToStringYY_MM_DD((Date) value));
			}
		}
		if (value instanceof String) {
			if (value != null) {
				cell.setCellValue(String.valueOf(value).trim());
			}
		}
	}


	/**
	 * 根据数据的长度设置列的宽度
	 */
	public static void setColumnWidth(HSSFSheet sheet, int colIndex, Object value) {
		if (value == null) {
			return;
		}
		int minLen = 2800;
		int maxLen = 256 * 150;
		int len = (value.toString().getBytes().length+1) * 512;
		if (len < minLen) {
			len = minLen;
		}
		if(len > maxLen){
			len = maxLen;
		}
		//参数width的单位是1/256个字符宽度，设置宽度的时候，一个字符长度要乘以256
		sheet.setColumnWidth(colIndex, len);
	}
	
	/**
	 * 设置单元格的背景颜色
	 * @param sheet
	 * @param rowCelIndexList
	 * @param backgroudColrList
	 */
	private static void setCellBackgroundColor(HSSFSheet sheet, List<List<int[]>> rowCelIndexList, List<Short> backgroudColrList) {
		if (rowCelIndexList == null || rowCelIndexList.isEmpty() || backgroudColrList == null || backgroudColrList.isEmpty()) {
			return;
		}
		Cell cell = null;
		// 如果表头不为空，则写入表头
		Row excelRow = null;
		int i = 0;
		for (List<int[]> indexList : rowCelIndexList) {
			HSSFCellStyle style = getCellStyle(sheet.getWorkbook());
			style.setFillForegroundColor(backgroudColrList.get(i++));// 设置背景色
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			for (int[] rowCell : indexList) {
				excelRow = sheet.getRow(rowCell[0]);
				cell = excelRow.getCell(rowCell[1]);
				cell.setCellStyle(style);
			}
		}
	}

	/**
	 * 获取表头样式
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle getTitleCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		//设置背景色：
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);

		//四、设置字体:
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);//设置字体大小
		style.setFont(font);//选择需要用到的字体格式
		style.setWrapText(true);//设置自动换行
		return style;
	}
	
	/**
	 * 获取表头样式
	 * @param wb
	 * @param configStr 宋体(字体样式),11(字体大小),right(对齐方式),20(行高),B(粗体)#I(斜体)#U(下划线),0顶端对齐(1中心对齐,2下端对齐,3垂直对齐)
	 * @return
	 */
	public static HSSFCellStyle getTitleCellStyle(HSSFWorkbook wb, String configStr) {
		if (configStr == null || "".equals(configStr)) {
			return getTitleCellStyle(wb);
		}
		String[] configArr = configStr.split(",");
		if (configArr.length < 5) {
			return getTitleCellStyle(wb);
		}
		String fontName = configArr[0];
		short fontSize = (short) ObjNumberUtils.toInt(configArr[1]);
		String align = configArr[2];
//		short height = (short) ObjNumberUtils.toInt(configArr[3]);
		String biu = configArr[4];
		
		HSSFCellStyle style = wb.createCellStyle();
		//设置背景色：
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//对齐方式
		if ("left".equals(align)) {
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		} else if ("right".equals(align)) {
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		} else {
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		
		//四、设置字体:
		HSSFFont font = wb.createFont();
		font.setFontName(fontName);
		font.setFontHeightInPoints((short) fontSize);//设置字体大小
		if (biu.indexOf("B") >= 0) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		if (biu.indexOf("N") >= 0) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		}
		if (biu.indexOf("I") >= 0) {
			font.setItalic(true);
		}
		if (biu.indexOf("U") >= 0) {
			font.setUnderline(FontFormatting.U_SINGLE);
		}
		
		//五、垂直方式
		if(configArr.length >= 6){
			Short vertical = Short.valueOf(configArr[5]);
			style.setVerticalAlignment(vertical);
		}
		
		style.setFont(font);//选择需要用到的字体格式
		style.setWrapText(true);//设置自动换行
		return style;
	}
	
	private static String getConfigStr(String configStr, int index) {
		if (configStr == null || "".equals(configStr)) {
			return null;
		}
		String[] configArr = configStr.split(",");
		if (configArr.length < index+1) {
			return null;
		}
		return configArr[index];
	}

	/**
	 * 获取表格样式
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle getCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);

		//四、设置字体:
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);//设置字体大小
		style.setFont(font);//选择需要用到的字体格式
		style.setWrapText(true);//设置自动换行
		
		//设置文本样式
//		DataFormat  format = wb.createDataFormat();
//		style.setDataFormat(format.getFormat("General"));

		return style;
	}
	
	/**
	 * 获取表格样式
	 * @param wb
	 * @param fontName
	 * @param fontSize
	 * @param align
	 * @return
	 */
	public static HSSFCellStyle getCellStyle(HSSFWorkbook wb, String fontName, Integer fontSize, String align) {
		HSSFCellStyle style = wb.createCellStyle();
		if ("left".equals(align)) {
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		} else if ("right".equals(align)) {
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		} else {
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);

		//四、设置字体:
		HSSFFont font = wb.createFont();
		if (fontName == null || "".equals(fontName)) {
			font.setFontName(fontName);
		} else {
			font.setFontName("宋体");
		}
		if (fontSize == null || fontSize.intValue() < 0) {
			font.setFontHeightInPoints((short) 11);
		} else {
			font.setFontHeightInPoints((short) fontSize.intValue());
		}
		style.setFont(font);//选择需要用到的字体格式
		style.setWrapText(true);//设置自动换行

		return style;
	}
	
	/**
	 * 多表头合并Excel生成demo
	 */
	private static void mulExcelDemo() throws Exception {
		String title = "多表头合并Excel";
		List<String> mainTitleList = new ArrayList<String>();
		mainTitleList.add("XXXXCCC国际学校丽泽分校年级统计表");
		mainTitleList.add("学年度：2015年6月-2016年9月");
		String footTitle = "供应商：XX发展有限公司";
		
		List<String[]> columnTitles = new ArrayList<String[]>();
		columnTitles.add(new String[]{"序号", "成绩", "#cspan", "#cspan", "等级", "#cspan"});
		columnTitles.add(new String[]{"#rspan", "BMI", "引体向上", "肺活量", "等级1", "等级2"});
		
		List<String[]> dataList = new ArrayList<String[]>();
		
		for(int i=1; i<12000; i++){
			dataList.add(new String[]{String.valueOf(i), "100", "101", "102", "103", "104"});
		}
		
		
		/*dataList.add(new String[]{"1", "100", "101", "102", "103", "104"});
		dataList.add(new String[]{"2", "200", "201", "202", "203", "204"});
		dataList.add(new String[]{"3", "300", "301", "302", "303", "304"});
		dataList.add(new String[]{"4", "400", "401", "402", "403", "404"});
		dataList.add(new String[]{"5", "500", "501", "502", "503", "504"});*/
		
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put("MainTitle1Style", "宋体,18,center,60,B");
		configMap.put("MainTitle2Style", "华文新魏,11,right,20,B#I");
		configMap.put("TitleStyle", "宋体,12,center,20,B");
		configMap.put("FootTitleStyle", "宋体,11,left,20,B#I");
		configMap.put("DataStyle", "宋体,10,20");
		configMap.put("DataWidth", "120,240,240,300,160,200");
		
		File file = mulitHeaderCreate(title, mainTitleList, footTitle, columnTitles, dataList, configMap);
		file.createNewFile();
	}
	
	/**
	 * 简单Excel demo
	 */
	private static void simpleExcelDemo() throws Exception {
		String title = "简单Excel";
		String[] columnTitles = {"表头1", "表头2", "表头3"};
		List<String[]> dataList = new ArrayList<String[]>();
		String[] data = null;
		for (int i=0; i<5; i++) {
			int j = 1;
			data = new String[3];
			data[0] = "String_" + (j++) + "_" + i;
			data[1] = "String_" + (j++) + "_" + i;
			data[2] = "String_" + (j++) + "_" + i;
			
			dataList.add(data);
		}
		List<List<int[]>> rowCelIndexList = new ArrayList<>();
        List<Short> backgroudColrList = new ArrayList<>();
        
    	List<int[]> cellList1 = new ArrayList<>();
    	cellList1.add(new int[]{1,2});
    	cellList1.add(new int[]{2,2});
    	rowCelIndexList.add(cellList1);
    	backgroudColrList.add(HSSFColor.GREEN.index);
    	
    	List<int[]> cellList2 = new ArrayList<>();
    	cellList2.add(new int[]{4,2});
    	cellList2.add(new int[]{3,2});
    	cellList2.add(new int[]{3,1});
    	rowCelIndexList.add(cellList2);
    	backgroudColrList.add(HSSFColor.YELLOW.index);
		
//		File file = ExcelWriter.simpleCreate(title, columnTitles, dataList, rowCelIndexList, backgroudColrList);
//		file.createNewFile();
	}
	
    public static String getTitile(String title) {
        return title + DateUtil.formatDateToString(new Date(), DateUtil.FORMAT_YYYYMMDDHHMMSS);
    }
	
    //判断是否继续进行查询
    public static Boolean judgePage(Integer length ,Integer pageNo){
        return length/1000 > pageNo ? true :false ;
    }
    
	public static void main(String[] args) throws Exception {
		simpleExcelDemo(); //简单Excel demo
		mulExcelDemo(); //多表头合并Excel生成demo
		
	}
	
}
