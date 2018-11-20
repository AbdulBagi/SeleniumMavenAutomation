package pageObejectModel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pagez.HomePage;

public class SuiteTest {

	
	WebDriver driver;
	@BeforeClass // run once for all class
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //only one time not every step
		 //driver.manage().window().fullscreen();
		 //driver.get("http://www.softaculous.com/demos/SuiteCRM");
	}
	
	@Test
	public void test1() throws InterruptedException {
		driver.get("http://www.softaculous.com/demos/SuiteCRM");
		driver.switchTo().frame("demobody");
		// we already have a login page, i just need to create an object 
		//from the login page object class and pass the driver
		LoginPage lp = new LoginPage(driver);
		lp.username.sendKeys("admin");
		lp.password.sendKeys("pass");
		lp.loginBtn.click(); 
		HomePage hp = new HomePage(driver);
		hp.textBox.sendKeys("Using POM" + Keys.ENTER);
}
}
