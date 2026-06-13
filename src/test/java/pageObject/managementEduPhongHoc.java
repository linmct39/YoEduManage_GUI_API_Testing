package pageObject;


import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import common.CommonFunctions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;



public class managementEduPhongHoc {
    WebDriver driver;
    CommonFunctions commonFunctions = new CommonFunctions();
    int intTimeOut = Integer.parseInt(System.getProperty("objectTimeout").trim());

    By btnAdd = By.xpath("//*[@data-testid='AddCircleOutlineIcon']");

    By txtMa = By.xpath("//label[contains(text(),'Mã')]/following-sibling::div//input");
    By txtTen = By.xpath("//label[contains(text(), 'Tên')]/following-sibling::div//input");
    By txtDienGiai = By.xpath("//label[contains(text(), 'Diễn giải')]/following-sibling::div//input");

    By drpCoSo = By.xpath("//label[contains(text(), 'Cơ Sở')]/following-sibling::div//input");

    By inputHiddenUpload = By.xpath("//input[@type='file']");

    By btnSave = By.xpath("//button[contains(@class, 'save-btn')]");

    By errorMessage = By.xpath("//p[contains(@class, 'Mui-error')]");

    public managementEduPhongHoc(WebDriver driver) {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            this.driver = driver;
        }
    }

    public void funcClickAddButton() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnAdd, intTimeOut);
                driver.findElement(btnAdd).click();
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    public void funcFillRoom(String ma, String ten, String diengiai, String tenCoSo) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, txtMa, intTimeOut);

                driver.findElement(txtMa).sendKeys(ma);
                driver.findElement(txtTen).sendKeys(ten);
                driver.findElement(txtDienGiai).sendKeys(diengiai);

                driver.findElement(drpCoSo).click();
                Thread.sleep(500);

                By itemCoSo = By.xpath("//li[contains(text(),'" + tenCoSo + "')]");
                driver.findElement(itemCoSo).click();

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void funcUploadImage(String imageName) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                String strImageFile = System.getProperty("user.dir").trim() + "/image/" + imageName;

                driver.findElement(inputHiddenUpload).sendKeys(strImageFile);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void funcClickSave() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                driver.findElement(btnSave).click();

                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    public void funcSearchRoom(String name) {
        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Tìm kiếm ...']"));
        searchInput.clear();
        searchInput.sendKeys(name);
        searchInput.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    public boolean isResultDisplayed(String expectedResult) {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                String xpath = "//*[contains(text(), '" + expectedResult + "')]";
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                return true;
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        }
        return false;
    }
    public boolean isTableEmpty() {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                Thread.sleep(2000);
                By tableRows = By.xpath("//tbody[contains(@class, 'MuiTableBody-root')]//tr");
                int rowCount = driver.findElements(tableRows).size();
                if (rowCount == 0) {
                    System.out.println("Bảng này trống");
                    return true;
                } else {
                    System.out.println("Bảng này không trống ");
                }
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        }
        return false;
    }
    public boolean isValidationMessageDisplayed() {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                Thread.sleep(2000);
                int errorCount = driver.findElements(errorMessage).size();
                if (errorCount > 0) {
                    System.out.println("Đã bắt được " + errorCount + " cảnh báo lỗi bắt buộc.");
                    return true;
                } else {
                    System.out.println("Không tìm thấy cảnh báo lỗi nào.");
                }
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        }
        return false;
    }
}



