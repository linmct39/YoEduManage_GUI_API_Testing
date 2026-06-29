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
import pageObject.managementEduDashboard;
import pageObject.managementEduHocVien;
import java.time.Duration;

public class ManagementEduLoginSteps {
    WebDriver driver;
    WebDriverWait wait;
    managementEduLogin loginPage;
    managementEduDashboard dashBoardPage;

    public ManagementEduLoginSteps() throws Throwable {
        ContextSteps contextSteps = new ContextSteps();
        if (System.getProperty("osName").trim().toLowerCase().contains("windows")) {
            driver = contextSteps.getDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));
            loginPage = new managementEduLogin(driver);
            dashBoardPage = new managementEduDashboard(driver);
        }
    }

    @Given("I open web page the YoEdu Management")
    public void i_open_web_page_the_yoedu_management() {
        try {
            System.out.println("Step: I open web page the YoEdu Management");
            driver.get(System.getProperty("baseUrl"));
        } catch (Exception e) {
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
        System.out.println("Step: Verify login error message is displayed");
        boolean isErrorShown = loginPage.isLoginErrorMessageDisplayed();
        Assert.assertTrue("Đăng nhập sai nhưng hệ thống không hiện thông báo lỗi!", isErrorShown);
    }

    @When("I open the sidebar menu")
    public void I_open_the_sidebar_menu() {
        try {
            System.out.println("Step: I open the sidebar menu");
            dashBoardPage.funcOpenSidebarMenu();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("I click on the menu {string}")
    public void I_click_on_the_menu(String menuName) {
        try {
            System.out.println("Step: I click on the menu: " + menuName);
            dashBoardPage.funcClickMenuItem(menuName);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("I select {string} option on action menu")
    public void iSelectOptionOnActionMenu(String strButton) {
        System.out.println("Step: Select " + strButton + " option on action menu (Shared)");
        new managementEduHocVien(driver).funcSelectAction(strButton);
    }

    @Then("I click {string} on delete confirmation popup")
    public void iClickOnDeleteConfirmationPopup(String option) {
        System.out.println("Step: Click " + option + " on delete confirmation popup (Shared)");
        new managementEduHocVien(driver).funcConfirmDelete(option);
    }
}
