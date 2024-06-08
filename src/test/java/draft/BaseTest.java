package draft;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public  AndroidDriver driver;
    public AppiumDriverLocalService service;
    public MobileActions mobileActions;

    @BeforeClass
    public void setUpAppium() throws InterruptedException, MalformedURLException {
        System.out.println("startAppiumService is called !!!!!!!!!!!!!!!!!!!!");
        startAppiumService();
        initializeStoreDriver();
        mobileActions = new MobileActions(driver);
    }

    @AfterClass
    public void tearDownAppium() {

        DriverFactory.getInstance().quitDriver();
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
        // Initialize AndroidDriver
        try {
            driver= DriverFactory.getInstance().createAndroidDriver();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void stopAppiumService() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
