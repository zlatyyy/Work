package testappFramework;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LabelInTestCaseSCRIPT extends
		LoadableComponent<LabelInTestCaseSCRIPT> {
	private WebDriver driver;
	private String url = "http://10.75.238.99:3000/cases";
	private String title = "RMS(one) Test Application";
	private String label;
	public WebDriverWait wait;
	@FindBy(how = How.CSS, using = "div.button.primary.new-test-case-button.right")
	private WebElement buttonNew;
	@FindBy(how = How.XPATH, using = "//div[@class='tag-list-input-wrapper test-case-labels']//input[@class='input']")
	private WebElement labelDropdown;
	@FindBy(how = How.CSS, using = "div.button.primary.test-case-create-save")
	private WebElement createCase;
	@FindBy(how = How.CSS, using = "input.input.full-width.test-case-name")
	private WebElement fillName;
	@FindBy(how = How.CSS, using = "select.dropdown.full-width.test-case-type")
	private WebElement fillType;
	@FindBy(how = How.CSS, using = "span.tag-list-item-text")
	private WebElement addedLabel;
	public LabelInTestCaseSCRIPT() {
		super();
		this.driver = new FirefoxDriver();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		this.driver.get(url);
	}

	@Override
	protected void isLoaded() {
		Assert.assertTrue(driver.getTitle().equals(title));
	}

	public void openB() {
		this.driver.get(url);
	}

	public void closeB() {
		this.driver.quit();
	}

	public void enterName(String testCaseName) {
		fillName.sendKeys(testCaseName);
	}

	public void enterType() {
		Select dropdownType = new Select(fillType);
		dropdownType.selectByValue("FUNC");
	}

	public void clickOnButtonNew() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		buttonNew.click();
	}

	public void enterLabel(String label) throws Throwable {
		this.label=label;
		labelDropdown.sendKeys(label);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
	}

	public void clickOnCreateCaseButton() {
		createCase.click();
	}

	public void verifyAddedLabel() {
		String visibleLabel = addedLabel.getText();
		Assert.assertTrue("The added label is not the same as the entered label",visibleLabel.equals(label));
		System.out.println(label);
	}
}