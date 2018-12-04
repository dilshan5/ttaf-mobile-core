package com.automation.qa.ttafmobilecore.util;

import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Properties;

/**
 * Constant.java - properties accessed as constants through out the application.( Global Access)
 */
public class Constant {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Constant.class));
    public static final String MOBILE_APP_TYPE_KEY = "mobile-app-type";
    public static final String MOBILE_PLATFORM_KEY = "mobile-platform";
    public static final String MOBILE_OS_VERSION_KEY = "mobile-version";
    public static final String APPIUM_SERVER_PORT_KEY = "appium-server-port";
    public static final String MOBILE_DEVICE_ID_KEY = "mobile-device-id";
    public static final String MOBILE_DEVICE_NAME_KEY = "mobile-device-name";
    public static final String APPIUM_SERVER_KEY = "appium-server";
    public static final String MOBILE_APP_LOCATION_KEY = "mobile-app-location";//for native application
    public static final String MOBILE_APP_NAME_KEY = "mobile-app-name";//for native application
    public static final String MOBILE_APP_PACKAGE_KEY = "mobile-app-package";//for native application
    public static final String MOBILE_APP_LAUNCH_ACTIVITY_KEY = "mobile-app-launch-activity";//for native application
    private static final String BROWSER_NAME_KEY = "browserName";//for web application
    private static final String TIMEOUT_IMPLICIT_KEY = "implicitWaitTime";
    private static final String URL_KEY = "url";
    private static final String HUBURL_KEY = "hubURL";
    private static final String GRID_MODE_KEY = "grid-mode";

    private static Properties properties;
    public static String BROWSER_NAME;
    public static int TIMEOUT_IMPLICIT;
    public static String URL;
    public static String hubURL;
    public static String GRID_MODE;
    public static String APPIUM_SERVER;
    public static String MOBILE_APP_TYPE;
    public static String MOBILE_PLATFORM;
    public static String MOBILE_OS_VERSION;
    public static String APPIUM_SERVER_PORT;
    public static String MOBILE_DEVICE_ID;
    public static String MOBILE_DEVICE_NAME;
    public static String MOBILE_APP_LOCATION;
    public static String MOBILE_APP_NAME;
    public static String MOBILE_APP_PACKAGE;
    public static String MOBILE_APP_LAUNCH_ACTIVITY;

    static {
        try {
            loadXmlProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading  values to global variables.
     *
     * @throws Exception
     */
    public static void loadXmlProperties() throws Exception {
        FrameworkProperties loadProperties = new FrameworkProperties();
        URL congfigFile = null;
        URL driverLocation = null;
        congfigFile = Constant.class.getClassLoader().getResource("Configuation.properties");
        driverLocation = Constant.class.getClassLoader().getResource("drivers/");
        properties = loadProperties.readProjEnvConfig(congfigFile.getPath());


        try {
            BROWSER_NAME = (properties.getProperty(BROWSER_NAME_KEY) == null ? "chrome" : properties.getProperty(BROWSER_NAME_KEY));
            URL = properties.getProperty(URL_KEY);
            TIMEOUT_IMPLICIT = Integer.parseInt(properties.getProperty(TIMEOUT_IMPLICIT_KEY));
            hubURL = properties.getProperty(HUBURL_KEY);
            GRID_MODE = properties.getProperty(GRID_MODE_KEY).toLowerCase();
            APPIUM_SERVER = properties.getProperty(APPIUM_SERVER_KEY);
            MOBILE_APP_TYPE = properties.getProperty(MOBILE_APP_TYPE_KEY);
            MOBILE_PLATFORM = properties.getProperty(MOBILE_PLATFORM_KEY);
            MOBILE_OS_VERSION = properties.getProperty(MOBILE_OS_VERSION_KEY);
            APPIUM_SERVER_PORT = properties.getProperty(APPIUM_SERVER_PORT_KEY);
            MOBILE_DEVICE_ID = properties.getProperty(MOBILE_DEVICE_ID_KEY);
            MOBILE_DEVICE_NAME = properties.getProperty(MOBILE_DEVICE_NAME_KEY);
            MOBILE_APP_LOCATION = properties.getProperty(MOBILE_APP_LOCATION_KEY);
            MOBILE_APP_NAME = properties.getProperty(MOBILE_APP_NAME_KEY);
            MOBILE_APP_PACKAGE = properties.getProperty(MOBILE_APP_PACKAGE_KEY);
            MOBILE_APP_LAUNCH_ACTIVITY = properties.getProperty(MOBILE_APP_LAUNCH_ACTIVITY_KEY);
            LOGGER.info("Set up Framework variables");
        } catch (Exception e) {
            LOGGER.error("Error: Unable to load framework variables ", e);
        }

    }
}
