package lesson_8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    private static final String BASE_URL = "https://postman-echo.com";

    @BeforeEach
    void setUp() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    @DisplayName("GET Request")
    void testGetMethod() {
        given().baseUri(BASE_URL)
                .when().get("/get?foo=bar")
                .then().statusCode(200)
                .body("args.foo", equalTo("bar"));
    }

    @Test
    @DisplayName("POST Request - JSON")
    void testPostJson() {
        given().baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Parfenov\", \"course\": \"QA\"}")
                .when().post("/post")
                .then().statusCode(200)
                .body("json.name", equalTo("Parfenov"));
    }

    @Test
    @DisplayName("POST Request - Form Data")
    void testPostFormData() {
        given()
                .baseUri(BASE_URL)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")  // явно указываем charset
                .formParam("firstname", "Ivan")
                .formParam("lastname", "Petrov")
                .formParam("age", "30")
                .formParam("city", "Minsk")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.firstname", equalTo("Ivan"))
                .body("form.lastname", equalTo("Petrov"));
    }

    @Test
    @DisplayName("POST Request - JSON")
    void testPostMethod() {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Parfenov\", \"course\": \"QA Automation\"}")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("Parfenov"))
                .body("json.course", equalTo("QA Automation"));
    }

    @Test
    @DisplayName("PUT Request")
    void testPutMethod() {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body("{\"updated\": true, \"id\": 123}")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.updated", equalTo(true));
    }

    @Test
    @DisplayName("PATCH Request")
    void testPatchMethod() {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body("{\"status\": \"partial update\"}")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.status", equalTo("partial update"));
    }

    @Test
    @DisplayName("DELETE Request")
    void testDeleteMethod() {
        given()
                .baseUri(BASE_URL)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("url", containsString("/delete"));  // Это поле всегда есть
    }

}