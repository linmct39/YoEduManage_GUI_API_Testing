package stepsDefinition;

import common.ContextSteps;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.blazeDemoChoose;

import java.time.Duration;

public class BlazeDemeChoose {
    WebDriver driver;
    AppiumDriver appiumDriver;
    WebDriverWait wait;
    blazeDemoChoose blazeDemoChoose;

    public BlazeDemeChoose() throws Throwable {
        ContextSteps contextSteps = new ContextSteps();

        if (System.getProperty("osName").trim().toLowerCase().contains("android")) {

            appiumDriver = (AppiumDriver) contextSteps.getDriver();

            blazeDemoChoose = new blazeDemoChoose(appiumDriver);

            wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
        } else if (System.getProperty("osName").trim().toLowerCase().contains("windows")
                || System.getProperty("osName").trim().toLowerCase().contains("mac")) {
            driver = contextSteps.getDriver();

            blazeDemoChoose = new blazeDemoChoose(driver);

            wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
        }
    }

    @Then("I click Choose This Flight")
    public void i_click_Choose_This_Flight() {
        try {
            System.out.println("I click Choose This Flight");

            blazeDemoChoose.funcClickButton();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
