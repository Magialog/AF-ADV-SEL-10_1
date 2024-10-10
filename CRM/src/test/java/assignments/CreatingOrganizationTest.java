package assignments;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatingOrganizationTest {
			
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		FileInputStream configs = new FileInputStream("./externalResources/vtigerConfig.properties");
		Properties properties = new Properties();
		properties.load(configs);
		
		FileInputStream testData = new FileInputStream("./externalresources/vtigerTestData.xlsx");
		Workbook book = WorkbookFactory.create(testData);
		String ORGNAME = book.getSheet("Organization")
				         .getRow((int) (Math.random() * 
				         book.getSheet("Organization").getLastRowNum()) + 1)
				         .getCell(0).getStringCellValue() +
				         "_" + rand.nextInt(Integer.MAX_VALUE);
		book.close();
		
		WebDriver driver = null;
		String BROWSER = properties.getProperty("browser");
		String URL = properties.getProperty("url");
		String USERNAME = properties.getProperty("userName");
		String PASSWORD = properties.getProperty("password");
		byte TIMEOUT = Byte.parseByte(properties.getProperty("timeout"));
		
		switch(BROWSER) {
			
			case "chrome" : driver = new ChromeDriver();
							break;
							
			case "firefox" : driver = new FirefoxDriver();
							 break;
							 
			case "edge" : driver = new EdgeDriver();
						  break;
						  
//					case "opera" : driver = new OperaDriver();
//								   break;
							 
			default : driver = new FirefoxDriver(); 
		
		}
		
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
		
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.cssSelector("img[alt = 'Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.xpath("//input[contains(@value, 'Save')]")).click();
		
		WebElement orgHeader = driver.findElement(By.className("dvHeaderText"));
		WebElement orgEntry = driver.findElement(By.id("dtlview_Organization Name")); 
		
		System.out.println("Organization creation status : " + 
		 (orgHeader.getText().contains(orgEntry.getText()) ? "true" : "false"));
		
		// Deletes the created account so that the test script can be re-run indefinitely.
		driver.findElement(By.name("Delete")).click();
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		
		action.moveToElement(driver.findElement(By.xpath("//img[contains(@src, 'user')]")))
			  .perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign Out")));
		action.moveToElement(driver.findElement(By.linkText("Sign Out")))
			  .click().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("loginForm")));
	    
		driver.quit();
		System.out.println("Script terminated successfully.");
	
	}
			
}