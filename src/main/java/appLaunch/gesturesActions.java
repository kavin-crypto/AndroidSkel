package appLaunch;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class gesturesActions {

    AndroidDriver driver;
    public gesturesActions(AndroidDriver driver){
        this.driver = driver;
    }

    public void explicitWait(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.attributeContains(element, attribute,value));//String attribute, String value
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void explicitWaiting(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void longPressAction(WebElement x){
        //RemoteWebElement it support both web and mobile elements
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement)x).
                        getId(),"duration",3000));

    }

    public void scrollByText(String textToFind) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + textToFind + "\"));"));
    }

    public void swipeAction(WebElement y,String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
                ((RemoteWebElement)y).getId(), "direction", direction, "percent", 0.01));
    }

    public  void scrollUpAction(){
        boolean scrolleUp;
        do {
            scrolleUp = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 300, // Adjusted top value
                    "width", 200, "height", 200,
                    "direction", "up",
                    "percent", 0.5)); // Reduced percent for smaller scrolls
        } while (scrolleUp);
    }

    public void scrollDownAction(){

        boolean scrollDown;
        do {
            scrollDown = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down", "percent", 1.0));
        }while (scrollDown);
    }

    public void dragDropAction(WebElement source, int x, int y){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) source).
                                getId(), "endX", x,
                        "endY", y));
    }

    public void SwipeActionCo(WebElement so,int x1, int y1, int x2, int y2){
//        TouchAction action = new TouchAction(driver);
//        action.longPress(PointOption.point(x1, y1)).moveTo(PointOption.point(x2, y2)).release().perform();

        Actions actions = new Actions(driver);

        actions.moveToElement(so, x1, y1) // Move to starting point.clickAndHold() // Press down
                .moveByOffset(x2 - x1, y2 - y1) // Move to the end point
                .release() // Release the touch
                .perform(); // Execute the action
    }

    public void appPackageAndAppActivities(String appPackageAndAppActivities){
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of("intent",appPackageAndAppActivities));
    }

}
