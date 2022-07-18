package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.HashMap;

public class DefaultCaculatorValues {
    public static final By locator_AdditionalIncome=By.xpath("//input[@id='additional-income']");
    public static final By locator_NumberOfRetirementYears=By.xpath("//input[@id='retirement-duration']");
    public static final By locator_PostRetirementIncomeIncrement=By.xpath("//input[@id='include-inflation']//following-sibling::label[text()='Yes']");
    public static final By locator_PercentFinalAnnualIncome=By.xpath("//input[@id='retirement-annual-income']");
    public static final By locator_PreRetirementInvestmentReturn=By.xpath("//input[@id='pre-retirement-roi']");
    public static final By locator_PostRetirementInvestmentReturn=By.xpath("//input[@id='post-retirement-roi']");
    public static final By locator_SaveChangesBtn=By.xpath("//button[text()='Save changes']");

    public static void saveDefaultCalculatorValues(WebDriver driver)throws Exception{

        Yaml yaml_config = new Yaml();
        FileInputStream fis_config = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\TestConfig.yaml");
        HashMap<String, String> map_config=yaml_config.load(fis_config);

        //AdditionalIncome
        String additionalIncome=map_config.get("AdditionalIncome");
        driver.findElement(locator_AdditionalIncome).sendKeys(additionalIncome);
        System.out.println(additionalIncome+" is entered as Additional Income");

        //NumberOfYearsRetirement
        String numberOfYearsRetirement=map_config.get("NumberOfYearsRetirement");
        driver.findElement(locator_NumberOfRetirementYears).sendKeys(numberOfYearsRetirement);
        System.out.println(numberOfYearsRetirement+" is entered as Number Of Years Retirement");

        //PostRetirementIncomeIncrease
        String postRetirementIncomeIncrease=map_config.get("PostRetirementIncomeIncrease");
        if(postRetirementIncomeIncrease.equalsIgnoreCase("Yes")){
            driver.findElement(locator_PostRetirementIncomeIncrement).click();
            System.out.println(postRetirementIncomeIncrease+" is selected for Post Retirement Income Increment");
        }

        //PercentOfFinalAnnualIncome
        String percentOfFinalAnnualIncome=map_config.get("PercentOfFinalAnnualIncome");
        driver.findElement(locator_PercentFinalAnnualIncome).sendKeys(percentOfFinalAnnualIncome);
        System.out.println(percentOfFinalAnnualIncome+" is entered as Percent Of Final Annual Income");

        //PreRetirementInvestmentReturn
        String preRetirementInvestmentReturn=map_config.get("PreRetirementInvestmentReturn");
        driver.findElement(locator_PreRetirementInvestmentReturn).sendKeys(preRetirementInvestmentReturn);
        System.out.println(preRetirementInvestmentReturn+" is entered as Pre Retirement Investment Return");

        //PostRetirementInvestmentReturn
        String postRetirementInvestmentReturn=map_config.get("PostRetirementInvestmentReturn");
        driver.findElement(locator_PostRetirementInvestmentReturn).sendKeys(postRetirementInvestmentReturn);
        System.out.println(postRetirementIncomeIncrease+" is entered as Post Retirement Investment Return");
        Thread.sleep(2000);

        //Save Changes
        driver.findElement(locator_SaveChangesBtn).click();
        System.out.println("Save Changes button is clicked");

        Thread.sleep(2000);
    }
}
