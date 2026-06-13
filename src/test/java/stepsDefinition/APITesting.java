package stepsDefinition;

import static org.junit.Assert.assertEquals;
import java.time.Duration;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.CommonFunctions;
import common.ContextSteps;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class APITesting {
    WebDriver driver;
    WebDriverWait wait;
    private Response invalidCase;

    int intRandomNumber = 0;

    CommonFunctions commonFunction;

    public APITesting() throws Throwable {
        ContextSteps contextSteps = new ContextSteps();

        driver = contextSteps.getDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("objectTimeout"))));

        commonFunction = new CommonFunctions();

        intRandomNumber = commonFunction.funcRandomIntegerBetweenRange(100000, 999999);
    }

    @Then("I execute the API to get an access token")
    public void iExecuteThaAPIToGetAnAccessToken() {
        try {
           RestAssured.baseURI = System.getProperty("apiUrl");

            String strTokenUrl = "/ids/connect/token";

            Response response = given()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .formParams("client_id", System.getProperty("client_id"))
                    .formParams("client_secret", System.getProperty("client_secret"))
                    .formParams("grant_type", "client_credentials")
                    .when()
                    .post(strTokenUrl)
                    .then()
                    .extract()
                    .response();

            assertEquals(200, response.statusCode());

            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            String accessToken = jsonObject.get("access_token").toString();
            System.setProperty("access_token", accessToken);
/*            RestAssured .baseURI = System.getProperty("apiUrl");
            String strTokenUrl="/ids/connect/token";
            Response response = given()
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .formParams("client_id",System.getProperty("client_id"))
                    .formParams("client_secret",System.getProperty("client_secret"))
                    .formParams("grant_type","client_credentials")
                    .when()
                    .post(strTokenUrl)
                    .then()
                    .extract()
                    .response();
            assertEquals(200,response.statusCode());
            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            String accessToken = jsonObject.getString("access_token").toString();
            System.getProperty("access_token",accessToken);*/

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("I execute the API to get a list of countries")
    public void iExecuteTheAPITpGetAListOfCoutries() {
       try {
            RestAssured.baseURI = System.getProperty("apiUrl");

            String strTokenUrl = "/dragonx/api/Internal/v1/Countries/GetList?SortBy=id&SortDirection=asc";

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .when()
                    .get(strTokenUrl)
                    .then()
                    .extract()
                    .response();

            assertEquals(200, response.statusCode());

            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            org.json.JSONArray ja = jsonObject.getJSONArray("items");

            for (int i=0; i<ja.length(); i++) {
                JSONObject country = ja.getJSONObject(i);
                String countryId = country.get("id").toString();
                String countryName = country.get("name").toString();

                System.out.println("Country Id : " + countryId + " Country Name : " + countryName);
            }
        //try {
        /*RestAssured.baseURI = System.getProperty("apiUrl");
        String  strTokenUrl="/dragonx/api/Internal/v1/Countries/GetList?SortBy=id&SortDirection=asc";
        Response response = given()
                .header("Content-Type","application/json")
                .formParams("Authorization","Bearer"+System.getProperty("acess_token"))
                .when()
                .get(strTokenUrl)
                .then()
                .extract()
                .response();
            assertEquals(200, response.statusCode());

            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            org.json.JSONArray ja = jsonObject.getJSONArray("items");
            for(int i =0; i< ja.length();i++){
                JSONObject country = ja.getJSONObject(i);
                String countryId= country.get("id").toString();
                String countryName = country.get("name").toString();
                System.out.println("Country Id : " + countryId + " Country Name : " + countryName);*/
            //}
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    @Then("I execute the API to get an access token YoEdu")
    public void iExecuteThaAPIToGetAnAccessTokenYoEdu() {
        try {
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strLoginUrl = "/yoedu/management/api/users/login";

            JSONObject loginParams = new JSONObject();
            loginParams.put("userName", "admin");
            loginParams.put("password", "admin@123");
            Response response = given()
                    .header("Content-Type", "application/json")
                    .body(loginParams.toString())
                    .when()
                    .post(strLoginUrl)
                    .then()
                    .extract()
                    .response();
            assertEquals(200, response.statusCode());
            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            String accessToken = "";
            if (jsonObject.has("token")) {
                accessToken = jsonObject.getString("token");
            } else if (jsonObject.has("access_token")) {
                accessToken = jsonObject.getString("access_token");
            } else if (jsonObject.has("data")) {
                accessToken = jsonObject.getJSONObject("data").getString("token");
            }
            System.setProperty("access_token", accessToken);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    @Then("I execute the API to update classroom with id {int}, code {string}, name {string}")
    public void iExecuteTheAPIToUpdateClassroom(int classId, String classCode, String className) {
        try {
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strUpdateUrl = "/yoedu/management/api/class-rooms";


            Response response = given()
                    .header("Content-Type", "multipart/form-data")
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .multiPart("id", String.valueOf(classId))
                    .multiPart("code", classCode)
                    .multiPart("name", className)
                    .multiPart("description", "Diễn giải cập nhật qua API")
                    .multiPart("isActive", "true")
                    .multiPart("branchId", "1")
                    .multiPart("capacity", "8")
                    .multiPart("photo", "")
                    .when()
                    .put(strUpdateUrl)
                    .then()
                    .extract()
                    .response();

            assertEquals(200, response.statusCode());
            System.out.println("API Update Classroom Response: " + response.getBody().asString());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    @Then("I execute the API to delete classroom with id {int}")
    public void iExecuteTheAPIToDeleteClassroom(int classId){
        try{
            RestAssured.baseURI=System.getProperty("apiUrl");
            String strDeleteUrl = "/yoedu/management/api/class-rooms/" + classId;
            Response response = given()
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .when()
                    .delete(strDeleteUrl)
                    .then()
                    .extract()
                    .response();
            assertEquals(200, response.statusCode());
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
    @Then("I execute the API to update a non-existent classroom with id {int}, code {string}, name {string}")
    public void iExecuteTheAPIToUpdateANonExistentClassroom(int fakeId, String classCode, String className) {
        try {
            System.out.println("Step: [API] Cố tình Sửa phòng không tồn tại (ID: " + fakeId + ")");
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strUpdateUrl = "/yoedu/management/api/class-rooms";


            invalidCase= given()
                    .header("Content-Type", "multipart/form-data")
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .multiPart("id", String.valueOf(fakeId))
                    .multiPart("code", classCode)
                    .multiPart("name", className)
                    .multiPart("description", "Test API Invalid Update")
                    .multiPart("isActive", "true")
                    .multiPart("branchId", "1")
                    .multiPart("capacity", "8")
                    .multiPart("photo", "")
                    .when()
                    .put(strUpdateUrl)
                    .then()
                    .extract()
                    .response();

            System.out.println("Response API (Invalid Update): " + invalidCase.asString());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("I execute the API to delete a non-existent classroom with id {int}")
    public void iExecuteTheAPIToDeleteANonExistentClassroom(int fakeId) {
        try {
            System.out.println("Step: [API] Cố tình Xóa phòng không tồn tại (ID: " + fakeId + ")");
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strDeleteUrl = "/yoedu/management/api/class-rooms/" + fakeId;


            invalidCase = given()
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .when()
                    .delete(strDeleteUrl)
                    .then()
                    .extract()
                    .response();

            System.out.println("Response API (Invalid Delete): " + invalidCase.asString());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The API should reject the request")
    public void theAPIShouldRejectTheRequest() {
        try {
            int statusCode = invalidCase.statusCode();
            String responseBody = invalidCase.asString();
            boolean isHttpError = (statusCode != 200 && statusCode != 201);
            boolean isLogicError = responseBody.contains("\"succeeded\":false") || responseBody.contains("\"succeeded\": false");
            boolean isRejected = isHttpError || isLogicError;
            Assert.assertTrue("LỖI: Hệ thống YoEdu vẫn chấp nhận data sai! (Status: " + statusCode + ")", isRejected);
            System.out.println("Hệ thống YoEdu đã chặn thành công request có ID ảo.");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
// hocvien api

    @Then("I execute the API to update student with id {int}, parent name {string}")
    public void iExecuteTheAPIToUpdateStudentParentName(int studentId, String parentName) {
        try {
            System.out.println("Step: [API] Sửa tên phụ huynh học viên ID=" + studentId + " → " + parentName);
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strUpdateUrl = "/yoedu/management/api/students";

            // Try multipart/form-data (same pattern as classroom update)
            Response response = given()
                    .header("Content-Type", "multipart/form-data")
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .multiPart("id", String.valueOf(studentId))
                    .multiPart("parentName", parentName)
                    .when()
                    .put(strUpdateUrl)
                    .then()
                    .extract()
                    .response();

            // Always log response so we can diagnose payload issues
            System.out.println("API Update Student (parent name) Status : " + response.statusCode());
            System.out.println("API Update Student (parent name) Response: " + response.getBody().asString());
            assertEquals(200, response.statusCode());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("I execute the API to update student with id {int}, code {string}, name {string}")
    public void iExecuteTheAPIToUpdateStudent(int studentId, String studentCode, String studentName) {
        try {
            System.out.println("Step: [API] Sửa học viên ID=" + studentId + ", code=" + studentCode + ", name=" + studentName);
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strUpdateUrl = "/yoedu/management/api/students";

            JSONObject body = new JSONObject();
            body.put("id", studentId);
            body.put("code", studentCode);
            body.put("name", studentName);
            body.put("isActive", true);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .body(body.toString())
                    .when()
                    .put(strUpdateUrl)
                    .then()
                    .extract()
                    .response();

            System.out.println("API Update Student Status : " + response.statusCode());
            System.out.println("API Update Student Response: " + response.getBody().asString());
            assertEquals(200, response.statusCode());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("I execute the API to delete student with id {int}")
    public void iExecuteTheAPIToDeleteStudent(int studentId) {
        try {
            System.out.println("Step: [API] Xóa học viên ID=" + studentId);
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strDeleteUrl = "/yoedu/management/api/students/" + studentId;

            Response response = given()
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .when()
                    .delete(strDeleteUrl)
                    .then()
                    .extract()
                    .response();

            assertEquals(200, response.statusCode());
            System.out.println("API Delete Student Response: " + response.getBody().asString());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("I execute the API to update a non-existent student with id {int}, code {string}, name {string}")
    public void iExecuteTheAPIToUpdateANonExistentStudent(int fakeId, String studentCode, String studentName) {
        try {
            System.out.println("Step: [API] Cố tình Sửa học viên không tồn tại (ID: " + fakeId + ")");
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strUpdateUrl = "/yoedu/management/api/students";

            invalidCase = given()
                    .header("Content-Type", "multipart/form-data")
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .multiPart("id", String.valueOf(fakeId))
                    .multiPart("code", studentCode)
                    .multiPart("name", studentName)
                    .multiPart("isActive", "true")
                    .when()
                    .put(strUpdateUrl)
                    .then()
                    .extract()
                    .response();

            System.out.println("Response API (Invalid Update Student): " + invalidCase.asString());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("I execute the API to delete a non-existent student with id {int}")
    public void iExecuteTheAPIToDeleteANonExistentStudent(int fakeId) {
        try {
            System.out.println("Step: [API] Cố tình Xóa học viên không tồn tại (ID: " + fakeId + ")");
            RestAssured.baseURI = System.getProperty("apiUrl");
            String strDeleteUrl = "/yoedu/management/api/students/" + fakeId;

            invalidCase = given()
                    .header("Authorization", "Bearer " + System.getProperty("access_token"))
                    .when()
                    .delete(strDeleteUrl)
                    .then()
                    .extract()
                    .response();

            System.out.println("Response API (Invalid Delete Student): " + invalidCase.asString());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
