package brokers;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.brokers.GetDataInvestEffects;
import vnscbyfinhay.api.brokers.GetListBroker;

import java.util.List;
import java.util.Map;

public class TestGetDataInvestEffects {
    String env = System.getProperty("env");
    GetDataInvestEffects dataInvestEffects = new GetDataInvestEffects(env);
    GetListBroker broker = new GetListBroker(env);

    @Test
    public void TC_01_givenBrokerInfoID1_whenGetDataInvestEffects_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffects(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_02_givenBrokerInfoID2_whenGetDataInvestEffects_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(1).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffects(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_03_givenBrokerInfoID3_whenGetDataInvestEffects_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffects(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_04_givenInputIDNotExits_whenGetDataInvestEffects_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffectsInvalid(177);
        Assert.assertEquals(data_1.get("message"), "Không thể tìm thấy thông tin của môi giới với mã 177, vui lòng thử lại");
        Assert.assertEquals(data_1.get("error_code"), "BROKER_001");
    }

    @Test
    public void  TC_05_givenInputIDNull_whenGetDataInvestEffects_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffectsInvalid(null);
        Assert.assertEquals(data_1.get("title"), "Bad Request");
    }
}
