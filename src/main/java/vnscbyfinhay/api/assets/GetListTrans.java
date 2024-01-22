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

public class GetListTrans {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIListTrans(Integer uid, String subAccId, String from, String to, Integer size) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(371))
                .param("from", from )
                .param("to", to )
                .param("size", size )
                .when()
                .get(ConfigPath.GET_LIST_TRANS +uid + "/sub-accounts/" + subAccId + "/transactions" )
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }
}
