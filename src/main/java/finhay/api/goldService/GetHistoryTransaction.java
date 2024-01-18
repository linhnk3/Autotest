package finhay.api.goldService;

import static io.restassured.RestAssured.given;

public class GetHistoryTransaction {
    public String  GetTransactionHistory() {

        String stt = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/37342/orders")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        return stt;
    }
}
