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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ExecuteOfTestCase {
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
		wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//span[.='geocode']")));
		WebElement testCase = driver
				.findElement(By
						.xpath("//span[.='geocode']"));
		testCase.click();
		wait.withTimeout(30, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.button.green.case-view-execute-button")));
		WebElement editTestCaseButton = driver
				.findElement(By.cssSelector("button.button.green.case-view-execute-button"));
		editTestCaseButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.card.blue.modal-dialog ")));
		Select environments=new Select(driver.findElement(By.cssSelector("select.dropdown.full-width.case-executor-environment ")));
		environments.selectByValue("ST");
		WebElement tenant = driver.findElement(By.cssSelector("input.text-input.full-width.case-executor-tenant-input "));
		tenant.sendKeys("hmm-test");
		WebElement username = driver.findElement(By.cssSelector("input.text-input.full-width.case-executor-username-input "));
		username.sendKeys("stfuser");
		WebElement password = driver.findElement(By.cssSelector("input.password-input.full-width.case-executor-password-input "));
		password.sendKeys("hmmtest");
		WebElement importExET=driver.findElement(By.cssSelector("input.checkbox-button"));
		if(importExET.isSelected()){			
			Select packageET = new Select(driver.findElement(By.cssSelector("select.dropdown.full-width.case-executor-packages-folder")));
			packageET.selectByVisibleText("test_subpackage");
				}
			else{
				System.out.println("The checkbox 'Store the results in Enterprise tester' is not selected by default!");
			}
		WebElement executeButton = driver.findElement(By.cssSelector("button.button.blue.case-executor-execute-button"));
		executeButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.alerts ")));
		try { 
			driver.findElement(By.cssSelector("div.alerts "));
			System.out.println("The success message is available");
			}
		catch (NotFoundException error){	
			System.out.println("The success message is not available.");
			}
		}		
	}
