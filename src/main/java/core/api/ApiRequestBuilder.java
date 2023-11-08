package core.api;

import java.util.Map;

public class ApiRequestBuilder {
    private ApiRequest apiRequest = new ApiRequest();

    public ApiRequestBuilder addBaseUri(final String baseUri) {
        apiRequest.setBaseUri(baseUri);
        return this;
    }

    public ApiRequestBuilder addEndpoint(final String endpoint) {
        apiRequest.setEndPoint(endpoint);
        return this;
    }

    public ApiRequestBuilder addBody(final String body) {
        apiRequest.setBody(body);
        return this;
    }

    public ApiRequestBuilder addMethod(final Enum<ApiMethod> method) {
        apiRequest.setMethod(method);
        return this;
    }

    public ApiRequestBuilder addHeader(final String header, final String value) {
        apiRequest.addHeaders(header, value);
        return this;
    }

    public ApiRequestBuilder addQueryParam(final String param, final String value) {
        apiRequest.addQueryParams(param, value);
        return this;
    }

    public ApiRequestBuilder addPathParam(final Map<String, String> pathParams) {
        apiRequest.addPathParams(pathParams);
        return this;
    }

    public ApiRequestBuilder addParams(final Map<String, String> params) {
        apiRequest.addParams(params);
        return this;
    }

    public ApiRequest build() {
        return this.apiRequest;
    }
}
