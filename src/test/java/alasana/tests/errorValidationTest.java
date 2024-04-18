package alasana.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import alasana.TestComponenets.BaseTest;
import alasana.TestComponenets.Retry;
import alasana.pageobjects.CartPage;
import alasana.pageobjects.ProductCatalogue;


public class errorValidationTest extends BaseTest {

	@Test (groups= {"errorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation()  {
		
		landingpage.Login("alasana@gmail.com", "Sana@321");
		Assert.assertEquals("Incorrect email password.", landingpage.geterrorMessage());
	}
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		String productName= "ZARA COAT 3";
		ProductCatalogue productcatalouge = landingpage.Login("sana301@gmail.com", "Sana@123");
		List<WebElement> products = productcatalouge.getProductList();
		productcatalouge.addProductToCart(productName);
		CartPage cartpage = productcatalouge.goToCart();
		Boolean match = cartpage.verifyCartItems("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
