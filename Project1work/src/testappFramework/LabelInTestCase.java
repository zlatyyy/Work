package testappFramework;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
public class LabelInTestCase extends LabelInTestCaseSCRIPT {		
	private LabelInTestCaseSCRIPT page;
	@Before
	public void openBrowser() {
		page = PageFactory.initElements(driver, LabelInTestCaseSCRIPT.class);
		page.openB("http://10.75.238.99:3000/cases");
		driver.get("");
	}
	@Test
	public void createTestCase() {
	page.clickOnButtonNew();
	}
}

