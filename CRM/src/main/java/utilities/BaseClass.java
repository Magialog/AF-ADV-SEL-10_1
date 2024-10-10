package utilities;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {
	
	public DriverUtility driverUtil = new DriverUtility();
	public LoginPage loginPage;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Established connections to the Database");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Pre-condition established");
	}
	
	@BeforeClass
	public void beforeClass() throws IOException {
		
		driverUtil.openBrowserInstance();
		Reporter.log(driverUtil.getBrowserName() + " instance created.", true);
		driverUtil.implicitWait(driverUtil.getWebDriver());
		
	}
	
	@BeforeMethod
	public void beforeMethod() throws IOException {
		
		loginPage = new LoginPage(driverUtil.getWebDriver());
		loginPage.fetchURL();
		loginPage.loginAction();
		Reporter.log("URL navigation phase passed", true);
	
	}
	
	@AfterMethod
	public void afterMethod() {
		
		HomePage homePage = new HomePage(driverUtil.getWebDriver());
		homePage.logoutAction();
		driverUtil.untilPageLoads(loginPage.getUserNameTextField())/*.quit()*/;
		Reporter.log("Logout phase passed", true);
		Reporter.log("Script terminated successfully", true);
		
	}
	
	@AfterClass
	public void afterClass() {
		driverUtil.quit();
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("Post-condition established");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("Connection to the database terminated");
	}

}