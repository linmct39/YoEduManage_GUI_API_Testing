Feature: API Testing Rest Assured
     ##Scenario: API Testing Rest Assured
       ##  Then I execute the API to get an access token
        ## Then I execute the API tp get a list of countries

  @APITestingRestAssured
  Scenario: API Testing Rest Assured
    Then I execute the API to get an access token
    Then I execute the API to get a list of countries
       ## API_Sửa

  Scenario: Update classroom via API
    Then I execute the API to get an access token YoEdu
    Then I execute the API to update classroom with id 304, code "PHUpdate1", name "Phòng học Update"
      ##API_xoá

  Scenario: Delete classroom via API
    Then I execute the API to get an access token YoEdu
    Then I execute the API to delete classroom with id 308

  Scenario: Delete a non-existent classroom via API
    Then I execute the API to get an access token YoEdu
    Then I execute the API to delete a non-existent classroom with id 999999
    Then The API should reject the request
      ##API sửa_invalid

  Scenario: Update a non-existent classroom via API
    Then I execute the API to get an access token YoEdu
    Then I execute the API to update a non-existent classroom with id 999999, code "PH_Fake", name "Phòng Ảo"
    Then The API should reject the request
      ##API xoá_invalid

  Scenario: Delete a non-existent classroom via API
    Then I execute the API to get an access token YoEdu
    Then I execute the API to delete a non-existent classroom with id 999999
    Then The API should reject the request
  ## hocvienapi
  ## API_Sửa học viên (happy case)

  ## [BUG] API_Sửa học viên - hệ thống hiện bị lỗi, API không sửa được
 ##  Scenario: Update student via API - known bug
 ##    Then I execute the API to get an access token YoEdu
 ##    Then I execute the API to update student with id 1742, parent name "Trần Thị C_update"
 ##    Then The API should reject the request
  ## API_Xóa học viên (happy case)

  Scenario: Delete student via API
    Then I execute the API to get an access token YoEdu
    Then I execute the API to delete student with id 2
  ## API_Sửa học viên không tồn tại (invalid)

  ## Scenario: Update a non-existent student via API
   ##  Then I execute the API to get an access token YoEdu
   ##  Then I execute the API to update a non-existent student with id 999999, code "HV_Fake", name "Học Viên Ảo"
   ##  Then The API should reject the request
  ## API_Xóa học viên không tồn tại (invalid)

  Scenario: Delete a non-existent student via API
    Then I execute the API to get an access token YoEdu
    Then I execute the API to delete a non-existent student with id 999999
    Then The API should reject the request
