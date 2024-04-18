package alasana.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import alasana.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\alasana\\resources\\GlobalData.properties");
		prop.load(fis);
		//java ternary operator -> condition ? argument1(if true) : argument2(if false)
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :  prop.getProperty("browser");
	    //String browserName = prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getJasonData(String filepath) throws IOException
	{
		String jasonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jasonContent,new TypeReference<List<HashMap<String,String>>>(){});
		return data;	
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png";	
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	@AfterMethod(alwaysRun=true)
	public void Close()
	{
		driver.close();
	}
	

}
