package brokers;
import vnscbyfinhay.api.brokers.GetListBroker;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGetListBroker {
    GetListBroker broker= new GetListBroker();
    @Test
    public void TC_O1_Check_Correct_List_Broker() throws Exception {
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
    @Test
    public void TC_O2_Check_InCorrect_List_Broker_With_Invalid_Token() throws Exception {
        JsonPath data= broker.getAPIListBrokerInvalidToken();
        Assert.assertEquals(data.get("message"), "Thông tin xác thực không hợp lệ");
        Assert.assertEquals(data.get("error_code"), "InvalidTokenException");

    }
    @Test
    public void TC_O3_Check_InCorrect_List_Broker_With_Expired_Token() throws Exception {
        JsonPath data= broker.getAPIListBrokerExpiredToken();
        Assert.assertEquals(data.get("message"), "Vui lòng đăng nhập lại để tiếp tục sử dụng");
        Assert.assertEquals(data.get("error_code"), "UnauthorizedException");
    }
}
