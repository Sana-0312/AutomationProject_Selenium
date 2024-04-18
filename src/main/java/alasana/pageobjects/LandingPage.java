package alasana.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import alasana.Abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement Email = driver.findElement(By.id("userEmail"));
	//PageFactory
	@FindBy(id="userEmail")
	WebElement Email;
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue Login(String email,String password) 
	{
		Email.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	public String geterrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	
	

}
