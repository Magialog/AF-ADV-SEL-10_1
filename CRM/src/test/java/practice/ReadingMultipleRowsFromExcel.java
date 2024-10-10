package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadingMultipleRowsFromExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream isteam = new FileInputStream("./externalResources/mobileTestData.xlsx");
		Sheet mobileWorksheet = WorkbookFactory.create(isteam).getSheet("Mobile");
		
		for(int row = 1; row <= mobileWorksheet.getLastRowNum(); row++)
			System.out.println(mobileWorksheet.getRow(row).getCell(0).getStringCellValue() + "\t"
					+ mobileWorksheet.getRow(row).getCell(1).getStringCellValue());
				
	}

}