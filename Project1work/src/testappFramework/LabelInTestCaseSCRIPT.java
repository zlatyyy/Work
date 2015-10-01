package testappFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LabelInTestCaseSCRIPT {	
	protected WebDriver drivert;
	public LabelInTestCaseSCRIPT(WebDriver driver){
		this.drivert=driver;
	}
	public WebDriverWait wait;
	@FindBy(how = How.CSS, using = "div.button.primary.new-test-case-button.right")
	 private WebElement buttonNew;
	 public void openB(String url){
		drivert.get(url);
	 }
	 public void closeB(){
			drivert.quit();
		 }
	    public void clickOnButtonNew () {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("div.button.primary.new-test-case-button.right")));
			buttonNew.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("select.dropdown.full-width.test-case-type")));
	    }
}