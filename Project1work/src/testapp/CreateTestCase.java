package testapp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.commands.GetAttribute;

public class CreateTestCase {
	public static final String USERNAME = "haemimont1";
	public static final String AUTOMATE_KEY = "cUX4WagqZq59SpTz9MSg";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY+ "@hub.browserstack.com/wd/hub";
	public static void main(String[] args) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "IE");
		caps.setCapability("browser_version", "11.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "7");
		caps.setCapability("resolution", "1024x768");
		caps.setCapability("browserstack.debug", "true");
		WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		driver.get("http://193.178.152.130:8090/cases");
		WebDriverWait wait = new WebDriverWait(driver, 8000);
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
		WebElement leftSelectStep1 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='REST']"));
		WebElement leftSelectStep2 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='losssettings']"));
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
			WebElement storage = driver
					.findElement(By
							.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[.='REST']"));
			storage.equals(driver.findElement(By
					.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='REST']")));
		} catch (NotFoundException error) {
			System.out
					.println("The test step \"REST\" is not present in the right box");
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'right-selector-container')]//span[.='losssettings']"));
		} catch (NotFoundException error) {
			System.out
					.println("The test step \"losssettings\" is not present in the right box");
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
