package testapp;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LabelInTestSuiteAndSearch {
	WebDriver driver;
	WebDriverWait wait;
	@Before
	public void openBr() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.75.238.99:3000/suites");
	}
	@Test
	public void createCaseWithLabel() throws AWTException, InterruptedException {
		wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.button.new-test-suite-button.blue.right")));
		driver.findElement(By
				.cssSelector("button.button.new-test-suite-button.blue.right")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.button.blue.test-suite-create-save")));
		driver.findElement(By.cssSelector("input.text-input.full-width.test-suite-name")).sendKeys("TestSuiteLabel1");
		driver
				.findElement(By
						.cssSelector("textarea.text-area.full-width.test-suite-description")).sendKeys("Some description.");
		Thread.sleep(2000);
		WebElement leftSelectCase1 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='0riskanalyses']"));
		WebElement leftSelectCase2 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='0Edm_import']"));
		WebElement arrowRight = driver.findElement(By
				.cssSelector("button.button.move-right.blue"));
		leftSelectCase1.click();
		leftSelectCase2.click();
		arrowRight.click();
		driver.findElement(By.cssSelector("input.input")).sendKeys("Label2");
		Thread.sleep(2000);
		//Keyboard Activity Using Robot Class 
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		wait.withTimeout(30, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("button.button.blue.test-suite-create-save")).click();
		driver.findElement(By.cssSelector("button.button.blue.test-suite-create-save")).click();
		String labelFieldText = driver.findElement(By.xpath("//span[@class='tag-list-item-text']")).getText();
		Assert.assertTrue("Text not found!", labelFieldText.contains("Label2"));
	}
	}
