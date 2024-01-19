package finhay.api.ratingapp;

import constants.ConfigPath;
import Connection.MySQL;
import java.sql.*;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RatingApp {
        public MySQL query = new MySQL();
        private Connection con = null;
        private PreparedStatement stmt = null;
        private ResultSet kq = null;
        Map<String, Object> maps;
    public String CallApiRatingApp(int user_id ){
    String result =  given().header("Content-Type", "application/json")
                    .when()
                    .get(ConfigPath.GET_RATING_APP+user_id+"/events/lastest_event")
                    .then()
                    .statusCode(200)
                    .assertThat().extract().response().getBody().jsonPath().getJsonObject("result");
        return result ;
    }

    public String CallApiRatingAppHaveResult(int user_id ) {
        String result = given().header("Content-Type", "application/json")
                .when()
                .get(ConfigPath.GET_RATING_APP + user_id + "/events/lastest_event")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result.content");
        return result;
    }

    public void update_CashBack_Success_At_User_1153() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb.events_rating set cashback_success_at = CURRENT_TIMESTAMP() WHERE user_id = 1153";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void update_Payout_Success_At_User_1873() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb.events_rating set payout_success_at = CURRENT_TIMESTAMP() WHERE user_id = 1873";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }


    public void update_Inviting_Friend_Success_At_User_2045() throws SQLException {
        try{
            Connection conn = MySQL.getMySQLConnection();
            String querySql = "UPDATE finhaydb.events_rating set inviting_friend_success_at = CURRENT_TIMESTAMP() WHERE user_id = 2045";
            Statement statement = conn.createStatement();
            int result = statement.executeUpdate(querySql);
            System.out.println(querySql);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    }

