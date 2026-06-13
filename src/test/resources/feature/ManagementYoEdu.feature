Feature: Management YoEdu
  Background: open web page
    Given I open web page the YoEdu Management

    @smokeTest @YoEdulogin @valid
      Scenario Outline: Test YoEdu Login with valid credentials
    #  Given I open web page the YoEdu Management
      When I login with username "<username>" and password "<password>"
      Then I verify title page is "Yoedu Management"
      Examples:
       | username | password |
       |  admin   | admin@123|
    @smokeTest @YoEdulogin @invalid
    Scenario Outline: Test YoEdu Login with invalid credentials
      When I login with username "<username>" and password "<password>"
      Then The system should display a login error message

      Examples:
        | username       | password      |
        | ADMINN         | admin@123     |
        | admin          | wrongpass123  |
        |                | admin@123     |
        | admin          |               |

      @smokeTest @phonghoc
        Scenario Outline: Navigate to Phong Hoc Successfully
        When I login with username "<username>" and password "<password>"
        And I open the sidebar menu
        And I click on the menu "<baselinemenu>"
        Then I click on the menu "<databaseline>"

        Examples:
         |username | password | baselinemenu | databaseline |
         |  admin |    admin@123 | Dữ liệu nền   | Phòng học |

        @smokeTest @taomoi_phonghoc @valid
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

           @smokeTest @taomoi_phonghoc @invalid
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

        Scenario: Search for a room by name
          Given I open web page the YoEdu Management
          And I login with username "admin" and password "admin@123"
          And I open the sidebar menu
          And I click on the menu "Dữ liệu nền"
          And I click on the menu "Phòng học"
          When I search for the room with name "Phòng thực hành 1"
          Then The result should display "Phòng thực hành 1"


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

        @smokeTest @hocvien
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

        @smokeTest @hocvien
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

        @smokeTest @hocvien
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

        @smokeTest @hocvien
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

        @smokeTest @hocvien
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


        @smokeTest @hocvien @taomoi_hocvien @valid
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


        @smokeTest @hocvien @taomoi_hocvien @invalid
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


        @smokeTest @hocvien @taomoi_hocvien @cancel
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


        @smokeTest @hocvien @reload
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

        @smokeTest @hocvien @excel
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

        @smokeTest @hocvien @excel
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



