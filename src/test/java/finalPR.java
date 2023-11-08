import core.ApiCore;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class finalPR {
    public ApiCore apiCore;

    @BeforeSuite
    public void beforeSuite(){
        apiCore = new ApiCore();
    }

    @Test
    public void registerSuccessfulTest() {
        Response response = apiCore.post("src/test/resources/jsons/Register.json", "/register");
        Assert.assertEquals(200, response.getStatusCode());
        response.then().body("id", equalTo(4));
        response.then().body("token", equalTo("QpwL5tke4Pnpja7X4"));
        Reporter.log(response.body().asString());
    }
    @Test
    public void registerUnsuccessfulTest() {
        Response response = apiCore.post("src/test/resources/jsons/resgisterUnsuccsessful.json", "/register");
        Assert.assertEquals(400, response.getStatusCode());
        response.then().body("error", equalTo("Missing password"));
        Reporter.log(response.body().asString());
    }
    @Test
    public void loginSuccessfulTest() {
        Response response = apiCore.post("src/test/resources/jsons/Login.json", "/login");
        Assert.assertEquals(200, response.getStatusCode());
        response.then().body("token", equalTo("QpwL5tke4Pnpja7X4"));
        Reporter.log(response.body().asString());
    }
    @Test
    public void loginUnsuccessfulTest() {
        Response response = apiCore.post("src/test/resources/jsons/LoginUnsuccsessful.json", "/register");
        Assert.assertEquals(400, response.getStatusCode());
        response.then().body("error", equalTo("Missing password"));
        Reporter.log(response.body().asString());
    }

    @Test
    public void singleUserTest() {
        Response response = apiCore.get("/users/2");
        Assert.assertEquals(200, response.getStatusCode());
        response.then().body("data.id", equalTo(2));
        response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
        response.then().body("data.first_name", equalTo("Janet"));
        response.then().body("data.last_name", equalTo("Weaver"));
        response.then().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
        response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
        response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        Reporter.log(response.body().asString());
    }

    @Test
    public void singleUserNotFoundTest() {
        Response response = apiCore.get("/users/22");
        Assert.assertEquals(404, response.getStatusCode());
        Reporter.log(response.body().asString());
    }

    @Test
    public void singleResourceTest() {
        Response response = apiCore.get("/unknown/2");
        Assert.assertEquals(200, response.getStatusCode());
        response.then().body("data.id", equalTo(2));
        response.then().body("data.name", equalTo("fuchsia rose"));
        response.then().body("data.year", equalTo(2001));
        response.then().body("data.color", equalTo("#C74375"));
        response.then().body("data.pantone_value", equalTo("17-2031"));
        response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
        response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        Reporter.log(response.body().asString());
    }

    @Test
    public void singleResourceNotFoundTest() {
        Response response = apiCore.get("/unknown/23");
        Assert.assertEquals(404, response.getStatusCode());
        Reporter.log(response.body().asString());
    }

    @Test
    public void updateTest() {
        Response response = apiCore.put("src/test/resources/jsons/user.json","/unknown/2");
        Assert.assertEquals(200, response.getStatusCode());
        response.then().body("name", equalTo("morpheus"));
        response.then().body("job", equalTo("zion resident"));
        Reporter.log(response.body().asString());
    }
}
