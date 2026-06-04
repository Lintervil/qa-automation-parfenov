package lesson_8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    private static final String BASE_URL = "https://postman-echo.com";

    @Test
    @DisplayName("GET Request")
    void testGetMethod() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/get?foo=bar&num=1")
                .then()
                .statusCode(200)
                .body("args.foo", equalTo("bar"))
                .body("args.num", equalTo("1"));
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