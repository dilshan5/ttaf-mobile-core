package com.automation.qa.ttafmobilecore.page;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Introduces an additional abstract layer for real Screen classes template, every screen class should be extended from this
 */
public class BaseScreen {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BaseScreen.class));
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BaseScreen() {
    }

    /**
     * Initialize BaseScreen
     *
     * @param driver
     */
    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
       // wait = new WebDriverWait(driver, 15);
    }

    protected void waitAndClick (By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    protected void click (By by) {
        waitVisibility(by);
        driver.findElement(by).click();
    }

    protected void hideKeyboard(){
        driver.navigate().back();
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected List<WebElement> waitAndFindElements (By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected WebElement waitAndFindElement (By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected String getText(By by) {
        return waitAndFindElement(by).getText();
    }

    protected void sendText (By by, String text) {
        waitAndFindElement(by).sendKeys(text);
    }

    protected void assertEquals (String actual, String expected) {
        Assert.assertEquals(actual,expected, "Two texts are not equal!" + "Actual: " + actual + " Expected: " + expected);
    }

    protected void waitVisibility (By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    protected void sleep (long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
