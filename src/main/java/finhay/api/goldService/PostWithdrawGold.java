package finhay.api.goldService;

import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;
import org.json.simple.JSONObject;


import java.sql.*;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostWithdrawGold {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;

    public String getMessageAPIPostWithdrawAllTypeSuccess(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Withdraw_Gold_Success_All_Type)
                .when()
                .post(ConfigPath.POST_WITHDRAW_GOLD+user_id+"/withdraw_orders")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String getMessageAPIPostWithdrawTypeGold24KSuccess(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Withdraw_Gold_24K_Type)
                .when()
                .post(ConfigPath.POST_WITHDRAW_GOLD+user_id+"/withdraw_orders")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public String getMessageAPIPostWithdrawTypeGold24KOneMaceSuccess(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Withdraw_Gold_Success_Gold24K_One_Mace)
                .when()
                .post(ConfigPath.POST_WITHDRAW_GOLD+user_id+"/withdraw_orders")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String getMessageAPIPostWithdrawNoEnoughGold(int user_id) {
        String message = given().header("Content-Type", "application/json")
                .body(BodyApi.Body_Withdraw_Gold_Success_All_Type)
                .when()
                .post(ConfigPath.POST_WITHDRAW_GOLD+user_id+"/withdraw_orders")
                .then()
                .statusCode(400)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public String runJobChangeStatusSubmitUndueToSubmitDue() {
        String message = given().header("Content-Type", "application/json")
                .when()
                .put(ConfigPath.PUT_JOB_FROM_SUBMIT_UNDUE_TO_SUBMIT_DUE)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
      return message;
    }
    public String runJobChangeStatusSubmitDueToOverDue() {
        String message = given().header("Content-Type", "application/json")
                .when()
                .put(ConfigPath.PUT_JOB_FROM_SUBMIT_DUE_TO_OVERDUE)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }

    public String runJobChangeStatusSubmitDueToShopConfirmed() {
        JSONObject request = new JSONObject();
        request.put("user_id",getUserIDWithdrawOrder());
        request.put("request_id",getRequestIDWithdrawOrder());
        String message = given().header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .put(ConfigPath.PUT_JOB_FROM_SUBMIT_DUE_TO_SHOP_CONFIRMED)
                .then()
               .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public String runJobChangeStatusFromShopConfirmedToCompleted(int user_id) {
        JSONObject request = new JSONObject();
        request.put("request_id",getRequestIDWithdrawOrder());
        String message = given().header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .put(ConfigPath.PUT_JOB_FROM_SHOP_CONFIRMED_TO_COMPLETED+user_id+"/withdraw_orders/_complete")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("message");
        System.out.println(message);
        return message;
    }
    public int getRequestMaceInDB() {
        String sql = "select request_maces from finhaydb_gold.withdraw_orders WHERE user_id = 1873 ORDER BY id DESC limit 1 ;";
        int mace = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                mace = kq.getInt("request_maces");
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
        return mace;

    }
    public String getStatusWithdrawGoldInDB() {
        String sql = "select status from finhaydb_gold.withdraw_orders WHERE user_id = 1873 ORDER BY id DESC limit 1 ;";
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
    public void updateAmountGoldTypeOneMaceUser38043() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb_gold.user_gold_bags set quantity = 0 WHERE user_id = 38043 and gold_type = \"GOLD_24K_ONE_MACE\";";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void updateAmountGoldType24KUser38043() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb_gold.user_gold_bags set quantity = 0 WHERE user_id = 38043 and gold_type = \"GOLD_24K\";";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void updateGoldType24KUser38043AmountIs2() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb_gold.user_gold_bags set quantity = 2 WHERE user_id = 38043 and gold_type = \"GOLD_24K\" LIMIT 1;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void deleteWithdrawOrderUser38043() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "DELETE FROM finhaydb_gold.withdraw_orders WHERE user_id =38043;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public String getOrderIDWithdrawGold() {
        String sql = "SELECT id from finhaydb_gold.withdraw_orders WHERE user_id = 38043 ORDER BY id DESC limit 1;";
        String stt = null;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                stt = kq.getString("id");
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
    public String getRequestIDWithdrawOrder() {
        String sql = "SELECT request_id from finhaydb_gold.withdraw_orders WHERE user_id = 1873 ORDER BY id DESC limit 1;";
        String stt = null;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                stt = kq.getString("request_id");
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
    public String getUserIDWithdrawOrder() {
        String sql = "SELECT user_id  from finhaydb_gold.withdraw_orders ORDER BY id DESC limit 1;";
        String stt = null;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                stt = kq.getString("user_id");
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
    public void deleteOrderItemWithdraw() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "DELETE FROM finhaydb_gold.withdraw_order_items  Where withdraw_order_id = "+getOrderIDWithdrawGold()+" order by withdraw_order_id DESC limit 1;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void updateRequestTimeNow() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();

            String querySql = "UPDATE finhaydb_gold.withdraw_orders set request_date = CURDATE() WHERE user_id = 1873 ORDER BY id DESC LIMIT 1;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void updateExpiredDateNow() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();

            String querySql = "UPDATE finhaydb_gold.withdraw_orders set expired_date = CURDATE() WHERE user_id = 1873 ORDER BY id DESC LIMIT 1;";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public Date getDateTimeNow(){
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        return date;
    }

}
