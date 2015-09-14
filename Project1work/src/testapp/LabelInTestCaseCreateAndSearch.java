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
public class LabelInTestCaseCreateAndSearch {
	WebDriver driver;
	WebDriverWait wait;
	@Before
	public void openBr() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.75.238.99:3000/cases");
	}
	@Test
	public void createCaseWithLabel() throws AWTException, InterruptedException {
		wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.button.new-test-case-button.blue.right")));
		driver.findElement(By
				.cssSelector("button.button.new-test-case-button.blue.right")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("select.dropdown.full-width.test-case-type")));
		driver.findElement(By.cssSelector("input.text-input.full-width.test-case-name")).sendKeys("TestCaseLabel1");
		Thread.sleep(2000);
		Select dropdown = new Select(driver.findElement(By
				.cssSelector("select.dropdown.full-width.test-case-type")));
		dropdown.selectByValue("FUNC");
		driver
				.findElement(By
						.cssSelector("textarea.text-area.full-width.test-case-description")).sendKeys("Some description.");
		WebElement importCheckbox = driver.findElement(By
				.cssSelector("input.checkbox-button"));
		if (!importCheckbox.isSelected()) {
			importCheckbox.click();
		}
		wait.withTimeout(30, TimeUnit.SECONDS);
		WebElement leftSelectStep1 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='0riskanalyses']"));
		WebElement leftSelectStep2 = driver
				.findElement(By
						.xpath("//div[contains(@class,'droppable-wrapper') and contains(@class ,'left-selector-container')]//span[.='0Losssettings']"));
		WebElement arrowRight = driver.findElement(By
				.cssSelector("button.button.move-right.blue"));
		leftSelectStep1.click();
		leftSelectStep2.click();
		arrowRight.click();
		driver.findElement(By.cssSelector("input.input")).sendKeys("Label2");
		Thread.sleep(2000);
		//Keyboard Activity Using Robot Class 
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		wait.withTimeout(30, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("button.button.blue.test-case-create-save")).click();
		driver.findElement(By.cssSelector("button.button.blue.test-case-create-save")).click();
		String labelFieldText = driver.findElement(By.xpath("//span[@class='tag-list-item-text']")).getText();
		Assert.assertTrue("Text not found!", labelFieldText.contains("Label2"));
	//	WebTester tester1=new WebTester();
		//tester1.assertTextPresent("The label is not visible.");
	}
	}
