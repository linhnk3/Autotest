package rest;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class RestResponse {
    public  RestResponse(){}
    private Response response;

    public RestResponse(Response response) {
        this.response = response;
    }

    public ValidatableResponse validate(){
        return response.then();
    }

    public Response extract(){
        return this.response;
    }

}
