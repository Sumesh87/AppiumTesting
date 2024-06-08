package draft;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ScrollAction extends BaseTestOld {



    @Test
    public void scrollToEndAction() throws InterruptedException, MalformedURLException
    {
        // Click on Views
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(1000);

        boolean canScrollMore;
        do{
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of(
                            "left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down",
                            "percent", 3.0
                    ));
        }while (canScrollMore);
    }


}
