package com.automation.qa.ttafmobilecore.page;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

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

}
