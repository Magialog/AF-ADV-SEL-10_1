package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	private String filePath = "./externalResources/";
	private String fileName;
	private FileInputStream inputStream;
	private FileOutputStream outputStream;
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;
	
	public String getCurrentFilePath() {
		return filePath;
	}

	public String getCurrentFileName() {
		return fileName;
	}

	private void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private void setInputStream(FileInputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	private void setOutputStream(FileOutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public ExcelUtility changeFilePath(String filePath) throws EncryptedDocumentException, IOException {
		
		setInputStream(new FileInputStream(filePath + "/" + fileName + ".xlsx"));
		System.out.println(filePath + "/" + fileName + ".xlsx");
		setWorkbook(WorkbookFactory.create(inputStream));
		return this;
	
	}

	public ExcelUtility changeFileName(String fileName) throws EncryptedDocumentException, IOException {
		
		setInputStream(new FileInputStream(filePath + "/" + fileName + ".xlsx"));
		setWorkbook(WorkbookFactory.create(inputStream));
		return this;
	
	}
	
	private void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public Sheet getCurrentSheetName() {
		return sheet;
	}

	public ExcelUtility changeSheetName(String sheetName) {
		
		this.sheet = workbook.getSheet(sheetName);
		return this;
	
	}

	public Row getCurrentRowNumber() {
		return row;
	}

	public ExcelUtility changeRowNumber(int rowNumber) {
		
		this.row = sheet.getRow(rowNumber);
		return this;
		
	}

	public Cell getCurrentCellNumber() {
		return cell;
	}

	public ExcelUtility changeCellNumber(int cellNumber) {
		
		this.cell = row.getCell(cellNumber);
		return this;
	}

	public ExcelUtility writeToSheet(String filePath, String fileName, String sheetName, int rowNumber,
        int cellNumber, String data) 
        throws EncryptedDocumentException, IOException {
		
		setFilePath(filePath);
		setFileName(fileName);
		
		setInputStream(new FileInputStream(filePath + "/" + fileName + ".xlsx"));
		workbook = WorkbookFactory.create(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.createCell(cellNumber);
		cell.setCellValue(data);
		
		setOutputStream(new FileOutputStream(filePath + "/" + fileName + ".xlsx"));
		workbook.write(outputStream);
		
		return this;
		
	}
	
	public ExcelUtility writeToSheet(String fileName, String sheetName, int rowNumber, int cellNumber, String data) throws EncryptedDocumentException, IOException {
		
		setFileName(fileName);
		
		setInputStream(new FileInputStream("./externalResources/" + fileName + ".xlsx"));
		workbook = WorkbookFactory.create(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.createCell(cellNumber);
		cell.setCellValue(data);
		
		setOutputStream(new FileOutputStream("./externalResources/" + fileName + ".xlsx"));
		workbook.write(outputStream);
		
		return this;
		
	}
	
	public String readCellValue() {
		return cell.getStringCellValue();
	}
	
	public String readCellValue(String fileName, String sheetName, int rowNumber, int cellNumber) throws EncryptedDocumentException, IOException {
			
		FileInputStream inputStream = new FileInputStream("./externalResources/" + fileName + ".xlsx");
		workbook = WorkbookFactory.create(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);
		
		return cell.getStringCellValue();
		
	}
	
	public String readCellValue(String filePath, String fileName, String sheetName, int rowNumber, int cellNumber) throws EncryptedDocumentException, IOException {
		
		FileInputStream inputStream = new FileInputStream(filePath + "/" + fileName + ".xlsx");
		workbook = WorkbookFactory.create(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);
		
		return cell.getStringCellValue();
		
	}
	
	public String readCurrentCellValue() {
		return cell.getStringCellValue();
	}
	
	public int getRowCount() {
		return sheet.getLastRowNum();
	}
	
	public void close() throws IOException {
		workbook.close();
	}

}