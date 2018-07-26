package webelements;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Element {

	WebDriver driver;
	@BeforeClass
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //only one time not every step
		 driver.manage().window().fullscreen();
	}
	
	@Test
	public void WebElementExamples() {
		driver.get("https:www.google.com");
		WebElement email = driver.findElement(By.name("Email"));
		String value = email.getAttribute("value");
		String maxLength = email.getAttribute("maxLength");
		String type = email.getAttribute("type");
		String tag = email.getAttribute("tag");
		boolean b = email.isEnabled();
		System.out.println("value: "+ value + "\n"+ "maxLength: "+ maxLength + 
				"\n"+ "type: "+ type + "\n"+ "tag: "+ tag + "\n" + "isEnabled: "+ b);
		assertEquals(value, "a.bagi12@gmail.com");
		
		email.clear();
		email.sendKeys("another@email.com");
		
		WebElement country = driver.findElement(By.id("Address_Country"));
		Select selectCountry = new Select(country);
		
		String d = selectCountry.getFirstSelectedOption().getText();
		System.out.println(d);
		selectCountry.selectByIndex(67);
		
		// let's generate StaleElementException 
		WebElement cSalary = driver.findElement(By.name("Number"));
		assertEquals(cSalary.isDisplayed(), true);
		
		driver.get("https:www.google.com");
		driver.findElement(By.xpath("//em[.='Next']")).click();
		cSalary.sendKeys("123456");
		
	}
}
