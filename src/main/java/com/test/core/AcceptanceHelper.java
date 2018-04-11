package com.test.core;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class AcceptanceHelper {

    public void uploadFileToRemoteHost(SelenideElement inputCss, String filePath) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].style.display = 'block';", inputCss);
        ((RemoteWebDriver) WebDriverRunner.getWebDriver()).setFileDetector(new LocalFileDetector());
        File file = new File(filePath);
        String absolutePath = file.getAbsolutePath();

        inputCss.sendKeys(absolutePath);
    }
}
