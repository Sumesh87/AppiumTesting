package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener {

    ExtentTest test;
    ExtentReports extent= ExtentReportNG.getReportObject();
    AppiumDriver driver;

     @Override
    public void onTestStart(ITestResult result) {
         test=extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        test.fail(result.getThrowable());


        try {
            driver= (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            test.addScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName(), driver),
                    result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

