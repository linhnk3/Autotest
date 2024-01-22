package vnscbyfinhay.api.login;

import Connection.MySQL;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class GetToken
{
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;

    public static HashMap<Integer, HashMap<String, String>> users = new HashMap<Integer, HashMap<String, String>>()
    {{
        put(371, new HashMap<String, String>() {{
            put("username", "0987654322");
            put("password", "Ab@12345");
        }});
        put(352, new HashMap<String, String>() {{
            put("username", "0862256684");
            put("password", "Ab@12345");
        }});
        put(244, new HashMap<String, String>() {{
                put("username", "0985452222");
                put("password", "Ab@12345");
            }});
    }};

    public static String getAPIToken(Integer uid) throws Exception {
        HashMap<String, String> user = users.get(uid);
        String username = user.get("username");
        String password = user.get("password");
        HttpResponse<JsonNode> response = Unirest.put("https://dev-api.vinasecurities.com/accounts/v1/login")
                .header("Content-Type", "application/json")
                .body(String.format("{\r\n    \"username\": \"%s\",\r\n   \"password\": \"%s\"\r\n}", username, password))
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
