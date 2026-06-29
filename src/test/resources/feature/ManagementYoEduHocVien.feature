Feature: Management YoEdu - Học viên
  Background: open web page
    Given I open web page the YoEdu Management

  @SmokeTest @HocVien @Valid
  Scenario Outline: Navigate to Student Management Successfully
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    Then I verify the student list page is displayed
    And The student list table should be visible
    Examples:
      | username | password  | baselinemenu      | databaseline |
      | admin    | admin@123 | Quản lý học viên | Học viên     |

  @SmokeTest @HocVien @SearchTest @Valid
  Scenario Outline: Search for a student successfully
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    When I search for the student with name "<existing_name>"
    Then The student result should display "<existing_name>"
    Examples:
      | existing_name |
      | Nguyễn        |

  @SmokeTest @HocVien @SearchTest @Invalid
  Scenario Outline: Search for a student unsuccessfully
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    When I search for the student with name "<random_name>"
    Then The student search results should display a no data message
    Examples:
      | random_name          |
      | testhocviengia       |
      | xyz123456789         |

  @SmokeTest @HocVien @SearchTest @Valid
  Scenario Outline: Advanced search for a student successfully
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    And I click on the Advanced Search button
    And I fill referrer phone with "<valid_phone>"
    And I submit Advanced Search
    Then The student result should display "<valid_phone>"
    Examples:
      | valid_phone |
      | 0905305796  |

  @SmokeTest @HocVien @SearchTest @Invalid
  Scenario Outline: Advanced search for a student unsuccessfully
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    And I click on the Advanced Search button
    And I fill referrer phone with "<invalid_phone>"
    And I submit Advanced Search
    Then The student search results should display a no data message
    Examples:
      | invalid_phone |
      | 0900000000    |
      | 123456        |

  @SmokeTest @HocVien @CreateTest @Valid
  Scenario Outline: Create a new Hoc Vien successfully
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    And I click on the Add Student button
    And I fill parent information: phone "<parent_phone>", password "<parent_password>", name "<parent_name>", address "<parent_address>", email "<parent_email>", gender "<parent_gender>", relationship "<relationship>"
    And I fill student information: name "<student_name>", code "<student_code>", dob "<student_dob>", gender "<student_gender>", phone "<student_phone>", description "<student_description>", school "<student_school>", grade "<student_grade>", note "<student_note>", status "<student_status>"
    And I upload the student image "<student_image>"
    And I click on the Save Student button
    Then I verify the student list page is displayed
    And The student list table should be visible
    When I search for the student with name "<student_name>"
    Then The student result should display "<student_name>"
    Examples:
      | username | password  | baselinemenu      | databaseline | parent_phone | parent_password | parent_name | parent_address | parent_email    | parent_gender | relationship | student_name | student_code | student_dob | student_gender | student_phone | student_description | student_school | student_grade | student_note | student_status | student_image |
      | admin    | admin@123 | Quản lý học viên | Học viên     | random       | password123     | Nguyễn Văn A | TP. HCM        | random          | Nam           | Cha          | Nguyễn Văn B | random       | 15/06/2012  | Nam            | random        | Học viên chăm chỉ   | Nguyễn Huệ     | Lớp 8         | Khá          | Hoạt động       | test.png      |
      | admin    | admin@123 | Quản lý học viên | Học viên     | random       | password456     | Trần Thị C   | Hà Nội         | random          | Nữ            | Mẹ           | Trần Thị D | random       | 20/08/2013  | Nữ             | random        | Học viên năng nổ    | Trần Hưng Đạo  | Lớp 7         | Giỏi         | Hoạt động       | test.png      |

  @SmokeTest @HocVien @CreateTest @Invalid
  Scenario Outline: Create a new Hoc Vien with empty required fields
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    And I click on the Add Student button
    And I click on the Save Student button without filling any fields
    Then The system should display student required field validation warnings
    Examples:
      | username | password  | baselinemenu      | databaseline |
      | admin    | admin@123 | Quản lý học viên | Học viên     |

  @SmokeTest @HocVien @CreateTest @Cancel @Valid
  Scenario Outline: Exit student form after partially filling required fields without saving
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    And I click on the Add Student button
    And I fill parent information: phone "<parent_phone>", password "<parent_password>", name "<parent_name>", address "<parent_address>", email "<parent_email>", gender "<parent_gender>", relationship "<relationship>"
    And I fill student information: name "<student_name>", code "<student_code>", dob "<student_dob>", gender "<student_gender>", phone "<student_phone>", description "<student_description>", school "<student_school>", grade "<student_grade>", note "<student_note>", status "<student_status>"
    When I click on the Cancel Student button
    Then The student form should be closed without saving
    And The student list table should be visible
    When I search for the student with name "<student_name>"
    Then The student search results should display a no data message
    Examples:
      | username | password  | baselinemenu      | databaseline | parent_phone | parent_password | parent_name  | parent_address | parent_email | parent_gender | relationship | student_name      | student_code | student_dob | student_gender | student_phone | student_description | student_school | student_grade | student_note | student_status |
      | admin    | admin@123 | Quản lý học viên | Học viên     | random       | password789     | Lê Văn Thoát | Đà Nẵng        | random       | Nam           | Cha          | Lê Văn Thoát Test | random       | 10/03/2011  | Nam            | random        | Test thoát form     | Lê Quý Đôn     | Lớp 9         | Bình thường  | Hoạt động      |

  @SmokeTest @HocVien @Reload @Valid
  Scenario Outline: Reload student list page clears the search box
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    And I type random text in the student search box
    When I click on the Reload button
    Then The student search box should be cleared
    And The student list table should be visible
    Examples:
      | username | password  | baselinemenu      | databaseline |
      | admin    | admin@123 | Quản lý học viên | Học viên     |

  @SmokeTest @HocVien @Excel @Valid
  Scenario Outline: Export student list to Excel with full content
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    And I clear the downloads directory
    And I click on the Export Excel button
    Then The downloaded Excel file should contain data rows
    Examples:
      | username | password  | baselinemenu      | databaseline |
      | admin    | admin@123 | Quản lý học viên | Học viên     |

  @SmokeTest @HocVien @Excel @Invalid
  Scenario Outline: Export student list to Excel with empty results
    When I login with username "<username>" and password "<password>"
    And I open the sidebar menu
    And I click on the menu "<baselinemenu>"
    And I click on the menu "<databaseline>"
    And I search for the student with name "vuôbv[qoev"
    And I clear the downloads directory
    And I click on the Export Excel button
    Then The downloaded Excel file should be empty
    Examples:
      | username | password  | baselinemenu      | databaseline |
      | admin    | admin@123 | Quản lý học viên | Học viên     |

  @ValidationTest @UITest @RegressionTest @HocVien @CreateTest @Invalid
  Scenario Outline: Create a New Student Validation
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    And I click on the Add Student button
    And I fill parent information: phone "<parent_phone>", password "<parent_password>", name "<parent_name>", address "TP. HCM", email "<parent_email>", gender "<parent_gender>", relationship "Cha"
    And I fill student information: name "<student_name>", code "<student_code>", dob "<student_dob>", gender "<student_gender>", phone "<student_phone>", description "Test", school "Nguyễn Du", grade "Lớp 8", note "Giỏi", status "Hoạt động"
    And I upload the student image "<student_image>"
    And I click on the Save Student button
    And I verify student error message "<expectedMessage>"
    Examples:
      | parent_phone | parent_password | parent_name | parent_email    | parent_gender | student_name | student_code | student_dob | student_gender | student_phone | student_image | expectedMessage                         |
      |              | password123     | Nguyễn Văn A| email@gmail.com | Nam           | Nguyễn Văn B | HV8888       | 15/06/2012  | Nam            | 0905000000    | test.png      | Vui lòng nhập số điện thoại phụ huynh!  |
      | 0905305111   | password123     |             | email@gmail.com | Nam           | Nguyễn Văn B | HV8888       | 15/06/2012  | Nam            | 0905000000    | test.png      | Vui lòng nhập tên phụ huynh!            |
      | 0905305111   | password123     | Nguyễn Văn A| @gmail.com      | Nam           | Nguyễn Văn B | HV8888       | 15/06/2012  | Nam            | 0905000000    | test.png      | Email không đúng định dạng!             |
      | 0905305111   | password123     | Nguyễn Văn A| emailgmail.com  | Nam           | Nguyễn Văn B | HV8888       | 15/06/2012  | Nam            | 0905000000    | test.png      | Email không đúng định dạng!             |
      | 0905305111   | password123     | Nguyễn Văn A| email@gmail     | Nam           | Nguyễn Văn B | HV8888       | 15/06/2012  | Nam            | 0905000000    | test.png      | Email không đúng định dạng!             |
      | 0905305111   | password123     | Nguyễn Văn A| email@gmail.com |               | Nguyễn Văn B | HV8888       | 15/06/2012  | Nam            | 0905000000    | test.png      | Vui lòng chọn giới tính phụ huynh!      |
      | 0905305111   | password123     | Nguyễn Văn A| email@gmail.com | Nam           |              | HV8888       | 15/06/2012  | Nam            | 0905000000    | test.png      | Vui lòng nhập tên học viên!             |
      | 0905305111   | password123     | Nguyễn Văn A| email@gmail.com | Nam           | Nguyễn Văn B | HV0001       | 15/06/2012  | Nam            | 0905000000    | test.png      | Mã học viên đã tồn tại!                 |
      | 0905305111   | password123     | Nguyễn Văn A| email@gmail.com | Nam           | Nguyễn Văn B | HV8888       | 31/05/2030  | Nam            | 0905000000    | test.png      | Ngày sinh không đúng định dạng!         |
      | 0905305111   | password123     | Nguyễn Văn A| email@gmail.com | Nam           | Nguyễn Văn B | HV8888       |             | Nam            | 0905000000    | test.png      | Vui lòng chọn ngày sinh!                |
      | 0905305111   | password123     | Nguyễn Văn A| email@gmail.com | Nam           | Nguyễn Văn B | HV8888       | 15/06/2012  |                | 0905000000    | test.png      | Vui lòng chọn giới tính học sinh        |
      | 0905305111   | password123     | Nguyễn Văn A| email@gmail.com | Nam           | Nguyễn Văn B | HV8888       | 15/06/2012  | Nam            | 0905000000    | invalid_file.xlsx | Ảnh không đúng định dạng!               |

  @SearchTest @UITest @RegressionTest @HocVien @Valid
  Scenario Outline: Search Student API and GUI Comparison
    When I execute the API to get an access token YoEdu
    And I open web page the YoEdu Management
    And I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    When I search for the student with name "<keywords>"
    And I execute the search API with keywords "<keywords>"
    Then I verify search result between API and GUI
    Examples:
      | keywords |
      | Nguyễn    |
      | Anh      |

  @DeleteTest @UITest @RegressionTest @HocVien @Valid
  Scenario Outline: Cancel Delete Student
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    When I search for the student with name "<studentCode>"
    Then The system should display the student row with code "<studentCode>"
    And T click the Three dots on the first student row
    And I select "<button>" option on action menu
    Then I click "<option>" on delete confirmation popup
    Then The system should display the student row with code "<studentCode>"
    Examples:
      | studentCode | button | option  |
      | HV0001      | Delete | Cancel  |

  @DeleteTest @UITest @RegressionTest @HocVien @Valid
  Scenario Outline: Accept Delete Student
    When I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    When I search for the student with name "<studentCode>"
    Then The system should display the student row with code "<studentCode>"
    And T click the Three dots on the first student row
    And I select "<button>" option on action menu
    Then I click "<option>" on delete confirmation popup
    When I search for the student with name "<studentCode>"
    Then The student search results should display a no data message
    Examples:
      | studentCode | button | option  |
      | HV0002      | Delete | Accept  |

  @UITest @RegressionTest @HocVien @Valid
  Scenario: Reload Page and Verify Student List
    When I execute the API to get an access token YoEdu
    And I open web page the YoEdu Management
    And I login with username "admin" and password "admin@123"
    And I open the sidebar menu
    And I click on the menu "Quản lý học viên"
    And I click on the menu "Học viên"
    When I click on the Reload button
    Then I verify student list with API
