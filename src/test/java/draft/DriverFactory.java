package draft;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

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

    public synchronized AndroidDriver createAndroidDriver() throws MalformedURLException {

        if (driver == null) {
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

    public synchronized void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

