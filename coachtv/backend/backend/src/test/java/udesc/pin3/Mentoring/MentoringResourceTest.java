package udesc.pin3.Mentoring;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import udesc.pin3.User.UserDTO;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MentoringResourceTest {

    @Test
    public void registerMentoringMissingTitleTest() {
        given()
                .when()
                .body(new MentoringDTO(0, new UserDTO(1), null, "description", 5,
                        MentoringSections.PROGRAMMING))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/mentoring/register")
                .then()
                .statusCode(400)
                .body(is("O campo \"título\" deve ser preenchido. Tente novamente!"));
    }

    @Test
    public void registerMentoringMissingDescriptionTest() {
        given()
                .when()
                .body(new MentoringDTO(0, new UserDTO(1), "title", null, 5,
                        MentoringSections.PROGRAMMING))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/mentoring/register")
                .then()
                .statusCode(400)
                .body(is("O campo \"descrição\" deve ser preenchido. Tente novamente!"));
    }

    @Test
    public void registerMentoringMissingSectionTest() {
        given()
                .when()
                .body(new MentoringDTO(0, new UserDTO(1), "title", "description", 5, null))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/mentoring/register")
                .then()
                .statusCode(400)
                .body(is("O campo \"seção\" deve ser preenchido. Tente novamente!"));
    }

    @Test
    public void registerMentoringTest() {
        given()
                .when()
                .body(new MentoringDTO(0, new UserDTO(1), "title", "description", 5,
                        MentoringSections.PROGRAMMING))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/mentoring/register")
                .then()
                .statusCode(200);
    }

    @Test
    public void getAllMentoringsTest() {
        given()
                .when()
                .get("/api/mentoring")
                .then()
                .statusCode(200);
    }

    @Test
    public void getMentoringByIdNotExistsTest() {
        given()
                .when()
                .get("/api/mentoring/10")
                .then()
                .statusCode(200)
                .body(is(""));
    }

    @Test
    public void getMentoringByIdTest() {
        given()
                .when()
                .get("/api/mentoring/2")
                .then()
                .statusCode(200);
    }


    @Test
    public void getSectionsTest() {
        given()
                .when()
                .get("/api/mentoring/sections")
                .then()
                .statusCode(200);
    }

}
