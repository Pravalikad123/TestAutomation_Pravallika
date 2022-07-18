package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.yaml.snakeyaml.Yaml;
import page.DefaultCaculatorValues;
import page.RetirementCalculator_page;
import utilities.Utils;

import java.io.FileInputStream;
import java.util.HashMap;

public class TestCase_PreRetirementCalculator {

    static WebDriver driver;
    static Yaml yaml_config;
    static FileInputStream fis_config;
    static HashMap<String, String> map_config;

    @BeforeMethod
    public void before() throws Exception{
        yaml_config = new Yaml();
        fis_config = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\TestConfig.yaml");
        map_config=yaml_config.load(fis_config);
    }

    @Test (priority = 1)
    public void test_01() throws Exception{

        String url=map_config.get("applicationURL");
        System.out.println("Application URL is :"+url);

        driver=Utils.loadApplicationURL(url,"firefox");

        RetirementCalculator_page.submitForm(driver,"Mandatory");

        RetirementCalculator_page.verifyRetirementGoalMessage(driver);

    }

    @Test (priority = 2)
    public void test_02() throws Exception{

        String url=map_config.get("applicationURL");
        System.out.println("Application URL is :"+url);

        driver=Utils.loadApplicationURL(url,"firefox");

        RetirementCalculator_page.validateAdditionalfields(driver);

    }

    @Test (priority = 3)
    public void test_03() throws Exception{

        String url=map_config.get("applicationURL");
        System.out.println("Application URL is :"+url);

        driver=Utils.loadApplicationURL(url,"firefox");

        RetirementCalculator_page.submitForm(driver,"AllFields");

        RetirementCalculator_page.verifyRetirementGoalMessage(driver);

    }

    @Test (priority = 4)
    public void test_04() throws Exception{

        String url=map_config.get("applicationURL");
        System.out.println("Application URL is :"+url);

        driver=Utils.loadApplicationURL(url,"firefox");

        RetirementCalculator_page.clickDefaultValues(driver);

        DefaultCaculatorValues.saveDefaultCalculatorValues(driver);

    }

    @AfterMethod
    public void tearDown() throws Exception{
        fis_config.close();
        driver.quit();
        System.out.println("Browser is closed and Driver Instance is killed");


    }
}
