package testappFramework;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LabelInTestCaseSCRIPT {	
	ChromeOptions options = new ChromeOptions();
	protected WebDriver driver = new ChromeDriver(options);
	WebDriverWait wait;
	 @FindBy(how = How.CSS, using = "button.button.new-test-case-button.blue.right")
	 private WebElement buttonNew;
	 public void openB (String url){
		 driver.get(url);
	 }
	    public void clickOnButtonNew () {
			System.setProperty("webdriver.chrome.driver",
					"D:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
			wait = new WebDriverWait(driver, 4000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("button.button.new-test-case-button.blue.right")));
			buttonNew.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("select.dropdown.full-width.test-case-type")));

	    }
}