package testappFramework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class LabelInTestCase {
	@Test
	public void createTestCase() throws Throwable {
		LabelInTestCaseSCRIPT page = new LabelInTestCaseSCRIPT();
		page.openB();
		page.clickOnButtonNew();
		page.enterName("TestCase1");
		page.enterType();
		page.enterLabel("Label1");
		page.clickOnCreateCaseButton ();
		page.verifyAddedLabel();
		page.closeB();
	}
}
