package test;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.FormPage;
import pageObjects.ProductsPage;
import utils.AppiumUtils;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EcommerceTest extends BaseTest {


    @BeforeMethod
    public void setHomePage() throws InterruptedException {

        mobileActions.navigateToHomePage();
    }

    @Test
    public void FillForm() throws InterruptedException {
        FormPage form =new FormPage(driver);
        form.setNameField("Sumesh");
        form.setGender("female");
        form.setCountry("Bahrain");

        ProductsPage productsPage=form.clickShop();
        Thread.sleep(3000);
        productsPage.scrollToProduct("Jordan Lift Off");
        productsPage.selectProduct("Jordan Lift Off");
    }

    @Test(dataProvider = "getData")
    public void FillFormPositiveFlow(HashMap<String,String > input) throws InterruptedException {
        FormPage form =new FormPage(driver);
        form.setNameField(input.get("name"));
        form.setGender(input.get("gender"));
        form.setCountry(input.get("country"));

        ProductsPage productsPage=form.clickShop();
        Thread.sleep(3000);
        productsPage.scrollToProduct("Jordan 6 Rings");
        productsPage.selectProduct("Jordan 6 Rings");

        CartPage cartPage=productsPage.navigateToCart();
        cartPage.validatePurchase();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String >> map= AppiumUtils.getJsonData();

        return new Object[][]{{map.get(0)}, {map.get(1)}};
    }
}
