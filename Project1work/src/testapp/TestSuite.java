package testapp;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TestSuite {
	WebDriver driver;
	@Before
	public void openBr() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://193.178.152.130:8090/suites");
	}
	@Test
	public void createSuite() {
		WebDriverWait wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.button.new-test-suite-button.blue.right")));
		WebElement newSuite = driver.findElement(By
				.cssSelector("button.button.new-test-suite-button.blue.right"));
		newSuite.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("input.text-input.full-width.test-suite-name")));
		WebElement nameSuite = driver.findElement(By
				.cssSelector("input.text-input.full-width.test-suite-name"));
		nameSuite.sendKeys("Name_testSuite");
		WebElement descriptionSuite = driver
				.findElement(By
						.cssSelector("textarea.text-area.full-width.test-suite-description"));
		descriptionSuite.sendKeys("Some entered description.");
		wait.withTimeout(20, TimeUnit.SECONDS);
		WebElement leftSelectCase1 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='geocode']"));
		WebElement leftSelectCase2 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='GETedmimport']"));
		WebElement leftSelectCase3 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='EDMupload']"));
		WebElement arrowRight = driver.findElement(By
				.cssSelector("button.button.move-right.blue"));
		leftSelectCase1.click();
		leftSelectCase2.click();
		leftSelectCase3.click();
		arrowRight.click();
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[.='geocode']"));
		} catch (NotFoundException error) {
			System.out
					.println("The test case \"geocode\" is not present in the right box");
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[.='GETedmimport']"));
		} catch (NotFoundException error) {
			System.out
					.println("The test case \"GETedmimport\" is not present in the right box");
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[.='EDMupload']"));
		} catch (NotFoundException error) {
			System.out
					.println("The test case \"EDMupload\" is not present in the right box");
		}
		WebElement createButton = driver.findElement(By
				.cssSelector("button.button.blue.test-suite-create-save"));
		createButton.click();
		try {
			WebElement viewPageForThisTestSuite = driver.findElement(By
					.linkText("Test Suite Details"));
		} catch (NotFoundException error) {
			System.out.println("The test suite was not created");
		}
	}
}
