package utilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtility {

	private int seconds = 20;
	private String browserName = "firefox";
	private WebDriver webDriver;
	private WebElement webElement;
	private Actions actions;
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
	
	public String getBrowserName() {
		return browserName;
	}

	private void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	private void setWebElement(WebElement webElement) {
		this.webElement = webElement;
	}

	private void setActions(Actions actions) {
		this.actions = actions;
	}
	
	public DriverUtility openBrowserInstance() throws IOException {
		
		PropertiesUtility propertiesUtil = new PropertiesUtility();
		propertiesUtil.load("vtigerConfig");
		
		switch(propertiesUtil.getValue("browser").trim().toLowerCase()) {
		
			case "firefox" : webDriver = new FirefoxDriver();
							 browserName = "firefox";
						 	 break;
						 
			case "chrome" : webDriver = new ChromeDriver();
							browserName = "chrome";
							break;
							
			case "edge" : webDriver = new EdgeDriver();
						  browserName = "edge";
						  break;
						  
			default : webDriver = new FirefoxDriver();
								  browserName = "firefox";
		
		}
		
		webDriver.manage().window().maximize();
		return this;
		
	}
	
	public DriverUtility get(String URL) {
		
		webDriver.get(URL);
		return this;
		
	}

	public DriverUtility implicitWait(WebDriver webDriver) {
		
		setWebDriver(webDriver);
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		setSeconds(20);
		return this;
		
	}
	
	public DriverUtility implicitWait(WebDriver webDriver, int seconds) {
		
		setWebDriver(webDriver);
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		setSeconds(seconds);
		return this;
		
	}
	
	public DriverUtility untilPageLoads(WebElement webElement) {
		
		setWebElement(webElement);
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(webElement));
		return this;
		
	}
	
	public DriverUtility switchToFrame(WebDriver webDriver, int index) {
		
		setWebDriver(webDriver);
		webDriver.switchTo().frame(index);
		return this;
		
	}
	
	public DriverUtility switchToFrame(WebDriver webDriver, String idOrValue) {
		
		setWebDriver(webDriver);
		webDriver.switchTo().frame(idOrValue);
		return this;
		
	}
	
	public DriverUtility switchBackToImmediateParent(WebDriver webDriver) {
		
		setWebDriver(webDriver);
		webDriver.switchTo().parentFrame();
		return this;
		
	}
	
	public DriverUtility switchBackToRootWindow(WebDriver webDriver) {
		
		setWebDriver(webDriver);
		webDriver.switchTo().defaultContent();
		return this;
		
	}
	
	public DriverUtility acceptAlert() {
		
		webDriver.switchTo().alert().accept();
		return this;
		
	}
	
	public DriverUtility dismissAlert() {
		
		webDriver.switchTo().alert().dismiss();
		return this;
		
	}
	
	public DriverUtility moveToElement(WebElement webElement) {
		
		setActions(new Actions(webDriver));
		setWebElement(webElement);
		actions.moveToElement(webElement).perform();
		return this;
		
	}
	
	public DriverUtility click(WebElement webElement) {
		
		setActions(new Actions(webDriver));
		setWebElement(webElement);
		actions.click(webElement).perform();
		return this;
		
	}
	
	public void quit() {
		webDriver.quit();
	}
	
	public String getText() {
		return webElement.getText();
	}

}