package com.test.grumpytest.configs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.io.Files;
import com.google.gson.JsonObject;
import com.test.core.Parallelized;
import com.test.core.RetryRule;
import com.test.grumpytest.pages.MainPage;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Platform;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;

@RunWith(Parallelized.class)
public class BaseTest {

    private final String browserName;
    private final String browserVersion;
    private final String gridUrl;
    private final String platform;

    public BaseTest(String browserName, String browserVersion, String gridUrl, String platform) {
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.gridUrl = gridUrl;
        this.platform = platform;
    }

    @Parameters(name = "{0} v{1}")
    public static LinkedList<String[]> getEnvironments() {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"chrome", "65.0", "http://localhost:4444/wd/hub", Platform.LINUX.toString()});
        env.add(new String[]{"firefox", "58.0", "http://localhost:4444/wd/hub", Platform.LINUX.toString()});
        env.add(new String[]{"opera", "51.0", "http://localhost:4444/wd/hub", Platform.LINUX.toString()});

        return env;
    }

    @Before
    public void setUp() throws Exception {
        Configuration.baseUrl = "http://172.17.0.2:8080";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        if (browserName.equals("opera")) {
            com.google.gson.JsonObject json = new com.google.gson.JsonObject();
            json.addProperty("binary", "/usr/bin/opera");
            capabilities.setCapability("operaOptions", json);
        }
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1366x768x24");
        capabilities.setCapability("name", "Parallel test");
        RemoteWebDriver driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }

    @Rule
    public RetryRule retryRule = new RetryRule(3);

    @After
    public void makeScreenshot() throws IOException {
        File lastSelenideScreenshot = Screenshots.getLastScreenshot();
        if (lastSelenideScreenshot != null) {
            saveScreenshot(Files.toByteArray(lastSelenideScreenshot));
        }
    }

    @After
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
