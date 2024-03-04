package vnscbyfinhay.api.assets;

import Connection.MySQL;
import constants.ConfigPath;
import io.restassured.path.json.JsonPath;
import vnscbyfinhay.api.login.GetToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetDataMoney {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    private final ConfigPath configPath;

    public GetDataMoney(String env) {
        if ("dev".equals(env)) {
            configPath = new ConfigPath("dev");
        } else if ("prod".equals(env)) {
            configPath = new ConfigPath("prod");
        } else {
            configPath = new ConfigPath("default");
        }
    }
    public JsonPath getAPIDataMoney(Integer uid) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(uid))
                .when()
                .get(configPath.GET_DATA_MONEY +uid + "/sub-account" )
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }
}
