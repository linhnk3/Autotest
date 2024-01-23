package brokers;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.brokers.GetListBroker;
import vnscbyfinhay.api.brokers.JoinGroupBroker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJoinGroupBroker {
    String env = System.getProperty("env");
    JoinGroupBroker groupBroker= new JoinGroupBroker(env);
    GetListBroker broker= new GetListBroker(env);

    @Test
    public void TC_O1_Check_Correct_Join_Group_Broker_With_ID_Broker_1() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBroker(kq, "true");
        System.out.println(data_1);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
        List<HashMap<String, Object>> dataDb = groupBroker.getGroupBroker(1);
        Assert.assertEquals(true, dataDb.get(0).get("favorite"));
    }

    @Test
    public void TC_O2_Check_Correct_Join_Group_Broker_With_ID_Broker_2() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(1).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBroker(kq, "true");
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
        List<HashMap<String, Object>> dataDb = groupBroker.getGroupBroker(2);
        Assert.assertEquals(true, dataDb.get(0).get("favorite"));
    }

    @Test
    public void TC_03_Check_Correct_Join_Group_Broker_With_ID_Broker_3() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBroker(kq, "true");
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
        List<HashMap<String, Object>> dataDb = groupBroker.getGroupBroker(3);
        Assert.assertEquals(true, dataDb.get(0).get("favorite"));
    }

    @Test
    public void TC_04_Check_Correct_Out_Group_Broker_With_ID_Broker_3() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBroker(kq, "true");
        JsonPath data_2 = groupBroker.getAPIJoinGroupBroker(kq, "false");
        Assert.assertEquals(data_2.get("message"), "Success");
        Assert.assertEquals(data_2.get("error_code"), "0");
        List<HashMap<String, Object>> dataDb = groupBroker.getGroupBroker(3);
        Assert.assertEquals(false, dataDb.get(0).get("favorite"));
    }

    @Test
    public void TC_05_Check_InCorrect_Join_Group_Broker_With_ID_Null() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(null, "true");
        Assert.assertEquals(data_1.get("title"), "Bad Request");
    }

    @Test
    public void TC_06_Check_InCorrect_Join_Group_Broker_With_ID_Not_Exits() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(1111, "true");
        Assert.assertEquals(data_1.get("message"), "Không thể tìm thấy thông tin của môi giới với mã 1111, vui lòng thử lại");
        Assert.assertEquals(data_1.get("error_code"), "BROKER_001");
    }

    @Test
    public void TC_07_Check_InCorrect_Join_Group_Broker_With_Body_Invalid() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(kq, "aa");
        Assert.assertEquals(data_1.get("message"), "Tham số truyền vào không hợp lệ");
        Assert.assertEquals(data_1.get("error_code"), "PARAM_NOT_VALID");
    }

    @Test
    public void TC_08_Check_InCorrect_Join_Group_Broker_With_Body_null() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(kq, null);
        Assert.assertEquals(data_1.get("message"), "Tham số truyền vào không hợp lệ");
        Assert.assertEquals(data_1.get("error_code"), "PARAM_NOT_VALID");
    }

    @Test
    public void TC_09_Check_InCorrect_Join_Group_Broker_With_Body_hollow() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(kq, "");
        Assert.assertEquals(data_1.get("message"), "Tham số truyền vào không hợp lệ");
        Assert.assertEquals(data_1.get("error_code"), "PARAM_NOT_VALID");
    }

    @Test
    public void TC_10_Check_InCorrect_Join_Group_Broker_With_Invalid_Token() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalidToken(kq, "true");
        Assert.assertEquals(data_1.get("message"), "Thông tin xác thực không hợp lệ");
        Assert.assertEquals(data_1.get("error_code"), "InvalidTokenException");
    }

    @Test
    public void TC_11_Check_InCorrect_Join_Group_Broker_With_Expired_Token() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerExpiredToken(kq, "true");
        Assert.assertEquals(data_1.get("message"), "Vui lòng đăng nhập lại để tiếp tục sử dụng");
        Assert.assertEquals(data_1.get("error_code"), "UnauthorizedException");
    }
}
