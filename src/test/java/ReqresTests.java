import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReqresTests {


    String url = "https://reqres.in/";


    @Test
    public void checkStatusCode(){
        given().baseUri(url).
                when().param("page",2)
                .get("/api/users").then().log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

}
