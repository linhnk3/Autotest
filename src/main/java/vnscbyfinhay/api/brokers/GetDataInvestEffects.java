package vnscbyfinhay.api.brokers;

import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;
import io.restassured.path.json.JsonPath;
import vnscbyfinhay.api.login.GetToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class GetDataInvestEffects {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    private final ConfigPath configPath;

    public GetDataInvestEffects(String env) {
        if ("dev".equals(env)) {
            configPath = new ConfigPath("dev");
        } else if ("prod".equals(env)) {
            configPath = new ConfigPath("prod");
        } else {
            configPath = new ConfigPath("default");
        }
    }

    public JsonPath getAPIInvestEffects(Integer id) throws Exception {
        return given().header("Authorization", "Bearer " + GetToken.getAPIToken(244))
                .params(BodyApi.BODY_GET_INVEST_EFFECTS)
                .when()
                .get(configPath.GET_INVEST_EFFECTS + id + "/backtest")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIInvestEffectsInvalid(Integer id) throws Exception {
        return given().header("Authorization", "Bearer " + GetToken.getAPIToken(244))
                .params(BodyApi.BODY_GET_INVEST_EFFECTS)
                .when()
                .get(configPath.GET_INVEST_EFFECTS + id + "/backtest")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIInvestEffectsInvalidToken(Integer id) throws Exception {
        return given().header("Authorization", "Bearer " + null)
                .params(BodyApi.BODY_GET_INVEST_EFFECTS)
                .when()
                .get(configPath.GET_INVEST_EFFECTS + id + "/backtest")
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIInvestEffectsExpiredToken(Integer id) throws Exception {
        return given().header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0")
                .params(BodyApi.BODY_GET_INVEST_EFFECTS)
                .when()
                .get(configPath.GET_INVEST_EFFECTS + id + "/backtest")
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }
}