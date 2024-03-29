package brokers;

import Connection.Redis;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.brokers.GetDataProfitAndLoss;
import vnscbyfinhay.api.brokers.GetListBroker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TestGetDataProfitAndLoss {
    String env = System.getProperty("env");
    GetDataProfitAndLoss dataProfitAndLoss = new GetDataProfitAndLoss(env);
    GetListBroker broker = new GetListBroker(env);

    @Test
    public void TC_01_givenBrokerInfoID3_whenGetDataProfitAndLoss_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(2).get("id");
        JsonPath data_1 = dataProfitAndLoss.getAPIProfitAndLoss(kq);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String, Object> dataApi_1 = data_1.get("data");
        String dataDb = Redis.getString(15, "markowitz_optimal_strategy");
        // fomat date
        // Example date in dd/MM/yyyy format
        Set<String> formattedDate = ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> LocalDate.parse(x.get("date").toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).collect(Collectors.toSet());
        for (int i = 0; i < dataApi.size(); i++) {
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("symbol")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("symbol")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("cost_price")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("cost_price")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("profit_percent")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("profit_percent")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("quantity")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("quantity")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("profit")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("profit")).collect(Collectors.toSet())
            );
        }
        for (int i = 0; i < dataApi.size(); i++) {
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("symbol")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("symbol")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("cost_price")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("cost_price")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("place_price")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("place_price")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("quantity")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("quantity")).collect(Collectors.toSet())
            );
            Assert.assertEquals(formattedDate, JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("date")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("profit")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("profit")).collect(Collectors.toSet())
            );
        }
    }

    @Test
    public void TC_02_givenBrokerInfoID1_whenGetDataProfitAndLoss_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = dataProfitAndLoss.getAPIProfitAndLoss(kq);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String, Object> dataApi_1 = data_1.get("data");
        String dataDb = Redis.getString(15, "turtle_trading_strategy");
        // fomat date
        // Example date in dd/MM/yyyy format
        Set<String> formattedDate = ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> LocalDate.parse(x.get("date").toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).collect(Collectors.toSet());
        for (int i = 0; i < dataApi.size(); i++) {
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("symbol")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("symbol")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("profit_percent")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("profit_percent")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("quantity")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("quantity")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("profit")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("profit")).collect(Collectors.toSet())
            );
        }
        for (int i = 0; i < dataApi.size(); i++) {
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("symbol")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("symbol")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("quantity")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("quantity")).collect(Collectors.toSet())
            );
            Assert.assertEquals(formattedDate, JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("date")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("profit")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("profit")).collect(Collectors.toSet())
            );
        }
    }

    @Test
    public void TC_03_givenBrokerInfoID2_whenGetDataProfitAndLoss_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(1).get("id");
        JsonPath data_1 = dataProfitAndLoss.getAPIProfitAndLoss(kq);
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
        HashMap<String, Object> dataApi_1 = data_1.get("data");
        String dataDb = Redis.getString(15, "ma_rsi_strategy");
        // fomat date
        // Example date in dd/MM/yyyy format
        Set<String> formattedDate = ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> LocalDate.parse(x.get("date").toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).collect(Collectors.toSet());
        for (int i = 0; i < dataApi.size(); i++) {
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("symbol")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("symbol")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("profit_percent")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("profit_percent")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("quantity")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("quantity")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("open_deals")).stream().map(x -> x.get("profit")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("open_deals").stream().map(x -> ((Map<String, Object>) x).get("profit")).collect(Collectors.toSet())
            );
        }
        for (int i = 0; i < dataApi.size(); i++) {
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("symbol")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("symbol")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("quantity")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("quantity")).collect(Collectors.toSet())
            );
            Assert.assertEquals(formattedDate, JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("date")).collect(Collectors.toSet())
            );
            Assert.assertEquals(
                    ((ArrayList<Map<String, Object>>) dataApi_1.get("closed_deals")).stream().map(x -> x.get("profit")).collect(Collectors.toSet()),
                    JsonPath.from(dataDb).getList("closed_deals").stream().map(x -> ((Map<String, Object>) x).get("profit")).collect(Collectors.toSet())
            );
        }
    }

    @Test
    public void TC_04_givenInputIDNotExits_whenGetDataProfitAndLoss_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = dataProfitAndLoss.getAPIProfitAndLossInvalid(188);
        Assert.assertEquals(data_1.get("message"), "Không thể tìm thấy thông tin của môi giới với mã 188, vui lòng thử lại");
        Assert.assertEquals(data_1.get("error_code"), "BROKER_001");
    }

    @Test
    public void TC_05_givenInputIDNull_whenGetDataProfitAndLoss_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data = broker.getAPIListBroker();
        List<Map<String, Object>> dataApi = data.getList("data");
        Integer kq = (Integer) dataApi.get(0).get("id");
        JsonPath data_1 = dataProfitAndLoss.getAPIProfitAndLossInvalid(null);
        Assert.assertEquals(data_1.get("title"), "Bad Request");
    }
}
