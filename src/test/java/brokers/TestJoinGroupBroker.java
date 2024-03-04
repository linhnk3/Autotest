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
    JoinGroupBroker groupBroker = new JoinGroupBroker(env);
    GetListBroker broker = new GetListBroker(env);

    @Test
    public void TC_01_givenBrokerInfoID1_whenJoinGroupBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBroker(kq, "true");
        System.out.println(data_1);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
        List<HashMap<String, Object>> dataDb = groupBroker.getGroupBroker(1);
        Assert.assertEquals(true, dataDb.get(0).get("favorite"));
    }

    @Test
    public void TC_02_givenBrokerInfoID2_whenJoinGroupBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(1).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBroker(kq, "true");
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
        List<HashMap<String, Object>> dataDb = groupBroker.getGroupBroker(2);
        Assert.assertEquals(true, dataDb.get(0).get("favorite"));
    }

    @Test
    public void TC_03_givenBrokerInfoID3_whenJoinGroupBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBroker(kq, "true");
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
        List<HashMap<String, Object>> dataDb = groupBroker.getGroupBroker(3);
        Assert.assertEquals(true, dataDb.get(0).get("favorite"));
    }

    @Test
    public void TC_04_givenBrokerInfoID3_whenOutGroupBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBroker(kq, "true");
        JsonPath data_2 = groupBroker.getAPIJoinGroupBroker(kq, "false");
        Assert.assertEquals(data_2.get("message"), "Success");
        Assert.assertEquals(data_2.get("error_code"), "0");
        List<HashMap<String, Object>> dataDb = groupBroker.getGroupBroker(3);
        Assert.assertEquals(false, dataDb.get(0).get("favorite"));
    }

    @Test
    public void TC_05_givenInputIDNull_whenJoinGroupBroker_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(null, "true");
        Assert.assertEquals(data_1.get("title"), "Bad Request");
    }

    @Test
    public void TC_06_givenInputIDNotExits_whenJoinGroupBroker_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(1111, "true");
        Assert.assertEquals(data_1.get("message"), "Không thể tìm thấy thông tin của môi giới với mã 1111, vui lòng thử lại");
        Assert.assertEquals(data_1.get("error_code"), "BROKER_001");
    }

    @Test
    public void TC_07_givenInputParamInvalid_whenJoinGroupBroker_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(kq, "aa");
        Assert.assertEquals(data_1.get("message"), "Tham số truyền vào không hợp lệ");
        Assert.assertEquals(data_1.get("error_code"), "PARAM_NOT_VALID");
    }

    @Test
    public void TC_08_givenInputParamNull_whenJoinGroupBroker_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(kq, null);
        Assert.assertEquals(data_1.get("message"), "Tham số truyền vào không hợp lệ");
        Assert.assertEquals(data_1.get("error_code"), "PARAM_NOT_VALID");
    }

    @Test
    public void TC_09_givenInputParamHollow_whenJoinGroupBroker_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = groupBroker.getAPIJoinGroupBrokerInvalid(kq, "");
        Assert.assertEquals(data_1.get("message"), "Tham số truyền vào không hợp lệ");
        Assert.assertEquals(data_1.get("error_code"), "PARAM_NOT_VALID");
    }
}
