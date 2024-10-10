package objectRepository;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverUtility;
import utilities.PropertiesUtility;

public class LoginPage {
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name = "user_name")
	private WebElement userNameTextField;
	
	@FindBy(name = "user_password")
	private WebElement passwordTextField;
	
	@FindBy(id = "submitButton")
	private WebElement submitButton;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getUserNameTextField() {
		return userNameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	public void fetchURL() throws IOException {
		
		PropertiesUtility properties = new PropertiesUtility();
		DriverUtility driverUtil = new DriverUtility();
		driverUtil.setWebDriver(driver);
		driver.get(properties.load("vtigerConfig").getValue("url"));
		
	}
	
	public void loginAction() throws IOException {
		
		PropertiesUtility properties = new PropertiesUtility();
		
		properties.load("vtigerConfig");
		userNameTextField.sendKeys(properties.getValue("userName"));
		passwordTextField.sendKeys(properties.getValue("password"));
		submitButton.click();
		
	}
	
}