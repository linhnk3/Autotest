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
    public void TC_O1_Check_Correct_Data_Broker_With_ID_1() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffects(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_O2_Check_Correct_Data_Broker_With_ID_2() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(1).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffects(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_O3_Check_Correct_Data_Broker_With_ID_3() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffects(kq);
        Assert.assertEquals(data_1.get("message"), "Success");
        Assert.assertEquals(data_1.get("error_code"), "0");
    }

    @Test
    public void TC_O4_Check_InCorrect_Data_Broker_With_Id_Not_Exits() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffectsInvalid(177);
        Assert.assertEquals(data_1.get("message"), "Không thể tìm thấy thông tin của môi giới với mã 177, vui lòng thử lại");
        Assert.assertEquals(data_1.get("error_code"), "BROKER_001");
    }

    @Test
    public void TC_O5_Check_InCorrect_Data_Broker_With_Id_Not_Null() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffectsInvalid(null);
        Assert.assertEquals(data_1.get("title"), "Bad Request");
    }

    @Test
    public void TC_O6_Check_InCorrect_Data_Broker_With_Invalid_Token() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffectsInvalidToken(kq);
        Assert.assertEquals(data_1.get("message"), "Thông tin xác thực không hợp lệ");
        Assert.assertEquals(data_1.get("error_code"), "InvalidTokenException");
    }

    @Test
    public void TC_O7_Check_InCorrect_Data_Broker_With_Expired_Token() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataInvestEffects.getAPIInvestEffectsExpiredToken(kq);
        Assert.assertEquals(data_1.get("message"), "Vui lòng đăng nhập lại để tiếp tục sử dụng");
        Assert.assertEquals(data_1.get("error_code"), "UnauthorizedException");
    }
}
