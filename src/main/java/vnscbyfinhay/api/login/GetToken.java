package vnscbyfinhay.api.login;

import Connection.MySQL;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class GetToken
{
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public static String getAPIToken() throws Exception {
//        JsonPath jsonPath = given().header("Content-Type", "application/json" )
//                .body(BodyApi.BODY_GET_TOKEN)
//                .when()
//                .put(configPath.GET_TOKEN)
//                .then()
//                .statusCode(200)
//                .assertThat().extract().response().getBody().jsonPath();
//        String message = jsonPath.getJsonObject("message");
//        String token = jsonPath.getMap("result").get("access_token").toString();
//        System.out.println(message + " " + token);
//        return message;
//        System.out.println(token);

        HttpResponse<JsonNode> response = Unirest.put("https://dev-api.vinasecurities.com/accounts/v1/login")
                .header("Content-Type", "application/json")
                .body("{\r\n    \"username\": \"0985802342\",\r\n   \"password\": \"Ab@12345\"\r\n}")
                .asJson();
        String token = response.getBody().getObject().getJSONObject("result").get("access_token").toString();
        return token;
    }
    public static String getAPIToken2() throws Exception {
        HttpResponse<JsonNode> response = Unirest.put("https://dev-api.vinasecurities.com/accounts/v1/login")
                .header("Content-Type", "application/json")
                .body("{\r\n    \"username\": \"0862256684\",\r\n   \"password\": \"Ab@123456\"\r\n}")
                .asJson();
        String token = response.getBody().getObject().getJSONObject("result").get("access_token").toString();
        return token;
    }

    public static String getAPIToken3() throws Exception {
        HttpResponse<JsonNode> response = Unirest.put("https://dev-api.vinasecurities.com/accounts/v1/login")
                .header("Content-Type", "application/json")
                .body("{\r\n    \"username\": \"0985452222\",\r\n   \"password\": \"Ab@12345\"\r\n}")
                .asJson();
        String token = response.getBody().getObject().getJSONObject("result").get("access_token").toString();
        return token;
    }
        public static void main(String[] args) throws Exception {
        GetToken a = new GetToken();
        System.out.println(a.getAPIToken3());
    }

}
