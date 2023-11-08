package core.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiManager {

    public static RequestSpecification buildRequest(final ApiRequest apiRequest) {
        return given()
                .headers(apiRequest.getHeaders())
                .queryParams(apiRequest.getQueryParams())
                .pathParams(apiRequest.getPathParams())
                .baseUri(apiRequest.getBaseUri())
                .contentType(ContentType.JSON)
                .log()
                .all();

    }

    public static void execute(final ApiRequest apiRequest, final ApiResponse apiResponse) {
        Response response = buildRequest(apiRequest).request(apiRequest.getMethod().name(), apiRequest.getEndPoint());
        apiResponse.setResponse(response);
    }

    public static void executeWithBody(final ApiRequest apiRequest, final ApiResponse apiResponse) {
        Response response = buildRequest(apiRequest).body(apiRequest.getBody())
                .request(apiRequest.getMethod().name(), apiRequest.getEndPoint());
        apiResponse.setResponse(response);
    }
}
