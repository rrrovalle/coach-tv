package udesc.pin3.Meeting;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import udesc.pin3.Mentoring.MentoringDTO;
import udesc.pin3.User.UserDTO;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MeetingResourceTest {

    @Test
    public void scheduleMeetingSuccessfulTest() {
        given()
                .when()
                .body(new MeetingDTO(45, 0, "06/04/2000 22:00", new UserDTO(1), new MentoringDTO(1),""))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/meeting/schedule/4/user/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void scheduleMeetingNotEnoughCreditsTest() {
        given()
                .when()
                .body(new MeetingDTO(45, 10000, "06/04/2000 22:00", new UserDTO(1), new MentoringDTO(1), ""))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/meeting/schedule/4/user/1")
                .then()
                .statusCode(400)
                .body(is("You don't have enough credits to schedule this meeting. Please, " +
                        "check you credits amount and try again!"));
    }

    @Test
    public void scheduleMeetingCoachAndCustomerSamePersonTest() {
        given()
                .when()
                .body(new MeetingDTO(45, 0, "06/04/2000 22:00", new UserDTO(1), null, ""))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/meeting/schedule/5/user/1")
                .then()
                .statusCode(400)
                .body(is("You can't purchase your own mentoring. Please, select another mentoring!"));
    }

    @Test
    public void getMeetingsByUserId() {
        given()
                .when()
                .get("/api/meeting/user/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void getMeetingsByMentoringId() {
        given()
                .when()
                .get("/api/meeting/mentoring/5")
                .then()
                .statusCode(200);
    }

}
