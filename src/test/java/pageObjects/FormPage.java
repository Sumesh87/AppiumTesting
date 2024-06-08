package pageObjects;

import draft.MobileActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    String countryName = "Bahrain";

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;

    @AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryField;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopBtn;

    public void setNameField(String name) throws InterruptedException {
        nameField.sendKeys(name);
        Thread.sleep(1500);
    }

    public void setGender(String name) throws InterruptedException {
        femaleOption.click();
        Thread.sleep(1500);
    }

    public void setCountry(String name) throws InterruptedException {
        countryField.click();
        Thread.sleep(1500);
        scrollToElement(name);
        Thread.sleep(1500);
        clickElement("dynamicxpath", "//android.widget.TextView[@text='", countryName);
    }

    public ProductsPage clickShop() throws InterruptedException {
       shopBtn.click();
       Thread.sleep(1500);
       return new ProductsPage(driver);
    }



}
