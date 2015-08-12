package testapp;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ExecuteOfTestSuitesBulk {
	WebDriver driver;
	WebDriverWait wait;

	@Before
	public void openBr() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://193.178.152.130:8090/suites");
	}
	@Test
	public void executeSuites() {
		wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//span[.='Workflow']")));
		WebElement testSuite1 = driver
				.findElement(By
						.xpath("//span[contains(.,'Workflow')]/preceding-sibling::div[contains(@class,'checkbox-button-wrapper') and contains(@class ,'name-cell-checkbox')]//label[@class='checkbox-button-label']"));
		testSuite1.click();
		WebElement testSuite2 = driver.findElement(By
				.xpath("//span[contains(.,'Risk analysis')]/preceding-sibling::div[contains(@class,'checkbox-button-wrapper') and contains(@class ,'name-cell-checkbox')]//label[@class='checkbox-button-label']"));
		testSuite2.click();
		WebElement testSuite3 = driver.findElement(By
				.xpath("//span[contains(.,'Test ET4')]/preceding-sibling::div[contains(@class,'checkbox-button-wrapper') and contains(@class ,'name-cell-checkbox')]//label[@class='checkbox-button-label']"));
		testSuite3.click();
		WebElement executeTestSuiteButton = driver.findElement(By
				.cssSelector("button.button.execute-selected-button.green.right"));
		executeTestSuiteButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("div.card.blue.modal-dialog ")));
		WebElement executionNames = driver
				.findElement(By
						.cssSelector("div.suite-executor-names"));
		String ExecutedSuites = executionNames.getText();
		ExecutedSuites.compareTo("Workflow, Risk analysis, Test ET4");
		Select environments = new Select(
				driver.findElement(By
						.cssSelector("select.dropdown.full-width.suite-executor-environment ")));
		environments.selectByValue("ST");
		WebElement tenant = driver
				.findElement(By
						.cssSelector("input.text-input.full-width.suite-executor-tenant-input "));
		tenant.sendKeys("hmm-test");
		WebElement username = driver
				.findElement(By
						.cssSelector("input.text-input.full-width.suite-executor-username-input "));
		username.sendKeys("stfuser");
		WebElement password = driver
				.findElement(By
						.cssSelector("input.password-input.full-width.suite-executor-password-input "));
		password.sendKeys("hmmtest");
		WebElement importExET = driver.findElement(By
				.cssSelector("input.checkbox-button"));
		try {
			Select packageET = new Select(
					driver.findElement(By
							.cssSelector("select.dropdown.full-width.suite-executor-packages-folder")));
			packageET.selectByVisibleText("test_subpackage");
		} catch(NoSuchElementException e) {
			System.out.println("The checkbox 'Store the results in Enterprise tester' is not selected by default!");
			e.getMessage();
		}
		WebElement executeButton = driver
				.findElement(By
						.cssSelector("button.button.blue.suite-executor-execute-button"));
		executeButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("div.alerts ")));
		try {
			driver.findElement(By.cssSelector("div.alerts "));
			System.out.println("The success message is available.");
		} catch (NotFoundException error) {
			System.out.println("The success message is not available.");
		}
	}
}
