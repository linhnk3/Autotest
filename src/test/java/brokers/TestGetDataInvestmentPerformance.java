package brokers;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.brokers.GetDataInvestmentPerformance;
import vnscbyfinhay.api.brokers.GetListBroker;

import java.util.List;
import java.util.Map;

public class TestGetDataInvestmentPerformance {
    String env = System.getProperty("env");
    GetDataInvestmentPerformance dataInvestmentPerformance = new GetDataInvestmentPerformance(env);
    GetListBroker broker = new GetListBroker(env);

    @Test
    public void TC_01_givenBrokerInfoID1_whenGetDataInvestmentPerformance_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = dataInvestmentPerformance.getAPIInvestmentPerformance(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_02_givenBrokerInfoID2_whenGetDataInvestmentPerformance_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(1).get("id");
        JsonPath data_1 = dataInvestmentPerformance.getAPIInvestmentPerformance(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_03_givenBrokerInfoID3_whenGetDataInvestmentPerformance_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestmentPerformance.getAPIInvestmentPerformance(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_04_givenInputIDNotExits_whenGetDataInvestmentPerformance_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestmentPerformance.getAPIInvestmentPerformanceInvalid(177);
        Assert.assertEquals(data_1.get("message"), "Không thể tìm thấy thông tin của môi giới với mã 177, vui lòng thử lại");
        Assert.assertEquals(data_1.get("error_code"), "BROKER_001");
    }

    @Test
    public void TC_05_givenInputIDNull_whenGetDataInvestmentPerformance_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestmentPerformance.getAPIInvestmentPerformanceInvalid(null);
        Assert.assertEquals(data_1.get("title"), "Bad Request");
    }
}
