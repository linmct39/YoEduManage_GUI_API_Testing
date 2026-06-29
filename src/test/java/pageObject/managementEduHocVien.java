package pageObject;

import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

import common.CommonFunctions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class managementEduHocVien {
    WebDriver driver;
    CommonFunctions commonFunctions = new CommonFunctions();
    int intTimeOut = Integer.parseInt(System.getProperty("objectTimeout").trim());


    By titleHocVien = By.xpath("//h6[text()='Học viên']");
    By tableHocVien = By.xpath("//table");
    By listRowHocVien = By.xpath("//table//tbody//tr");
    By btnAdvancedSearch = By.xpath("//*[@data-testid='FilterAltIcon']");
    By txtReferrer = By.xpath("//input[@placeholder='Tìm kiếm theo người giới thiệu ...']");
    By btnSubmitAdvancedSearch = By.xpath("//button[contains(text(), 'Tìm kiếm') or contains(text(), 'TÌM KIẾM')]");
    By btnClearFilter = By.xpath("//button[contains(text(), 'Xóa bộ lọc') or contains(text(), 'XÓA BỘ LỌC')]");
    By btnAddStudent = By.xpath("//*[@data-testid='AddCircleOutlineIcon']");
    By btnReload = By.xpath("//input[@placeholder='Tìm kiếm ...']/following::button[2]");
    By btnExportExcel = By.xpath("//input[@placeholder='Tìm kiếm ...']/following::button[3]");
    By txtSearchBox = By.xpath("//input[@placeholder='Tìm kiếm ...']");
    By txtSearchBoxEmpty = By.xpath("//input[@value='']");

    // Action button, menu options and delete dialog locators
    By firstStudentRow = By.xpath("(//tbody//tr)[1]");
    By firstStudentActionButton = By.xpath("(//tbody//tr[1]//td[last()]//div[contains(@class,'pointer')])[1]");
    By menuView = By.xpath("//li[@role='menuitem' and .//span[normalize-space(.)='Xem']]");
    By menuDelete = By.xpath("//li[@role='menuitem' and (.//span[normalize-space(.)='Xóa'] or .//span[normalize-space(.)='Xoá'])]");
    By btnCloseDelete = By.xpath("//div[@role='dialog']//button[normalize-space(.)='Đóng' or normalize-space(.)='Đóng']");
    By btnConfirmDelete = By.xpath("//div[@role='dialog']//button[normalize-space(.)='Xóa' or normalize-space(.)='Xoá']");

    public managementEduHocVien(WebDriver driver) {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            this.driver = driver;
        }
    }

    public boolean isStudentPageDisplayed() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(intTimeOut));
                wait.until(ExpectedConditions.urlContains("/students"));
                wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/new")));
                return true;
            }
        } catch (Exception e) {
            Assert.fail("Student page is not displayed (URL does not match): " + e.getMessage());
        }
        return false;
    }

    public boolean isStudentTableDisplayed() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, tableHocVien, intTimeOut);
                int rowCount = driver.findElements(listRowHocVien).size();
                System.out.println("Found " + rowCount + " student records.");
                return driver.findElement(tableHocVien).isDisplayed() && rowCount >= 0;
            }
        } catch (Exception e) {
            Assert.fail("Student list table not found: " + e.getMessage());
        }
        return false;
    }

    public void funcSearchStudent(String name) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Tìm kiếm ...']"));
                searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
                searchInput.sendKeys(name);
                searchInput.sendKeys(Keys.ENTER);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            Assert.fail("Failed to search student: " + e.getMessage());
        }
    }

    public boolean isStudentResultDisplayed(String expectedResult) {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                String xpath = "//*[contains(text(), '" + expectedResult + "')]";
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                return true;
            } catch (Exception e) {
                Assert.fail("Student search result not displayed: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean isStudentTableEmpty() {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                Thread.sleep(500);
                By noDataElement = By.xpath("//*[contains(text(), 'Không có dữ liệu') or contains(text(), 'No data')]");
                int count = driver.findElements(noDataElement).size();
                if (count > 0) {
                    System.out.println("Found empty data message.");
                    return true;
                }
                System.out.println("Bug: Table did not display 'Không có dữ liệu' or 'No data' message when empty.");
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        }
        return false;
    }

    public void funcClickAdvancedSearch() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnAdvancedSearch, intTimeOut);
                driver.findElement(btnAdvancedSearch).click();
                Thread.sleep(300);
            }
        } catch (Exception e) {
            Assert.fail("Failed to click Advanced Search: " + e.getMessage());
        }
    }

    public void funcFillReferrerPhone(String phone) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, txtReferrer, intTimeOut);
                driver.findElement(txtReferrer).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
                driver.findElement(txtReferrer).sendKeys(phone);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            Assert.fail("Failed to fill referrer phone: " + e.getMessage());
        }
    }

    public void funcSubmitAdvancedSearch() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnSubmitAdvancedSearch, intTimeOut);
                driver.findElement(btnSubmitAdvancedSearch).click();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            Assert.fail("Failed to submit Advanced Search: " + e.getMessage());
        }
    }

    public void funcClearFilter() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnClearFilter, intTimeOut);
                driver.findElement(btnClearFilter).click();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            Assert.fail("Failed to clear advanced search filter: " + e.getMessage());
        }
    }

    public void funcClickAddStudentButton() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnAddStudent, intTimeOut);
                driver.findElement(btnAddStudent).click();
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail("Failed to click Add Student button: " + e.getMessage());
        }
    }

    public void funcTypeRandomTextInSearch() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, txtSearchBox, intTimeOut);
                String randomText = "abc" + (int)(Math.random() * 9000 + 1000);
                System.out.println("Typing random search text: " + randomText);
                driver.findElement(txtSearchBox).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
                driver.findElement(txtSearchBox).sendKeys(randomText);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            Assert.fail("Failed to type random text in search box: " + e.getMessage());
        }
    }

    public void funcClickReloadButton() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnReload, intTimeOut);
                driver.findElement(btnReload).click();
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail("Failed to click Reload button: " + e.getMessage());
        }
    }

    public boolean isSearchBoxCleared() {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                Thread.sleep(500);
                WebElement searchBox = driver.findElement(txtSearchBox);
                String currentValue = searchBox.getAttribute("value");
                System.out.println("Search box value after reload: '" + currentValue + "'");
                if (currentValue == null || currentValue.trim().isEmpty()) {
                    System.out.println("Thanh tìm kiếm đã bị xóa sau khi tải lại — Đúng!");
                    return true;
                }
                System.out.println("Bug: Thanh tìm kiếm vẫn còn giá trị cũ sau khi tải lại!");
            } catch (Exception e) {
                Assert.fail("Failed to check search box value: " + e.getMessage());
            }
        }
        return false;
    }

    public void funcClearDownloadFolder() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                java.io.File downloadDir = new java.io.File(System.getProperty("user.dir") + java.io.File.separator + "downloads");
                if (downloadDir.exists() && downloadDir.isDirectory()) {
                    java.io.File[] files = downloadDir.listFiles();
                    if (files != null) {
                        for (java.io.File file : files) {
                            if (file.isFile() && (file.getName().endsWith(".xlsx") || file.getName().endsWith(".tmp") || file.getName().endsWith(".crdownload"))) {
                                file.delete();
                            }
                        }
                    }
                }
                System.out.println("Cleared downloads directory successfully.");
            }
        } catch (Exception e) {
            System.out.println("Failed to clear downloads directory: " + e.getMessage());
        }
    }

    public void funcClickExportExcelButton() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, btnExportExcel, intTimeOut);
                driver.findElement(btnExportExcel).click();
                System.out.println("Clicked Export Excel button.");
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail("Failed to click Export Excel button: " + e.getMessage());
        }
    }

    public boolean verifyDownloadedExcel(boolean expectEmpty) {
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            try {
                java.io.File downloadDir = new java.io.File(System.getProperty("user.dir") + java.io.File.separator + "downloads");
                java.io.File downloadedFile = null;
                for (int i = 0; i < 20; i++) {
                    java.io.File[] files = downloadDir.listFiles();
                    if (files != null) {
                        for (java.io.File file : files) {
                            if (file.isFile() && file.getName().endsWith(".xlsx")) {
                                downloadedFile = file;
                                break;
                            }
                        }
                    }
                    if (downloadedFile != null) {
                        break;
                    }
                    Thread.sleep(500);
                }

                if (downloadedFile == null) {
                    Assert.fail("No Excel file (.xlsx) found in downloads folder!");
                    return false;
                }

                System.out.println("Found downloaded Excel file: " + downloadedFile.getAbsolutePath() + " (Size: " + downloadedFile.length() + " bytes)");
                int rowCount = 0;
                try (java.util.zip.ZipFile zipFile = new java.util.zip.ZipFile(downloadedFile)) {
                    java.util.zip.ZipEntry entry = zipFile.getEntry("xl/worksheets/sheet1.xml");
                    if (entry != null) {
                        try (java.io.InputStream is = zipFile.getInputStream(entry);
                             java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(is, "UTF-8"))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                int lastIdx = 0;
                                while ((lastIdx = line.indexOf("<row", lastIdx)) != -1) {
                                    rowCount++;
                                    lastIdx += 4;
                                }
                            }
                        }
                    } else {
                        System.out.println("Warning: xl/worksheets/sheet1.xml not found in Excel archive!");
                    }
                }

                System.out.println("Excel sheet contains " + rowCount + " rows.");

                if (expectEmpty) {
                    if (rowCount <= 1) {
                        System.out.println("Excel is indeed empty/blank (contains " + rowCount + " rows) — Correct!");
                        return true;
                    } else {
                        Assert.fail("Expected Excel to be empty, but found " + rowCount + " rows including header and data!");
                        return false;
                    }
                } else {
                    if (rowCount > 1) {
                        System.out.println("Excel contains full content (contains " + rowCount + " rows) — Correct!");
                        return true;
                    } else {
                        Assert.fail("Expected Excel to have data rows, but found only " + rowCount + " rows (empty/header only)!");
                        return false;
                    }
                }
            } catch (Exception e) {
                Assert.fail("Failed to verify downloaded Excel: " + e.getMessage());
            }
        }
        return false;
    }

    public void funcClickThreeDots() {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                commonFunctions.waitUntilElementLocated(driver, firstStudentRow, intTimeOut);
                commonFunctions.waitUntilElementLocated(driver, firstStudentActionButton, intTimeOut);
                WebElement actionBtn = driver.findElement(firstStudentActionButton);
                try {
                    actionBtn.click();
                } catch (Exception clickEx) {
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", actionBtn);
                }
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void funcSelectAction(String strButton) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                By targetMenuOption = strButton.equalsIgnoreCase("Delete") ? menuDelete : menuView;
                commonFunctions.waitUntilElementLocated(driver, targetMenuOption, intTimeOut);
                driver.findElement(targetMenuOption).click();
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void funcConfirmDelete(String option) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                By targetButton = option.equalsIgnoreCase("Accept") ? btnConfirmDelete : btnCloseDelete;
                commonFunctions.waitUntilElementLocated(driver, targetButton, intTimeOut);
                WebElement btn = driver.findElement(targetButton);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void funcIsStudentVisible(String studentCode) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(tableHocVien));
                String studentRow = String.format("//table//tbody/tr[contains(., '%s')]", studentCode);
                List<WebElement> rows = driver.findElements(By.xpath(studentRow));
                boolean isStudentFound = false;
                if (!rows.isEmpty()) {
                    String rowText = rows.get(0).getText().trim();
                    if (!rowText.isEmpty() && !rowText.equalsIgnoreCase("Không có dữ liệu") && !rowText.equalsIgnoreCase("No data")) {
                        isStudentFound = true;
                    }
                }
                Assert.assertTrue("Mong đợi học viên " + studentCode + " xuất hiện, nhưng không tìm thấy hoặc bảng trống", isStudentFound);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void funcIsStudentNotVisible(String studentCode) {
        try {
            if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
                String studentRow = String.format("//table//tbody/tr[contains(., '%s')]", studentCode);
                List<WebElement> rows = driver.findElements(By.xpath(studentRow));
                boolean isStudentFound = false;
                if (!rows.isEmpty()) {
                    String rowText = rows.get(0).getText().trim();
                    if (!rowText.isEmpty() && !rowText.equalsIgnoreCase("Không có dữ liệu") && !rowText.equalsIgnoreCase("No data")) {
                        isStudentFound = true;
                    }
                }
                Assert.assertFalse("Mong đợi học viên " + studentCode + " bị xóa, nhưng vẫn tìm thấy trong bảng", isStudentFound);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
