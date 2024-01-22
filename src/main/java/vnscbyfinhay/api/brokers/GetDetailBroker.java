package vnscbyfinhay.api.brokers;
import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;
import io.restassured.path.json.JsonPath;
import vnscbyfinhay.api.login.GetToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetDetailBroker {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIDetailBroker(Integer id) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(244))
                .params(BodyApi.BODY_GET_DETAIL_BROKER)
                .when()
                .get(ConfigPath.GET_DETAIL_BROKER +id )
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIDetailBrokerInvalid(Integer id) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(244))
                .params(BodyApi.BODY_GET_DETAIL_BROKER)
                .when()
                .get(ConfigPath.GET_DETAIL_BROKER +id )
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIDetailBrokerInvalidToken(Integer id) throws Exception {
        return given().header( "Authorization","Bearer "+ null)
                .params(BodyApi.BODY_GET_DETAIL_BROKER)
                .when()
                .get(ConfigPath.GET_DETAIL_BROKER +id )
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }
    public JsonPath getAPIDetailBrokerExpiredToken(Integer id) throws Exception {
        return given().header( "Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0")
                .params(BodyApi.BODY_GET_DETAIL_BROKER)
                .when()
                .get(ConfigPath.GET_DETAIL_BROKER +id )
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }
    public List<HashMap<String, Object>> getDetailBrokerById(Integer id) {
        String sql = String.format("SELECT * FROM vnsc_trading_api.brokers WHERE id = '%s'", id);
        List<HashMap<String, Object>> result = new ArrayList<>();
        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            System.out.println(stmt.toString());

            while (kq.next()) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("description", kq.getString(2));
                data.put("name", kq.getString(3));
                data.put("short_name", kq.getString(4));
                result.add(data);
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
        // System.out.println("id " + result);
        return result;
    }
}
