package amazonsearch;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrintAllProductDetails {

	 WebDriver driver;
	 @BeforeClass 
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
}
	@Test
	public void getAllProducts() {
		driver.get("https://www.amazon.com/s/ref=nb_sb_ss_c_1_8?url="
				+ "search-alias%3Daps&field-keywords=wooden+spoons&sprefix=wooden+"
				+ "s%2Caps%2C198&crid=2ZJDZQCO55UX0");
		
		List<WebElement> descriptions = driver.findElements(By.xpath("//h2"));
		for(WebElement item : descriptions) {
			System.out.println(item.getText());
			}
		List<WebElement> prices = driver.findElements(By.xpath("//span[@class = 'sx-price sx-price-large']"));
		for(WebElement price : prices) {
			System.out.println(price.getText());
		}
		
		//how we can put this two for-loop together 
		for(int i = 0; i < descriptions.size() && i < prices.size(); i++) {
			System.out.println(descriptions.get(i).getText());
			System.out.println(prices.get(i).getText());
			System.out.println("........................");
			
	List<WebElement> wholeItems = driver.findElements(By.xpath("(//div[@class='s-item-container'])"
			+ "//span[@class = 'sx-price sx-price-large']"));
	System.out.println("wholeItem.size():" + wholeItems.size());
	
	for(int j =0; j < wholeItems.size(); j++) {
		if(wholeItems.get(i).getText().isEmpty())continue;
	}
	
		String desXpath = "(//div[@class='s-item-container'])[" + (i + 1) + "]//h2";
		String priceXpath = "(//div[@class='s-item-container'])[" + (i + 1) + "]//span[@class = 'sx-price sx-price-large']";
		
		System.out.println(driver.findElement(By.xpath(desXpath)).getText());
		System.out.println(driver.findElement(By.xpath(priceXpath)).getText());
		System.out.println(".............................");
			
		}
		}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}