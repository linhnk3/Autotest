package watchList;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.watchlist.GetDetailWatchlist;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestGetDetailWatchList {
    GetDetailWatchlist watchlist= new GetDetailWatchlist();
    @Test
    public void TC_O1_Check_Correct_Detail_WatchList_With_ID_Exits() throws Exception {
        JsonPath data = watchlist.getAPIDetailWatchlist(205);
        List<HashMap<String, Object>> result = (List<HashMap<String, Object>>) data.getMap("result").get("items");
        Set<Integer> watchlistItemIds = result.stream().map(x -> Integer.valueOf(x.get("id").toString())).collect(Collectors.toSet());
        Assert.assertFalse(watchlistItemIds.isEmpty());
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
        List<Integer> id = watchlist.getDetailWatchlistById(205);
        for (Object x : watchlistItemIds) {
            Assert.assertTrue(id.contains(x));
        }
    }
    
    @Test
    public void TC_O2_Check_Correct_Detail_WatchList_With_Id_Not_Exits() throws Exception {
        JsonPath data = watchlist.getAPIDetailWatchlist(100000000);
        Assert.assertEquals(data.get("message"), "Watchlist không tồn tại");
        Assert.assertEquals((String) data.get("result"), null);
        Assert.assertEquals(data.get("error_code"), "WATCHLIST_NOT_EXIST");
    }

    @Test
    public void TC_O3_Check_InCorrect_Detail_WatchList_With_Id_Null() throws Exception {
        JsonPath data = watchlist.getAPIDetailWatchlistWithIDNull(null);
        Assert.assertEquals(data.get("message"), "Internal server error");
        Assert.assertEquals(data.get("error_code"), (Object) 500);
    }
}