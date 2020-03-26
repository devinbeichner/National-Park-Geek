package com.techelevator.npgeek.cukes;

import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DetailPageTest {
	
	private static WebDriver webDriver;
	
	/* Creating an instance of the WebDriver is time consuming
	 * since it opens up a browser window. Therefore, we do this
	 * only once at the start of the class and reuse the same
	 * browser window for all tests. */
	@BeforeClass
	public static void openWebBrowserForTesting() {
		
		String homeDir = System.getProperty("user.home"); // ~ or $HOME
		/* The ChromeDriver requires a system property with the name "webdriver.chrome.driver" that
		 * contains the directory path to the ChromeDriver executable. The following line assumes
		 * we have installed the ChromeDriver in a known location under our home directory */
		System.setProperty("webdriver.chrome.driver", homeDir+"/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
	}

	@Before
	public void openHomePage() {
		webDriver.get("http://localhost:8080/45-m3-java-capstone");
	}
	
	@AfterClass
	public static void closeWebBrowser() {
		webDriver.close();
	}
	
	@Test
	public void has_header_and_footer() {
		clickIntoDetails();
		WebElement header = webDriver.findElement(By.tagName("header"));
		WebElement footer = webDriver.findElement(By.tagName("footer"));
		assertNotNull(header);
		assertNotNull(footer);

	}
	
	@Test
	public void detail_page_has_park_information() {
		clickIntoDetails();
		WebElement detailImage = webDriver.findElement(By.id("detailImage"));
		WebElement parkName = webDriver.findElement(By.id("parkName"));
		WebElement state = webDriver.findElement(By.id("state"));
		WebElement acreage = webDriver.findElement(By.id("acreage"));
		WebElement elevation = webDriver.findElement(By.id("elevation"));
		WebElement milesOfTrail = webDriver.findElement(By.id("milesOfTrail"));
		WebElement numberOfCampsites = webDriver.findElement(By.id("numberOfCampsites"));
		WebElement climate = webDriver.findElement(By.id("climate"));
		WebElement yearFounded = webDriver.findElement(By.id("yearFounded"));
		WebElement annualVisitorCount = webDriver.findElement(By.id("annualVisitorCount"));
		WebElement quote = webDriver.findElement(By.id("quote"));
		WebElement quoteSource = webDriver.findElement(By.id("quoteSource"));
		WebElement description = webDriver.findElement(By.id("description"));
		WebElement entryFee = webDriver.findElement(By.id("entryFee"));
		WebElement numberOfAnimalSpecies = webDriver.findElement(By.id("numberOfAnimalSpecies"));
		
		assertNotNull(detailImage);
		assertNotNull(parkName);
		assertNotNull(state);
		assertNotNull(acreage);
		assertNotNull(elevation);
		assertNotNull(milesOfTrail);
		assertNotNull(numberOfCampsites);
		assertNotNull(climate);
		assertNotNull(yearFounded);
		assertNotNull(annualVisitorCount);
		assertNotNull(quote);
		assertNotNull(quoteSource);
		assertNotNull(description);
		assertNotNull(entryFee);
		assertNotNull(numberOfAnimalSpecies);
		
	}
	
	@Test
	public void detail_page_has_weather_info() {
		
		clickIntoDetails();
		
		WebElement weatherImage = webDriver.findElement(By.className("weatherImage"));
		WebElement tempHigh = webDriver.findElement(By.className("tempHigh"));
		WebElement tempLow = webDriver.findElement(By.className("tempLow"));
		WebElement advisoryMessage = webDriver.findElement(By.className("advisoryMessage"));
		
		assertNotNull(weatherImage);
		assertNotNull(tempHigh);
		assertNotNull(tempLow);
		assertNotNull(advisoryMessage);
	}
	
	@Test
	public void detail_page_has_f_or_c_selector() {
		
		clickIntoDetails();
		
		WebElement f = webDriver.findElement(By.id("f"));
		WebElement c = webDriver.findElement(By.id("c"));
		WebElement submitButton = webDriver.findElement(By.id("submitButton"));
		
		assertNotNull(f);
		assertNotNull(c);
		assertNotNull(submitButton);
	}
	
	private void clickIntoDetails() {
		WebElement imageLink = webDriver.findElement(By.xpath("/html/body/main/div[1]/a/img"));
		JavascriptExecutor ex = (JavascriptExecutor)webDriver;
		ex.executeScript("arguments[0].click();", imageLink);
	}
}
