package finhay.api.goldService;

import Connection.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetAssetGoldUser {
    public MySQL query = new MySQL();
    private Connection con =  null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String,Object> maps ;
    public Integer GetAssetGoldAPI() {
        int  assetUser = given()
                .when()
                .get("https://stg.finhay.app/gw/gold/v1/users/1153/gold_jar")
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result.summary.total_mace_holding");
        System.out.println("asset user is " + assetUser);
        return assetUser;
    }

    public Integer getAssetUserInDB() {
        String sql = " Select SUM(quantity) from finhaydb_gold.user_gold_bags where user_id =1153 ;";
        int  assetUser = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                assetUser = kq.getInt("SUM(quantity)");
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
        System.out.println("asset usser in DB is " + assetUser);
        return assetUser;

    }
}
