package com.automation.qa.ttafmobilecore.driver;

import com.automation.qa.ttafmobilecore.util.Constant;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

public class DriverManager {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DriverManager.class));
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

    public static AppiumDriver getDriver() {
        if (driver.get() == null) {
            // this is need when running tests from IDE
            LOGGER.info("Thread has no WedDriver, creating new one");
            DriverFactory.createInstance(Constant.APPIUM_SERVER_PORT, Constant.MOBILE_DEVICE_NAME, Constant.MOBILE_OS_VERSION, Constant.BROWSER_NAME,Constant.MOBILE_DEVICE_ID);
        }
        LOGGER.info("Getting instance of remote driver" + driver.get().getClass());
        return driver.get();
    }
}
