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

public class test3 {
    protected final Logger logger = LogManager.getLogger(getClass());

    @Test
    public void first() {
        RestAssured.baseURI = "https://reqres.in/api/";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/unknown/2");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        Reporter.log("Sucess 200 validation");
        response.then().log().all();
        response.then().body("data.id", equalTo(2));
        response.then().body("data.name", equalTo("fuchsia rose"));
        response.then().body("data.year", equalTo(2001));
        response.then().body("data.color", equalTo("#C74375"));
        response.then().body("data.pantone_value", equalTo("17-2031"));
        response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
        response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        Reporter.log(response.body().asString());
        logger.info("Sucess 200 validation");
    }

    @Test
    public void first_Gherkin() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat()
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .body("data.year", equalTo("#C74375"))
                .body("data.pantone_value", equalTo("17-2031"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        Reporter.log("Sucess 200 validation");
    }
}
