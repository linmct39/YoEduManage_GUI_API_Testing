package stepsDefinition;

import common.ContextSteps;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.blazeDemoSelect;

import java.time.Duration;

public class BlazeDemoSelect {
    WebDriver driver;
    AppiumDriver appiumDriver;
    WebDriverWait wait;
    blazeDemoSelect blazeDemoSelect;

    public BlazeDemoSelect() throws Throwable {
        ContextSteps contextSteps = new ContextSteps();

        if (System.getProperty("osName").trim().toLowerCase().contains("android")) {

            appiumDriver = (AppiumDriver) contextSteps.getDriver();

            blazeDemoSelect = new blazeDemoSelect(appiumDriver);

            wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
        } else if (System.getProperty("osName").trim().toLowerCase().contains("windows")
                || System.getProperty("osName").trim().toLowerCase().contains("mac")) {
            driver = contextSteps.getDriver();

            blazeDemoSelect = new blazeDemoSelect(driver);

            wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
        }
    }

    @Then("I select flight from {string} to {string}")
    public void iSelectFlightFromTo(String strFrom, String strTo) {
        try {
            System.out.println("I select flight from " + strFrom + " to " + strTo);

            blazeDemoSelect.funcFindFlight(strFrom, strTo);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
