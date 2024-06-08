package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {


    private  static DriverFactory instance = null;
    private AndroidDriver driver;

    private DriverFactory() {
        // Private constructor to prevent instantiation
    }

    public static synchronized DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    public synchronized AndroidDriver createAndroidDriver(String browser) throws MalformedURLException {

        if (driver == null) {
            if(browser.equalsIgnoreCase("chrome"))
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), getBrowserOptions());
            else
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), getOptions());
        }
        return driver;
    }

    public synchronized UiAutomator2Options getOptions() throws MalformedURLException {
        String apkPath = "D:\\local E\\Softwares to be installed\\mobile app\\General-Store.apk";
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setApp(apkPath);
        return options;
    }
    public synchronized UiAutomator2Options getBrowserOptions() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        String chromeDriverPath = WebDriverManager.chromedriver().getDownloadedDriverPath();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setCapability("browserName", "Chrome");
        options.setChromedriverExecutable(chromeDriverPath);
        return options;
    }

    public synchronized void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

