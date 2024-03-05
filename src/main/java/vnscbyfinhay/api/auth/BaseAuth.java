package vnscbyfinhay.api.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.RestMethod;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAuth {

    public static void verifyGet(String url, String token, Map<String, Object> params, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        verify(RestMethod.GET, url, token, params, new HashMap<>(), new HashMap<>(), statusCode, expectedMessage,expectdErrorCode);
    }

    public static void verifyGet(String url, String token, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        verify(RestMethod.GET, url, token,new HashMap<>(),new HashMap<>(),new HashMap<>(), statusCode, expectedMessage,expectdErrorCode);
    }

    public static void verifyPost(String url, String token, Map<String, Object> body, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        verify(RestMethod.POST, url, token, new HashMap<>(), body, headers, statusCode, expectedMessage,expectdErrorCode);
    }

    public static void verifyPost(String url, String token, Map<String, Object> body, Map<String, String> headers, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        verify(RestMethod.POST, url, token, new HashMap<>(), body, headers, statusCode, expectedMessage,expectdErrorCode);
    }

    public static void verifyPut(String url, String token, Map<String, Object> body, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        verify(RestMethod.PUT, url, token, new HashMap<>(), body, headers, statusCode, expectedMessage,expectdErrorCode);
    }

    public static void verifyPut(String url, String token, Map<String, Object> body, Map<String, String> headers, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        verify(RestMethod.PUT, url, token, new HashMap<>(), body, headers, statusCode, expectedMessage,expectdErrorCode);
    }

    public static void verifyDelete(String url, String token, Map<String, Object> body, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        verify(RestMethod.DELETE, url, token, new HashMap<>(), body, headers, statusCode, expectedMessage,expectdErrorCode);
    }

    public static void verifyDelete(String url, String token, Map<String, Object> body, Map<String, String> headers, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        verify(RestMethod.DELETE, url, token, new HashMap<>(), body, headers, statusCode, expectedMessage,expectdErrorCode);
    }

    public static void verify(RestMethod method, String url, String token, Map<String, Object> params, Map<String, Object> body, Map<String, String> headers, int statusCode,String expectedMessage, String expectdErrorCode) throws JsonProcessingException {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();
        String bodyString = objectMapper.writeValueAsString(body);

        RequestSpecification progress = given().header("Authorization", "Bearer " + token)
                .params(params).headers(headers).body(bodyString);

        switch (method.method()) {
            case "GET":
                progress.when().get(url).then().statusCode(statusCode);
                return;
            case "POST":
                progress.when().post(url).then().statusCode(statusCode);
                return;
            case "PUT":
                progress.when().put(url).then().statusCode(statusCode);
                return;
            case "DELETE":
                progress.when().delete(url).then().statusCode(statusCode);
        }
    }

    public static void main(String[] args) throws Exception {
    }
}
