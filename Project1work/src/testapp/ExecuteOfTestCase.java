package testapp;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
		driver.get("http://10.75.238.99:3000/cases");
	}
	@Test
	public void editCase() {
		wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='0Edm_import']")));
		WebElement testCase = driver
				.findElement(By
						.xpath("//span[.='0Edm_import']"));
		testCase.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.button.green.case-view-execute-button")));
		WebElement editTestCaseButton = driver
				.findElement(By.cssSelector("button.button.green.case-view-execute-button"));
		editTestCaseButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.card.blue.modal-dialog ")));
		Select environments=new Select(driver.findElement(By.cssSelector("select.dropdown.full-width.case-executor-environment ")));
		environments.selectByValue("ST");
		WebElement tenant = driver.findElement(By.cssSelector("input.text-input.full-width.case-executor-tenant-input "));
		tenant.sendKeys("RMS");
		WebElement username = driver.findElement(By.cssSelector("input.text-input.full-width.case-executor-username-input "));
		username.sendKeys("rmsUser");
		WebElement password = driver.findElement(By.cssSelector("input.password-input.full-width.case-executor-password-input "));
		password.sendKeys("P@ssword1");
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
