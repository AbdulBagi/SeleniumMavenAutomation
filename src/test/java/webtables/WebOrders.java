package webtables;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import webelements.WebElements;

public class WebOrders {
	 
	static WebDriver driver;
	 @Test 
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //only one time not every step
		 //driver.manage().window().fullscreen();
		
		
		/*Task1: 
			1. login with creditional username Tester, pass test
			2. print out all the names in the order table
			*/
		 driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
		 
	WebElement userName = driver.findElement(By.id("ctl00_MainContent_username"));
	WebElement passWord = driver.findElement(By.id("ctl00_MainContent_password"));
	WebElement logIn = driver.findElement(By.id("ctl00_MainContent_login_button"));
	String userNameValue = "Tester";
	String passWordValue = "test";
	userName.sendKeys(userNameValue);
	passWord.sendKeys(passWordValue);
	logIn.click();
	

	List<WebElement> names = driver.findElements(By.xpath("//table[@class ='SampleTable']//tr/td[2]"));
	for(WebElement name: names) {
		System.out.println("Name:" + name.getText());
	}
	
	//  way to print out the data by calling the method and pass the value as String
	printValues("Date");
	printValues("City");
	
	//we can check the delete method in here
	
	deleteRow("Bob Feather");
	System.out.println("Check:" + checkName("Bob Faether"));
	System.out.println("Check2:" + checkName("Paul Brown"));
	 }
	 
		Task2:
			create a method that accepts String (header)
			Based on the header value your method should print out the value for each row
			
	public static void printValues(String header){
		String xpath = "";
		switch(header) {
		case "Name":
			xpath = "//table[@class ='SampleTable']//tr/td[2]";
			break;
		case "Product":
			xpath = "//table[@class ='SampleTable']//tr/td[3]";
			break;
		case "Date":
			xpath = "//table[@class ='SampleTable']//tr/td[5]";
			break;
		case "Street":
			xpath = "//table[@class ='SampleTable']//tr/td[6]";
			break;
		case "City":
			xpath = "//table[@class ='SampleTable']//tr/td[7]";
			break;
		}
		
		List<WebElement> data = driver.findElements(By.xpath(xpath));
	
		for(WebElement da: data) {
			System.out.println(header + ":"+ da.getText());
		}
	} 
	
	Task3:
		Create a method 
		name: deleteRow
		param: name
		action: based on the given name it needs to find row and select the row then delete it 
	
	String xpath;
	public static void deleteRow(String name) {
		WebElement deleteRowBtn = driver.findElement(By.id("ctl00_MainContent_btnDelete"));
		String xpath = "//td[.='" + name + "']/../td/input";
		driver.findElement(By.xpath(xpath)).click();
		deleteRowBtn.click();
		}
	
	Task4:
		create a method
		name: checkName
		param: name
		return: boolean
		purpose: takes name and checks if it exists in the row and return boolean 
	
	public static boolean checkName(String name) {
		String xpath = " //table[@class ='SampleTable']//tr/td[2]";
		List<WebElement> data = driver.findElements(By.xpath(xpath));
		
		for(WebElement da: data) {
			if(da.getText().equalsIgnoreCase(name));
			return true;
	}
		return false;
	}

}

		
	

