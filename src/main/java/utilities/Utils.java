package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Utils {
    public static WebDriver loadApplicationURL(String applicationURL, String browser) throws Exception{
        WebDriver driver=null;
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }
        Thread.sleep(5000);

        driver.get(applicationURL);
        System.out.println(applicationURL+" is loaded");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }
}
