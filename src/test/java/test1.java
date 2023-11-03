import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class test1 {
    protected final Logger logger = LogManager.getLogger(getClass());
    @Test
    public void f() {

        RestAssured.baseURI = "https://reqres.in/api/";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/users/2");

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode,200);
        Reporter.log("Sucess 200 validation");
        response.then().body("data.id", equalTo(2));
        response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
        response.then().body("data.first_name", equalTo("Janet"));
        response.then().body("data.last_name", equalTo("Weaver"));
        response.then().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
        response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
        response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        Reporter.log(response.body().asString());
        logger.info("Sucess 200 validation");
    }

    @Test
    public void f_Gherkin() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat()
                .body("data.first_name", equalTo("Janet"))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        Reporter.log("Sucess 200 validation");

    }
}
