package com.techelevator.npgeek.cukes;

import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FavoritePageTest {

	private static WebDriver webDriver;

	/*
	 * Creating an instance of the WebDriver is time consuming since it opens up a
	 * browser window. Therefore, we do this only once at the start of the class and
	 * reuse the same browser window for all tests.
	 */
	@BeforeClass
	public static void openWebBrowserForTesting() {

		String homeDir = System.getProperty("user.home"); // ~ or $HOME
		/*
		 * The ChromeDriver requires a system property with the name
		 * "webdriver.chrome.driver" that contains the directory path to the
		 * ChromeDriver executable. The following line assumes we have installed the
		 * ChromeDriver in a known location under our home directory
		 */
		System.setProperty("webdriver.chrome.driver", homeDir + "/dev-tools/chromedriver/chromedriver");
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
	public void favorites_page_loads_properly() {
		submitSurveyForm();
		
		WebElement header = webDriver.findElement(By.tagName("header"));
		WebElement footer = webDriver.findElement(By.tagName("footer"));
		WebElement table = webDriver.findElement(By.tagName("table"));

		assertNotNull(table);
		assertNotNull(header);
		assertNotNull(footer);


	}
	
	private void submitSurveyForm() {
		WebElement surveyLink = webDriver.findElement(By.linkText("Survey"));
		surveyLink.click();

		Select parkCodeDropDown = new Select(webDriver.findElement(By.name("parkCode")));
		parkCodeDropDown.selectByVisibleText("Glacier National Park");

		WebElement email = webDriver.findElement(By.name("emailAddress"));
		email.sendKeys("test@test.com");

		Select stateDropDown = new Select(webDriver.findElement(By.name("state")));
		stateDropDown.selectByVisibleText("Puerto Rico");

		WebElement inactiveRadio = webDriver.findElement(By.id("inactive"));
		inactiveRadio.click();

		WebElement submitButton = webDriver.findElement(By.tagName("button"));
		submitButton.click();
		
	}

}
