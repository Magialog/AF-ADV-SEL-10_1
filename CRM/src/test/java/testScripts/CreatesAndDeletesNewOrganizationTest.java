package testScripts;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import objectRepository.HomePage;
//import objectRepository.LoginPage;
import objectRepository.OrganizationsCreatePage;
import objectRepository.OrganizationsInformationPage;
import objectRepository.OrganizationsPage;
import utilities.BaseClass;
//import utilities.DriverUtility;

public class CreatesAndDeletesNewOrganizationTest extends BaseClass {
	
	@Test
	public void createAndDeleteOrganization() throws IOException {
		
//		DriverUtility driverUtil = new DriverUtility();
		
//		driverUtil.openBrowserInstance();
//		Reporter.log(driverUtil.getBrowserName() + " instance creation phase passed", true);
		
//		LoginPage loginPage = new LoginPage(driverUtil.getWebDriver());
		HomePage homePage = new HomePage(driverUtil.getWebDriver());
		OrganizationsPage organizationsPage = new OrganizationsPage(driverUtil.getWebDriver());
		OrganizationsCreatePage organizationsCreatePage = new OrganizationsCreatePage(driverUtil.getWebDriver());
		OrganizationsInformationPage organizationsInformationPage = new OrganizationsInformationPage(driverUtil.getWebDriver());
		
//		driverUtil.implicitWait(driverUtil.getWebDriver());
		
//		loginPage.fetchURL();
		
//		loginPage.loginAction();
//		Reporter.log("URL navigation phase passed", true);
		
		homePage.getOrganizationTab().click();
		Reporter.log("Login phase passed", true);
		
		organizationsPage.getCreateOrganizationIcon().click();
		Reporter.log("Organization tab action phase passed", true);
		
		organizationsCreatePage.fillAndSubmitOrganizationDetailsAction();
		Reporter.log("Create organization icon action phase passed", true);
		
		organizationsInformationPage.deleteCreatedOrganizationAction();
		Reporter.log("Data entry phase passed", true);
		
//		homePage.logoutAction();
//		Reporter.log("Entry deletion phase passed", true);
		
//		driverUtil.untilPageLoads(loginPage.getUserNameTextField()).quit();
//		Reporter.log("Logout phase passed", true);
//		Reporter.log("Script terminated successfully", true);
//		System.out.println("----------------------------------");
		
	}
	
}