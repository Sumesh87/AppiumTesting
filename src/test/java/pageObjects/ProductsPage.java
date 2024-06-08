package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class ProductsPage extends AndroidActions {
    AndroidDriver driver;

    public ProductsPage(AndroidDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    private List<WebElement> productElement;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
    private List<WebElement> addToCartBtn;

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartBtn;



    public void scrollToProduct(String name) throws InterruptedException {
        scrollToElement(name);
        Thread.sleep(2000);
    }

    public void selectProduct(String name) throws InterruptedException {
        int productCount = productElement.size();
        System.out.println("The product size is : " + productCount);

        for (int i = 0; i < productCount; i++) {
            String productName = productElement.get(i).getText();
            if (productName.equals(name)) {
                addToCartBtn.get(i).click();
                System.out.println(name + " is added to Cart");
            }
        }
        Thread.sleep(2000);
    }

    public CartPage navigateToCart() throws InterruptedException {
        cartBtn.click();
        return new CartPage(driver);
    }







}
