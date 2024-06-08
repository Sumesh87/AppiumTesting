package test;

import base.BrowserBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BrowserTest  extends BrowserBaseTest {

    @Test
    public void testchrome() throws InterruptedException {

        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("name")).sendKeys("Sumesh M S");
        //driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        Thread.sleep(6000);


    }


}
