package watchList;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.watchlist.GetDetailWatchlist;
import vnscbyfinhay.api.watchlist.GetListWatchlist;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestGetDetailWatchList {
    GetListWatchlist listWatchlist= new GetListWatchlist();
    GetDetailWatchlist watchlist= new GetDetailWatchlist();
    @Test
    public void TC_O1_Check_Correct_Detail_WatchList_Has_Stock_With_ID_Exits() throws Exception {
        JsonPath data = listWatchlist.getAPIListWatchlist();
        List<Object> result = data.getList("result.id");
        Integer id_watchlist = (Integer) result.get(1);
        JsonPath data_1 = watchlist.getAPIDetailWatchlist(id_watchlist);
        List<HashMap<String, Object>> result_1 = (List<HashMap<String, Object>>) data_1.getMap("result").get("items");
        Set<Integer> watchlistItemIds = result_1.stream().map(x -> Integer.valueOf(x.get("id").toString())).collect(Collectors.toSet());
        Assert.assertFalse(watchlistItemIds.isEmpty());
        Assert.assertEquals(data_1.get("message"), "success");
        Assert.assertEquals(data_1.get("error_code"), "0");
        List<Integer> id = watchlist.getDetailWatchlistById(id_watchlist);
        for (Object x : watchlistItemIds) {
            Assert.assertTrue(id.contains(x));
        }
    }

    @Test
    public void TC_O2_Check_Correct_Detail_WatchList_No_Stock_With_ID_Exits() throws Exception {
        JsonPath data = listWatchlist.getAPIListWatchlist();
        List<Object> result = data.getList("result.id");
        Integer id_watchlist = (Integer) result.get(0);
        JsonPath data_1 = watchlist.getAPIDetailWatchlist(id_watchlist);
        List<HashMap<String, Object>> result_1 = (List<HashMap<String, Object>>) data_1.getMap("result").get("items");
        Set<Integer> watchlistItemIds = result_1.stream().map(x -> Integer.valueOf(x.get("id").toString())).collect(Collectors.toSet());
        Assert.assertTrue(watchlistItemIds.isEmpty());
        Assert.assertEquals(data_1.get("message"), "success");
        Assert.assertEquals(data_1.get("error_code"), "0");
        List<Integer> id = watchlist.getDetailWatchlistById(id_watchlist);
        for (Object x : watchlistItemIds) {
            Assert.assertTrue(id.contains(x));
        }
    }
    
    @Test
    public void TC_O3_Check_Correct_Detail_WatchList_With_Id_Not_Exits() throws Exception {
        JsonPath data = watchlist.getAPIDetailWatchlist(100000000);
        Assert.assertEquals(data.get("message"), "Watchlist không tồn tại");
        Assert.assertEquals((String) data.get("result"), null);
        Assert.assertEquals(data.get("error_code"), "WATCHLIST_NOT_EXIST");
    }

    @Test
    public void TC_O4_Check_InCorrect_Detail_WatchList_With_Id_Null() throws Exception {
        JsonPath data = watchlist.getAPIDetailWatchlistWithIDNull(null);
    }
}