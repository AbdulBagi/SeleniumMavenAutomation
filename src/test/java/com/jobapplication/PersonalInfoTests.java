package com.jobapplication;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PersonalInfoTests {
	WebDriver driver;
	String firstName;
	String lastName;
	int gender;
	String dateOfBirth;
	String email;
	String phoneNumber;
	String city;
	String state;
	String country;
	int annualSalary;
	List<String> technologies;
	int yearsOfExperince;
	String education;
	String github;
	List<String> certifications;
	String additionalSkills;
	Faker data = new Faker();  // we generated it in here so everyone can use it. not to be local 
	Random random = new Random();
	
	
	@BeforeClass // runs once for all test
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //only one time not every step
		driver.manage().window().fullscreen();
	}
	@BeforeMethod
	public void navigateToHomePage() {
		System.out.println("Navigating to homepage in @BeforeMethod ...");
		driver.get("\"https:forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4_mekBNfNLIconAHvfdlk3CJSQ");
		firstName = data.name().firstName();
		lastName = data.name().lastName();
		gender =  random.nextInt(2)+1;
		dateOfBirth = data.date().birthday().toString();
		email = "a.bagi12@gmail.com";
		phoneNumber = data.phoneNumber().cellPhone();
		city = data.address().cityName();
		state = data.address().stateAbbr();
		country = data.address().country();
		annualSalary = data.number().numberBetween(60000, 150000);
		technologies = new ArrayList<>();
		technologies.add("java-" + data.number().numberBetween(1, 4));
		technologies.add("html-" + data.number().numberBetween(1, 4));
		technologies.add("Selenium WebDriver-" + data.number().numberBetween(1, 4));
		technologies.add("TestNG-"+ data.number().numberBetween(1, 4));
		technologies.add("github"+ data.number().numberBetween(1, 4));
		technologies.add("Maven"+ data.number().numberBetween(1, 4));
		technologies.add("JUnit"+data.number().numberBetween(1, 4));
		technologies.add("Cucumber"+data.number().numberBetween(1, 4));
		technologies.add("API Automation-"+ data.number().numberBetween(1, 4));
		technologies.add("JDBC-"+data.number().numberBetween(1, 4));
		technologies.add("SQL-"+data.number().numberBetween(1, 4));
		
		yearsOfExperince = data.number().numberBetween(0, 20);
		education = data.number().numberBetween(1, 4)+"";
		github = "C:\\Users\\bagi\\eclipse-workspace\\SeleniumMavenAutomation";
		certifications = new ArrayList<>();
		certifications.add("java OCA");
		certifications.add("AWS");
		additionalSkills = data.job().keySkills();	
	}

	@Test
	public void submitFullApplication() {
		driver.findElement(By.xpath("//input[@name='Name_First']")).sendKeys("firstName");
		driver.findElement(By.xpath("//input[@name='Name_Last']")).sendKeys("lastName");
		setGender(gender);
		setDateOfBirth(dateOfBirth);
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("email");
		driver.findElement(By.xpath("//input[@name='countrycode']")).sendKeys("phoneNumber");
		driver.findElement(By.xpath("//input[@name='Address_City']")).sendKeys("city");
		driver.findElement(By.xpath("//input[@name='Address_Region']")).sendKeys("state");
		Select countryElem = new Select(driver.findElement(By.xpath("//select[@id='Address_Country']")));
		countryElem.selectByIndex(data.number().numberBetween(1, countryElem.getOptions().size()));
		driver.findElement(By.xpath("//input[@name='Number']")).sendKeys(String.valueOf("annualSalary")+Keys.TAB);
		
		
	}
	//create a method to make sure the calculated salary is correct
	public void verifySalaryCalculations(int annual) {
		String monthly = driver.findElement(By.xpath("//input[@name='Formula']")).getAttribute("value");
		String weekly = driver.findElement(By.xpath("//input[@name='Formula1']")).getAttribute("value");
		String hourly = driver.findElement(By.xpath("//input[@name='Formula2']")).getAttribute("value");
		System.out.println(monthly);
		System.out.println(weekly);
		System.out.println(hourly);
		
		DecimalFormat formatter = new DecimalFormat("#.##");
		
		assertEquals(Double.parseDouble(monthly), formatter.format((double)annual/(double)12.0));
		assertEquals(Double.parseDouble(weekly), formatter.format((double)annual/(double)52.0));
		assertEquals(Double.parseDouble(hourly), formatter.format((double)annual/(double)52.0/40.0));
	}
	//Sun Nov 27 04:04:22 EST 1977
	public void setDateOfBirth(String bday) {
		String[] pieces = bday.split(" ");
		String birthDay = pieces[2]+ "-" + pieces[1]+ "-" + pieces[5];
		driver.findElement(By.xpath("//input[@id='Date-date']")).sendKeys(birthDay);
	}
	public void setGender(int n) {
		if(n==1) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		}else {
			driver.findElement(By.xpath("//input[@value='Female']")).click();
		}
		
	}
	@Test
	public void fullNameEmptyTest() {
		//firstly assert that you are on the correct page
		assertEquals(driver.getTitle(), "SDET Job Application");
		driver.findElement(By.xpath("//input[@name='Name_First']")).clear();
		driver.findElement(By.xpath("//input[@name='Name_Last']")).clear();
		//<em>Next</em>
		driver.findElement(By.xpath("//em[.=' Next ']"));
		//write xpath with tagname + id
		//get the text and assert text equal to enter a value for this field
		String nameError = driver.findElement(By.xpath("//p[@id='error-Name]")).getText();
		assertEquals(nameError, "Enter a value for this field");
	}
}