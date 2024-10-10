package testScripts;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import objectRepository.CampaignsCreatePage;
import objectRepository.CampaignsInformationPage;
import objectRepository.CampaignsPage;
import objectRepository.HomePage;
//import objectRepository.LoginPage;
import utilities.BaseClass;
//import utilities.DriverUtility;

public class CreatesAndDeletesNewCampaignTest extends BaseClass {

	@Test
	public void createAndDeleteOrganization() throws IOException {
		
//		DriverUtility driverUtil = new DriverUtility();
		
//		driverUtil.openBrowserInstance();
//		Reporter.log(driverUtil.getBrowserName() + " instance creation phase passed", true);
		
//		LoginPage loginPage = new LoginPage(driverUtil.getWebDriver());
		HomePage homePage = new HomePage(driverUtil.getWebDriver());
		CampaignsPage campaignsPage = new CampaignsPage(driverUtil.getWebDriver());
		CampaignsCreatePage campaignsCreatePage = new CampaignsCreatePage(driverUtil.getWebDriver());
		CampaignsInformationPage campaignsInformationPage = new CampaignsInformationPage(driverUtil.getWebDriver());
		
//		driverUtil.implicitWait(driverUtil.getWebDriver());
		
//		loginPage.fetchURL();
		
//		loginPage.loginAction();
//		Reporter.log("URL navigation phase passed", true);
		
		homePage.navigateToCampaignsLinkText();
		Reporter.log("Login phase passed", true);
		
		campaignsPage.getCreateCampaignIcon().click();
		Reporter.log("Campaign tab action phase passed", true);
		
		campaignsCreatePage.fillAndSubmitCampaignDetailsAction();
		Reporter.log("Create campaign icon action phase passed", true);
		
		campaignsInformationPage.deleteCreatedCampaignsAction();
		Reporter.log("Data entry phase passed", true);
		
//		homePage.logoutAction();
//		Reporter.log("Entry deletion phase passed", true);
		
//		driverUtil.untilPageLoads(loginPage.getUserNameTextField())/*.quit()*/;
//		Reporter.log("Logout phase passed", true);
//		Reporter.log("Script terminated successfully", true);
//		System.out.println("----------------------------------");
		
	}

}