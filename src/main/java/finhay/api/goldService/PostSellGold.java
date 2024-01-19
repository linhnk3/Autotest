package finhay.api.goldService;

import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostSellGold {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;


    public String getMessageAPIPostSellGold24KSuccess(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.body_gold_sell_success_gold_24K)
                .when()
                .post(ConfigPath.POST_BUY_SELL_GOLD+user_id+"/requests")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public String getMessageAPIPostSellGold24KOneMaceSuccess(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Gold_Sell_Gold_24K_One_Mark_Success)
                .when()
                .post(ConfigPath.POST_BUY_SELL_GOLD+user_id+"/requests")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String getMessageAPIPostSellGoldAllTypeSuccess(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Gold_Sell_Gold_All_Type_Success)
                .when()
                .post(ConfigPath.POST_BUY_SELL_GOLD+user_id+"/requests")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String getMessageAPIPostSellGoldFail(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.body_gold_sell_success_gold_24K)
                .when()
                .post(ConfigPath.POST_BUY_SELL_GOLD+user_id+"/requests")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public String getMessageAPIPostSellOverGold(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.body_gold_sell_over_gold_24K)
                .when()
                .post(ConfigPath.POST_BUY_SELL_GOLD+user_id+"/requests")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public String getMessageAPIPostSellOverGoldForTheDay(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Gold_Sell_Over_Limit_For_The_Day_Gold_Gold_24K)
                .when()
                .post(ConfigPath.POST_BUY_SELL_GOLD+user_id+"/requests")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public String getMessageAPIPostSellGoldLessThanMinimum(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Gold_Sell_Less_Minimum_Gold_24K)
                .when()
                .post(ConfigPath.POST_BUY_SELL_GOLD+user_id+"/requests")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }


    public String getMessageAPIPostSellGoldNoInputAmount(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Gold_Sell_No_Input_Amount)
                .when()
                .post(ConfigPath.POST_BUY_SELL_GOLD+user_id+"/requests")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String getStatusOrderInDB() {
        String sql = "Select status from finhaydb_gold.orders where user_id = 1181 ORDER by id desc limit 1";
        String stt = null;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                stt = kq.getString("status");
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

    public Integer getQuantityInventoryGold24KInDB() {
        String sql = "SELECT quantity from finhaydb_gold.inventory WHERE id = 1;";
        int quantity = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                quantity = kq.getInt("quantity");
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
        return quantity;
    }

    public Integer getQuantityInventoryGold24OneMaceKInDB() {
        String sql = "SELECT quantity from finhaydb_gold.inventory WHERE id = 2;";
        int quantity = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                quantity = kq.getInt("quantity");
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
        return quantity;
    }

    public Integer getPaidAmountCodTransactionInDB() {
        String sql = "SELECT paid_amount FROM finhaydb_cod.cod_transactions WHERE user_id = 1181 ORDER BY id DESC limit 1 ;";
        int amount = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                amount = kq.getInt("paid_amount");
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
        return amount;
    }

    public Integer getPaidAmountCodTransactionInDBUser1873() {
        String sql = "SELECT paid_amount FROM finhaydb_cod.cod_transactions WHERE user_id = 1873 ORDER BY id DESC limit 1 ;";
        int amount = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                amount = kq.getInt("paid_amount");
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
        return amount;
    }
    public Integer getTotalAmountGoldBagsInDB() {
        String sql = "SELECT sum(quantity) as total from finhaydb_gold.user_gold_bags ugb WHERE user_id =1181;";
        int amount = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                amount = kq.getInt("total");
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
        return amount;
    }
    public Integer getTotalAmountGoldBagsInDBUser1873() {
        String sql = "SELECT sum(quantity) as total from finhaydb_gold.user_gold_bags ugb WHERE user_id =1873;";
        int amount = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                amount = kq.getInt("total");
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
        return amount;
    }
}