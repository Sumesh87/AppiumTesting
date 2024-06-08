package draft;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTestOld {

    public  AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void setUpAppium() throws InterruptedException, MalformedURLException {
        startAppiumService();
       // initializeDriver();
        initializeStoreDriver();
       // handleAlerts();
    }

    @AfterClass
    public void tearDownAppium() {
        quitDriver();
        stopAppiumService();
    }

    private void startAppiumService() {
        String mainJsPath = "C:\\Users\\sumes\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(mainJsPath))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
        // Wait for Appium server to start, you may adjust the sleep time as needed
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initializeStoreDriver() throws MalformedURLException {
        String apkPath = "D:\\local E\\Softwares to be installed\\mobile app\\General-Store.apk";
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setApp(apkPath);

        // Initialize AndroidDriver
        try {


            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void initializeDriver() throws MalformedURLException {
        String apkPath = "D:\\local E\\Learnings\\Mobile Automation\\AppiumMobileProject\\src\\test\\resources\\ApiDemos-debug.apk";
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setApp(apkPath);
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity(".ApiDemos");
        // Initialize AndroidDriver
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw e;
        }
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

    public void setAppProperties()
    {

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
        Thread.sleep(2500);
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

    private void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void stopAppiumService() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
