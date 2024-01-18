package finhay.api.goldService;


import Connection.MySQL;
import constants.BodyApi;
import constants.configPath;


import java.sql.*;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostBuyGold {
    public MySQL query = new MySQL();
    private Connection con =  null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String,Object> maps ;

    public String GetMessageBuyGold(int userid) {
     String stt =   given().header("Content-Type","application/json")
                .body(BodyApi.body_post_buy_gold_normal_case_gold_one_mace)
                .when()
                .post(configPath.POST_BUY_SELL_GOLD+userid+"/requests")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(stt);
return stt;
    }
    public String GetMessageBuyGoldAllType(int userid) {
        String stt =   given().header("Content-Type","application/json")
                .body(BodyApi.body_post_buy_gold_normal_case_all_type)
                .when()
                .post(configPath.POST_BUY_SELL_GOLD+userid+"/requests")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(stt);
        return stt;
    }
    public String GetMessageBuyGoldOutOfGold(int userid) {
        String stt =   given().header("Content-Type","application/json")
                .body(BodyApi.body_post_buy_gold_over_inventory)
                .when()
                .post(configPath.POST_BUY_SELL_GOLD+userid+"/requests")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(stt);
        return stt;
    }
    public String GetMessageBuyGoldOverLimitPerDay(int userid) {
        String stt =   given().header("Content-Type","application/json")
                .body(BodyApi.body_post_buy_gold_over_limit_per_day)
                .when()
                .post(configPath.POST_BUY_SELL_GOLD+userid+"/requests")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(stt);
        return stt;
    }

    public String GetStatusGoldOverAssetCOD(int userid) {
      String response =  given().header("Content-Type","application/json")
                .body(BodyApi.body_post_buy_gold_over_asset_COD)
                .when()
                .post(configPath.POST_BUY_SELL_GOLD+userid+"/requests")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("error_code");
        return response;
    }
    public String GetMessageUserNoInputAmountGold(int userid) {
     String  responseNoInputAmount = given().header("Content-Type","application/json")
                .body(BodyApi.body_post_buy_gold_no_input_amount_gold)
                .when()
                .post(configPath.POST_BUY_SELL_GOLD+userid+"/requests")
         .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
  return responseNoInputAmount;
   }

    public String GetMessageUserBuyOverQuantityInventory(int userid) {
        String  responseNoInputAmount = given().header("Content-Type","application/json")
                .body(BodyApi.body_post_buy_gold_no_input_amount_gold)
                .when()
                .post(configPath.POST_BUY_SELL_GOLD+userid+"/requests")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        return responseNoInputAmount;
    }
public void updateInventoryGoldTypeOneMaceReturn0() throws SQLException {
    try{
        Connection conn = MySQL.getMySQLConnection();
        String querySql = "UPDATE finhaydb_gold.inventory set quantity = 0 WHERE id = 2;";
        Statement statement = conn.createStatement();
        int result = statement.executeUpdate(querySql);
        System.out.println(querySql);
        conn.close();
    } catch(SQLException e){
        e.printStackTrace();
    }

}
    public void updateInventoryGoldTypeOneMaceReturn1() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb_gold.inventory set quantity = 1 WHERE id = 2;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void updateInventoryGoldType24KReturn1() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb_gold.inventory set quantity = 1 WHERE id = 1;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void updateInventoryGoldType24KReturn0() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb_gold.inventory set quantity = 0 WHERE id = 1;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void updateInventoryGoldTypeOneMaceTo100() throws SQLException {
        try {
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb_gold.inventory set quantity = 1000 WHERE id = 2;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getStatusOrderInDB() {
        String sql = "Select status from finhaydb_gold.orders where user_id = 1873 ORDER by id desc limit 1";
        String  stt = null;

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



    public Integer getQuantityInventoryGoldOneMace() {
        String sql = "SELECT quantity from finhaydb_gold.inventory WHERE id = 2";
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
    public Integer getQuantityInventoryGold24K() {
        String sql = "SELECT quantity from finhaydb_gold.inventory WHERE id = 1";
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
    public Integer getQuantityUserGoldBagsGoldOneMace() {
        String sql = "SELECT sum(quantity) from finhaydb_gold.user_gold_bags ugb WHERE user_id = 1873 and gold_type = \"GOLD_24K_ONE_MACE\"";
        int quantity = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                quantity = kq.getInt("sum(quantity)");
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
    public Integer getQuantityUserGoldBagsGold24K() {
        String sql = "SELECT sum(quantity) from finhaydb_gold.user_gold_bags ugb WHERE user_id = 1873 and gold_type = \"GOLD_24K\"";
        int quantity = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                quantity = kq.getInt("sum(quantity)");
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
}
