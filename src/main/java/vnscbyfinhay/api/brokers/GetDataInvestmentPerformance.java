package vnscbyfinhay.api.brokers;

import Connection.MySQL;
import constants.BodyApi;
import constants.configPath;
import io.restassured.path.json.JsonPath;
import vnscbyfinhay.api.login.GetToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetDataInvestmentPerformance {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;

    public JsonPath getAPIInvestmentPerformance(Integer id) throws Exception {
        return given().header("Authorization", "Bearer " + GetToken.getAPIToken3())
                .params(BodyApi.BODY_GET_INVESTMENT_PERFORMANCE)
                .when()
                .get(configPath.GET_INVESTMENT_PERFORMANCE + id + "/investment-performance")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIInvestmentPerformanceInvalid(Integer id) throws Exception {
        return given().header("Authorization", "Bearer " + GetToken.getAPIToken3())
                .params(BodyApi.BODY_GET_INVESTMENT_PERFORMANCE)
                .when()
                .get(configPath.GET_INVESTMENT_PERFORMANCE + id + "/investment-performance")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIInvestmentPerformanceInvalidToken(Integer id) throws Exception {
        return given().header("Authorization", "Bearer " + null)
                .params(BodyApi.BODY_GET_INVESTMENT_PERFORMANCE)
                .when()
                .get(configPath.GET_INVESTMENT_PERFORMANCE + id + "/investment-performance")
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIInvestmentPerformanceExpiredToken(Integer id) throws Exception {
        return given().header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0")
                .params(BodyApi.BODY_GET_INVESTMENT_PERFORMANCE)
                .when()
                .get(configPath.GET_INVESTMENT_PERFORMANCE + id + "/investment-performance")
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }
}

