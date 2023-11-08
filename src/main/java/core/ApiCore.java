package core;

import core.api.ApiManager;
import core.api.ApiMethod;
import core.api.ApiRequestBuilder;
import core.api.ApiResponse;
import core.utils.JsonFileReader;
import io.restassured.response.Response;

public class ApiCore {

    public Response post(String jsonPath, String endpoint){
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder();
        ApiResponse apiResponse = new ApiResponse();
        apiRequestBuilder.addBaseUri("https://reqres.in/api/")
                .addBody(JsonFileReader.readJsonFile(jsonPath))
                .addEndpoint(endpoint)
                .addMethod(ApiMethod.POST);
        ApiManager.executeWithBody(apiRequestBuilder.build(), apiResponse);
        apiResponse.logAll();
        return  apiResponse.getResponse();
    }

    public Response get(String endpoint){
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder();
        ApiResponse apiResponse = new ApiResponse();
        apiRequestBuilder.addBaseUri("https://reqres.in/api/")
                .addEndpoint(endpoint)
                .addMethod(ApiMethod.GET);
        ApiManager.execute(apiRequestBuilder.build(), apiResponse);
        apiResponse.logAll();
        return  apiResponse.getResponse();
    }
    public Response put(String jsonPath, String endpoint){
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder();
        ApiResponse apiResponse = new ApiResponse();
        apiRequestBuilder.addBaseUri("https://reqres.in/api/")
                .addBody(JsonFileReader.readJsonFile(jsonPath))
                .addEndpoint(endpoint)
                .addMethod(ApiMethod.PUT);
        ApiManager.executeWithBody(apiRequestBuilder.build(), apiResponse);
        apiResponse.logAll();
        return  apiResponse.getResponse();
    }
}
