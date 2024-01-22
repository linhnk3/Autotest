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

public class GetDataAssetGrowth {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIDataAssetGrowth(Integer uid, String cacheControl) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(371))
                .param("cache-control", cacheControl )
                .when()
                .get(ConfigPath.GET_DATA_ASSET_GROWTH +uid + "/assets/summary" )
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }
}
