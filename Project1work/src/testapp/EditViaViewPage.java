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

import com.thoughtworks.selenium.webdriven.commands.GetAttribute;
public class EditViaViewPage {
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
			WebDriverWait wait= new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.name-cell-text")));
			WebElement viewDetails=driver.findElement(By.cssSelector("span.name-cell-text"));
			viewDetails.click();
			WebElement editStep=driver.findElement(By.cssSelector("button.button.blue.step-view-edit-button"));
			editStep.click();
			WebElement enterName=driver.findElement(By.cssSelector("input.text-input.full-width.test-step-name"));
			enterName.clear();
			enterName.sendKeys("modifiedName");
			WebElement enterURL=driver.findElement(By.cssSelector("input.text-input.full-width.test-step-endpoint"));
			enterURL.clear();
			enterURL.sendKeys("/edmuploads");
			WebElement enterInput=driver.findElement(By.cssSelector("textarea.text-area.full-width.test-step-input"));
			enterInput.clear();
			enterInput.sendKeys("");
			WebElement enterResult=driver.findElement(By.cssSelector("textarea.text-area.full-width.no-wrap.test-step-expected-result"));
			enterResult.clear();
			enterResult.sendKeys("");
			WebElement saveModifiedStep=driver.findElement(By.cssSelector("button.button.blue.test-step-create-save"));
			saveModifiedStep.click();
			WebElement nameDetailsViewAfterEditedStep=driver.findElement(By.cssSelector("h2.step-view-name"));
			Assert.assertEquals("The name is icorrect.", "name_editMode", nameDetailsViewAfterEditedStep.getText());
			WebElement urlDetailsViewAfterEditedStep=driver.findElement(By.cssSelector("div.step-view-endpoint"));
			Assert.assertEquals("The URL is icorrect.", "name_editMode", urlDetailsViewAfterEditedStep.getText());
			WebElement inputDetailsViewAfterEditedStep=driver.findElement(By.cssSelector("div.step-view-input>div.formatted-text"));
			Assert.assertEquals("The input data is icorrect.", "name_editMode", inputDetailsViewAfterEditedStep.getText());
			WebElement expectedResDetailsViewAfterEditedStep=driver.findElement(By.cssSelector("div.step-view-expected-result>div.formatted-text"));
			Assert.assertEquals("The expected result is icorrect.", "name_editMode", expectedResDetailsViewAfterEditedStep.getText());
		}
	}
