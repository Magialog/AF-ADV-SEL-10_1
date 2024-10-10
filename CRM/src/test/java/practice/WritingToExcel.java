package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WritingToExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream istream = new FileInputStream("./externalResources/mobileTestData.xlsx");
		Workbook availStatus = WorkbookFactory.create(istream);
		
		Iterator <Row> itr = availStatus.getSheet("Availablity").rowIterator();
		
		while(itr.hasNext()) {
			
			int currentRow = itr.next().getRowNum();
			
			if(currentRow > 0) {
				
				availStatus.getSheet("Availablity")
	  			   .getRow(currentRow)
				   .getCell(2)
				   .setCellValue(Math.random() <= 0.5 ? true : false);
				
			}
			
		}
		
		// This is deprecated and not working too.
		// cellProps.setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
		
		FileOutputStream ostream = new FileOutputStream("./externalResources/mobileTestData.xlsx");
		availStatus.write(ostream);
		
		availStatus.close();

	}

}