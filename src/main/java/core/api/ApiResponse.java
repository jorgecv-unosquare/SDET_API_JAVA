package core.api;

import io.restassured.response.Response;

public class ApiResponse {
    private Response response;

    public ApiResponse() {
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(final Response newResponse) {
        this.response = newResponse;
    }

    public <T> T getBody(final Class<T> cls) {
        return response.getBody().as(cls);
    }
    public void logAll(){
        this.response.then().log().all();
    }
    public String getPath (final String path) {
        return response.getBody().jsonPath().getJsonObject(path).toString();
    }
}
