package pageObject;

import appLaunch.gesturesActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class productsPage extends gesturesActions {

    AndroidDriver driver;

    public productsPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private List<WebElement> getProductNames;

    @AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
    private List<WebElement> getAddToCartButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/counterText")
    private WebElement getCartCountElement;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement getCartButton;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    private WebElement toastMsg;

    public String toastMessage(){
        getCartButton.click();
        String toastMessage= toastMsg.getDomAttribute("name");
        return toastMessage;
        //Assert.assertEquals(toastMessage, "Please add some product at first");//"Please add some product at first"

    }

    public int productSelection(String[] productNeeded) {
        int count = 0;
        for (String productName : productNeeded) {
            scrollByText(productName);
            WebElement proName = driver.findElement(By.xpath("//*[@text='" + productName + "']"));
            explicitWaiting(proName);

            List<WebElement> productNames = getProductNames;
            List<WebElement> addToCartButtons = getAddToCartButton;
            for (int i = 0; i < productNames.size(); i++) {
                if (productNames.get(i).getText().contains(productName)) {
                    try {
                        addToCartButtons.get(i).click();
                        count++;
                        break;
                    } catch (Exception e) {
                        System.out.println("Button not clickable: " + e.getMessage());
                    }
                }
            }
        }
//        WebElement cartCountElement = getCartCountElement;
//        String cartCountText = cartCountElement.getText();
//        int cartCount = Integer.parseInt(cartCountText);
//        Assert.assertEquals(count, cartCount);
        return count;
    }

    public int productCountOnCart(){
        WebElement cartCountElement = getCartCountElement;
        String cartCountText = cartCountElement.getText();
        int cartCount = Integer.parseInt(cartCountText);
        return cartCount;
        //Assert.assertEquals(count, cartCount);
    }

    public cartPage CartButton(){
        getCartButton.click();
        return new cartPage(driver);
    }
}


