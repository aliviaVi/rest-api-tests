import io.restassured.http.ContentType;
import org.example.dto.LoginUserRequest;
import org.example.dto.LoginUserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginUserTest extends TestBase {


    @Test
    public void loginUserStatus201_test() {
        LoginUserRequest loginUserRequest = new LoginUserRequest("eve.holt@reqres.in", "cityslicka");
        String responseToken = "QpwL5tke4Pnpja7X4";

        LoginUserResponse loginUserResponse = given()
                .body(loginUserRequest)
                .when()
                .post("login")
                .then().assertThat().statusCode(200)
                .log().all()
                .contentType(ContentType.JSON)
                .extract().as(LoginUserResponse.class);

        Assertions.assertNotNull(loginUserResponse);

        Assertions.assertEquals(responseToken, loginUserResponse.getToken());
    }
}
