package testappFramework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class LabelInTestCase {		
	public LabelInTestCaseSCRIPT page;
	@Before
	public void openBr(){
		PageFactory.initElements(new FirefoxDriver(), LabelInTestCaseSCRIPT.class);
		page.openB("http://193.178.152.151:4000/cases");
	}
	@Test
	public void createTestCase() {
	page.clickOnButtonNew();
	}
	@After
	public void close (){
		page.closeB();
	}
}

