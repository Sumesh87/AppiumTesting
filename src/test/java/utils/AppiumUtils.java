package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {



    public static List<HashMap<String,String >> getJsonData() throws IOException {
        String path= System.getProperty("user.dir")+ "//src/test/resources/ecommerce.json";
        String jsonData= FileUtils.readFileToString(new File(path));

        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String >> map=mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String >>>() {
        });

        return map;
    }


    public String getScreenshot(String testcaseName, AppiumDriver driver) throws IOException {
        File source= driver.getScreenshotAs(OutputType.FILE);
        String destinationFile =System.getProperty("user.dir")+ "//reports//" + testcaseName + ".png" ;

        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;



    }
}
