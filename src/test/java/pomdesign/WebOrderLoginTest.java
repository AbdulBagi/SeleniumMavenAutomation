package pomdesign;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.WebOrdersLoginPage;

public class WebOrderLoginTest {
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //only one time not every step
		 driver.manage().window().fullscreen();
	}
	
	@Ignore  
	@Test
	public void positiveloginTest() {
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
	}
	
	@Test(priority = 2)
	public void positiveLoginUsingPOM() {
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
		WebOrdersLoginPage loginPage = new WebOrdersLoginPage(driver);
		loginPage.userName.sendKeys("Tester");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
	}
	
/*	Test case:
		enter invalid userName
		enter valid password
		click login
		"invalid login or password" message should be displayed*/
	
	@Test(priority = 1)
	public void invalidUsernameTest() {
		driver.get("\"http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
		//we already have the page, so we don't have to create it again
		WebOrdersLoginPage loginPage = new WebOrdersLoginPage(driver);
		loginPage.userName.sendKeys("invalid");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		//step four// we can do driver.findElementBy() but
		// we are using page, so we don't have to use it, 
		// instead we added to our central location = FindBy
		// like go to the page, add the new element and come back here to use it
		String ErroMessage = loginPage.InvalidUserNameErrorMessage.getText();
		
		assertEquals(ErroMessage, "Invalid Login or Password");
		
	}
}
