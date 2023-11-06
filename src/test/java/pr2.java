import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;


public class pr2 {
    protected final Logger logger = LogManager.getLogger(getClass());
    @Test
    public void jsn_file_Gherkin() throws IOException, ParseException {
        JSONParser json = new JSONParser();
        FileReader fileReader = new FileReader("src/test/resources/jsons/Register.json");
        JSONObject jsonObject = (JSONObject) json.parse(fileReader);
        Response response = given()
                .when()
                .body(jsonObject)
                .post("https://reqres.in/api/users");

        response.then()
                .assertThat().statusCode(201)
                .assertThat().contentType(ContentType.JSON);
        Reporter.log(response.getBody().asString());
        Reporter.log(Integer.toString(response.getStatusCode()));
        Reporter.log(jsonObject.toString());
        Reporter.log("https://reqres.in/api/users");

    }
    @Test
    public void jsn_file() throws IOException, ParseException {

        JSONParser json = new JSONParser();
        FileReader fileReader = new FileReader("src/test/resources/jsons/Register.json");
        JSONObject jsonObject = (JSONObject) json.parse(fileReader);
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.headers("Content-Type", "application/json");
        httpRequest.body(jsonObject.toString());
        Response response = httpRequest.post("/users");
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(httpRequest);
        String retrievePath = queryRequest.getURI();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,201);
        Reporter.log(response.getBody().asString());
        Reporter.log(Integer.toString(response.getStatusCode()));
        Reporter.log(jsonObject.toString());
        Reporter.log(retrievePath);
    }
}
