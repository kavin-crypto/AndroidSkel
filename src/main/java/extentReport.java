import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReport {

    static ExtentReports extent;
    public static ExtentReports getReport() {

        String path = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter rep = new ExtentSparkReporter(path);
        rep.config().setReportName("Android Automation Results");

        rep.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(rep);
        extent.setSystemInfo("Tester", "Kavin");

        return extent;


    }

}
