package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginUsingDDTFromExternalResources {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver = null;
		
		FileInputStream istream = new FileInputStream("./externalResources/credentials.properties");
		Properties property = new Properties();
		
		property.load(istream);
		
		String BROWSER = property.getProperty("browser");
		String URL = property.getProperty("url");
		String USERNAME = property.getProperty("userName");
		String PASSWORD = property.getProperty("password");
		
		System.out.println("Cross-browser testing is limited for chrome and firefox for now");
			
		switch(BROWSER) {
		
		case "chrome" : driver = new ChromeDriver();
						break;
						
		case "firefox" : driver = new FirefoxDriver();
						 break;
	
		}
			
		
//		Browser settings:
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.quit();
		
	}

}