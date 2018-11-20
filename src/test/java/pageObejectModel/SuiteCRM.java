package pageObejectModel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SuiteCRM {
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
		WebElement username = driver.findElement(By.id("user_name"));
		WebElement password = driver.findElement(By.id("username_password"));
		WebElement loginBtn = driver.findElement(By.id("bigbutton"));
		username.sendKeys("admin");
		password.sendKeys("pass");
		loginBtn.click();
		Thread.sleep(3000);
		WebElement textBox = driver.findElement(By.xpath("//input[@.='text'])[1]"));
		textBox.sendKeys("just for test" + Keys.ENTER);
		
		
	}
}
