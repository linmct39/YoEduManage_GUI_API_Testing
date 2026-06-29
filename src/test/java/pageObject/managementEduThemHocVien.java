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

public class managementEduThemHocVien {
    WebDriver driver;
    CommonFunctions commonFunctions = new CommonFunctions();
    int intTimeOut = Integer.parseInt(System.getProperty("objectTimeout").trim());

    // Locators
    By txtParentPhone = By.xpath("//label[contains(., 'Số điện thoại phụ huynh')]/parent::*//input");
    By txtParentPassword = By.xpath("//label[contains(., 'Mật khẩu')]/parent::*//input");
    By txtParentName = By.xpath("//label[contains(., 'Tên phụ huynh')]/parent::*//input");
    By txtParentAddress = By.xpath("//label[contains(., 'Địa chỉ')]/parent::*//input");
    By txtParentEmail = By.xpath("//label[contains(., 'Email')]/parent::*//input");
    By cbParentGender = By.xpath("(//label[contains(., 'Giới tính')]/parent::*//div[@role='combobox'])[1]");
    By cbRelationship = By.xpath("//label[contains(., 'Mối quan hệ với học sinh')]/parent::*//div[@role='combobox']");

    // Student Info
    By txtStudentName = By.xpath("//label[contains(., 'Tên học viên')]/parent::*//input");
    By txtStudentCode = By.xpath("//label[contains(., 'Mã học viên')]/parent::*//input");
    By txtStudentDob = By.xpath("//label[contains(., 'Ngày sinh')]/parent::*//input");
    By cbStudentGender = By.xpath("(//label[contains(., 'Giới tính')]/parent::*//div[@role='combobox'])[2]");
    By txtStudentPhone = By.xpath("//label[contains(., 'Số điện thoại học viên')]/parent::*//input");
    By txtStudentDesc = By.xpath("//label[contains(., 'Diễn giải')]/parent::*//input");
    By txtStudentSchool = By.xpath("//label[contains(., 'Trường đang học')]/parent::*//input");
    By cbStudentGrade = By.xpath("//label[contains(., 'Khối lớp')]/parent::*//div[@role='combobox']");
    By txtStudentNote = By.xpath("//label[contains(., 'Ghi chú')]/parent::*//input");
    By cbStudentStatus = By.xpath("//div[@role='combobox' and @id='status-input-label']");
    By fileInput = By.xpath("//input[@type='file' and contains(@accept, 'image')]");

    // Buttons
    By btnSave = By.xpath("//button[contains(., 'Lưu') or contains(., 'LƯU')]");
    By btnCancel = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tạo mới học viên'])[1]/preceding::*[name()='svg'][1]");

