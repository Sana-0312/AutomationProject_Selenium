package alasana.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import alasana.Abstractcomponents.AbstractComponents;

public class ordersPage extends AbstractComponents{
	WebDriver driver;
	public ordersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> pNameList;
	
	public Boolean verifyOrderPage(String productName)
	{
		Boolean match= pNameList.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}

}
