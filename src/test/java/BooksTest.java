import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BooksTest {
    
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }
    
    @Test
    void getBooksReturns200() {
        given()
          .when()
          .get("/books")
          .then()
          .statusCode(200);
    }
}

