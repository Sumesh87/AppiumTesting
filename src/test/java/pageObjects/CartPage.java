package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    private WebElement productNameAdded;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement completePurchase;

    public void validatePurchase() throws InterruptedException {

        Thread.sleep(3000);
        waitForAttributeContains("com.androidsample.generalstore:id/toolbar_title", "Cart");

        String lastProductAdded = productNameAdded.getText();
        System.out.println("The product added to cart is : " + lastProductAdded);
        Assert.assertEquals(lastProductAdded, "Jordan 6 Rings");

        // click on purchase button
        System.out.println("Finally click on Complete purchase button !------");
        completePurchase.click();
        Thread.sleep(4000);
    }



}
