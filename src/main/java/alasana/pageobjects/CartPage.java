package alasana.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import alasana.Abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> CartItems; 
	
	@FindBy(xpath="//*[@class='totalRow']/button")
	WebElement checkout;
	
	public Boolean verifyCartItems(String productName)
	{
		Boolean match= CartItems.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage CheckoutButton()
	{
		checkout.click();
		return new CheckoutPage(driver);
		
	}

}
