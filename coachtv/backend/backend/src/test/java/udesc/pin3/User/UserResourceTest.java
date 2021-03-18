package udesc.pin3.User;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserResourceTest {

    @Test
    public void registerUserTest() {
        given()
                .when()
                .body(new UserDTO("guilherme@hotmail.com", "123", "Guilherme", LocalDate.now(), 500))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/user/register")
                .then()
                .statusCode(200);
    }

    @Test
    public void loginUserTest() {
        given()
                .when().post("/api/user/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void getUserByIdTest() {
        given()
                .when().get("/api/user/1")
                .then()
                .statusCode(200);
    }

}
