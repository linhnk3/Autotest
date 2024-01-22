package finhay.api.goldService;

import Connection.MySQL;
import constants.BodyApi;
import constants.ConfigPath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BuySellWithdrawGoldManyTime {
    public MySQL query = new MySQL();
    private Connection con =  null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String,Object> maps ;

    /**
     * Lấy User_ID
     * @return
     */
    public List<Integer> getUserID() {
        String sql = "select distinct user_id from finhaydb_gold.orders ;";
        int userId = 0;
        List<Integer> user_id = new ArrayList<>();

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                userId = kq.getInt("user_id");
                user_id.add(userId);
//                System.out.println(user_id);
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
    //    System.out.println("userId  is" + userId);
        return user_id;

    }

    public void CallAPiBuyGold(){
        for (int i =0;i<65;i++) {
            given().header("Content-Type", "application/json")
                    //    .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjExNTMsImlhdCI6MTYyNjc1MjgwMiwiZXhwIjoxNjI5MzQ0ODAyfQ.G8GXq8wNF5jzGSCVSYHtBqIVDcLiWYIeiHgvdxxHpfA")
                    .body(BodyApi.body_gold_buy_many_time)
                    .when()
                    .post(ConfigPath.POST_BUY_SELL_GOLD+getUserID().get(i)+ "/requests")
                    .then()
                    .statusCode(200)
                    .assertThat().extract().response().getBody().jsonPath().prettyPrint();
        }
    }

    public void CallAPISellGold() {
        for (int i =0;i<65;i++) {
            given().header("Content-Type", "application/json")
                    .body(BodyApi.body_gold_sell_many_time)
                    .when()
                    .post(ConfigPath.POST_BUY_SELL_GOLD+getUserID().get(i)+ "/requests")
                    .then()
                    //.statusCode(200)
                    .assertThat().extract().response().getBody().jsonPath().prettyPrint();
        }
    }
    public void CallAPIWithdrawGold() {
        for (int i =0;i<65;i++) {
            given().header("Content-Type", "application/json")
                    .body(BodyApi.Body_Withdraw_Gold_Success_All_Type)
                    .when()
                    .post(ConfigPath.POST_WITHDRAW_GOLD+getUserID().get(i)+ "/withdraw_orders")
                    .then()
                    //.statusCode(200)
                    .assertThat().extract().response().getBody().jsonPath().prettyPrint();
        }
    }

    public Integer getStatusTransactionBuyGold24KInDB() {
//
//        String sql ="select sum(quantity) as sum_quantity from finhaydb_gold.orders o \n" +
//                "where (status = \"CONFIRMED\" or status =\"PROCESSING\")\n" +
//                ";";
        String sql = "select sum(quantity) as sum_quantity  from finhaydb_gold.orders\n" +
                " where status = \"COMPLETED\" and order_type =\"BUY\" and inventory_id = 1;";
        int assetUser = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                assetUser = kq.getInt("sum_quantity");
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
        return assetUser;

    }
    public Integer getStatusTransactionBuyGoldOneMaceInDB() {
//
//        String sql ="select sum(quantity) as sum_quantity from finhaydb_gold.orders o \n" +
//                "where (status = \"CONFIRMED\" or status =\"PROCESSING\")\n" +
//                ";";
        String sql = "select sum(quantity) as sum_quantity  from finhaydb_gold.orders\n" +
                " where status = \"COMPLETED\" and order_type =\"BUY\" and inventory_id = 2;";
        int assetUser = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                assetUser = kq.getInt("sum_quantity");
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
        return assetUser;

    }


    /**
     * Total quality ban đầu /cuôi DB
     * @return
     */
    public Integer getStatusTransactionSellGoldInDB() {

//        String sql = "Select sum(if(status ='COMPLETED', quantity, 0)) as sum_quantity from (select * from finhaydb_gold.orders order by id DESC limit 34) as orders;";

        String sql ="select sum(quantity) as sum_quantity  from\n" +
                "finhaydb_gold.orders \n" +
                "where status = \"COMPLETED\" and order_type =\"SELL\";";
        int assetUser = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                assetUser = kq.getInt("sum_quantity");
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
        return assetUser;

    }

    public Integer getStatusTransactionSellGoldInDBGoldType24K() {

//        String sql = "Select sum(if(status ='COMPLETED', quantity, 0)) as sum_quantity from (select * from finhaydb_gold.orders order by id DESC limit 34) as orders;";

        String sql =" select sum(quantity) as sum_quantity  from finhaydb_gold.orders where status = \"COMPLETED\" and order_type =\"SELL\" and inventory_id = 1;";
        int assetUser = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                assetUser = kq.getInt("sum_quantity");
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
        return assetUser;

    }

    public Integer getStatusTransactionSellGoldInDBGoldType24KOneMace() {

//        String sql = "Select sum(if(status ='COMPLETED', quantity, 0)) as sum_quantity from (select * from finhaydb_gold.orders order by id DESC limit 34) as orders;";

        String sql =" select sum(quantity) as sum_quantity  from finhaydb_gold.orders where status = \"COMPLETED\" and order_type =\"SELL\" and inventory_id = 2;";
        int assetUser = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                assetUser = kq.getInt("sum_quantity");
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
        return assetUser;

    }
    public Integer getQuantityInventoryInDB() {
        String sql = "SELECT quantity FROM finhaydb_gold.inventory WHERE id = 1;";
        int assetUser = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                assetUser = kq.getInt("quantity");
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
        System.out.println("quantity inventory is" + assetUser);
        return assetUser;

    }
    public Integer getQuantityUserReservationInDB() {
        String sql = "SELECT user_reservation FROM finhaydb_gold.inventory WHERE id = 1;";
        int assetUser = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                assetUser = kq.getInt("user_reservation");
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
        System.out.println("user_reservation is" + assetUser);
        return assetUser;

    }


    public Integer getSumStatusWithdrawGoldSubmitUndueInDB() {
        String sql = "select count(status) as sumSTT from finhaydb_gold.withdraw_orders WHERE status ='SUBMITTED_UNDUE';";
        int sum = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                sum = kq.getInt("sumSTT");
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

        return sum;

    }


}

