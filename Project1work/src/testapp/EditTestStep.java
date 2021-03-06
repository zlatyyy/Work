package testapp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class EditTestStep {
	public static final String USERNAME="haemimont1";
	public static final String AUTOMATE_KEY="cUX4WagqZq59SpTz9MSg";
	public static final String URL="http://"+USERNAME+":"+AUTOMATE_KEY+"@hub.browserstack.com/wd/hub";
	public static void main(String[] args) throws Exception {
		DesiredCapabilities caps=new DesiredCapabilities();
		caps.setCapability("browser", "IE");
		caps.setCapability("browser_version", "11.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "7");
		caps.setCapability("resolution", "1024x768");
		caps.setCapability("browserstack.debug", "true");
			WebDriver driver= new RemoteWebDriver(new URL(URL), caps);
			driver.get("http://193.178.152.130:8090/steps");
			WebDriverWait wait= new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.button.actions-cell-edit")));
			WebElement editStep=driver.findElement(By.cssSelector("button.button.actions-cell-edit"));
			editStep.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select.dropdown.test-step-type.full-width")));
			WebElement stepType = driver.findElement(By.cssSelector("select.dropdown.test-step-type.full-width"));
			Assert.assertFalse(stepType.isDisplayed());
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
