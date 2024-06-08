package test;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class AddItemsToCartTest extends BaseTest {


    @Test
    public void AddToCart() throws InterruptedException {
        Thread.sleep(5000);
        String productXpath = "com.androidsample.generalstore:id/productName";
        String addToCartBtn = "com.androidsample.generalstore:id/productAddCart";
        String CartBtn = "com.androidsample.generalstore:id/appbar_btn_cart";

        loginInCart();

        mobileActions.scrollToElement("Jordan 6 Rings");
        Thread.sleep(2000);

        int productCount = driver.findElements(By.id(productXpath)).size();
        System.out.println("The product size is : " + productCount);

        for (int i = 0; i < productCount; i++) {
            String productName = driver.findElements(By.id(productXpath)).get(i).getText();
            if (productName.equals("Jordan 6 Rings")) {
                driver.findElements(By.id(addToCartBtn)).get(i).click();
            }
        }

        mobileActions.clickElement("id", CartBtn, "");
        Thread.sleep(5000);

        mobileActions.waitForAttributeContains("com.androidsample.generalstore:id/toolbar_title", "Cart");

        String lastProductAdded = mobileActions.getText("id", "com.androidsample.generalstore:id/productName");
        System.out.println("The product is : " + lastProductAdded);
        Assert.assertEquals(lastProductAdded, "Jordan 6 Rings");


        String termButton = "com.androidsample.generalstore:id/termsButton";
        String okButton = "android:id/button1";
        String completePurchase = "com.androidsample.generalstore:id/btnProceed";

        Thread.sleep(1000);
        // Long press on term button
//        WebElement element=driver.findElement(By.id(termButton));
//       mobileActions.longClickElement(element, 5);

        // click on ok button
//        mobileActions.clickElement("id", okButton,"");

        // click on purchase button
        mobileActions.clickElement("id", completePurchase, "");

        Thread.sleep(3000);

        //switchWebview();
    }

    public void switchWebview() throws InterruptedException {
        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts) {
            if (contextName.contains("WEBVIEW")) {
                driver.context("WEBVIEW_com.androidsample.generalstore");
            }
        }
        driver.findElement(By.name("q")).sendKeys("Testing mobile");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        Thread.sleep(3000);
     }

    public void loginInCart () throws InterruptedException {
        String nameTxt = "com.androidsample.generalstore:id/nameField";
        String genderRadioBtn = "//android.widget.RadioButton[@text='Female']";
        String countryDrpdwn = "com.androidsample.generalstore:id/spinnerCountry";
        String shopBtn = "com.androidsample.generalstore:id/btnLetsShop";
        String countryName = "Bahrain";

        // Type name
        mobileActions.typeData("id", nameTxt, "Ahana S");
        // Select gender
        mobileActions.clickElement("xpath", genderRadioBtn, "");
        // Click on Country
        mobileActions.clickElement("id", countryDrpdwn, "");
        // Scroll to Country
        mobileActions.scrollToElement(countryName);
        // Select Country
        mobileActions.clickElement("dynamicxpath", "//android.widget.TextView[@text='", countryName);
        // Click on Shop
        mobileActions.clickElement("id", shopBtn, "");
    }


}
