package testapp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Verify;
import com.thoughtworks.selenium.condition.Presence;

public class TestSteps {
	WebDriver driver;
	@Before
	public void openBr() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\SELENIUM\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://193.178.152.130:8090/steps");
	}

	/*
	 * @After public void closeBrowser(){ driver.quit(); }
	 */
	 @Test
	public void createStep() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.new-test-step-button.blue.right")));
		WebElement newCreationStep = driver.findElement(By
				.cssSelector("button.new-test-step-button.blue.right"));
		newCreationStep.click();
		WebElement nameOfCreatingStep = driver.findElement(By
				.cssSelector("input.text-input.full-width.test-step-name"));
		nameOfCreatingStep.sendKeys("Step1");
		Select typeOfCreatingStep = new Select(driver.findElement(By
				.cssSelector("select.dropdown.test-step-type.full-width")));
		typeOfCreatingStep.selectByValue("REST");
		WebElement anynchCreatingStep = driver.findElement(By
				.cssSelector("label.checkbox-button-label"));
		anynchCreatingStep.click();
		WebElement urlCreatingStep = driver.findElement(By
				.cssSelector("input.text-input.full-width.test-step-endpoint"));
		urlCreatingStep.sendKeys("/riskanalyses");
		WebElement inputCreatingStep = driver.findElement(By
				.cssSelector("textarea.text-area.full-width.test-step-input"));
		inputCreatingStep
				.sendKeys("{\"type\": \"HTTP\",\"parameters\": null,\"headers\": [{\"key\": \"Authorization\",\"data\":\"BearerYm9zY236Ym9zY28=\"},{\"key\": \"Content-Type\",\"data\": \"application/json\"}],\"body\":\"null\",\"method\": \"POST\"}");
		WebElement resultCreatingStep = driver
				.findElement(By
						.cssSelector("textarea.text-area.full-width.no-wrap.test-step-expected-result"));
		resultCreatingStep
				.sendKeys("{\"type\": \"HTTP\",\"executionTimeMillis\": 0,\"statusCode\":200,\"payload\":null}");
		WebElement buttonCreateStep = driver.findElement(By
				.cssSelector("button.button.blue.test-step-create-save"));
		buttonCreateStep.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.button.blue.step-view-edit-button")));
		WebElement verifyCreation = driver.findElement(By
				.cssSelector("h2.step-view-name"));
		verifyCreation.isDisplayed();
	} 

	@Test
	public void editStep() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.button.actions-cell-edit")));
		WebElement editStep=driver.findElement(By.cssSelector("button.button.actions-cell-edit"));
		editStep.click();
		wait.withTimeout(20, TimeUnit.SECONDS);
		WebElement stepTypeRest = driver.findElement(By.cssSelector("select.dropdown.test-step-type.full-width"));
			WebElement editStepName = driver.findElement(By.cssSelector("input.text-input.full-width.test-step-name"));
			editStepName.clear();
			editStepName.sendKeys("name_editMode");
			WebElement editStepUrl = driver.findElement(By.cssSelector("input.text-input.full-width.test-step-endpoint"));
			editStepUrl.clear();
			editStepUrl.sendKeys("/endpoint_edit");
			WebElement editStepExResult = driver.findElement(By.cssSelector("textarea.text-area.full-width.no-wrap.test-step-expected-result"));
			editStepExResult.clear();
			editStepExResult.sendKeys("{\"type\": \"CSVFILE\",\"executionTimeMillis\": 5000,\"statusCode\":200,\"payload\":null}");
			WebElement editStepDescription = driver.findElement(By.cssSelector("textarea.text-area.full-width.test-step-description"));
			editStepDescription.clear();
			editStepDescription.sendKeys("Edit description for this test step.");
			List<String> list = new ArrayList<String>();
			list.add(editStepName.getAttribute("value"));
			list.add(editStepUrl.getAttribute("value"));
			list.add(editStepExResult.getAttribute("value"));
			list.add(editStepDescription.getAttribute("value"));
			System.out.println(list);
			WebElement saveEditedStep = driver.findElement(By.cssSelector("button.button.blue.test-step-create-save"));
			saveEditedStep.click();		
			wait.withTimeout(90, TimeUnit.SECONDS);
			List listViewMode = new ArrayList<WebElement>();
			listViewMode.add(driver.findElement(By.cssSelector("div.col>h2")));
			listViewMode.add(driver.findElement(By.cssSelector("div.step-view-endpoint-group>div.step-view-endpoint")));
			listViewMode.add(driver.findElement(By.cssSelector("div.step-view-expected-result>div.formatted-text")));
			listViewMode.add(driver.findElement(By.cssSelector("span.step-view-description")));
			Assert.assertTrue("Equal",list.equals(listViewMode));
	}
}
