import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthIntegrationTest {

    static {
        RestAssured.baseURI = "http://localhost:8080"; // fictício
    }

    @Test
    public void deveRetornarTokenComCredenciaisValidas() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"username\":\"user\",\"password\":\"pass\"}")
        .when()
            .post("/auth/login")
        .then()
            .statusCode(200)
            .body("token", notNullValue());
    }

    @Test
    public void deveRetornar401ComCredenciaisInvalidas() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"username\":\"user\",\"password\":\"errado\"}")
        .when()
            .post("/auth/login")
        .then()
            .statusCode(401);
    }

    @Test
    public void deveRetornar400QuandoPayloadEstiverMalformado() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"username\":}")
        .when()
            .post("/auth/login")
        .then()
            .statusCode(400);
    }
}
