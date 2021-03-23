package udesc.pin3.Mentoring;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import udesc.pin3.User.UserDTO;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MentoringResourceTest {

    @Test
    public void registerMentoringTest() {
        given()
                .when()
                .body(new MentoringDTO(new UserDTO(1), "title", "description", 5,
                        MentoringSections.PROGRAMMING))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/mentoring/register")
                .then()
                .statusCode(200);
    }

}
