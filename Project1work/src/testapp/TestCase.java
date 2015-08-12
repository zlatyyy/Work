package testapp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TestCase {
	WebDriver driver;
	@Before
	public void openBr() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://193.178.152.130:8090/cases");
	}
	@Test
	public void createCase() {
		WebDriverWait wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.button.new-test-case-button.blue.right")));
		WebElement newCase = driver.findElement(By
				.cssSelector("button.button.new-test-case-button.blue.right"));
		newCase.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("select.dropdown.full-width.test-case-type")));
		Select dropdown = new Select(driver.findElement(By
				.cssSelector("select.dropdown.full-width.test-case-type")));
		dropdown.selectByValue("FUNC");
		WebElement nameCase = driver.findElement(By
				.cssSelector("input.text-input.full-width.test-case-name"));
		nameCase.sendKeys("Name_testCase");
		WebElement descriptionCase = driver
				.findElement(By
						.cssSelector("textarea.text-area.full-width.test-case-description"));
		descriptionCase.sendKeys("Some entered description.");
		WebElement importCheckbox = driver.findElement(By
				.cssSelector("input.checkbox-button"));
		if (!importCheckbox.isSelected()) {
			importCheckbox.click();
		}
		wait.withTimeout(40, TimeUnit.SECONDS);
		WebElement leftSelectStep1 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='REST']"));
		WebElement leftSelectStep2 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='lossssetings']"));
		WebElement leftSelectStep3 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='RISKanalyses']"));
		WebElement arrowRight = driver.findElement(By
				.cssSelector("button.button.move-right.blue"));
		leftSelectStep1.click();
		leftSelectStep2.click();
		leftSelectStep3.click();
		arrowRight.click();
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[.='REST']"));
		} catch (NotFoundException error) {
			System.out
					.println("The test step \"REST\" is not present in the right box");
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[.='lossssetings']"));
		} catch (NotFoundException error) {
			System.out
					.println("The test step \"lossssetings\" is not present in the right box");
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[.='RISKanalyses']"));
		} catch (NotFoundException error) {
			System.out
					.println("The test step \"RISKanalyses\" is not present in the right box");
		}
		WebElement createButton = driver.findElement(By
				.cssSelector("button.button.blue.test-case-create-save"));
		createButton.click();
		try {
			WebElement viewPageForThisTestCase = driver.findElement(By
					.linkText("Test Case Details"));
		} catch (NotFoundException error) {
			System.out.println("The test case was not created");
		}
	}
}
