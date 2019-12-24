package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static Logger log = Logger.getLogger("devpinoyLogger");
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fout = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public ExcelReader(String path) {
		this.path = path;

		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			log.error("Exception occure while fetching Excel Sheet from path -" + path, e);
		}
	}

	// returns row count in a sheet
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return 0;
		} else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {

			// ----- row number should be greater than 0
			if (rowNum <= 0)
				return "";

			// ----- sheet name should be present in the sheet
			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;

			if (index == -1)
				return "";

			// ----- get cell number by iterating first row
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
					col_Num = i;
				}
			}

			if (col_Num == -1)
				return "";

			// Get cell from row and cell number in sheet
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";

			cell = row.getCell(col_Num);

			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();

			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());

				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);

					cellText = cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
				}
				return cellText;

			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}
		} catch (Exception e) {
			log.error("Exception occure while retriving the data from sheet" + sheetName, e);
			return "row " + rowNum + " or Column " + colName + " does not exists in xls.";
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {

			// ----- row number should be greater than 0
			if (rowNum <= 0)
				return "";

			// ----- sheet name should be present in the sheet
			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			// ----- get cell number by iterating first row
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);

			if (row == null)
				return "";

			cell = row.getCell(colNum);

			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();

			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());

				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);

					cellText = cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
				}
				return cellText;

			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}
		} catch (Exception e) {
			log.error("Exception occure while retriving the data from sheet" + sheetName);
			return "row " + rowNum + " or Column " + colNum + " does not exists in xls.";
		}
	}

	// returns true if sheet is created successfully else false
	public boolean addShhet(String sheetName) {
		FileOutputStream fileOut;

		try {
			workbook.createSheet(sheetName);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			return true;

		} catch (Exception e) {
			log.error("Exception occure while creating new sheet in file", e);
			return false;
		}
	}

	// returns true if the sheet is removed successfully
	public boolean removeSheet(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return false;
		}

		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			return true;

		} catch (Exception e) {
			log.error("Exception occure while creating new sheet in file" + e);
			return false;
		}
	}

	// returns true if column is created successfully
	public boolean addColumn(String sheetName, String colName) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);

			if (row == null)
				row = sheet.createRow(0);

			if (row.getLastCellNum() == -1) {
				cell = row.createCell(0);
			} else {
				cell = row.createCell(row.getLastCellNum());

				cell.setCellValue(colName);
				cell.setCellStyle(style);

				fout = new FileOutputStream(path);
				workbook.write(fout);
				fout.close();
			}
		} catch (Exception e) {
			log.error("Exception occure while adding column" + colName + " in the sheet " + sheetName, e);
			return false;
		}
		return true;
	}

	// returns true if column is created successfully
	public boolean addColumn(String sheetName, int colNum) {
		try {

			if (!isSheetExist(sheetName))
				return false;

			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			XSSFCreationHelper createHelper = workbook.getCreationHelper();
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);

				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
				fout = new FileOutputStream(path);
				workbook.write(fout);
				fout.close();
			}
		} catch (Exception e) {
			log.error("Exception occure while adding column" + colNum + " in the sheet " + sheetName, e);
			return false;
		}
		return true;
	}

	private boolean isSheetExist(String sheetName) {
		// TODO Auto-generated method stub
		return false;
	}

}
