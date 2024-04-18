package alasana.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import alasana.TestComponenets.BaseTest;
import alasana.TestComponenets.Retry;
import alasana.pageobjects.CartPage;
import alasana.pageobjects.CheckoutPage;
import alasana.pageobjects.LandingPage;
import alasana.pageobjects.ProductCatalogue;
import alasana.pageobjects.ordersPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class E2ETest extends BaseTest {
	String productName= "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"purchase"},retryAnalyzer=Retry.class) 
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		//String productName= "ZARA COAT 3";
		ProductCatalogue productcatalouge = landingpage.Login(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalouge.getProductList();
		productcatalouge.addProductToCart(input.get("productName"));
		CartPage cartpage = productcatalouge.goToCart();
		Boolean match = cartpage.verifyCartItems(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.CheckoutButton();
		checkoutpage.selectCountry("India");
		checkoutpage.submitOrder();
		String ConfirmMessage = checkoutpage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void orderHistory()
	{
		ProductCatalogue productcatalouge = landingpage.Login("alasana@gmail.com", "Sana@123");
		ordersPage orderPage =productcatalouge.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderPage(productName));
		
	}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJasonData(System.getProperty("user.dir")+"\\src\\test\\java\\selenium\\data\\data.jason");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
