package testappFramework;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class LabelInTestCase {		
	public LabelInTestCaseSCRIPT page;
	@Test
	public void createTestCase() {
	page.openB("http://193.178.152.151:4000/cases");
	page.clickOnButtonNew();
	}
}

