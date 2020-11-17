package com.learning.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionTest {
	public WebDriver driver;

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
     @Test
	 public void staleEleExcep() {
		 driver.get("http://the-internet.herokuapp.com/dynamic_controls");
		 driver.manage().window().maximize();
		 WebElement checkBox=driver.findElement(By.xpath("//div[@id='checkbox']"));
		 checkBox.getText();
		 driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();
		 WebDriverWait wait=new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='checkbox']")));
		 boolean checkBox1=driver.findElement(By.xpath("//button[contains(text(),'Add')]")).isDisplayed();
		 Assert.assertTrue(checkBox1, "actual mess is" +checkBox1+ " excepted is A checkbox");
		 
	 }
     @Test
     public void fun() {
    	 String ele="Arish";
		 driver.get("http://the-internet.herokuapp.com/dynamic_controls");
		 driver.manage().window().maximize();
		 WebElement textField=driver.findElement(By.xpath("//input[@type='text']"));
//		 System.out.println("is Disabled "+textField.getAttribute("disabled"));

		 System.out.println("is Disabled "+!textField.getAttribute("disabled").equals("true"));
		 System.out.println("is Enabled "+textField.isEnabled());

		 driver.findElement(By.xpath("//input[@type='text']/following-sibling::button")).click();
		 WebDriverWait wait=new WebDriverWait(driver, 10);
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text']"))); 
		 System.out.println("is Disabled ");
		 textField.sendKeys(ele);
		 Assert.assertEquals(textField.getAttribute("value"), ele, "expected text"+ele+ "actual text "+textField.getAttribute("value"));
		 System.out.println("expected text"+ele+ "actual text "+textField.getAttribute("value") );
     }
	/*
	 * @Test public void loginHappyPath() { System.out.println("*******Started ");
	 * driver.manage().window().maximize(); sleepForSometime(5);
	 * driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
	 * sleepForSometime(2); WebElement startButton =
	 * driver.findElement(By.xpath("//div[@id='start']/button"));
	 * startButton.click(); String expectedMessage = "Hello World!"; WebDriverWait
	 * waitTime = new WebDriverWait(driver, 2); try {
	 * waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "//div[@id='finish']"))); } catch (TimeoutException d) { sleepForSometime(5);
	 * }catch (Exception d) { System.out.println("EXCEPTION IS " + d); } String
	 * finishText = driver.findElement(By.xpath("//div[@id='finish']")).getText();
	 * 
	 * Assert.assertTrue(finishText.contains(expectedMessage),
	 * "Mismatch expected is " + expectedMessage + " but got " + finishText); }
	 */
	@AfterTest(alwaysRun = true)
	public void endTask() {
		driver.quit();
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
