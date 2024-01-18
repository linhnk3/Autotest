package finhay.api.bankAccount;

import Connection.MySQL;
import constants.configPath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetListBank {
    public MySQL query = new MySQL();
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet kq = null;
    Map<String, Object> maps;
    public Integer getAPIListBank() {
         int list = given()
                    .when()
                    .get(configPath.GET_LIST_BANK)
                    .then()
                    .statusCode(200)
                    .assertThat().extract().response().getBody().jsonPath().getList("result").size();
           System.out.println(" size list bank : " + list);
        return list;
    }
    public Integer getAmountBankList() {
        String sql = "SELECT COUNT(id) as countID from finhaydb.bank_lists bl WHERE not bank_code = \"null\" ;";
        int amount = 0;

        try {
            con = query.extracted(query);
            stmt = con.prepareStatement(sql);
            kq = stmt.executeQuery();
            System.out.println(stmt.toString());

            while (kq.next()) {
                amount = kq.getInt("countID");
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
