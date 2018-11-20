package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllOrdersPage {
	
	public AllOrdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//h1[.='Web Orders']")
	public WebElement webOrder;
	
	@FindBy(css = ".login_info")
	public WebElement welcomeMesg;
	
	@FindBy(xpath="//h2[.='List of All Orders']")
	public WebElement listOfAllOrders;
	
	//the links are eassy just linkText and then pass the text
	
	@FindBy(linkText = "View all orders")
	public WebElement viewAllOrders;
	
	@FindBy(linkText = "View all products")
	public WebElement viewAllProducts;
	
	@FindBy(linkText = "Order")
	public WebElement orderTab;
	
	@FindBy(id="ctl00_logout")
	public WebElement logoutLink;
	
	// we want to create a method to click logout
	public void logout() {
		logoutLink.click();
		
	//what if we want to create a method to login
		
	}
}
