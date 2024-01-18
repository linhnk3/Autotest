package news;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.news.GetListNews;
import vnscbyfinhay.api.news.GetNewestNews;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class TestGetNewestNews {
    GetNewestNews news= new GetNewestNews();
    @Test
    public void TC_O1_Check_Correct_Newest_News_With_Stock_Exits_News() throws Exception {
        JsonPath data = news.getAPINewestNews("DHT");
        String result = data.getMap("result").toString();
        System.out.println("Newest news:\n" +result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
    }

    @Test
    public void TC_O2_Check_Correct_Newest_News_With_Stock_Not_Exits_News() throws Exception {
        JsonPath data = news.getAPINewestNews("HPG");
        Assert.assertEquals((String) data.get("result"), null);
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
    }

    @Test
    public void TC_O3_Check_InCorrect_List_News_With_Stock_Null() throws Exception {
        JsonPath data = news.getAPINewestNews(null);
        Assert.assertEquals((String) data.get("result"), null);
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
    }
}
