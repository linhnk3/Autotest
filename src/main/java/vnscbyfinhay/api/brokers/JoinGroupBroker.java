package vnscbyfinhay.api.brokers;

import Connection.MySQL;
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

public class JoinGroupBroker {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;

    public JsonPath getAPIJoinGroupBroker(Integer id, String favorite) throws Exception {
        return given().header("Authorization", "Bearer " + GetToken.getAPIToken(244))
                .header("Content-Type", "application/json")
                .body(String.format("{\n    \"favorite\": %s\n}", favorite))
                .when()
                .put(ConfigPath.JOIN_GROUP_BROKER + id)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIJoinGroupBrokerInvalid(Integer id,String favorite) throws Exception {
        return given().header("Authorization", "Bearer " + GetToken.getAPIToken(244))
                .header("Content-Type", "application/json")
                .body(String.format("{\n    \"favorite\": %s\n}", favorite))
                .when()
                .put(ConfigPath.JOIN_GROUP_BROKER + id)
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIJoinGroupBrokerInvalidToken(Integer id, String favorite) throws Exception {
        return given().header("Authorization", "Bearer " + null)
                .header("Content-Type", "application/json")
                .body(String.format("{\n    \"favorite\": %s\n}", favorite))
                .when()
                .put(ConfigPath.JOIN_GROUP_BROKER + id)
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIJoinGroupBrokerExpiredToken(Integer id, String favorite) throws Exception {
        return given().header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0")
                .header("Content-Type", "application/json")
                .body(String.format("{\n    \"favorite\": %s\n}", favorite))
                .when()
                .put(ConfigPath.JOIN_GROUP_BROKER + id)
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }
    public List<HashMap<String, Object>> getGroupBroker(Integer brokerId) {
        String sql = String.format("SELECT * FROM vnsc_trading_api.user_brokers where broker_id = '%s' and user_id='244'", brokerId);
        List<HashMap<String, Object>> result = new ArrayList<>();
        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            System.out.println(stmt.toString());

            while (kq.next()) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("broker_id", kq.getInt((1)));
                data.put("favorite", kq.getBoolean(5));
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
        return result;
    }
}
