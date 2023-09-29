package org.gunceTestCases;

import com.thoughtworks.gauge.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BaseTest {

    static WebDriver driver;

    @BeforeScenario
    public void setUp()
    {
        //WebDriverManager.firefoxdriver().setup();
        //WebDriverManager.edgedriver().setup();
        WebDriverManager.chromedriver().setup();
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://test.gunce.com/");

    }


    @AfterScenario
    public void teardown()
    {
        driver.quit();
    }


}