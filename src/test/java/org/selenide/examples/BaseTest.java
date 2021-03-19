package org.selenide.examples;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

abstract class BaseTest {

    static Properties prop = new Properties();

    @Rule
    public TextReport report = new TextReport();

    @BeforeClass
    static public void setupAllure() {
        Configuration.timeout = 4;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        loadProperties();

        if (prop.getProperty("selenoid.remote").equals("true")) {
            setupCapabilities();
        }
    }

    private static void loadProperties() {
        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void setupCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        Configuration.browserCapabilities = capabilities;
        Configuration.remote = prop.getProperty("selenoid.url");
    }

}
