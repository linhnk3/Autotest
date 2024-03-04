package brokers;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.brokers.GetDetailBroker;
import vnscbyfinhay.api.brokers.GetListBroker;


import java.util.*;

public class TestGetDetailBroker {
    String env = System.getProperty("env");
    GetDetailBroker broker_detail= new GetDetailBroker(env);
    GetListBroker broker= new GetListBroker(env);
    @Test
    public void TC_01_givenBrokerInfoID1_whenGetDetailBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
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
    public void TC_02_givenBrokerInfoID2_whenGetDetailBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
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
    public void TC_03_givenBrokerInfoID3_whenGetDetailBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
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
    public void TC_04_givenInputIDInvalid_whenGetDetailBroker_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker_detail.getAPIDetailBrokerInvalid(155555);
        Assert.assertEquals(data.get("message"), "Không thể tìm thấy thông tin của môi giới với mã 155555, vui lòng thử lại");
        Assert.assertEquals((String) data.get("result"), null);
        Assert.assertEquals(data.get("error_code"), "BROKER_001");
    }

    @Test
    public void TC_05_givenInputIDNull_whenGetDetailBroker_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker_detail.getAPIDetailBrokerInvalid(null);
        Assert.assertEquals(data.get("title"), "Bad Request");
    }

    @Test
    public void TC_06_givenBrokerInfoID1_whenGetDetailBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
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
}
