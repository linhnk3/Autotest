package finhay.api.goldService;

import static io.restassured.RestAssured.given;

public class GetPermissionBuyGold {
    public  Boolean  GetUserNoKYC() {

        Boolean permission = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/38163/permission")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result");
        return permission;
    }

    public Boolean  GetUserHaveAUMInvestAndNoKYC() {

        Boolean permission = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/38164/permission")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result");
        return permission;
    }

    public Boolean  GetUserHaveAUMCD3MAndNoKYC() {

        Boolean permission = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/38165/permission")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result");
        return permission;
    }
    public Boolean  GetUserHaveAUMCD12MAndNoKYC() {

        Boolean permission = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/38167/permission")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result");
        return permission;
    }
    public Boolean  GetUserHaveAUMInvestAndHaveKYC() {

        Boolean permission = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/38160/permission")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result");
        return permission;
    }
    public Boolean  GetUserHaveAUMCd3mAndHaveKYC() {

        Boolean permission = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/38168/permission")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result");
        return permission;
    }
    public Boolean  GetUserHaveAUMCd12mAndHaveKYC() {

        Boolean permission = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/38169/permission")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result");
        return permission;
    }
}
