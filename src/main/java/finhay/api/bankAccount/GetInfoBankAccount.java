package finhay.api.bankAccount;

import constants.BodyApi;
import constants.ConfigPath;

import static io.restassured.RestAssured.given;

public class GetInfoBankAccount {
    public String getTokenUser() {
        String result =   given().header("Content-Type","application/json")
                .body(BodyApi.Body_Login_1153)
                .when()
                .post(ConfigPath.POST_LOGIN)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result.access_token");
        System.out.println(result);
        return result;
    }
    public Boolean getInfoBankAccountCorrect() {
        boolean result =  given().header("Content-Type","application/json")
                .header("Authorization","Bearer "+getTokenUser())
                .body(BodyApi.Body_Login_1153)
                .when()
                .get(ConfigPath.GET_INFO_BANK_ACCOUNT_CORRECT)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result.is_valid");
        System.out.println(result);
        return result;
    }


    public Boolean getInfoBankAccountNotExist() {
        boolean result =  given().header("Content-Type","application/json")
                .header("Authorization","Bearer "+getTokenUser())
                .body(BodyApi.Body_Login_1153)
                .when()
                .get(ConfigPath.GET_INFO_BANK_ACCOUNT_NOT_EXIST_IN_DB)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result.is_valid");
        System.out.println(result);
        return result;
    }
    public String getInfoBankAccountWrongAccountNumber() {
        String result =  given().header("Content-Type","application/json")
                .header("Authorization","Bearer "+getTokenUser())
                .body(BodyApi.Body_Login_1153)
                .when()
                .get(ConfigPath.GET_INFO_BANK_ACCOUNT_WRONG_ACCOUNT_NUMBER)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(result);
        return result;
    }
}
