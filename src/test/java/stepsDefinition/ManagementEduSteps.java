package stepsDefinition;

import common.ContextSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.managementEduLogin;
import java.time.Duration;
import pageObject.managementEduDashboard;
import pageObject.managementEduPhongHoc;
import pageObject.managementEduHocVien;
import pageObject.managementEduThemHocVien;

public class ManagementEduSteps {
    WebDriver driver;
    WebDriverWait wait;
    managementEduLogin loginPage;
    managementEduDashboard dashBoardPage;
    managementEduPhongHoc phongHocPage;
    managementEduHocVien hocVienPage;
    managementEduThemHocVien themHocVienPage;

    public ManagementEduSteps()throws Throwable{
        ContextSteps contextSteps = new ContextSteps();
       if (System.getProperty("osName").trim().toLowerCase().contains("windows")){
           driver =contextSteps.getDriver();
           wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
           loginPage = new managementEduLogin(driver);
           dashBoardPage = new managementEduDashboard(driver);
           phongHocPage = new managementEduPhongHoc(driver);
           hocVienPage = new managementEduHocVien(driver);
           themHocVienPage = new managementEduThemHocVien(driver);

       }
    }
    @Given("I open web page the YoEdu Management")
    public void i_open_web_page_the_yoedu_management(){
      try {
          System.out.println("Step: I open web page the YoEdu Management");
          driver.get(System.getProperty("baseUrl"));
      }catch (Exception e){
          Assert.fail(e.getMessage());
      }
    }
    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        try {
            System.out.println("Step: I login with username: " + username + " | password: " + password);
            loginPage.funcLogin(username, password);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    @Then("The system should display a login error message")
    public void the_system_should_display_a_login_error_message() {
        System.out.println("Step: Verify hiển thị cảnh báo đăng nhập thất bại");
        boolean isErrorShown = loginPage.isLoginErrorMessageDisplayed();
        Assert.assertTrue("Đăng nhập sai nhưng hệ thống không hiện thông báo lỗi!", isErrorShown);
    }
    @When("I open the sidebar menu")
    public void I_open_the_sidebar_menu (){
        try{
            System.out.println("Step: I open the sidebar menu");
            dashBoardPage.funcOpenSidebarMenu();
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
    @When("I click on the menu {string}")
    public void I_click_on_the_menu(String menuName){
        try{
            System.out.println("Step: I click on the menu: "+ menuName);
            dashBoardPage.funcClickMenuItem(menuName);
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
    @When("I click on the Add new button")
    public void I_click_on_the_Add_new_button(){
        try{
            System.out.println("Step:I click on the Add new button");
            phongHocPage.funcClickAddButton();
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
    @When("I fill room with code {string}, name {string}, description {string}, facility {string}")
    public void  i_fill_with_code_name_description_facility(String code,String name,String description,String facility){
        try{
            System.out.println("Step:Fill room into - "+ code +"|" + name);
            phongHocPage.funcFillRoom(code,name,description,facility);
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }
    @When("I upload the room image {string}")
    public void i_upload_the_room_image(String imageName){
        try{
            System.out.println("Step: Upload image - " + imageName);
            phongHocPage.funcUploadImage(imageName);
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
    @When("I click on the Save button")
    public void I_click_on_the_save_button(){
        try{
            System.out.println("Step: I click on the save button");
            phongHocPage.funcClickSave();
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
    @When("I search for the room with name {string}")
    public void i_search_for_the_room_with_name(String roomName) {
        System.out.println("Step: Tìm kiếm phòng với tên: " + roomName);
        phongHocPage.funcSearchRoom(roomName);
    }
    @Then("The result should display {string}")
    public void the_result_should_display(String expectedResult) {
        boolean isDisplayed = phongHocPage.isResultDisplayed(expectedResult);
        Assert.assertTrue("Kết quả tìm kiếm không hiển thị: " + expectedResult, isDisplayed);
    }
    @Then("The system should display a no data message")
    public void the_system_should_display_a_no_data_message() {
        System.out.println("Step: Verify bảng kết quả trống");
        boolean isEmpty = phongHocPage.isTableEmpty();
        Assert.assertTrue("Kết quả tìm kiếm sai: Bảng vẫn đang hiển thị dữ liệu thay vì trống!", isEmpty);
    }
    @Then("The system should display required field validation messages")
    public void the_system_should_display_required_field_validation_messages() {
        System.out.println("Verify hiển thị cảnh báo lỗi nhập liệu");
        boolean isWarningShown = phongHocPage.isValidationMessageDisplayed();
        Assert.assertTrue("Cho phép lưu khi bỏ trống trường bắt buộc!", isWarningShown);
    }

    @Then("I verify the student list page is displayed")
    public void i_verify_the_student_list_page_is_displayed() {
        System.out.println("Step: Verify student list page is displayed");
        boolean isDisplayed = hocVienPage.isStudentPageDisplayed();
        Assert.assertTrue("Student page is not displayed!", isDisplayed);
    }

    @And("The student list table should be visible")
    public void the_student_list_table_should_be_visible() {
        System.out.println("Step: Verify student list table is visible");
        boolean isVisible = hocVienPage.isStudentTableDisplayed();
        Assert.assertTrue("Student list table is not visible or rows couldn't be fetched!", isVisible);
    }

    @When("I search for the student with name {string}")
    public void i_search_for_the_student_with_name(String name) {
        System.out.println("Step: Search for student: " + name);
        hocVienPage.funcSearchStudent(name);
    }

    @Then("The student result should display {string}")
    public void the_student_result_should_display(String expectedResult) {
        System.out.println("Step: Verify search result displays: " + expectedResult);
        boolean isDisplayed = hocVienPage.isStudentResultDisplayed(expectedResult);
        Assert.assertTrue("Expected search result not found: " + expectedResult, isDisplayed);
    }

    @Then("The student search results should display a no data message")
    public void the_student_search_results_should_display_a_no_data_message() {
        System.out.println("Step: Verify student search results display empty message");
        boolean isEmpty = hocVienPage.isStudentTableEmpty();
        Assert.assertTrue("Bug: Không hiển thị thông báo 'Không có dữ liệu' hay 'No data' khi tìm kiếm không ra kết quả!", isEmpty);
    }

    @When("I click on the Advanced Search button")
    public void i_click_on_the_advanced_search_button() {
        System.out.println("Step: Click Advanced Search button");
        hocVienPage.funcClickAdvancedSearch();
    }

    @When("I fill referrer phone with {string}")
    public void i_fill_referrer_phone_with(String phone) {
        System.out.println("Step: Fill referrer phone: " + phone);
        hocVienPage.funcFillReferrerPhone(phone);
    }

    @When("I submit Advanced Search")
    public void i_submit_advanced_search() {
        System.out.println("Step: Submit Advanced Search");
        hocVienPage.funcSubmitAdvancedSearch();
    }

    @When("I clear Advanced Search filter")
    public void i_clear_advanced_search_filter() {
        System.out.println("Step: Clear Advanced Search filter");
        hocVienPage.funcClearFilter();
    }

    @When("I click on the Add Student button")
    public void i_click_on_the_add_student_button() {
        System.out.println("Step: Click on the Add Student button");
        hocVienPage.funcClickAddStudentButton();
    }

    @When("I fill parent information: phone {string}, password {string}, name {string}, address {string}, email {string}, gender {string}, relationship {string}")
    public void i_fill_parent_information(String phone, String password, String name, String address, String email, String gender, String relationship) {
        System.out.println("Step: Fill parent information: " + name + " | " + phone);
        themHocVienPage.funcFillParentInfo(phone, password, name, address, email, gender, relationship);
    }

    @When("I fill student information: name {string}, code {string}, dob {string}, gender {string}, phone {string}, description {string}, school {string}, grade {string}, note {string}, status {string}")
    public void i_fill_student_information(String name, String code, String dob, String gender, String phone, String description, String school, String grade, String note, String status) {
        System.out.println("Step: Fill student information: " + name + " | " + code);
        themHocVienPage.funcFillStudentInfo(name, code, dob, gender, phone, description, school, grade, note, status);
    }

    @When("I upload the student image {string}")
    public void i_upload_the_student_image(String imageName) {
        System.out.println("Step: Upload student image: " + imageName);
        themHocVienPage.funcUploadStudentImage(imageName);
    }

    @When("I click on the Save Student button")
    public void i_click_on_the_save_student_button() {
        System.out.println("Step: Click on the Save Student button");
        themHocVienPage.funcClickSaveStudent();
    }

    @When("I click on the Save Student button without filling any fields")
    public void i_click_on_the_save_student_button_without_filling() {
        System.out.println("Step: Click Save Student with no fields filled");
        themHocVienPage.funcClickSaveStudentWithoutFilling();
    }

    @Then("The system should display student required field validation warnings")
    public void the_system_should_display_student_required_field_validation_warnings() {
        System.out.println("Step: Verify validation warnings shown for empty student form");
        boolean isWarningShown = themHocVienPage.isStudentValidationWarningDisplayed();
        Assert.assertTrue("Hệ thống không hiển thị cảnh báo khi bỏ trống trường bắt buộc!", isWarningShown);
    }

    @When("I click on the Cancel Student button")
    public void i_click_on_the_cancel_student_button() {
        System.out.println("Step: Click Cancel/Exit button on student form");
        themHocVienPage.funcClickCancelStudent();
    }

    @Then("The student form should be closed without saving")
    public void the_student_form_should_be_closed_without_saving() {
        System.out.println("Step: Verify form closed and no data was saved");
        boolean isClosed = themHocVienPage.isFormClosedWithoutSaving();
        Assert.assertTrue("Form không thoát về danh sách sau khi nhấn Hủy/Thoát!", isClosed);
    }

    @When("I type random text in the student search box")
    public void i_type_random_text_in_the_student_search_box() {
        System.out.println("Step: Type random text in search box");
        hocVienPage.funcTypeRandomTextInSearch();
    }

    @When("I click on the Reload button")
    public void i_click_on_the_reload_button() {
        System.out.println("Step: Click Reload button on student list");
        hocVienPage.funcClickReloadButton();
    }

    @Then("The student search box should be cleared")
    public void the_student_search_box_should_be_cleared() {
        System.out.println("Step: Verify search box is cleared after reload");
        boolean isCleared = hocVienPage.isSearchBoxCleared();
        Assert.assertTrue("Bug: Thanh tìm kiếm vẫn còn giá trị cũ sau khi tải lại trang!", isCleared);
    }

    @When("I clear the downloads directory")
    public void i_clear_the_downloads_directory() {
        System.out.println("Step: Clear old downloaded Excel files");
        hocVienPage.funcClearDownloadFolder();
    }

    @When("I click on the Export Excel button")
    public void i_click_on_the_export_excel_button() {
        System.out.println("Step: Click Export Excel button");
        hocVienPage.funcClickExportExcelButton();
    }

    @Then("The downloaded Excel file should contain data rows")
    public void the_downloaded_excel_file_should_contain_data_rows() {
        System.out.println("Step: Verify downloaded Excel file has data");
        boolean isValid = hocVienPage.verifyDownloadedExcel(false);
        Assert.assertTrue("Excel is empty or not downloaded correctly!", isValid);
    }

    @Then("The downloaded Excel file should be empty")
    public void the_downloaded_excel_file_should_be_empty() {
        System.out.println("Step: Verify downloaded Excel file is empty");
        boolean isValid = hocVienPage.verifyDownloadedExcel(true);
        Assert.assertTrue("Excel has data rows, but expected it to be empty!", isValid);
    }
}
