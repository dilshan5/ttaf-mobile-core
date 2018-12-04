package com.automation.qa.ttafmobilecore.page;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

/**
 * Introduces an additional abstract layer for real Page classes template, every page class should be extended from this
 */
public class BasicPage {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BasicPage.class));
    protected AppiumDriver driver;

    public BasicPage() {
    }

    /**
     * Initialize Page Base
     *
     * @param driver
     */
    public BasicPage(AppiumDriver driver) {
        this.driver = driver;
    }
}
