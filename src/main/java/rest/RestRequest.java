package rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.JsonConfig.jsonConfig;

public class RestRequest {
    private String url;
    private String path;
    private RestHeaders header;
    private RestBody body;
    private RestMethod method;
    private RestParams params;
    @SuppressWarnings("unused")
    private RestAssured restAssured;


    RequestSpecification requestSpec;
    RequestSpecBuilder requestSpecBuilder;
    private RestAssuredConfig config;



    public RestRequest(String url, String path, RestMethod method) {
        this.url = url;
        this.path = path;
        this.method = method;
        init();
    }




    private void init() {
        header = new RestHeaders();
        body = new RestBody();
        params = new RestParams();
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(this.url);


        config = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));

    }






    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RestHeaders getHeader() {
        return header;
    }

    public void setHeader(RestHeaders header) {
        this.header = header;
        requestSpecBuilder.addHeaders(this.header.getHeaders());
    }

    public RestBody getBody() {
        return body;
    }

    public void setBody(RestBody body) {
        this.body = body;
        requestSpecBuilder.setBody(this.body.print());

    }

    public RestParams getParams() {
        return params;
    }

    public void setParams(RestParams params) {
        this.params = params;
        if (this.method.equals(RestMethod.GET)) {
            requestSpecBuilder.addParams(this.params.getParams());
        } else {
            requestSpecBuilder.addQueryParams(this.params.getParams());
        }


    }

    public RestResponse send() {
        requestSpec = requestSpecBuilder.build();
//        requestLog.info(curlConverter.printCurl());
        switch (this.method) {
            case GET:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).when().get(path));

            case POST:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).when().post(path));

            case PUT:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).when().put(path));

            case PATCH:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).when().patch(path));

            case DELETE:
                return new RestResponse(RestAssured.given().config(config).when().delete(path));
        }
        return null;
    }


}
