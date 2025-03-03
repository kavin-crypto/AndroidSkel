package test_case;


import appLaunch.appInvoke;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.cartPage;
import pageObject.productsPage;


public class mainCase extends appInvoke {

    public productsPage productsPage;
    public cartPage cartPage;
    public SoftAssert softAssert;

    @DataProvider
    public Object[][] loginData() {
        // the reason we use Object[][] is because we can pass multiple data types like String,int to the test case
        return new Object[][]{{"Aruba", "Ethi", "Male"}};
    }


    @Test(groups = {"smoke"})
    public void errorValidationLoginScreen() {
        String toastMsg = loginPage.getToastMessage();
        Assert.assertEquals(toastMsg, "Please enter your name");

    }

    @Test(dataProvider = "loginData", dependsOnMethods = "errorValidationLoginScreen", groups = {"smoke"})
    public void loginScreen(String countryName, String userName, String gender) {
        loginPage.selectCountry(countryName);
        String selectedCountry = loginPage.getSelectedCountry();
        Assert.assertEquals(selectedCountry, countryName);
        loginPage.enterName(userName);
        loginPage.gender(gender);
        productsPage = loginPage.shopButton();
    }

    //alwaysRun = true
    @Test(dependsOnMethods = "loginScreen")
    public void errorValidationProductsScreen() {
        //softAssert = new SoftAssert();
        String toastMessage = productsPage.toastMessage();
        Assert.assertEquals(toastMessage, "Please add some product at first");
        //softAssert.assertAll();
    }

    @Test(dependsOnMethods = "errorValidationProductsScreen", groups = {"smoke"})
    public void productSelection() {
        String[] productNeeded = {"PG 3", "Jordan Lift Off"};
        int count = productsPage.productSelection(productNeeded);
        int cartCount = productsPage.productCountOnCart();
        Assert.assertEquals(count, cartCount);
        cartPage = productsPage.CartButton();
    }

    @Test(dependsOnMethods = "productSelection")
    public void cartValidation() {
        double totalAmountCheck = cartPage.productTotalCheck();
        double tot = cartPage.totalCheck();
        Assert.assertEquals(totalAmountCheck, tot);
    }

    @Test(dependsOnMethods = "cartValidation")
    public void cartCheckout() {
        cartPage.checkBoxAndTermsFunc();
        cartPage.checkoutFunc();
    }


}