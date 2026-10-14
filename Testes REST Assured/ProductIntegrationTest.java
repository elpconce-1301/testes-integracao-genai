import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductIntegrationTest {

    static {
        RestAssured.baseURI = "http://localhost:8080"; // fictício
    }

    private String obterTokenValido() {
        return given()
            .contentType(ContentType.JSON)
            .body("{\"username\":\"user\",\"password\":\"pass\"}")
        .when()
            .post("/auth/login")
        .then()
            .extract()
            .path("token");
    }

    @Test
    public void deveRetornarListaDeProdutosComTokenValido() {
        String token = obterTokenValido();

        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("/products/list")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    public void deveRetornar401SemToken() {
        when()
            .get("/products/list")
        .then()
            .statusCode(401);
    }

    @Test
    public void deveRetornar403ComTokenExpirado() {
        given()
            .header("Authorization", "Bearer token_expirado_123")
        .when()
            .get("/products/list")
        .then()
            .statusCode(403);
    }

    @Test
    public void deveRetornar403ComTokenMalformado() {
        given()
            .header("Authorization", "Bearer abc.def")
        .when()
            .get("/products/list")
        .then()
            .statusCode(403);
    }
}
