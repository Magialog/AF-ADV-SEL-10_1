package objectRepository;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CoreUtility;
import utilities.ExcelUtility;

public class OrganizationsCreatePage {

	WebDriver driver;
	
	public OrganizationsCreatePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name = "accountname")
	private WebElement organizationNameTextField;
	
	@FindBy(xpath = "//input[contains(@value, 'Save')]")
	private WebElement saveButton;
	
	public void fillAndSubmitOrganizationDetailsAction() throws EncryptedDocumentException, IOException {
		
		ExcelUtility excel = new ExcelUtility();
		CoreUtility core = new CoreUtility();
		
		excel.changeFileName("vtigerTestData").changeSheetName("Organization")
		     .changeRowNumber(0).changeCellNumber(0);
		
		String orgName = excel.readCellValue("vtigerTestData", "Organization", 
				(int) ((Math.random() * excel.getRowCount()) + 1), 0) + core.genNum();
		
		organizationNameTextField.sendKeys(orgName);
		saveButton.click();
		
	}
	
}