package draft;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class FirstMobileTest extends BaseTestOld {



    @Test
    public void launchMobApp() throws MalformedURLException, InterruptedException {

        String continuePath="//android.widget.Button[@resource-id='com.android.permissioncontroller:id/continue_button']";
        String okButtonPath="//android.widget.Button[@text='OK']";
        if(driver.findElements(AppiumBy.xpath(continuePath)).size()>0)
        {
            System.out.println("Continue exists");;
            driver.findElement(AppiumBy.xpath(continuePath)).click();
        }
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath(okButtonPath)).click();
        Thread.sleep(2000);
    }
}
