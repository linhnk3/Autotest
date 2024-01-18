package finhay.api.goldService;

import Connection.MySQL;
import constants.configPath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetPriceGold {
    public MySQL query = new MySQL();
    private Connection con =  null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String,Object> maps ;


    public  Integer getAskPriceInDB() {
        String sql = " SELECT ask from finhaydb_gold.price_finhay  order by id DESC Limit 1;";
        int askPrice = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                askPrice = kq.getInt("ask");
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
        System.out.println( "ask in DB is " + askPrice);
        return askPrice;

    }


    /**
     * get email
     *
     * @return
     */
    public Integer getBidPriceInDB() {
        String sql = " SELECT bid from finhaydb_gold.price_finhay order by id DESC Limit 1";
        int bidPrice = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            //  System.out.println(stmt.toString());

            while (kq.next()) {
                bidPrice = kq.getInt("bid");
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
        System.out.println("bid price in DB " + bidPrice);
        return bidPrice;

    }
    public Integer GetBidPriceInAPI() {

        Integer priceBidGold = given()
                .when()
                .get(configPath.GET_PRICE_GOLD)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result.bid");
        System.out.println("bid price in API is " + priceBidGold);
        return priceBidGold;
    }
    public Integer GetAskPriceInAPI() {

        Integer priceAskGold = given()
                .when()
                .get(configPath.GET_PRICE_GOLD)
                .then()
                .statusCode(200)
                .assertThat().extract().response().getBody().jsonPath().getJsonObject("result.ask");
        System.out.println("ask price in API  is " + priceAskGold);
        return priceAskGold;

    }

}
