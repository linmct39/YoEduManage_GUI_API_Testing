package pageObject;

import common.CommonFunctions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class managementEduLogin {
    WebDriver driver;
    By txtUsername = By.id("email");
    By txtPassword = By.id("password");
    By btnLogin = By.xpath("//button[@type='button']");
    By loginErrorMessage = By.xpath("//div[contains(@class, 'MuiAlert-message')] | //p[contains(@class, 'Mui-error')]");
    CommonFunctions commonFunctions = new CommonFunctions();
    int intTimeOut = Integer.parseInt(System.getProperty("objectTimeout").trim());

    public managementEduLogin (WebDriver driver){
        if(System.getProperty("osName").trim().toLowerCase().contains("windows")){
            this.driver=driver;
        }
    }
    public void funcLogin ( String username, String password){
        try{
                if(System.getProperty("osName").trim().toLowerCase().contains("windows")){
                    commonFunctions.waitUntilElementLocated(driver,txtUsername,intTimeOut);

                    driver.findElement(txtUsername).sendKeys(username);
                    driver.findElement(txtPassword).sendKeys(password);
                    driver.findElement(btnLogin).click();
                    Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
                }


        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
    public boolean isLoginErrorMessageDisplayed() {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {

                Thread.sleep(2000);

                int errorCount = driver.findElements(loginErrorMessage).size();

                if (errorCount > 0) {
                    System.out.println(" Hệ thống đã chặn đăng nhập và hiện cảnh báo.");
                    return true;
                } else {
                    System.out.println("Nhập sai thông tin nhưng không có cảnh báo nào hiện lên!");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Lỗi khi tìm thông báo lỗi đăng nhập: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
}
