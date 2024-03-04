package brokers;
import vnscbyfinhay.api.brokers.GetListBroker;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGetListBroker {
    String env = System.getProperty("env");
    GetListBroker broker= new GetListBroker(env);

    @Test
    public void TC_01_givenBrokerInfo_whenGetListBroker_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data= broker.getAPIListBroker();
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        List<Map<String, Object>> dataApi = data.getList("data");
        List<HashMap<String, Object>> dataDb = broker.getListBroker();
        for (int i = 0; i < dataApi.size(); i++) {
            Assert.assertEquals(dataApi.get(i).get("id"), dataDb.get(i).get("id"));
            Assert.assertEquals(dataApi.get(i).get("name"), dataDb.get(i).get("name"));
            Assert.assertEquals(dataApi.get(i).get("description"), dataDb.get(i).get("description"));
            Assert.assertEquals(dataApi.get(i).get("short_name"), dataDb.get(i).get("short_name"));
        }
    }
}
