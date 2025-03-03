package pageObject;

import appLaunch.gesturesActions;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class loginPage extends gesturesActions {

    AndroidDriver driver;

    public loginPage(AndroidDriver driver) {
        /* by super method we can call the parent class constructor
         by this we can use the driver in the parent class */
        super(driver);
         /* by 'this' (current class instance by this step we are giving
        life to driver for entire class) we can use the driver in the current class*/
        this.driver = driver;
       /* By initElements method helps to write a locator in the format of
          driver.findElement(By.id("android:id/text1")) by AppiumFieldDecorator */
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "android:id/text1")
    private WebElement countryDropDownButton;

//    @AndroidFindBy(xpath = "//*[@text='" + countryName + "']")
//    private WebElement countryNameOptions;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopButton;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    private WebElement toastMsg;

    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void gender(String gender) {
        if (gender.contains("Male")) {
            maleOption.click();
        } else {
            femaleOption.click();
        }
    }

    public void selectCountry(String countryName) {
        countryDropDownButton.click();
        scrollByText(countryName);
        driver.findElement(By.xpath("//*[@text='" + countryName + "']")).click();

        //Assert.assertEquals(countryDropDownButton.getText(), countryName);

    }

    public String getSelectedCountry() {
        return countryDropDownButton.getText();
    }

    public String getToastMessage() {
        letsShopButton.click();
        String toastMessage = toastMsg.getDomAttribute("name");
        return toastMessage;
        //Assert.assertEquals(toastMessage, "Please enter your name");//"Please add some product at first"

    }

    public void setActivities(){

        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));

    }

    public productsPage shopButton() {
        letsShopButton.click();
        return new productsPage(driver);

    }

}