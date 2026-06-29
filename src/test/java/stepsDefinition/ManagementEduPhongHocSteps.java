package stepsDefinition;

import common.ContextSteps;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.managementEduPhongHoc;
import java.time.Duration;

public class ManagementEduPhongHocSteps {
    WebDriver driver;
    WebDriverWait wait;
    managementEduPhongHoc phongHocPage;

    public ManagementEduPhongHocSteps() throws Throwable {
        ContextSteps contextSteps = new ContextSteps();
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            driver = contextSteps.getDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
            phongHocPage = new managementEduPhongHoc(driver);
        }
    }

    @When("I click on the Add new button")
    public void I_click_on_the_Add_new_button() {
        try {
            System.out.println("Step: I click on the Add new button");
            phongHocPage.funcClickAddButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("I fill room with code {string}, name {string}, description {string}, facility {string}")
    public void i_fill_with_code_name_description_facility(String code, String name, String description, String facility) {
        try {
            System.out.println("Step: Fill room details - " + code + "|" + name);
            phongHocPage.funcFillRoom(code, name, description, facility);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("I upload the room image {string}")
    public void i_upload_the_room_image(String imageName) {
        try {
            System.out.println("Step: Upload room image - " + imageName);
            phongHocPage.funcUploadImage(imageName);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("I click on the Save button")
    public void I_click_on_the_save_button() {
        try {
            System.out.println("Step: I click on the save button");
            phongHocPage.funcClickSave();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("I search for the room with name {string}")
    public void i_search_for_the_room_with_name(String roomName) {
        System.out.println("Step: Search for room with name: " + roomName);
        phongHocPage.funcSearchRoom(roomName);
    }

    @Then("The result should display {string}")
    public void the_result_should_display(String expectedResult) {
        boolean isDisplayed = phongHocPage.isResultDisplayed(expectedResult);
        Assert.assertTrue("Kết quả tìm kiếm không hiển thị: " + expectedResult, isDisplayed);
    }

    @Then("The system should display a no data message")
    public void the_system_should_display_a_no_data_message() {
        System.out.println("Step: Verify table result is empty");
        boolean isEmpty = phongHocPage.isTableEmpty();
        Assert.assertTrue("Kết quả tìm kiếm sai: Bảng vẫn đang hiển thị dữ liệu thay vì trống!", isEmpty);
    }

    @Then("The system should display required field validation messages")
    public void the_system_should_display_required_field_validation_messages() {
        System.out.println("Step: Verify validation messages displayed for empty fields");
        boolean isWarningShown = phongHocPage.isValidationMessageDisplayed();
        Assert.assertTrue("Cho phép lưu khi bỏ trống trường bắt buộc!", isWarningShown);
    }

    @And("T click the Three dots on the first room row")
    public void tClickTheThreeDotsOnTheFirstRoomRow() {
        System.out.println("Step: Click the Three dots on the first room row");
        phongHocPage.funcClickThreeDots();
    }



    @And("I verify room error message {string}")
    public void iVerifyRoomErrorMessage(String expectedMessage) {
        System.out.println("Step: Verify room error message is: " + expectedMessage);
        phongHocPage.verifyRoomErrorMessage(expectedMessage);
    }
}
