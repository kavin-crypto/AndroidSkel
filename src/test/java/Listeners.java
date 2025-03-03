import appLaunch.appInvoke;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;



public class Listeners extends appInvoke implements ITestListener {

    ExtentTest test;
    AndroidDriver driver;
    ExtentReports extent = extentReport.getReport();


    public void onTestStart(ITestResult result) {
        // ITestResult result holds the information of the test cases which present in mainCase
        test = extent.createTest(result.getMethod().getMethodName());


    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        try {
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());


        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void onFinish(ITestContext context) {

        extent.flush();
    }

}
