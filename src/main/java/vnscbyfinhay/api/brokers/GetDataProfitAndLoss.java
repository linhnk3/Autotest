package vnscbyfinhay.api.brokers;

import Connection.Redis;
import constants.BodyApi;
import constants.configPath;
import io.restassured.path.json.JsonPath;
import vnscbyfinhay.api.login.GetToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetDataProfitAndLoss {
    public Redis query = new Redis();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public JsonPath getAPIProfitAndLoss(Integer id) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(244))
                .params(BodyApi.BODY_GET_PROFIT_AND_LOSS_BROKER)
                .when()
                .get(configPath.GET_PROFIT_AND_LOSS_BROKER +id + "/deals" )
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIProfitAndLossInvalid(Integer id) throws Exception {
        return given().header( "Authorization","Bearer "+ GetToken.getAPIToken(244))
                .params(BodyApi.BODY_GET_PROFIT_AND_LOSS_BROKER)
                .when()
                .get(configPath.GET_PROFIT_AND_LOSS_BROKER +id + "/deals" )
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath();
    }

    public JsonPath getAPIProfitAndLossInvalidToken(Integer id) throws Exception {
        return given().header( "Authorization","Bearer "+ null)
                .params(BodyApi.BODY_GET_PROFIT_AND_LOSS_BROKER)
                .when()
                .get(configPath.GET_PROFIT_AND_LOSS_BROKER +id + "/deals" )
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }
    public JsonPath getAPIProfitAndLossExpiredToken(Integer id) throws Exception {
        return given().header( "Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0")
                .params(BodyApi.BODY_GET_PROFIT_AND_LOSS_BROKER)
                .when()
                .get(configPath.GET_PROFIT_AND_LOSS_BROKER +id + "/deals" )
                .then()
                .statusCode(401)
                .assertThat().extract().response().getBody().jsonPath();
    }
}
