package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;

public class RetirementCalculator_page {
    public static final By locator_currentAge=By.cssSelector("#current-age");
    public static final By locator_PlantoRetireAge=By.cssSelector("#retirement-age");
    public static final By locator_currentAnnualIncome=By.cssSelector("#current-income");
    public static final By locator_spouseAnnualIncome=By.xpath("//input[@id='spouse-income']");
    public static final By locator_currentSavingsBalance=By.xpath("//input[@id='current-total-savings']");
    public static final By locator_currentSavingsPercentage=By.xpath("//input[@id='current-annual-savings']");
    public static final By locator_IncreaseSavingsEachYear=By.xpath("//input[@id='savings-increase-rate']");
    public static final By locator_SocialSecuriyIncome_Yes=By.xpath("//input[@name='social-security-benefits']//following-sibling::label[text()='Yes']");
    public static final By locator_SocialSecuriyIncome_No=By.xpath("//input[@name='social-security-benefits']//following-sibling::label[text()='No']");
    public static final By locator_MarialStatus_Single=By.xpath("//input[@name='marital-status']//following-sibling::label[text()='Single']");
    public static final By locator_MarialStatus_Married=By.xpath("//input[@name='marital-status']//following-sibling::label[text()='Married']");
    public static final By locator_SocialSecurityOverideAmount=By.xpath("//input[@id='social-security-override']");
    public static final By locator_CalculateBtn=By.xpath("//button[@data-tag-id='submit']");
    public static final By locator_verifyRetirementGoalMessage=By.xpath("//h3[text()='Results']//following-sibling::div//p[@id='result-message']");

    //DefaultCalculatorValues
    public static final By locator_AdjustDefaultValues=By.xpath("//a[text()='Adjust default values']");


    public static void submitForm(WebDriver driver, String executeType) throws Exception{
        Yaml yaml_config = new Yaml();
        FileInputStream fis_config = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\TestConfig.yaml");
        HashMap<String, String> map_config=yaml_config.load(fis_config);

        //currentAge
        String currentAge=map_config.get("currentAge");
        driver.findElement(locator_currentAge).sendKeys(currentAge);
        System.out.println(currentAge+" is entered as Current Age");

        //RetirementAge
        String retirementAge=map_config.get("RetirementAge");
        driver.findElement(locator_PlantoRetireAge).sendKeys(retirementAge);
        System.out.println(retirementAge+" is entered as Retirement Age");

        //CurrentAnnualIncome
        String currentAnnualIncome=map_config.get("CurrentAnnualIncome");
        driver.findElement(locator_currentAnnualIncome).sendKeys(currentAnnualIncome);
        System.out.println(currentAnnualIncome+" is entered as Current Annual Income");

        //SpouseAnnualIncome
        if(executeType.equalsIgnoreCase("AllFields")){
            String spouseAnnualIncome=map_config.get("SpouseAnnualIncome");
            driver.findElement(locator_spouseAnnualIncome).sendKeys(spouseAnnualIncome);
            System.out.println(spouseAnnualIncome+" is entered as Spouse Annual Income");
        }

        //CurrentRetirementSavings
        String currentRetirementSavings= map_config.get("CurrentRetirementSavings");
        driver.findElement(locator_currentSavingsBalance).sendKeys(currentRetirementSavings);
        System.out.println(currentRetirementSavings+" is entered as Current Retirement Savings");

        //CurrentRetirementContribution
        String currentRetirementContribution=map_config.get("CurrentRetirementContribution");
        driver.findElement(locator_currentSavingsPercentage).sendKeys(currentRetirementContribution);
        System.out.println(currentRetirementContribution+" is entered as Current Retirement Contribution");

        //AnnualIncomeRetirementContribution
        String annualIncomeRetirementContribution=map_config.get("AnnualIncomeRetirementContribution");
        driver.findElement(locator_IncreaseSavingsEachYear).sendKeys(annualIncomeRetirementContribution);
        System.out.println(annualIncomeRetirementContribution+" is entered as AnnualIncomeRetirementContribution");

        //SocialSecurityIncome
        String socialSecurityIncome=map_config.get("SocialSecurityIncome");
        if(socialSecurityIncome.equalsIgnoreCase("Yes")){
            driver.findElement(locator_SocialSecuriyIncome_Yes).click();
            System.out.println("Social Security Income is selected as Yes");

            //MaritalStatus
            String maritalStatus=map_config.get("RelationshipStatus");
            if(maritalStatus.equalsIgnoreCase("Married")){
                driver.findElement(locator_MarialStatus_Married).click();
                System.out.println("Marital Status is selected as Married");

            }else{
                driver.findElement(locator_MarialStatus_Single).click();
                System.out.println("Marital Status is selected as Sngle");
            }

            //AdditionalIncome
            if(executeType.equalsIgnoreCase("AllFields")){
                String additionalIncome=map_config.get("AdditionalIncome");
                driver.findElement(locator_SocialSecurityOverideAmount).sendKeys(additionalIncome);
                System.out.println(additionalIncome+" is entered as Additional Income");
            }
        }else{
            driver.findElement(locator_SocialSecuriyIncome_No).click();
            System.out.println("Social Security Income is selected as No");
        }

        driver.findElement(locator_CalculateBtn).click();
        System.out.println("Calculate Button is clicked");
    }

    public static void verifyRetirementGoalMessage(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator_verifyRetirementGoalMessage));

        String resultMessage=driver.findElement(locator_verifyRetirementGoalMessage).getText();
        System.out.println("Retirement Goal Message is :"+resultMessage);
    }

    public static void validateAdditionalfields(WebDriver driver) throws Exception{
        Yaml yaml_config = new Yaml();
        FileInputStream fis_config = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\TestConfig.yaml");
        HashMap<String, String> map_config=yaml_config.load(fis_config);

        String socialSecurityIncome=map_config.get("SocialSecurityIncome");

        if(socialSecurityIncome.equalsIgnoreCase("Yes")){
            driver.findElement(locator_SocialSecuriyIncome_Yes).click();
            System.out.println("Social Security Income is selected as Yes");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator_MarialStatus_Married));

            Boolean isMaritalStatus_Displayed=driver.findElement(locator_MarialStatus_Married).isDisplayed();
            Assert.assertTrue(isMaritalStatus_Displayed,"Marital Status Radio-button is not displayed");
            System.out.println("Marital Status Radio-button is displayed");

            Boolean isAdditionalIncome_Displayed=driver.findElement(locator_SocialSecurityOverideAmount).isDisplayed();
            Assert.assertTrue(isAdditionalIncome_Displayed,"Additional Income text-box is not displayed");
            System.out.println("Additional Income text-box is displayed");
        }else{
            System.out.println("Social Security Income is selected as No");
        }
    }

    public static void clickDefaultValues(WebDriver driver){
        driver.findElement(locator_AdjustDefaultValues).click();
        System.out.println("Adjust Default Values link is clicked");
    }

}
