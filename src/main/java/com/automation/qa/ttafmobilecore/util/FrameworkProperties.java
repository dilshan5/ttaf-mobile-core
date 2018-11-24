package com.automation.qa.ttafmobilecore.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class FrameworkProperties {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(FrameworkProperties.class));
    private Properties properties;

    /**
     * @param projectSettingsFile
     * @return
     * @throws Exception
     */
    public Properties readProjEnvConfig(String projectSettingsFile) throws Exception {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(projectSettingsFile));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Configuration.properties not found at " + projectSettingsFile, e);
        }

        return properties;

    }
}