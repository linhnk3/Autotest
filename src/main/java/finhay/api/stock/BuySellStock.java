package finhay.api.stock;

import Connection.MySQL;
import constants.BodyApi;
import constants.configPath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BuySellStock {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;

    public String getMessageAPIPostBuyStockSuccess(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.BODY_BUY_STOCK_SUCCESS)
                .when()
                .post(configPath.POST_STOCK+user_id+"/buy_orders")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public String getMessageAPIPostBuyOddStock(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.BODY_BUY_ODD_STOCK)
                .when()
                .post(configPath.POST_STOCK+user_id+"/buy_orders")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public void getMessageAPIPostBuyOddStockManyTime(int user_id) {
        for (int i =0;i<30;i++) {
          given().header("Content-Type", "application/json")
                    .body(BodyApi.BODY_BUY_ODD_STOCK)
                    .when()
                    .post(configPath.POST_STOCK_LOCAL + user_id + "/buy_orders")
                    .then()
                    .statusCode(200)
                    .assertThat().extract().response().getBody().jsonPath().prettyPrint();

        }
    }


    public String getMessageAPISellStockSuccess(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.BODY_BUY_STOCK_SUCCESS)
                .when()
                .post(configPath.POST_STOCK + user_id + "/sell_orders")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String getMessageAPIPostSellOddStock(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.BODY_BUY_ODD_STOCK)
                .when()
                .post(configPath.POST_STOCK+user_id+"/sell_orders")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String getMessageAPIPostSellOverStock(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.BODY_SELL_OVER_STOCK)
                .when()
                .post(configPath.POST_STOCK+user_id+"/sell_orders")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String getStatusBuyStockUser1153() {
        String sql = "SELECT order_status FROM finhaydb_stock_trading.orders ORDER BY id DESC limit 1;";
        String stt = null;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                stt = kq.getString("order_status");
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
        System.out.println("order_status " + stt);
        return stt;

    }
    public Integer getQuantityBuyStockUser1153() {
        String sql = "SELECT quantity FROM finhaydb_stock_trading.orders ORDER BY id DESC limit 1;";
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
        System.out.println("quantity " + quantity);
        return quantity;

    }


}
