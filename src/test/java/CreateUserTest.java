import io.restassured.http.ContentType;
import org.example.dto.UserCreate;
import org.example.dto.UserCreateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class CreateUserTest extends TestBase{


    @Test
    public void createNewUserStatus201_test(){
        UserCreate newUser = new UserCreate("morpheus", "leader");

        UserCreateResponse userCreateResponse = given()
                .body(newUser.toString())
                .when()
                .post("/users")
                .then().assertThat().statusCode(201)
                .log().all()
                .contentType(ContentType.JSON)
                .extract().as(UserCreateResponse.class);

        Assertions.assertNotNull(userCreateResponse.getId());
        Assertions.assertNotNull(userCreateResponse.getCreatedAt());
       // Assertions.assertEquals(newUser.getName(), userCreateResponse.getName());
       // Assertions.assertEquals(newUser.getJob(), userCreateResponse.getJob());
    }

    @Test
    public void createUserStatusUnSuccess_test(){
        UserCreate newUser = new UserCreate("morpheus", "");
        UserCreateResponse userCreateResponse = given()
                .body(newUser.toString())
                .when()
                .post("/users")
                .then().assertThat().statusCode(400)
                .log().all()
                .contentType(ContentType.JSON)
                .extract().as(UserCreateResponse.class);

        Assertions.assertNotNull(userCreateResponse.getId());
        Assertions.assertNotNull(userCreateResponse.getCreatedAt());

    }



    
}
