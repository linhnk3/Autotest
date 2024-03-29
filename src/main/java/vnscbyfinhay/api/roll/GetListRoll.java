package vnscbyfinhay.api.roll;

import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;
import io.restassured.path.json.JsonPath;
import vnscbyfinhay.api.login.GetToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetListRoll {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIListRollValid(String account_id) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(371))
                .params(BodyApi.BODY_GET_LIST_ROLL)
                .when()
                .get(ConfigPath.GET_LIST_ROLL+account_id+"/user-rights")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIListRollInvalid(String account_id) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(371))
                .params(BodyApi.BODY_GET_LIST_ROLL)
                .when()
                .get(ConfigPath.GET_LIST_ROLL+account_id+"/user-rights")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }
}
