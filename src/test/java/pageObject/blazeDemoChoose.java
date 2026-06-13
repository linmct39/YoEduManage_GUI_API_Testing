package pageObject;

import common.CommonFunctions;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class blazeDemoChoose {
	WebDriver driver;
	AppiumDriver appiumDriver;

	By btnChooseThisFlight = By.xpath("(//input[@value='Choose This Flight'])[2]");

	CommonFunctions commonFunction = new CommonFunctions();

	int intTimeOut = Integer.parseInt(System.getProperty("objectTimeout").trim());

	public blazeDemoChoose(WebDriver driver) {
		if (System.getProperty("osName").trim().toLowerCase().contains("android")) {
			this.appiumDriver = (AppiumDriver) driver;
		} else {
			this.driver = driver;
		}
	}

	public void funcClickButton() {
		try {
			if (System.getProperty("osName").trim().toLowerCase().contains("windows")
					|| System.getProperty("osName").trim().toLowerCase().contains("mac")) {
				commonFunction.waitUntilElementLocated(driver, btnChooseThisFlight, intTimeOut);

				driver.findElement(btnChooseThisFlight).click();
			}

			if (System.getProperty("osName").trim().toLowerCase().contains("android")
					|| System.getProperty("osName").trim().toLowerCase().contains("ios")) {

				commonFunction.waitUntilElementLocated(appiumDriver, btnChooseThisFlight, intTimeOut);

				appiumDriver.findElement(btnChooseThisFlight).click();
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}


}
