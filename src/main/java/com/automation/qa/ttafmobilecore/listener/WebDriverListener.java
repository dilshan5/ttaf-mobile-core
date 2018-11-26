package com.automation.qa.ttafmobilecore.listener;

import com.automation.qa.ttafmobilecore.driver.DriverFactory;
import com.automation.qa.ttafmobilecore.util.Constant;
import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class WebDriverListener implements IInvokedMethodListener {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(WebDriverListener.class));

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        LOGGER.info("TTAF MESSAGE:BEGIN: com.automation.qa.ttafuicore.listener.WebDriverListener.afterInvocation");
        if (iInvokedMethod.isTestMethod()) {

        } else {
            LOGGER.info("TTAF MESSAGE: TTAF Provided method is NOT a TestNG testMethod!!!");
        }
        LOGGER.info("TTAF MESSAGE:END: com.automation.qa.ttafuicore.listener.WebDriverListener.afterInvocation");
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        LOGGER.info("TTAF MESSAGE:BEGIN: com.automation.qa.ttafuicore.listener.WebDriverListener.beforeInvocation");
        if (iInvokedMethod.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
            String browserName = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            String deviceID = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("mobile-device-id");
            String OSverison = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("mobile-version");
            String deviceName = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("mobile-device-name");
            String appiumServerPort = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("appium-server-port");

            DriverFactory.createInstance(appiumServerPort, deviceName, OSverison, browserName, deviceID);
            LOGGER.info("Done! Created " + deviceName + " driver!");
            
            Constant.BROWSER_NAME = browserName;// set Browser Name
            LOGGER.info("Execution Browser set as: " + browserName);
            LOGGER.info("Execution Device ID set as: " + deviceID);
            LOGGER.info("Execution Device OS set as: " + OSverison);
            LOGGER.info("Execution Device Name set as: " + deviceName);
            LOGGER.info("Execution Appium Server Port is: " + appiumServerPort);



        } else {
            LOGGER.info("TTAF MESSAGE: TTAF Provided method is NOT a TestNG testMethod!!!");
        }
        LOGGER.info("TTAF MESSAGE:END: com.automation.qa.ttafuicore.listener.WebDriverListener.beforeInvocation");
    }
}
