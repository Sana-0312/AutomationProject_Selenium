package alasana.TestComponenets;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import alasana.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReportsNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//using threadlocal makes the object thread safe ->means no matter if 
	//you run concurrently, each object creation will have its own thread so it wont interrupt the other overriding variable
	//we have to tell the threadlocal what kind of objects we have place in the threadlocal for every entry
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		//so whenever we create a new object, we have push that object into the treadlocal
		extentTest.set(test);//whenever the object is set into the thread local ,it assigns one unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// when test fails the below method gives the error message
		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filepath = null;
		//Take screenshot
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
