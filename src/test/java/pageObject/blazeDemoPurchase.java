package pageObject;

import common.CommonFunctions;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class blazeDemoPurchase {
	WebDriver driver;
	AppiumDriver appiumDriver;

	By blazeName = By.name("inputName");
	By blazeAddress = By.name("address");
	By blazeCity = By.name("city");
	By blazeState = By.name("state");
	By blazeZipCode = By.name("zipCode");
	By blazeCreditCardNumber = By.name("creditCardNumber");
	By blazeCreditCardMonth = By.xpath("//input[@name='creditCardMonth']");
	By blazeCreditCardYear = By.xpath("//input[@name='creditCardYear']");
	By blazeCreditCardName = By.name("nameOnCard");

	By btnPurchaseFlight = By.xpath("//input[@name='rememberMe']/parent::label/../input");

	public blazeDemoPurchase(WebDriver driver) {
		if (System.getProperty("osName").trim().toLowerCase().contains("android")) {
			this.appiumDriver = (AppiumDriver) driver;
		} else {
			this.driver = driver;
		}
	}

	public void funcEnterInformation() {
		try {
			if (System.getProperty("osName").trim().toLowerCase().contains("windows")
					|| System.getProperty("osName").trim().toLowerCase().contains("mac")) {

				driver.findElement(blazeName).sendKeys(System.getProperty("dBlazeName").trim());
				driver.findElement(blazeAddress).sendKeys(System.getProperty("dBlazeAddress").trim());
				driver.findElement(blazeCity).sendKeys(System.getProperty("dBlazeCity").trim());
				driver.findElement(blazeState).sendKeys(System.getProperty("dBlazeState").trim());
				driver.findElement(blazeZipCode).sendKeys(System.getProperty("dBlazeZipCode").trim());
				driver.findElement(blazeCreditCardNumber).sendKeys(System.getProperty("dBlazeCreditCardNumber").trim());
				driver.findElement(blazeCreditCardMonth).clear();
				driver.findElement(blazeCreditCardMonth).sendKeys(System.getProperty("dBlazeCreditCardMonth").trim());
				driver.findElement(blazeCreditCardYear).clear();
				driver.findElement(blazeCreditCardYear).sendKeys(System.getProperty("dBlazeCreditCardYear").trim());
				driver.findElement(blazeCreditCardName).sendKeys(System.getProperty("dBlazeCreditCardName").trim());

				driver.findElement(btnPurchaseFlight).click();
			}

			if (System.getProperty("osName").trim().toLowerCase().contains("android")
					|| System.getProperty("osName").trim().toLowerCase().contains("ios")) {

				appiumDriver.findElement(blazeName).sendKeys(System.getProperty("dBlazeName").trim());
				appiumDriver.findElement(blazeAddress).sendKeys(System.getProperty("dBlazeAddress").trim());
				appiumDriver.findElement(blazeCity).sendKeys(System.getProperty("dBlazeCity").trim());
				appiumDriver.findElement(blazeState).sendKeys(System.getProperty("dBlazeState").trim());
				appiumDriver.findElement(blazeZipCode).sendKeys(System.getProperty("dBlazeZipCode").trim());
				appiumDriver.findElement(blazeCreditCardNumber)
						.sendKeys(System.getProperty("dBlazeCreditCardNumber").trim());
				appiumDriver.findElement(blazeCreditCardMonth).clear();
				appiumDriver.findElement(blazeCreditCardMonth)
						.sendKeys(System.getProperty("dBlazeCreditCardMonth").trim());
				appiumDriver.findElement(blazeCreditCardYear).clear();
				appiumDriver.findElement(blazeCreditCardYear)
						.sendKeys(System.getProperty("dBlazeCreditCardYear").trim());
				appiumDriver.findElement(blazeCreditCardName)
						.sendKeys(System.getProperty("dBlazeCreditCardName").trim());

				WebElement button = appiumDriver.findElement(btnPurchaseFlight);
				((JavascriptExecutor) appiumDriver).executeScript("arguments[0].click();", button);
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}


}
