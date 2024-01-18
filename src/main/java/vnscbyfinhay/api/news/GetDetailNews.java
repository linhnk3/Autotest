package vnscbyfinhay.api.news;

import Connection.MySQL;
import com.mashape.unirest.http.exceptions.UnirestException;
import constants.BodyApi;
import constants.configPath;
import io.restassured.path.json.JsonPath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetDetailNews {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIDetailNews(Integer id) throws Exception {
        return given()
                .params(BodyApi.BODY_GET_DETAIL_NEWS)
                .when()
                .get(configPath.GET_DETAIL_NEWS +id + "/detail")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public List<String> getDetailNewsById(Integer id) {
        String sql = String.format("SELECT path FROM vnsc_datafeed.news WHERE id = '%s'", id);
        List<String> result = new ArrayList<>();
        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                result.add(kq.getString("id"));
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
