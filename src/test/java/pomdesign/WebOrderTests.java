package pomdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTests {

	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrdersPage;
	String userId = "Tester";
	String password = "test";
	String productsPage;
	
	@BeforeClass // run once for all class
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //only one time not every step
		 driver.manage().window().fullscreen();
		 driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
	}
	
	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
		//create an object using the class name
		loginPage = new WebOrdersLoginPage(driver);
	}
	
	@Test(description = "verify lables and tab links are displayed")
	public void lableVerification() {
		// to make sure we are on the right webpage first we do:
		assertEquals(driver.getTitle(),"Web Orders"+ "LoginPage is not displayed. Application is down");
		/*loginPage.userName.sendKeys(userId);
		loginPage.password.sendKeys(password);
		loginPage.loginButton.click();
		*/
		// the above three lines can written as below in one line
		loginPage.login(userId, password);
		// create new object 
		allOrdersPage = new AllOrdersPage(driver);
		// then now verify that (Web orders, list of all orders, welcome tester are displayed)
		assertTrue(allOrdersPage.webOrder.isDisplayed(),"Web Orders is not displayed");
		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(), "List of all orders is not displayed");
		// then u can create a varible for userId as string 
		assertEquals(allOrdersPage.welcomeMesg.getText().replace(" | Logout", ""), "Welcome, " + userId + "!" ); 
		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(), " viewAllOrders is not displayed");
		assertTrue(allOrdersPage.orderTab.isDisplayed(), "orders tab is not displayed");
		
	}
	
/*	Step 1. Navigate to loginpage
	Step 2. Assert that you are on loginpage 
	Step 3. login using valid credentials 
	Step 4. Click on view all products
	Step 5. Verify following products are displayed:
		MyMoney  
		FamilyAlbum  
		ScreenSaver  
	
	Step 6. Verify prices and discounts:
		
		MyMoney         100$   8%
		FamilyAlbum     80$   15%
		ScreenSaver     20$   10%*/
	
	@Test(description = "verify dafults and prices")
	public void availableProductsTest() {
		assertEquals(driver.getTitle(), "Web Orders LoginPage is not displayed.");
		loginPage.login(userId, password);
		allOrdersPage = new AllOrdersPage(drive);
		allOrdersPage.viewAllProducts.click();
		ProductsPage = new ProductsPage(driver);
		List<String> expectProducts = Arrays.asList("MyMoney", "FamilyAlbum","ScreenSaver");
		List<String> actuProducts = new ArrayList<>();
		
		/*productsPage.productNames.forEach(
				elem -> actuProducts.add(elem.getText())
				);*/
		
		for(WebElement prod : ProductsPage.productNames) {
			actuProducts.add(prod.getText());
		}
		assertEquals(actuProducts, expectProducts);
		
		for(WebElement row : productsPage.productsRows) {
			if(row.getText().startsWith("MyMoney")) {
				
			}
			System.out.println(row.getText());
			String[] prodData = row.getText().split(" ");
			switch(prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1], "$100");
				assertEquals(prodData[2], "$8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1], "$80");
				assertEquals(prodData[2], "15%");
				break;
			case "ScreenSaver":
				assertEquals(prodData[1], "$20");
				assertEquals(prodData[2], "10%");
				break;
			}
		}
	}
	
	
	
	// logout after each test then close the browser 
	@AfterMethod
	public void logout() {
		//before method, after method you don't put any assertion, only @Test
		// to logout, you need to go to that page(AllordersPage) to add @FindBy first
		allOrdersPage.logout();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
