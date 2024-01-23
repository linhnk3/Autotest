package watchList;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.watchlist.GetListWatchlist;

import java.util.List;

public class TestGetListWatchList {
    GetListWatchlist listWatchlist= new GetListWatchlist();

    @Test
    public void TC_O1_Check_Correct_List_Watchlist_Success() throws Exception {
        JsonPath data = listWatchlist.getAPIListWatchlist();
        List<Object> result = data.getList("result.id");
        System.out.println("List watchlist user:\n" +result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
        List<Integer> id = listWatchlist.getListWatchlistByUser("371");
        for (Object x : result) {
            Assert.assertTrue(id.contains((Integer) x));
        }
    }
    @Test
    public void TC_O2_Check_Correct_List_Watchlist_Default() throws Exception {
        JsonPath data = listWatchlist.getAPIListWatchlistDefault();
        List<Object> result = data.getList("result.id");
        System.out.println("List watchlist user:\n" +result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
        List<Integer> id = listWatchlist.getListWatchlistByUser("371");
        for (Object x : result) {
            Assert.assertTrue(id.contains((Integer) x));
        }
    }
}
