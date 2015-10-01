package testappFramework;
import java.util.NoSuchElementException;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;
public class LabelInTestCaseSCRIPT {	
	private final WebDriver driver;
	public LabelInTestCaseSCRIPT(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public WebDriverWait wait;
	@FindBy(how = How.CSS, using = "div.button.primary.new-test-case-button.right")
	 public WebElement buttonNew;
	 public void openB(String url){
		driver.get(url);
	 }
	    public void clickOnButtonNew () {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("div.button.primary.new-test-case-button.right")));
			buttonNew.click();
			//wait.until(ExpectedConditions.presenceOfElementLocated(By
			//		.cssSelector("select.dropdown.full-width.test-case-type")));

	    }
}