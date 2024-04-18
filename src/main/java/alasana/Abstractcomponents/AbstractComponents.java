package alasana.Abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import alasana.pageobjects.CartPage;
import alasana.pageobjects.ordersPage;

public class AbstractComponents {
	WebDriver driver;
	public AbstractComponents(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="button[routerlink*='cart']")
	WebElement Cartbutton;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderButton;
	
	public CartPage goToCart()
	{
		Cartbutton.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	public ordersPage goToOrderPage()
	{
		orderButton.click();
		ordersPage orderPage = new ordersPage(driver);
		return orderPage;
	}
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void waitForElementToDisappear(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	

}
