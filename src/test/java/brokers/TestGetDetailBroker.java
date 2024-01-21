package brokers;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.brokers.GetDetailBroker;
import vnscbyfinhay.api.brokers.GetListBroker;


import java.util.*;

public class TestGetDetailBroker {
    String env = System.getProperty("env");
    GetDetailBroker broker_detail= new GetDetailBroker();
    GetListBroker broker= new GetListBroker(env);
    @Test
    public void TC_O1_Check_Correct_Detail_Broker_With_ID_1() throws Exception {
        JsonPath data = broker_detail.getAPIDetailBroker(1);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String,Object> dataApi = data.get("data");
        List<HashMap<String, Object>> dataDb = broker_detail.getDetailBrokerById(1);
        Assert.assertEquals(dataApi.get("name"), dataDb.get(0).get("name"));
        Assert.assertEquals(dataApi.get("short_name"), dataDb.get(0).get("short_name"));
        Assert.assertEquals(dataApi.get("description"), dataDb.get(0).get("description"));
        }

    @Test
    public void TC_O2_Check_Correct_Detail_Broker_With_ID_2() throws Exception {
        JsonPath data = broker_detail.getAPIDetailBroker(2);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String,Object> dataApi = data.get("data");
        List<HashMap<String, Object>> dataDb = broker_detail.getDetailBrokerById(2);
        Assert.assertEquals(dataApi.get("name"), dataDb.get(0).get("name"));
        Assert.assertEquals(dataApi.get("short_name"), dataDb.get(0).get("short_name"));
        Assert.assertEquals(dataApi.get("description"), dataDb.get(0).get("description"));
    }

    @Test
    public void TC_O3_Check_Correct_Detail_Broker_With_ID_3() throws Exception {
        JsonPath data = broker_detail.getAPIDetailBroker(3);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String, Object> dataApi = data.get("data");
        List<HashMap<String, Object>> dataDb = broker_detail.getDetailBrokerById(3);
        Assert.assertEquals(dataApi.get("name"), dataDb.get(0).get("name"));
        Assert.assertEquals(dataApi.get("short_name"), dataDb.get(0).get("short_name"));
        Assert.assertEquals(dataApi.get("description"), dataDb.get(0).get("description"));
    }

    @Test
    public void TC_O4_Check_InCorrect_Detail_Broker_With_Id_Not_Exits() throws Exception {
        JsonPath data = broker_detail.getAPIDetailBrokerInvalid(155555);
        Assert.assertEquals(data.get("message"), "Không thể tìm thấy thông tin của môi giới với mã 155555, vui lòng thử lại");
        Assert.assertEquals((String) data.get("result"), null);
        Assert.assertEquals(data.get("error_code"), "BROKER_001");
    }

    @Test
    public void TC_O5_Check_InCorrect_Detail_Broker_With_Id_Null() throws Exception {
        JsonPath data = broker_detail.getAPIDetailBrokerInvalid(null);
        Assert.assertEquals(data.get("title"), "Bad Request");
    }

    @Test
    public void TC_O6_Check_InCorrect_Detail_Broker_With_Invalid_Token() throws Exception {
        JsonPath data= broker_detail.getAPIDetailBrokerInvalidToken(1);
        Assert.assertEquals(data.get("message"), "Thông tin xác thực không hợp lệ");
        Assert.assertEquals(data.get("error_code"), "InvalidTokenException");

    }
    @Test
    public void TC_O7_Check_InCorrect_Detail_Broker_With_Expired_Token() throws Exception {
        JsonPath data= broker_detail.getAPIDetailBrokerExpiredToken(1);
        Assert.assertEquals(data.get("message"), "Vui lòng đăng nhập lại để tiếp tục sử dụng");
        Assert.assertEquals(data.get("error_code"), "UnauthorizedException");
    }

    @Test
    public void TC_O8_Check_Correct_Detail_Broker_With_ID_1() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = broker_detail.getAPIDetailBroker(kq);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String,Object> dataApi_1 = data_1.get("data");
        List<HashMap<String, Object>> dataDb = broker_detail.getDetailBrokerById(1);
        Assert.assertEquals(dataApi_1.get("name"), dataDb.get(0).get("name"));
        Assert.assertEquals(dataApi_1.get("short_name"), dataDb.get(0).get("short_name"));
        Assert.assertEquals(dataApi_1.get("description"), dataDb.get(0).get("description"));
    }

    @Test
    public void TC_O9_Check_Correct_Detail_Broker_With_ID_2() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(1).get("id");
        JsonPath data_1 = broker_detail.getAPIDetailBroker(kq);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String,Object> dataApi_1 = data_1.get("data");
        List<HashMap<String, Object>> dataDb = broker_detail.getDetailBrokerById(2);
        Assert.assertEquals(dataApi_1.get("name"), dataDb.get(0).get("name"));
        Assert.assertEquals(dataApi_1.get("short_name"), dataDb.get(0).get("short_name"));
        Assert.assertEquals(dataApi_1.get("description"), dataDb.get(0).get("description"));
    }

    @Test
    public void TC_10_Check_Correct_Detail_Broker_With_ID_3() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq= (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = broker_detail.getAPIDetailBroker(kq);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String,Object> dataApi_1 = data_1.get("data");
        List<HashMap<String, Object>> dataDb = broker_detail.getDetailBrokerById(3);
        Assert.assertEquals(dataApi_1.get("name"), dataDb.get(0).get("name"));
        Assert.assertEquals(dataApi_1.get("short_name"), dataDb.get(0).get("short_name"));
        Assert.assertEquals(dataApi_1.get("description"), dataDb.get(0).get("description"));
    }
}
