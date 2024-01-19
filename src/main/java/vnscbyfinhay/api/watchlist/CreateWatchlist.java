package vnscbyfinhay.api.watchlist;

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

public class CreateWatchlist {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;

    public JsonPath createAPIWatchlistValid() throws Exception {
        return given().headers("Content-Type", "application/json", "Authorization", "Bearer " + GetToken.getAPIToken())
                .body(BodyApi.BODY_CREATE_WATCHLIST_SUCCESS)
                .when()
                .post(ConfigPath.POST_CREATE_WATCHLIST)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }
    public JsonPath createAPIWatchlistInvalid() throws Exception {
        return given().headers("Content-Type", "application/json", "Authorization", "Bearer " + GetToken.getAPIToken())
                .body(BodyApi.BODY_CREATE_WATCHLIST_SUCCESS)
                .when()
                .post(ConfigPath.POST_CREATE_WATCHLIST)
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath createAPIWatchlistWithNameEmpty() throws Exception {
        return given().headers("Content-Type", "application/json", "Authorization", "Bearer " + GetToken.getAPIToken())
                .body(BodyApi.BODY_CREATE_WATCHLIST_WITH_NAME_EMPTY)
                .when()
                .post(ConfigPath.POST_CREATE_WATCHLIST)
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }
    public JsonPath createAPIWatchlistWithNameNull() throws Exception {
        return given().headers("Content-Type", "application/json", "Authorization", "Bearer " + GetToken.getAPIToken())
                .body(BodyApi.BODY_CREATE_WATCHLIST_WITH_NAME_NULL)
                .when()
                .post(ConfigPath.POST_CREATE_WATCHLIST)
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }
}
