package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;
	
	public OrganizationsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = "img[title = 'Create Organization...']")
	private WebElement createOrganizationIcon;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCreateOrganizationIcon() {
		return createOrganizationIcon;
	}
	
}