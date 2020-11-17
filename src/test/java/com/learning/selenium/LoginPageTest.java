package com.learning.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPageTest {
	public WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void endTask5s() {
		System.out.println("I am in BeforeSuite");
	}
	@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	public void initialSetUp(String ayya) {
		switch (ayya) {
		case "chrome":
			System.out.println("IN CHROME CASE");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
			driver = new ChromeDriver();
			break;
        case "firefox":
        	System.setProperty("webdriver.chrome.driver", "src/main/resources/firefox");
    		driver = new ChromeDriver();
			break;		

		default:
			System.out.println("IN DEFAULKT CASE");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
			driver = new ChromeDriver();
			break;
		}		
	}

	@BeforeClass(alwaysRun = true)
	public void endTask2() {
		System.out.println("I am in BeforeClass");
	}

	@BeforeMethod(alwaysRun = true)
	public void endTask7fx() {
		System.out.println("I am in BeforeMethod");
	}

	@Parameters({ "username", "password" })
	@Test(groups = { "Happy Path" })
	public void loginHappyPath(String username, String password) {
		System.out.println("*******Started loginHappyPath");
		// System.setProperty("webdriver.chrome.driver",
		// "src/main/resources/chromedriver");
		// WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		sleepForSometime(5);

		driver.get("http://the-internet.herokuapp.com/login");
		sleepForSometime(2);

		driver.findElement(By.xpath("//*[@name='username']")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='radius']")).click();
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Logout button not displayed");
		Assert.assertTrue(logOutButton.isEnabled(), "Logout message not enabled");

		String expectedMessage = "You logged into a secure area!";
		String actualMessage = driver.findElement(By.cssSelector("div#flash")).getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Mismatch expected is " + expectedMessage + " but got " + actualMessage);
	}

	@Parameters({ "username", "password" })
	@Test(priority = 2, groups = { "errorMessageTest", "incorrectusername" })
	public void loginWrongUsername(String username, String password) {
		System.out.println("*******Started loginWrongUsername");

		// System.setProperty("webdriver.chrome.driver",
		// "src/main/resources/chromedriver");
		// WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		sleepForSometime(5);

		driver.get("http://the-internet.herokuapp.com/login");
		sleepForSometime(2);

		driver.findElement(By.xpath("//*[@name='username']")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='radius']")).click();
		// WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button
		// secondary radius']"));
		String expectedMessage = "Your username is invalid!";
		String actualMessage = driver.findElement(By.cssSelector("div#flash")).getText();
		System.out.println("**********" + expectedMessage + "*********" + actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Mismatch expected is " + expectedMessage + " but got " + actualMessage);
	}

	@Parameters({ "username", "password" })
	@Test(priority = 1, groups = { "errorMessageTest" })
	public void loginWrongPassword(String username, String p) {
		System.out.println("*******Started loginWrongPassword");

		// System.setProperty("webdriver.chrome.driver",
		// "src/main/resources/chromedriver");
		// WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		sleepForSometime(5);

		driver.get("http://the-internet.herokuapp.com/login");
		sleepForSometime(2);

		driver.findElement(By.xpath("//*[@name='username']")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(p);
		driver.findElement(By.xpath("//button[@class='radius']")).click();
		// WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button
		// secondary radius']"));
		String expectedMessage = "Your password is invalid!";
		String actualMessage = driver.findElement(By.cssSelector("div#flash")).getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Mismatch expected is " + expectedMessage + " but got " + actualMessage);
	}

//	//public WebElement xpath(String xpathLocator) {
//		//WebDriver driver = new ChromeDriver();
//
//		//return driver.findElement(By.xpath(xpathLocator));
//	}
//	
//	public void click(String xpath) {
//		//WebDriver driver = new ChromeDriver();
//
//		//driver.findElement(By.xpath(xpath)).click();
//		
//	}

	// }

	@AfterMethod(alwaysRun = true)
	public void endTask8() {
		System.out.println("I am in AfterMethod");
	}

	@AfterClass(alwaysRun = true)
	public void endTask1() {
		System.out.println("I am in AfterClass");
	}

	@AfterTest(alwaysRun = true)
	public void endTask() {
		driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void endTask6() {
		System.out.println("I am in AfterSuite");
	}

	public void sleepForSometime(int timeToSleep) {
		try {
			Thread.sleep(timeToSleep * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
