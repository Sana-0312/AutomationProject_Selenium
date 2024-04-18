package alasana.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import alasana.Abstractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents  {
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//section[contains(@class,'ta-results')]/button)[2]")
	WebElement SelectCountry;
	
	@FindBy(css=".actions a")
	WebElement PlaceOrder;
	
	@FindBy(css=".hero-primary")
	WebElement ConfirmMessage;
	
	public void selectCountry(String countryName )
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		SelectCountry.click();
	}
	public void submitOrder()
	{
		PlaceOrder.click();
	}
	public String getConfirmationMessage()
	{
		return ConfirmMessage.getText();
		
	}
	
	

}
