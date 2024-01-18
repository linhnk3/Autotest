package news;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.news.GetListNews;

import java.util.LinkedHashMap;
import java.util.List;

public class TestGetListNews {
    GetListNews news= new GetListNews();
    @Test
    public void TC_O1_Check_Correct_List_News_With_Stock_Exits_News() throws Exception {
        JsonPath data = news.getAPIListNews("DHT");
        List<Object> result = data.getList("result");
        System.out.println("List news user:\n" +result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
        List<String> paths = news.getListNewsByStock("DHT");
        result.forEach(r -> {
            LinkedHashMap<String, Object> rr = (LinkedHashMap) r;
            System.out.println(rr.get("path"));
            Assert.assertTrue(paths.contains(rr.get("path")));
        });
    }
    @Test
    public void TC_O2_Check_Correct_List_News_With_Stock_Not_Exits_News() throws Exception {
        JsonPath data = news.getAPIListNews("HPG");
        List<Object> result = data.getList("result");
        Assert.assertTrue(result.isEmpty());
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
    }

    @Test
    public void TC_O3_Check_InCorrect_List_News_With_Stock_Null() throws Exception {
        JsonPath data = news.getAPIListNews(null);
        List<Object> result = data.getList("result");
        Assert.assertTrue(result.isEmpty());
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
    }
}
