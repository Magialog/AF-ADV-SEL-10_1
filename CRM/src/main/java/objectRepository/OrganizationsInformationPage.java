package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsInformationPage {

	WebDriver driver;
	
	public OrganizationsInformationPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name = "Delete")
	private WebElement deleteButton;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}
	
	public void deleteCreatedOrganizationAction() {
		
		deleteButton.click();
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		
	}
}