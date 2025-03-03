package appLaunch;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageObject.loginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class appInvoke {

    public AppiumDriverLocalService service;
    public static AndroidDriver driver;
    public loginPage loginPage;
    public Properties pro;

    @BeforeClass(alwaysRun = true)
    public void invoke() throws URISyntaxException, IOException {

        pro = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resource/data.properties");
        pro.load(file);
        String ipAddress =  pro.getProperty("ipAddress");
        int port = Integer.parseInt(pro.getProperty("port"));

        service = new AppiumServiceBuilder().withAppiumJS(new File(pro.getProperty("appiumServerLocation")))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(pro.getProperty("androidDeviceName"));
        //options.setChromedriverExecutable("/Users/kavin/Downloads/chromedriver-mac-x64/chromedriver");
        //instead of this: options.setApp("/Users/kavin/Desktop/app_brushup/app_sample/ecomApplication/src/main/java/resource/General-Store.apk");
        options.setApp(System.getProperty("user.dir")+"/src/main/java/resource/General-Store.apk");
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new loginPage(driver);

    }

    @AfterClass(alwaysRun = true)
    public void appStartStop(){

        driver.quit();
        service.stop();
    }

    public String getScreenshotPath(String testCaseName, AndroidDriver driver) throws IOException
    {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/Screenshot/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
        //1. capture and place in folder //2. extent report pick file and attach to report

    }

}
