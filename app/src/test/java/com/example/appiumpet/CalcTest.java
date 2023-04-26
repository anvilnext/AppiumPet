package com.example.appiumpet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CalcTest {

    private AndroidDriver driver;
    private final String androidPackage = "com.android.calculator2";

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel 4");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("appPackage", androidPackage);
        capabilities.setCapability("appActivity", androidPackage + ".Calculator");
        capabilities.setCapability("automationName", "UiAutomator2");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @Test
    public void calcTest() {
        final int firstDigit = 3;
        final int secondDigit = 4;

        MobileElement digit = (MobileElement) driver.findElementById(androidPackage + ":id/digit_" + Integer.toString(firstDigit));
        digit.click();
        driver.findElementById(androidPackage + ":id/digit_" + Integer.toString(firstDigit)).click();
        driver.findElementByAccessibilityId("plus").click();
        driver.findElementById(androidPackage + ":id/digit_" + Integer.toString(secondDigit)).click();
        driver.findElementByAccessibilityId("equals").click();

        String result = driver. findElementById(androidPackage + ":id/formula").getText();
        Assert.assertEquals(Integer.toString(firstDigit + secondDigit), result);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
