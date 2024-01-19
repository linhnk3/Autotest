package vnscbyfinhay.api.watchlist;

import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;
import io.restassured.path.json.JsonPath;
import vnscbyfinhay.api.login.GetToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetListWatchlist {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIListWatchlist() throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken())
                .params(BodyApi.BODY_GET_LIST_WATCHLIST)
                .when()
                .get(ConfigPath.GET_LIST_WATCHLIST)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }
    public JsonPath getAPIListWatchlistDefault() throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken2())
                .params(BodyApi.BODY_GET_LIST_WATCHLIST)
                .when()
                .get(ConfigPath.GET_LIST_WATCHLIST)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }
    public List<Integer> getListWatchlistByUser( String user_id) {
        String sql = String.format("SELECT id FROM vnsc_db.watchlist WHERE user_id = '%s'", user_id);
        List<Integer> result = new ArrayList<>();
        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();

            while (kq.next()) {
                result.add(kq.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("id " + result);
        return result;

    }
}
