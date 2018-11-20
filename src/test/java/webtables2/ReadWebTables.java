package webtables2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadWebTables {
	
	String url = "file:///C:/Users/bagi/eclipse-workspace/SeleniumMavenAutomation/src/test/java/webtables2/webtables2.html";
	
	 WebDriver driver;
	 @BeforeClass 
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
}
	 @Test
	 public void ReadScore() {
		 driver.get(url);
		 //read whole webtable data
		 WebElement  table = driver.findElement(By.tagName("table")); 
		 System.out.println(table.getText());
		 
		 //find how many rows in this table
		 List <WebElement> rows =  driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr"));
			 System.out.println("number of rows in the table:" + rows.size());
			 
		
		String HeadPath = "//table[@id = 'worldcup']/thead/tr/th";
		List <WebElement> headers = driver.findElements(By.xpath(HeadPath));
		
		List<String> expHeader = Arrays.asList("Team1", "Score" , "Team2");
		List<String> actuHeader = new ArrayList<>();
		for(WebElement head : headers) {
			actuHeader.add(head.getText());
		}
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actuHeader, expHeader);
		
		//write xpath and findElement gettext--> need to print German
		String GermPath = "//table[@id = 'worldcup']/tbody/tr[3]/td[3]";
		softAssert.assertEquals(driver.findElement(By.xpath(GermPath)).getText(), "German");
		
		//loop it and print all data
		//get number of rows/columns then nested loop
		int rowCount = driver.findElements(By.xpath("//table[@id = 'worldcup']/tbody/tr")).size();
		int ColuCount = driver.findElements(By.xpath("//table[@id = 'worldcup']/thead/tr/th")).size();
		
		System.out.println("=================");
		
		for(int rowNum = 1; rowNum <= rowCount; rowNum++) {
			for(int col = 1; col <= ColuCount; col++) {
			
		String xpath = "//table[@id = 'worldcup']/tbody/tr[" + rowNum + "]/td [ " + col + "]";
		String tData = driver.findElement(By.xpath(xpath)).getText();
		System.out.println(tData + "\t");
			}
			System.out.println("");
		}
		softAssert.assertAll();
		 
	 }
}