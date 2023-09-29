package org.gunceTestCases;

import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gunceTestCases.helper.ElementHelper;
import org.gunceTestCases.helper.StoreHelper;
import org.gunceTestCases.model.ElementInfo;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class StepImplementation extends BaseTest{

    Logger logger = LogManager.getLogger(StepImplementation.class);
    private WebDriverWait wait;
    private WebElement findElement(String key) {
        try {

            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);

            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            webDriverWait
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(infoParam));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    driver.findElement(infoParam));
            return driver.findElement(infoParam);
        } catch (Exception e) {
            return null;
        }
    }
    @Step("<time> saniye kadar bekle")
    public void waitForseconds(int time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

    @Step("<key> objesine tıklanır")
    public void clickByid(String key) {
        findElement(key).click();
        logger.info("" + key + "' id'li elemente basarili bir sekilde tiklandi");
    }

    @Step("<key> objesi görünür durumda")
    public void verifyElementsById(String key) {
        Assert.assertNotNull("Element görüntülenmedi",findElement(key));
        logger.info("" + key +"' element basarili bir sekilde goruntulendi");
    }

    @Step("<key> objesini bul ve <text> değerini yaz")
    public void sendkeysByid(String key, String text) {
        findElement(key).sendKeys(text);
        logger.info(""+key+" ' 'li inputa '"+text+"' degeri girildi");
    }

    @Step("<key> checkbox'ını işaretle")
    public void checkboxuIsaretle( String key) {
        // Belirtilen XPath ile checkbox'u bul
        WebElement checkbox = findElement(key);

        // JavaScriptExecutor kullanarak checkbox'u işaretle
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", checkbox);
    }

    @Step("Sayfayı <key> görünene kadar kaydır")
    public void sayfayiElementGoruneneKadarKaydir(String key) {

        WebElement element = findElement(key);
        // Element görünür hale gelene kadar sayfayı kaydır
        while (!isElementVisible(element)) {
            scrollPageDown();
        }
    }
    @Step("<key> objesi görünene kadar bekle")
    public void webElementiGoruneneKadarBekle(String key) {
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Belirtilen XPath ile elementi bul
        findElement(key);

        // Bekleyerek elementin görünür hale gelmesini sağla
        //wait.until(ExpectedConditions.visibilityOf(element));
    }
    private boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    private void scrollPageDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200);"); // Sayfayı 200 piksel aşağı kaydır
    }

}
