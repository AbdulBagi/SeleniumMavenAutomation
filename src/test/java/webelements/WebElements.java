package webelements;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
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
	public void myLinks() {
		driver.get("https://github.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		// how many links on github home page
		int numberOfLinksOnGithub = links.size();
		System.out.println("number of links: "+ numberOfLinksOnGithub);
		//print text of each link
		for (WebElement link: links) {
			// if empty don't print it
			if(!link.getText().isEmpty()) {
			System.out.println(link.getText());
			}
		}
		
		// add each link text into a list of Strings
		
	}
}