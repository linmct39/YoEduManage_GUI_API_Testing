package pageObject;

import common.CommonFunctions;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class blazeDemoSelect {
	WebDriver driver;
	AppiumDriver appiumDriver;

	By cboFrom = By.name("fromPort");
	By cboTo = By.name("toPort");

	By btnFindFlight = By.xpath("//input[@value='Find Flights']");

	CommonFunctions commonFunction = new CommonFunctions();

	int intTimeOut = Integer.parseInt(System.getProperty("objectTimeout").trim());

	public blazeDemoSelect(WebDriver driver) {
		if (System.getProperty("osName").trim().toLowerCase().contains("android")) {
			this.appiumDriver = (AppiumDriver) driver;
		} else {
			this.driver = driver;
		}
	}

	public void funcFindFlight(String strFrom, String strTo) {
		try {
			if (System.getProperty("osName").trim().toLowerCase().contains("windows")
					|| System.getProperty("osName").trim().toLowerCase().contains("mac")) {
				commonFunction.waitUntilElementLocated(driver, btnFindFlight, intTimeOut);

				driver.findElement(cboFrom).click();
				new Select(driver.findElement(cboFrom)).selectByVisibleText(strFrom);

				driver.findElement(cboTo).click();
				new Select(driver.findElement(cboTo)).selectByVisibleText(strTo);

				driver.findElement(btnFindFlight).click();
				Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
			}

			if (System.getProperty("osName").trim().toLowerCase().contains("android")
					|| System.getProperty("osName").trim().toLowerCase().contains("ios")) {

				commonFunction.waitUntilElementLocated(appiumDriver, cboFrom, intTimeOut);

				appiumDriver.findElement(cboFrom).click();
				new Select(appiumDriver.findElement(cboFrom)).selectByVisibleText(strFrom);

				commonFunction.waitUntilElementLocated(appiumDriver, cboFrom, intTimeOut);

				appiumDriver.findElement(cboTo).click();
				new Select(appiumDriver.findElement(cboTo)).selectByVisibleText(strTo);

				commonFunction.waitUntilElementLocated(appiumDriver, cboFrom, intTimeOut);

				appiumDriver.findElement(btnFindFlight).click();
				java.lang.Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}


}
