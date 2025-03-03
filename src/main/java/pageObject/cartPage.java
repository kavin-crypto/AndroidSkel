package pageObject;

import appLaunch.gesturesActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class cartPage extends gesturesActions {

    AndroidDriver driver;

    public cartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//*[@text='Cart']")
    private WebElement getCartPage;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> getProductsPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement getTotalAmount;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement getCheckBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement getTerms;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement getCloseButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement getProceedButton;


    public double productTotalCheck() {
        explicitWait(getCartPage);
        List<WebElement> cartProducts = getProductsPrice;
        double total = 0;
        for (int i = 0; i < cartProducts.size(); i++) {
            String productPrice = cartProducts.get(i).getText();
            Double sumAmount = Double.parseDouble(productPrice.substring(1));
            total = total + sumAmount;
        }
        return total;
    }

    public double totalCheck(){
        String tot = getTotalAmount.getText();
        Double totalAmount = Double.parseDouble(tot.substring(1));
        return totalAmount;
    }

    public void checkBoxAndTermsFunc(){
        getCheckBox.click();
        longPressAction(getTerms);
        getCloseButton.click();
    }

    public void checkoutFunc(){
        getProceedButton.click();
        driver.navigate().back();
    }

}
