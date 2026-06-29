Feature: Management YoEdu - Phòng học
  Background: open web page
    Given I open web page the YoEdu Management

  @SmokeTest @YoEduLogin @Valid
  Scenario Outline: Test YoEdu Login with valid credentials
    When I login with username "<username>" and password "<password>"
    Then I verify title page is "Yoedu Management"
    Examples:
      | username | password |
      | admin    | admin@123|

  @SmokeTest @YoEduLogin @Invalid
  Scenario Outline: Test YoEdu Login with invalid credentials
    When I login with username "<username>" and password "<password>"
    Then The system should display a login error message
    Examples:
      | username       | password      |
      | ADMINN         | admin@123     |
      | admin          | wrongpass123  |
      |                | admin@123     |
      | admin          |               |

  @SmokeTest @PhongHoc @Valid
  Scenario Outline: Navigate to Phong Hoc Successfully
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    Then I click on the menu "<databaseline>"
    Examples:
      | username | password  | baselinemenu | databaseline |
      | admin    | admin@123 | Dữ liệu nền  | Phòng học    |

  @SmokeTest @PhongHoc @CreateTest @Valid
  Scenario Outline: Create a new Phong Hoc Successfully
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    And I click on the Add new button
    And I fill room with code "PH001", name "Phòng thực hành 1", description "Phòng máy tính", facility "Thạch Lam"
    And I upload the room image "test.png"
    And I click on the Save button
    Examples:
      | username | password  | baselinemenu | databaseline |
      | admin    | admin@123 | Dữ liệu nền  | Phòng học    |

  @SmokeTest @PhongHoc @CreateTest @Invalid
  Scenario Outline: Create a new Phong Hoc with empty required fields
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    And I click on the Add new button
    And I fill room with code "", name "", description "Phòng test lỗi rỗng", facility "Thạch Lam"
    And I click on the Save button
    Then The system should display required field validation messages
    Examples:
      | username | password  | baselinemenu | databaseline |
      | admin    | admin@123 | Dữ liệu nền  | Phòng học    |

  @PhongHoc @SearchTest @Valid
  Scenario: Search for a room by name
    Given I open web page the YoEdu Management
    And I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Dữ liệu nền"
    And I click on the menu "Phòng học"
    When I search for the room with name "Phòng thực hành 1"
    Then The result should display "Phòng thực hành 1"

  @PhongHoc @SearchTest @Invalid
  Scenario Outline: Search for a room with invalid data
    Given I open web page the YoEdu Management
    And I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Dữ liệu nền"
    And I click on the menu "Phòng học"
    When I search for the room with name "<invalid_name>"
    Then The system should display a no data message
    Examples:
      | invalid_name           |
      | Phòng Không Tồn Tại    |
      | @#$%^&*()              |

  @DeleteTest @UITest @RegressionTest @PhongHoc @Valid
  Scenario Outline: Cancel Delete Room
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Dữ liệu nền"
    And I click on the menu "Phòng học"
    When I search for the room with name "<roomCode>"
    And T click the Three dots on the first room row
    And I select "<button>" option on action menu
    Then I click "<option>" on delete confirmation popup
    Then The result should display "<roomCode>"
    Examples:
      | roomCode | button | option  |
      | PH001    | Delete | Cancel  |

  @DeleteTest @UITest @RegressionTest @PhongHoc @Valid
  Scenario Outline: Accept Delete Room
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Dữ liệu nền"
    And I click on the menu "Phòng học"
    When I search for the room with name "<roomCode>"
    And T click the Three dots on the first room row
    And I select "<button>" option on action menu
    Then I click "<option>" on delete confirmation popup
    When I search for the room with name "<roomCode>"
    Then The system should display a no data message
    Examples:
      | roomCode | button | option  |
      | PH001    | Delete | Accept  |

  @ValidationTest @UITest @RegressionTest @PhongHoc @CreateTest @Invalid
  Scenario Outline: Create Room Duplicate Code Validation
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Dữ liệu nền"
    And I click on the menu "Phòng học"
    And I click on the Add new button
    And I fill room with code "<roomCode>", name "<roomName>", description "Trùng mã", facility "Thạch Lam"
    And I click on the Save button
    And I verify room error message "<expectedMessage>"
    Examples:
      | roomCode | roomName          | expectedMessage      |
      | PH001    | Phòng thực hành 1 | Mã này bị trùng rồi |
