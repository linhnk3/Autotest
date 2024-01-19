package finhay.api.bankAccount;

import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;

import java.sql.*;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostAddBankAccount {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public String getTokenUser() {
        String result =   given().header("Content-Type","application/json")
                .body(BodyApi.Body_Login_38325)
                .when()
                .post(ConfigPath.POST_LOGIN)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result.access_token");
        System.out.println(result);
        return result;
    }
    public String CallAPISaveBankAccountSuccess() {
        String stt =   given().header("Content-Type","application/json")
                .header("Authorization","Bearer "+getTokenUser())
                .body(BodyApi.BODY_SAVE_BANK_ACCOUNT_SUCCESS)
                .when()
                .post(ConfigPath.POST_SAVE_BANK_ACCOUNT)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(stt);
        return stt;
    }

    public String getAccountNumberUserID38327() {
        String sql = "SELECT account_number from finhaydb.bank_accounts WHERE user_id = 38327;";
        String stt = null;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                stt = kq.getString("account_number");
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
        return stt;

    }
    public void deleteBankAccountUserID38327() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "DELETE from finhaydb.bank_accounts WHERE user_id =38327;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
}
