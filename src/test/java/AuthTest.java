import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthTest {

@BeforeEach
void setUp() {
    RestAssured.baseURI = "http://localhost:8080/api/v1";
}

@Test
void loginReturns200() {
    String requestBody = """
      {
      "email":"test@test.com",
      "password":"test123"
      }
      """;
    
    given()
      .contentType("application/json")
      .body(requestBody)
      .when()
      .post("/auth/login")
      .then()
      .statusCode(200)
      .body("token",notNullValue())
      .body("user_id", notNullValue())
      .body("expires_at", notNullValue());
}

@Test
void loginWithWrongPasswordReturn400() {
    String requestBody = """
      {
      "email":"test@test.com",
      "password":"wrongpassword"
      }
      """;
    
    given()
      .contentType("application/json")
      .body(requestBody)
      .when()
      .post("/auth/login")
      .then()
      .statusCode(401)
      .body("error", notNullValue());
    }
}