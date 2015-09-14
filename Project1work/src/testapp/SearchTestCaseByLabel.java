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
public class SearchTestCaseByLabel {
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("div.tag-list-placeholder")).click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CAPS_LOCK);
		robot.keyPress(KeyEvent.VK_L);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_B);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_L);
		robot.keyPress(KeyEvent.VK_1);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);	
		Thread.sleep(1000);
		// driver.findElement(By.cssSelector("div.autocomplete-dropdown")).click();
		driver.findElement(By.cssSelector("button.button.search-cases-button"))
				.click();
		Thread.sleep(2500);
		String text = driver.findElement(By.xpath("//td/div/span")).getText();
		if (text != "There are no test cases to display.") {
			driver.findElement(
					By.xpath("//tr/td/div[@class='name-cell']//span[@class='name-cell-text']"))
					.click();
			try {
				String labelText=driver
						.findElement(
								By.cssSelector("span.tag-list-item.read-only>span.tag-list-item-text"))
						.getText();
				String enteredLabel="Label1";
				System.out.println(labelText);
				Assert.assertTrue( enteredLabel.equals(labelText));
			} catch (Exception e) {
				System.out.println("The element is not found.");
			}
			
		}
		else {
			System.out.println("There are no test cases to display.");
		}
	}
}
