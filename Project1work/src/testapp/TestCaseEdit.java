package testapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Point;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import static java.io.File.separator;
public class TestCaseEdit {
	WebDriver driver;
	WebDriverWait wait;
	@Before
	public void openBr() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://193.178.152.130:8090/cases");
	}
	@Test
	public void editCase() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//tr[1]/td/div/span")));
		WebElement testCase = driver.findElement(By.xpath("//span[.='1']"));
		testCase.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.button.blue.case-view-edit-button")));
		// Find and click on Edit button
		WebElement editTestCaseButton = driver.findElement(By
				.cssSelector("button.button.blue.case-view-edit-button"));
		editTestCaseButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[@class='list-item-label']")));
		// Find the first test step in the Left multi-selection component
		WebElement lSelectStep = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[@class='list-item-label']"));
		// Get the name of the first test step
		String availableStepInLeft = lSelectStep.getText();
		// Store xpath locators for the test step with the gotten name - in the
		// left and in the right
		String xpath = "//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[@class='list-item-label'][contains(text(), "
				+ "'" + availableStepInLeft + "')]";
		String xpathMove = "//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[@class='list-item-label'][contains(text(), "
				+ "'" + availableStepInLeft + "')]";
		// Move the test step in the right multi-selection component
		WebElement leftSelectStep1 = driver.findElement(By.xpath(xpath));
		String storedTextOfTheUsedStep = leftSelectStep1.getText();
		leftSelectStep1.click();
		WebElement arrowRight = driver.findElement(By
				.cssSelector("button.button.move-right.blue"));
		arrowRight.click();
		System.out.println(availableStepInLeft);
		wait.withTimeout(40, TimeUnit.SECONDS);
		List<WebElement> collectionLeft = driver
				.findElements(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[@class='list-item-label']"));
		System.out.println(collectionLeft.size());
		List<WebElement> collectionRight = driver
				.findElements(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[@class='list-item-label']"));
		// Verify whether the test step is out of the left component and is in
		// the right component
		for (WebElement element : collectionLeft) {
			Assert.assertFalse("The step is in the left container.", element
					.getText().equals(storedTextOfTheUsedStep));
		}
		for (WebElement element2 : collectionRight) {
			Assert.assertTrue(storedTextOfTheUsedStep.contains(element2
					.getText()));
		}
		WebElement rightSelectStep2 = driver.findElement(By.xpath(xpathMove));
		rightSelectStep2.click();
		WebElement arrowLeft = driver.findElement(By
				.cssSelector("button.button.move-left.blue"));
		arrowLeft.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Verify the test step is out of the right component and is in the left
		// component.
		List<WebElement> collectionLeftBack = driver
				.findElements(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[@class='list-item-label']"));
		List<WebElement> collectionRightBack = driver
				.findElements(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[@class='list-item-label']"));
		if (collectionRight.size() != 0) {
			for (WebElement el : collectionRightBack) {
				Assert.assertFalse("The step is in the right container.",
						storedTextOfTheUsedStep.contains(el.getText()));
			}
		}
		System.out.println(collectionLeftBack
				.get(collectionLeftBack.size() - 1).getText());
		Assert.assertTrue(
				"The step is not in the left container.",
				storedTextOfTheUsedStep.contains(collectionLeftBack.get(
						collectionLeftBack.size() - 1).getText()));
	}
	/*
//Code for TestNG
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult)
			throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(
					"D:\\SystemTesting\\ScreenCase.jpg"));
					
		}
	}
	*/


}