    public managementEduThemHocVien(WebDriver driver) {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            this.driver = driver;
        }
    }

    public String generateRandomPhoneNumber() {
        String[] prefixes = {"033", "086", "037"};
        java.util.Random rand = new java.util.Random();
        String prefix = prefixes[rand.nextInt(prefixes.length)];
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < 7; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public String generateRandomStudentCode() {
        int randNum = (int) (Math.random() * 90000) + 10000;
        return "HV" + randNum;
    }

    public String generateRandomEmail() {
        int randNum = (int) (Math.random() * 90000) + 10000;
        return "parent" + randNum + "@gmail.com";
    }

    public void funcFillParentInfo(String phone, String password, String name, String address, String email, String gender, String relationship) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, txtParentPhone, intTimeOut);

                if (phone.equalsIgnoreCase("random")) {
                    phone = generateRandomPhoneNumber();
                }
                System.out.println("Using Parent Phone: " + phone);

                if (email.equalsIgnoreCase("random")) {
                    email = generateRandomEmail();
                }
                System.out.println("Using Parent Email: " + email);

                driver.findElement(txtParentPhone).sendKeys(phone);
                driver.findElement(txtParentPassword).sendKeys(password);
                driver.findElement(txtParentName).sendKeys(name);
                driver.findElement(txtParentAddress).sendKeys(address);
                driver.findElement(txtParentEmail).sendKeys(email);

                driver.findElement(cbParentGender).click();
                Thread.sleep(500);
                By optionGender = By.xpath("//li[@role='option' and text()='" + gender + "']");
                driver.findElement(optionGender).click();
                Thread.sleep(500);

                driver.findElement(cbRelationship).click();
                Thread.sleep(500);
                By optionRelation = By.xpath("//li[@role='option' and text()='" + relationship + "']");
                driver.findElement(optionRelation).click();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            Assert.fail("Failed to fill parent info: " + e.getMessage());
        }
    }

    public void funcFillStudentInfo(String name, String code, String dob, String gender, String phone, String description, String school, String grade, String note, String status) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, txtStudentName, intTimeOut);

                driver.findElement(txtStudentName).sendKeys(name);

                if (code.equalsIgnoreCase("random")) {
                    code = generateRandomStudentCode();
                }
                System.out.println("Using Student Code: " + code);

                driver.findElement(txtStudentCode).sendKeys(code);
                WebElement dobElement = driver.findElement(txtStudentDob);
                dobElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
                dobElement.sendKeys(dob);

                driver.findElement(cbStudentGender).click();
                Thread.sleep(500);
                By optionGender = By.xpath("//li[@role='option' and text()='" + gender + "']");
                driver.findElement(optionGender).click();
                Thread.sleep(500);

                if (phone.equalsIgnoreCase("random")) {
                    phone = generateRandomPhoneNumber();
                }
                System.out.println("Using Student Phone: " + phone);

                driver.findElement(txtStudentPhone).sendKeys(phone);
                driver.findElement(txtStudentDesc).sendKeys(description);
                driver.findElement(txtStudentSchool).sendKeys(school);


                driver.findElement(cbStudentGrade).click();
                Thread.sleep(500);
                By optionGrade = By.xpath("//li[@role='option' and contains(text(), '" + grade + "')]");
                driver.findElement(optionGrade).click();
                Thread.sleep(500);

                driver.findElement(txtStudentNote).sendKeys(note);

                WebElement statusElement = driver.findElement(cbStudentStatus);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", statusElement);
                Thread.sleep(500);


                driver.findElement(cbStudentStatus).click();
                Thread.sleep(500);
                By optionStatus;
                if (status.toLowerCase().contains("ngưng") || status.toLowerCase().contains("ngung") || status.toLowerCase().contains("inactive")) {
                    optionStatus = By.xpath("//li[@role='option' and contains(., 'Ngưng')]");
                } else {
                    optionStatus = By.xpath("//li[@role='option' and not(contains(., 'Ngưng'))]");
                }
                driver.findElement(optionStatus).click();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            Assert.fail("Failed to fill student info: " + e.getMessage());
        }
    }

    public void funcUploadStudentImage(String imageName) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                String strImageFile = new java.io.File("image/" + imageName).getAbsolutePath();
                System.out.println("Uploading image from absolute path: " + strImageFile);
                driver.findElement(fileInput).sendKeys(strImageFile);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail("Failed to upload student image: " + e.getMessage());
        }
    }

    public void funcClickSaveStudent() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnSave, intTimeOut);
                driver.findElement(btnSave).click();
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail("Failed to click Save Student: " + e.getMessage());
        }
    }

    public void funcClickSaveStudentWithoutFilling() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnSave, intTimeOut);
                WebElement saveBtn = driver.findElement(btnSave);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
                Thread.sleep(300);
                saveBtn.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail("Failed to click Save Student (empty form): " + e.getMessage());
        }
    }

    public boolean isStudentValidationWarningDisplayed() {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                Thread.sleep(1000);
                By errorLocator = By.xpath(
                    "//p[contains(@class,'Mui-error')] | " +
                    "//span[contains(@class,'Mui-error')] | " +
                    "//*[contains(text(),'bắt buộc') or contains(text(),'required') or contains(text(),'không hợp lệ') or contains(text(),'Vui lòng')]"
                );
                int errorCount = driver.findElements(errorLocator).size();
                if (errorCount > 0) {
                    System.out.println("Bắt được " + errorCount + " cảnh báo validation khi bỏ trống bắt buộc.");
                    return true;
                }
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.contains("/new")) {
                    System.out.println("Form chưa submit được, hệ thống giữ nguyên trên trang /new — validation đã chặn.");
                    return true;
                }
                System.out.println("Không tìm thấy cảnh báo validation.");
            } catch (Exception e) {
                Assert.fail("Lỗi khi kiểm tra validation: " + e.getMessage());
            }
        }
        return false;
    }

    public void funcClickCancelStudent() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnCancel, intTimeOut);
                WebElement cancelBtn = driver.findElement(btnCancel);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cancelBtn);
                Thread.sleep(300);
                cancelBtn.click();
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail("Failed to click Cancel/Exit button: " + e.getMessage());
        }
    }

    public boolean isFormClosedWithoutSaving() {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(intTimeOut));
                wait.until(ExpectedConditions.urlContains("/students"));
                wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/new")));
                System.out.println("Form đã thoát, URL trở về: " + driver.getCurrentUrl());
                return true;
            } catch (Exception e) {
                Assert.fail("Form không thoát về danh sách: " + e.getMessage());
            }
        }
        return false;
    }

    public void verifyErrorMessage(String strErrorMessage) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                By errorMessageLocator = By.xpath(
                        "//*[contains(@class,'MuiFormHelperText-root') " +
                                "or contains(@class,'MuiAlert-message') " +
                                "or contains(@class,'MuiSnackbarContent-message') " +
                                "or contains(@class,'MuiFormHelperText-contained')]" +
                                "[contains(normalize-space(.),\"" + strErrorMessage + "\")]"
                );
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
                Assert.assertTrue("Expected error message: " + strErrorMessage + " but got: " + element.getText().trim(), element.getText().trim().contains(strErrorMessage));
            }
        } catch (Exception e) {
            Assert.fail("Error message validation failed: " + e.getMessage());
        }
    }
}
