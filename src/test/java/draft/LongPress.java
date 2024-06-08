package draft;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LongPress  extends BaseTestOld {



    @Test
    public void LongPressGesture() throws InterruptedException, MalformedURLException {

        // Click on Views
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(1000);

        // Click on expandable List
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        Thread.sleep(1000);

        // Click on Custom Adapter
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        Thread.sleep(5000);

        // Long Press on "People Names"
        String peopleName="//android.widget.TextView[@text='People Names']";
        WebElement ele= driver.findElement(AppiumBy.xpath(peopleName));

        longClickElement(ele, 2000); // Long click for 2 seconds

        // Long Press on "People Names"
        String sampleMenu="android:id/title";
        String menuTxt=driver.findElement(AppiumBy.id(sampleMenu)).getText();
        System.out.println("The menu text is : " +  menuTxt);

        Assert.assertEquals(menuTxt,"Sample menu");



   }

}
