package udesc.pin3.Credit;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import udesc.pin3.User.UserDTO;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CreditResourceTest {

    @Test
    public void getCreditPlansTest() {
        given()
                .when()
                .get("/api/credits/plans")
                .then()
                .statusCode(200);
    }

    @Test
    public void purchaseCreditsSuccessfulTest(){
        given()
                .when()
                .body(new CreditPackDTO(new UserDTO(1), "Pacote - 1500 Créditos"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/credits/purchase")
                .then()
                .statusCode(200)
                .body(is("Compra de créditos finalizada com sucesso!"));
    }

    @Test
    public void purchaseCreditsUserNotFoundTest(){
        given()
                .when()
                .body(new CreditPackDTO(new UserDTO(2), "Pacote - 1500 Créditos"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/credits/purchase")
                .then()
                .statusCode(400)
                .body(is("Usuário não encontrado com a identificação informada!"));
    }

    @Test
    public void purchaseCreditsWrongTitleTest(){
        given()
                .when()
                .body(new CreditPackDTO(new UserDTO(1), "Pacote - 0 Créditos"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/credits/purchase")
                .then()
                .statusCode(400)
                .body(is("Não foi encontrado um pacote de créditos com o título informado. " +
                        "Tente novamente!"));
    }

    @Test
    public void purchaseCreditsMissingTitleTest(){
        given()
                .when()
                .body(new CreditPackDTO(new UserDTO(1), null))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/api/credits/purchase")
                .then()
                .statusCode(400)
                .body(is("Para efetuar a compra de créditos, o título do pacote deve ser informado!"));
    }

}
