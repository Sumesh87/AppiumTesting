package draft;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddItemsToCart  extends BaseTestOld {

    @Test
    public void AddToCart() throws InterruptedException {
        String productXpath= "com.androidsample.generalstore:id/productName";
        String addToCartBtn="com.androidsample.generalstore:id/productAddCart";
        String CartBtn="com.androidsample.generalstore:id/appbar_btn_cart";

        loginInCart();

        scrollToElement("Jordan 6 Rings");
        Thread.sleep(2000);

       int productCount= driver.findElements(By.id(productXpath)).size();
        System.out.println("The product size is : " + productCount);

       for(int i=0;i<productCount;i++)
       {
           String productName= driver.findElements(By.id(productXpath)).get(i).getText();
           if(productName.equals("Jordan 6 Rings"))
           {
               driver.findElements(By.id(addToCartBtn)).get(i).click();
           }
       }

       clickElement("id", CartBtn,"");
       Thread.sleep(5000);

       WebElement element =driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
       WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
       wait.until(ExpectedConditions
               .attributeContains(element, "text", "Cart")
       );

       String lastProductAdded= getText("id", "com.androidsample.generalstore:id/productName");
        System.out.println("The product is : " + lastProductAdded );
       Assert.assertEquals(lastProductAdded, "Jordan 6 Rings");
    }

    public void loginInCart() throws InterruptedException {
        String nameTxt= "com.androidsample.generalstore:id/nameField";
        String genderRadioBtn="//android.widget.RadioButton[@text='Female']";
        String countryDrpdwn="com.androidsample.generalstore:id/spinnerCountry";
        String shopBtn="com.androidsample.generalstore:id/btnLetsShop";
        String countryName="Bahrain";

        // Type name
        typeData("id",nameTxt, "Ahana S" );
        // Select gender
        clickElement("xpath", genderRadioBtn, "");
        // Click on Country
        clickElement("id", countryDrpdwn, "");
        // Scroll to Country
        scrollToElement(countryName);
        // Select Country
        clickElement("dynamicxpath", "//android.widget.TextView[@text='", countryName);
        // Click on Shop
        clickElement("id", shopBtn, "");
    }

}
