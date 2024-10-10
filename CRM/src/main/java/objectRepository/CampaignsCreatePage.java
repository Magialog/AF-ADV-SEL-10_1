package objectRepository;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CoreUtility;
import utilities.ExcelUtility;

public class CampaignsCreatePage {
	
	WebDriver driver;
	
	public CampaignsCreatePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name = "campaignname")
	private WebElement campaignNameTextField;
	
	@FindBy(name="closingdate")
	private WebElement expectedClosingDateTextField;
	
	@FindBy(xpath = "//input[contains(@value, 'Save')]")
	private WebElement saveButton;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCampaignNameTextField() {
		return campaignNameTextField;
	}

	public WebElement getExpectedClosingDateTextField() {
		return expectedClosingDateTextField;
	}
	
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void fillAndSubmitCampaignDetailsAction() throws EncryptedDocumentException, IOException {
		
		ExcelUtility excel = new ExcelUtility();
		CoreUtility core = new CoreUtility();
		
		excel.changeFileName("vtigerTestData").changeSheetName("Campaign")
	     .changeRowNumber(0).changeCellNumber(0);
	
		String campaignName = excel.readCellValue("vtigerTestData", "Campaign", 
			(int) ((Math.random() * excel.getRowCount()) + 1), 0) + core.genNum();
		
		campaignNameTextField.sendKeys(campaignName);
		expectedClosingDateTextField.clear();
		expectedClosingDateTextField.sendKeys(core.addDaysToToday(30));
		saveButton.click();
		
	}

}