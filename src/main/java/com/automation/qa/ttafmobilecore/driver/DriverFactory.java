package com.automation.qa.ttafmobilecore.driver;

import com.automation.qa.ttafmobilecore.util.Constant;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DriverFactory.class));
    private static String strExecuteBrowser = "";
    protected static DesiredCapabilities capability;

    public static void createInstance(String appiumServerPort, String deviceName, String OSverison, String browserName, String deviceID, String systemPort) {
        strExecuteBrowser = (browserName != null) ? browserName : "chrome";

        initDeviceCapabilities(deviceName, browserName, OSverison, deviceID,systemPort);

        if (Constant.GRID_MODE.equals("on")) {

        } else {
            initAppiumDevice(capability, appiumServerPort);

        }
        setDriverSettings();
    }

    /**
     * Setup Basic WebDriver Browser Settings
     */
    private static void setDriverSettings() {
        if (Constant.MOBILE_APP_TYPE.equalsIgnoreCase("WEB")) {
            LOGGER.info("TTAF MESSAGE: Initiate " + Constant.MOBILE_PLATFORM.toUpperCase() + " Driver");
            DriverManager.driver.get().navigate().to(Constant.URL);
            LOGGER.info("TTAF MESSAGE: Browser Loaded And Navigated To : [" + Constant.URL + " ]");
        } else if (Constant.MOBILE_APP_TYPE.equalsIgnoreCase("NATIVE") || Constant.MOBILE_APP_TYPE.equalsIgnoreCase("HYBRID")) {

        } else {
            System.out.print("TTAF MESSAGE: Invalid App type..Exit from the execution..");
            System.exit(1);
        }
    }

    /**
     * This method set up device (appium) based capabilities for ios or android
     * refer following URL for Appium Desired Capabilities,
     * http://appium.io/docs/en/writing-running-appium/caps/
     *
     * @param deviceName
     * @param browser
     * @param OSverison
     * @param deviceID
     */
    private static void initDeviceCapabilities(String deviceName, String browser, String OSverison, String deviceID,String systemPort) {
        if (Constant.MOBILE_PLATFORM.equalsIgnoreCase("IOS")) {
            capability = new DesiredCapabilities();
            capability.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
            capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capability.setCapability("autoDismissAlerts", true);
            capability.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");

        } else if (Constant.MOBILE_PLATFORM.equalsIgnoreCase("ANDROID")) {
            capability = new DesiredCapabilities();
            capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capability.setCapability(MobileCapabilityType.UDID, deviceID);
            //UiAutomator2 support from andriod 5.0 onwards only
           capability.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
          capability.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Integer.valueOf(systemPort));
            capability.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
        }
        //Appium will wait for a new command from the client before assuming the client quit and ending the session
        capability.setCapability("newCommandTimeout", 200);
        capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, OSverison);
        capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        // capability.setCapability("udid", deviceName);
        capability.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
        long id = Thread.currentThread().getId();
        System.out.println("Set Device capability to Thread id " + id);
    }

    /**
     * initiate appium driver (ios or android) with given capabilities for local execution or saucelabs
     *
     * @param capabilities preferred configurations for ios or android driver
     */
    private static void initAppiumDevice(DesiredCapabilities capabilities, String port) {
        if (Constant.MOBILE_APP_TYPE.equalsIgnoreCase("NATIVE") || Constant.MOBILE_APP_TYPE.equalsIgnoreCase("HYBRID")) {
            capability.setCapability(MobileCapabilityType.APP, loadApplication().getAbsolutePath());
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,Constant.MOBILE_APP_PACKAGE);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,Constant.MOBILE_APP_LAUNCH_ACTIVITY);;
           // capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, Constant.MOBILE_PLATFORM.equalsIgnoreCase("IOS") ? "IOS" : "Android");
        } else {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, strExecuteBrowser);
        }

        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        // capabilities.setCapability("--session-override", true);

        // setting appium version
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.9.1");

        try {
            URL url = null;

            // URL creation
            String appiumURL = Constant.APPIUM_SERVER;
            appiumURL = appiumURL == null ? "http://127.0.0.1" : appiumURL;
            if (!appiumURL.startsWith("http://")) {
                appiumURL = "http://" + appiumURL;
            }
            if (!appiumURL.matches("(.*?):[0-9][0-9][0-9][0-9](.*?)")) {
                appiumURL += ":" + port;
            }
            if (!appiumURL.endsWith("/wd/hub")) {
                appiumURL += "/wd/hub";
            }
            url = new URL(appiumURL);

            if (Constant.MOBILE_PLATFORM.equalsIgnoreCase("IOS")) {
                //  driver = new IOSDriver(url, capabilities);
                DriverManager.driver.set(new IOSDriver(url, capabilities));
            } else {
                // driver = new AndroidDriver(url, capabilities);
                DriverManager.driver.set(new AndroidDriver(url, capabilities));
            }
            long id = Thread.currentThread().getId();
            System.out.println("Starting device Thread id " + id);
        } catch (MalformedURLException e) {
            System.err.println("Could not create appium driver: " + e);
        }
        DriverManager.driver.get().manage().timeouts().implicitlyWait(Constant.TIMEOUT_IMPLICIT, TimeUnit.MILLISECONDS);
    }

    /**
     * Get the native application location
     *
     * @return
     */
    private static File loadApplication() {
        File appDir = new File(Constant.MOBILE_APP_LOCATION);
        return new File(appDir, Constant.MOBILE_APP_NAME);
    }

}

