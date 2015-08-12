package testapp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
public class CreateTestStep {
	public static final String USERNAME="haemimont1";
	public static final String AUTOMATE_KEY="cUX4WagqZq59SpTz9MSg";
	public static final String URL="http://"+USERNAME+":"+AUTOMATE_KEY+"@hub.browserstack.com/wd/hub";
	WebDriver driver;
	  @Before
	  public void setUp() throws Exception {  
	    DesiredCapabilities capability = DesiredCapabilities.chrome();
	    capability.setPlatform(Platform.WINDOWS);
	    capability.setCapability("build", "JUnit - Sample");
	    driver = new RemoteWebDriver(new URL(URL),capability);
	  }
	  @Test
	  public void createStep(){
		driver.get("http://193.178.152.130:8090/steps");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("button.new-test-step-button.blue.right")));
		WebElement newCreationStep = driver.findElement(By
				.cssSelector("button.new-test-step-button.blue.right"));
		newCreationStep.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("select.dropdown.test-step-type.full-width")));
		WebElement nameOfCreatingStep = driver.findElement(By
				.cssSelector("input.text-input.full-width.test-step-name"));
		nameOfCreatingStep.sendKeys("Step1");
		Select typeOfCreatingStep = new Select(driver.findElement(By.cssSelector("select.dropdown.test-step-type.full-width")));
		typeOfCreatingStep.selectByValue("REST");
		wait.withTimeout(50, TimeUnit.SECONDS);
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
	}
