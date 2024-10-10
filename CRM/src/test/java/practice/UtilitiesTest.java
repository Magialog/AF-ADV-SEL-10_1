package practice;

import java.io.IOException;
import java.util.ArrayList;

import utilities.CoreUtility;
import utilities.ExcelUtility;
import utilities.PropertiesUtility;

public class UtilitiesTest {

	public static void main(String[] args) throws IOException {
//		 TODO Auto-generated method stub
		
		PropertiesUtility properties = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		CoreUtility core = new CoreUtility();
		ArrayList<String> keys = new ArrayList<String>();
		
		keys.add("key2");
		keys.add("key1");
		
		System.out.println(properties.load("./externalResources", "dummyConfig")
				  .getKeys());
		System.out.println(properties.getValues());
		System.out.println(properties.getSize());
		properties.print();
		
		properties.changeFileName("vtigerConfig").print();
		
		System.out.println(excel.readCellValue("mobileTestData", "Availablity", 1, 0));
		excel.changeCellNumber(1);
		System.out.println(excel.changeSheetName("Mobile").changeRowNumber(1)
										.changeCellNumber(1).readCellValue());
										
		
		System.out.println(excel.changeFileName("vtigerTestData").changeSheetName("Campaign")
		     .changeRowNumber(1).changeCellNumber(0).readCellValue());
		
		System.out.println(core.today());
		
	}

}