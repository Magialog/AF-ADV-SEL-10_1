package assignments;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatingCampaignTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver = null;
		Random rand = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calendar = Calendar.getInstance();
		Date today = new Date(System.currentTimeMillis());
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		
		FileInputStream configPath = new FileInputStream("./externalResources/vtigerConfig.properties");
		FileInputStream testDataPath = new FileInputStream("./externalResources/vtigerTestData.xlsx");
		Properties vtigerConfig = new Properties();
		
		vtigerConfig.load(configPath);
		
		Workbook campaignSheet = WorkbookFactory.create(testDataPath);
		
		int TIMEOUT = Integer.parseInt(vtigerConfig.getProperty("timeout"));
		String BROWSER = vtigerConfig.getProperty("browser");
		String URL = vtigerConfig.getProperty("url");
		String USERNAME = vtigerConfig.getProperty("userName");
		String PASSWORD = vtigerConfig.getProperty("password");
		String CAMPAIGNNAME = campaignSheet.getSheet("Campaign")
				                           .getRow(rand.nextInt(campaignSheet.getSheet("Campaign")
				                           .getLastRowNum()) + 1)
				                           .getCell(0).getStringCellValue() + "_" +
				                           rand.nextInt(Integer.MAX_VALUE);
		String DATE = format.format(calendar.getTime());
		
		switch(BROWSER) {
			
			case "chrome" : driver = new ChromeDriver();
							break;
			
			case "firefox" : driver = new FirefoxDriver();
							 break;
							 
			case "edge" : driver = new EdgeDriver();
						  break;
						  
			default : driver = new FirefoxDriver();
			 		  break;
		
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		Actions action = new Actions(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		action.moveToElement(driver.findElement(By.linkText("More"))).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Campaigns")));
		action.moveToElement(driver.findElement(By.linkText("Campaigns"))).click().perform();
		
		driver.findElement(By.cssSelector("img[title = 'Create Campaign...']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(CAMPAIGNNAME);
		
		driver.findElement(By.name("closingdate")).clear();
		driver.findElement(By.name("closingdate")).sendKeys(DATE);
		driver.findElement(By.xpath("//input[contains(@value, 'Save')]")).click();
		
		System.out.println("Campaign creation status: " + 
		                  (driver.findElement(By.className("dvHeaderText")).getText()
		                		  .contains(driver.findElement(By.id("dtlview_Campaign Name"))
		                	      .getText()) ? "true" : "false"));
		
		driver.findElement(By.cssSelector("input[value='Delete']")).click();
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		
		action.moveToElement(driver.findElement(By.xpath("//img[contains(@src, 'user')]"))).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign Out")));
		action.moveToElement(driver.findElement(By.linkText("Sign Out")))
			  .click().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("loginForm")));
	  
		driver.quit();
		System.out.println("Script terminated successfully.");
	}

}