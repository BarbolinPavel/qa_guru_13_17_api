package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class APItest extends TestBase{

    @Tag("checkedApi")
    @Test
    void checkUserPage() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total_pages", is(2));
    }

    @Tag("checkedApi")
    @Test
    void checkUserID12() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.findAll {id = 12}.first_name", hasItems("Rachel"));
    }

    @Tag("checkedApi")
    @Test
    void loginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"proverka13\" }";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Tag("checkedApi")
    @Test
    void changeFirstNameTest() {
        String body = "{ \"first_name\": \"morpheus\"}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Tag("checkedApi")
    @Test
    void checkUser15() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("/users/15")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }
}



