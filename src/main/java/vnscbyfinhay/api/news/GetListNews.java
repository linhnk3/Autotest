package vnscbyfinhay.api.news;

import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;
import io.restassured.path.json.JsonPath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetListNews {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIListNews(String stock) throws Exception {
        return given()
                .params(BodyApi.BODY_GET_LIST_NEWS)
                .when()
                .get(ConfigPath.GET_LIST_NEWS +stock)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public List<String> getListNewsByStock(String stock) {
        String sql = String.format("SELECT path FROM vnsc_datafeed.news WHERE stock = '%s'", stock);
        List<String> result = new ArrayList<>();
        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();

            while (kq.next()) {
                result.add(kq.getString("path"));
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
        System.out.println("paths " + result);
        return result;

    }
}
