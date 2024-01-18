package vnscbyfinhay.api.watchlist;

import Connection.MySQL;
import com.mashape.unirest.http.exceptions.UnirestException;
import constants.BodyApi;
import constants.configPath;
import io.restassured.path.json.JsonPath;
import vnscbyfinhay.api.login.GetToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetDetailWatchlist {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIDetailWatchlist(Integer id) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken2())
                .when()
                .get(configPath.GET_DETAIL_WATCHLIST + id)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIDetailWatchlistWithIDNull(Integer id) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken2())
                .when()
                .get(configPath.GET_DETAIL_WATCHLIST + id)
                .then()
                .statusCode(500)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public List<Integer> getDetailWatchlistById(Integer id) {
        String sql = String.format("SELECT * FROM vnsc_db.watchlist_item a join vnsc_db.watchlist b " +
                "on a.watchlist_id=b.id where b.id= %s", id);
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
