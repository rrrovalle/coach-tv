package udesc.pin3.User;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTest {

    @Test
    public void registerUserTest() {
        given()
                .when()
                .body(new UserDTO("guilherme@hotmail.com", "123", "Guilherme", "06/04/2000", 500))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/user/register")
                .then()
                .statusCode(200);
    }

    @Test
    public void registerUserEmailAlreadyExistsTest() {
        given()
                .when()
                .body(new UserDTO("guilherme@hotmail.com", "123", "Guilherme", "06/04/2000", 0))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/user/register")
                .then()
                .statusCode(400)
                .body(is("O e-mail informado já existe no sistema. Tente novamente!"));
    }

    @Test
    public void registerUserMissingEmailTest() {
        given()
                .when()
                .body(new UserDTO(null, "123", "Guilherme", "06/04/2000", 0))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/user/register")
                .then()
                .statusCode(400)
                .body(is("O campo \"e-mail\" deve ser preenchido!"));
    }

    @Test
    public void failLoginTest() {
        UserDTO dto = new UserDTO();
        dto.setPassword("wrong password");
        dto.setEmail("guilherme@hotmail.com");

        given()
                .when()
                .body(dto)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/user/login")
                .then()
                .statusCode(400)
                .body(is("E-mail ou senha incorreto. Tente novamente!"));
    }

    @Test
    public void loginSuccessfulTest() {
        UserDTO dto = new UserDTO();
        dto.setPassword("123");
        dto.setEmail("guilherme@hotmail.com");

        given()
                .when()
                .body(dto)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/user/login")
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
