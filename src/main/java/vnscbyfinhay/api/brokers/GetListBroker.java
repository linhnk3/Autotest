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

public class GetListBroker {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    private final ConfigPath configPath;

    public GetListBroker(String env) {
        if ("dev".equals(env)) {
            configPath = new ConfigPath("dev");
        } else if ("prod".equals(env)) {
            configPath = new ConfigPath("prod");
        } else {
            configPath = new ConfigPath("default");
        }
    }

    public JsonPath getAPIListBroker() throws Exception {
        return given().header("Authorization", "Bearer " + GetToken.getAPIToken3())
                .params(BodyApi.BODY_GET_LIST_BROKER)
                .when()
                .get(configPath.GET_LIST_BROKER)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIListBrokerInvalidToken() throws Exception {
        return given().header("Authorization", "Bearer " + null)
                .params(BodyApi.BODY_GET_LIST_BROKER)
                .when()
                .get(configPath.GET_LIST_BROKER)
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIListBrokerExpiredToken() throws Exception {
        return given().header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0")
                .params(BodyApi.BODY_GET_LIST_BROKER)
                .when()
                .get(configPath.GET_LIST_BROKER)
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public List<HashMap<String, Object>> getListBroker() {
        String sql = String.format("SELECT * FROM vnsc_trading_api.brokers");
        List<HashMap<String, Object>> result = new ArrayList<>();
        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            System.out.println(stmt.toString());

            while (kq.next()) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("id", kq.getInt(1));
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
