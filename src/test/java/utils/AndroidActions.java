package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AndroidActions
{
    private final AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
    }


    public void handleAlerts() throws MalformedURLException, InterruptedException {

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

    public void waitForAttributeContains(String xpath, String text)
    {
        WebElement element =driver.findElement(By.id(xpath));
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions
                .attributeContains(element, "text", text)
        );
    }


    public void longClickElement(WebElement element, int duration) {
        String elementId = ((RemoteWebElement) element).getId();
        Map<String, Object> params = Map.of("elementId", elementId, "duration", duration);
        executeMobileLongClick(params);
    }

    private void executeMobileLongClick(Map<String, Object> params) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("mobile: longClickGesture", params);
    }

    public void scrollToElement(String text)
    {
        // Scroll to Country
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector())" +
                        ".scrollIntoView" +
                        "(text(\""+ text + "\"));"));
    }

    public void clickElement(String type, String locatorName, String value) throws InterruptedException {
        switch (type)
        {
            case "xpath":
                driver.findElement(By.xpath(locatorName)).click();
                break;
            case "dynamicxpath":
                driver.findElement(By.xpath(locatorName + value +"']")).click();
                break;
            case "id":
                driver.findElement(By.id(locatorName)).click();
                break;

        }
        Thread.sleep(1500);
    }

    public void typeData(String type, String locatorName, String value) throws InterruptedException {
        switch (type)
        {
            case "xpath":
                driver.findElement(By.xpath(locatorName)).sendKeys(value);
                break;
            case "id":
                driver.findElement(By.id(locatorName)).sendKeys(value);
                break;
        }
        Thread.sleep(1500);
    }

    public String getAttributeValue(String type, String locatorName, String key) throws InterruptedException {
        String attr_value="";
        switch (type)
        {
            case "xpath":
                attr_value= driver.findElement(By.xpath(locatorName)).getAttribute(key);
                break;
            case "id":
                attr_value= driver.findElement(By.id(locatorName)).getAttribute(key);
                break;
        }
        Thread.sleep(1500);
        return  attr_value;
    }

    public String getText(String type, String locatorName) throws InterruptedException {
        String value="";
        Thread.sleep(1500);
        switch (type)
        {
            case "xpath":
                value= driver.findElement(By.xpath(locatorName)).getText();
                break;
            case "id":
                value= driver.findElement(By.id(locatorName)).getText();
                break;
        }
        return  value;
    }

    public void navigateToHomePage() throws InterruptedException {
        driver.executeScript("mobile: startActivity",
                ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
        Thread.sleep(5000);
    }

    public static void grantPermission(AndroidDriver driver, String packageName, String permission) {
        Map<String, Object> args = new HashMap<>();
        args.put("command", "pm grant " + packageName + " " + permission);
        driver.executeScript("mobile: shell", args);
    }

    public static void startActivity(AndroidDriver driver, String packageName, String activityName) {
        Map<String, Object> args = new HashMap<>();
        args.put("command", "am start -n " + packageName + "/" + activityName);
        driver.executeScript("mobile: shell", args);
    }
}
