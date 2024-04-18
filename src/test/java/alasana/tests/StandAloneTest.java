package alasana.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import alasana.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	@Test
	public void SubmitOrderTest()
	{
		String productName= "ZARA COAT 3";
		String Country = "India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		//login
		driver.findElement(By.id("userEmail")).sendKeys("alasana@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sana@123");
		driver.findElement(By.id("login")).click();
		//add to cart
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.xpath("//h5/b")).getText().equals(productName)).findFirst().orElse(null);	
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
	 
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartproducts= driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match= cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//*[@class='totalRow']/button")).click();
		//section[@class='ta-results list-group ng-star-inserted'] //span
		/*driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countries = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted'] //span"));
		WebElement C = countries.stream().filter(country-> country.getText().equalsIgnoreCase(Country)).findFirst().orElse(null);
		C.click();*/
		WebElement country = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(country, "India").build().perform();
		driver.findElement(By.xpath("(//section[contains(@class,'ta-results')]/button)[2]")).click();
		driver.findElement(By.cssSelector(".actions a")).click();
		String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		

	}

}
