package core.api;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiRequest {
    private String baseUri;
    private String endPoint;
    private String body;
    private Enum<ApiMethod> method;
    private List<Header> headers;
    private Map<String, String> queryParams;
    private Map<String, String> pathParams;
    private Map<String, String> params;

    public ApiRequest() {
        headers = new ArrayList<>();
        queryParams = new HashMap<>();
        pathParams = new HashMap<>();
        params = new HashMap<>();
        body = "";
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Enum<ApiMethod> getMethod() {
        return method;
    }

    public void setMethod(Enum<ApiMethod> method) {
        this.method = method;
    }

    public Headers getHeaders() {
        return new Headers(headers);
    }

    public void addHeaders(final String header, final String value) {
        this.headers.add(new Header(header, value));
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void addQueryParams(final String param, final String value) {
        this.queryParams.put(param, value);
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public void addPathParams(final Map<String, String> newPathParams)  {
        this.pathParams.putAll(newPathParams);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void addParams(final Map<String, String> params) {
        this.params.putAll(params);
    }
}
