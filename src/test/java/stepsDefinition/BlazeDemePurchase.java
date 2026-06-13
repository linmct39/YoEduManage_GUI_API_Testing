package stepsDefinition;

import common.ContextSteps;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.blazeDemoPurchase;

import java.time.Duration;

public class BlazeDemePurchase {
    WebDriver driver;
    AppiumDriver appiumDriver;
    WebDriverWait wait;
    blazeDemoPurchase blazeDemoPurchase;

    public BlazeDemePurchase() throws Throwable {
        ContextSteps contextSteps = new ContextSteps();

        if (System.getProperty("osName").trim().toLowerCase().contains("android")) {

            appiumDriver = (AppiumDriver) contextSteps.getDriver();

            blazeDemoPurchase = new blazeDemoPurchase(appiumDriver);

            wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
        } else if (System.getProperty("osName").trim().toLowerCase().contains("windows")
                || System.getProperty("osName").trim().toLowerCase().contains("mac")) {
            driver = contextSteps.getDriver();

            blazeDemoPurchase = new blazeDemoPurchase(driver);

            wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
        }
    }

    @Then("I enter detail information")
    public void i_enter_detail_information() {
        try {
            System.out.println("I enter detail information");

            blazeDemoPurchase.funcEnterInformation();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
