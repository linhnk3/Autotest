package news;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.news.GetDetailNews;


public class TestGetDetailNews {
    GetDetailNews news= new GetDetailNews();
    @Test
    public void TC_O1_Check_Correct_Detail_News_With_ID_Exits() throws Exception {
        JsonPath data = news.getAPIDetailNews(100);
        String result = data.getMap("result").toString();
        System.out.println("Detail news:\n" + result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
    }
    @Test
    public void TC_O2_Check_Correct_Detail_News_With_Id_Not_Exits() throws Exception {
        JsonPath data = news.getAPIDetailNews(100000000);
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals((String) data.get("result"), null);
        Assert.assertEquals(data.get("error_code"), "0");
    }

    @Test
    public void TC_O3_Check_InCorrect_Detail_News_With_Id_Null() throws Exception {
        JsonPath data = news.getAPIDetailNews(null);
        Assert.assertEquals(data.get("message"), "Id must be number");
        Assert.assertEquals(data.get("error_code"), "400");
    }
}
