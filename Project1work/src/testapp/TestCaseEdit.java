package testapp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Point;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
			Assert.assertTrue("The step is not in the right container.",
					element2.getText().equals(storedTextOfTheUsedStep));
			System.out.println(element2.getText());
		}
		WebElement rightSelectStep2 = driver.findElement(By.xpath(xpathMove));
		rightSelectStep2.click();
		WebElement arrowLeft = driver.findElement(By
				.cssSelector("button.button.move-left.blue"));
		arrowLeft.click();
		// Verify the test step is out of the right component and is in the left
		// component.
		/*
		 * try { for (WebElement el : collectionRight) {
		 * Assert.assertFalse("The step is in the right container.", el
		 * .getText().equals(storedTextOfTheUsedStep)); } } catch (Exception e)
		 * { System.out
		 * .println("There isn't any test step in the right container"); } //for
		 * (WebElement elem : collectionLeft) { //Assert. //
		 * Assert.assertTrue("The step is not in the left container.", elem
		 * //.getText().equals(storedTextOfTheUsedStep)); //}
		 * 
		 * @AfterMethod public void takeScreenShotOnFailure(ITestResult
		 * testResult) throws IOException { if (testResult.getStatus() ==
		 * ITestResult.FAILURE) { System.out.println(testResult.getStatus());
		 * File scrFile =
		 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 * FileUtils.copyFile(scrFile, new File("D:\\testScreenShot.jpg")); }
		 */
	}
}
