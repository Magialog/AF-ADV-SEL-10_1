package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverUtility;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(linkText = "leads")
	private WebElement leadsTab;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationTab;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsTab;
	
	@FindBy(linkText = "More")
	private WebElement moreTab;
	
	@FindBy(name="Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(xpath = "//img[contains(@src, 'user.PNG')]")
	private WebElement userIcon;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLinkText;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getLeadsTab() {
		return leadsTab;
	}

	public WebElement getOrganizationTab() {
		return organizationTab;
	}

	public WebElement getContactsTab() {
		return contactsTab;
	}

	public WebElement getMoreTab() {
		return moreTab;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}
	
	public WebElement getUserIcon() {
		return userIcon;
	}
	
	public WebElement getSignOutLinkText() {
		return signOutLinkText;
	}
	
	public void logoutAction() {
		
		DriverUtility driverUtil = new DriverUtility();
		driverUtil.setWebDriver(driver);
		driverUtil.moveToElement(userIcon).untilPageLoads(signOutLinkText)
		.moveToElement(signOutLinkText).click(signOutLinkText);
		
	}
	
	public void navigateToCampaignsLinkText() {
		
		DriverUtility driverUtil = new DriverUtility();
		
		driverUtil.setWebDriver(driver);
		driverUtil.moveToElement(moreTab).untilPageLoads(campaignsLink)
		          .moveToElement(campaignsLink).click(campaignsLink);
		
	}

}