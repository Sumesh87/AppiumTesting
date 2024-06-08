package draft;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FillForm extends BaseTestOld {


    @Test
    public void fillForm() throws InterruptedException {
        String nameTxt= "com.androidsample.generalstore:id/nameField";
        String genderRadioBtn="//android.widget.RadioButton[@text='Female']";
        String countryDrpdwn="com.androidsample.generalstore:id/spinnerCountry";
        String shopBtn="com.androidsample.generalstore:id/btnLetsShop";
        String countryName="Bahrain";

        // Type name
//        typeData("id",nameTxt, "Ahana S" );
        // Select gender
        clickElement("xpath", genderRadioBtn, "");
        // Click on Country
        clickElement("id", countryDrpdwn, "");
        // Scroll to Country
        scrollToElement(countryName);
        // Select Country
        clickElement("dynamicxpath", "//android.widget.TextView[@text='", countryName);
        // Click on Shop
        clickElement("id", shopBtn, "");

        String toastMsg= getAttributeValue("xpath", "(//android.widget.Toast)[1]", "name");
        Assert.assertEquals(toastMsg, "Please enter your name");
    }



}
