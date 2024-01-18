package watchList;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.watchlist.CreateWatchlist;

import java.util.List;

public class TestCreateWatchList {
    CreateWatchlist watchlist = new CreateWatchlist();
    @Test
    public void TC1_Create_Watchlist_Success() throws Exception {
        JsonPath data= watchlist.createAPIWatchlistValid();
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
    }
    @Test
    public void TC2_Create_Watchlist_With_Name_Existed() throws Exception {
        JsonPath data= watchlist.createAPIWatchlistInvalid();
        Assert.assertEquals(data.get("message"), "Duplicate entry");
        Assert.assertEquals(data.get("error_code").toString(), "400");
    }
    @Test
    public void TC3_Create_Watchlist_With_Name_Empty() throws Exception {
        JsonPath data= watchlist.createAPIWatchlistWithNameEmpty();
        Assert.assertEquals(data.get("message"), "tên không hợp lệ");
        Assert.assertEquals(data.get("error_code").toString(), "400");
    }
    @Test
    public void TC4_Create_Watchlist_With_Name_Null() throws Exception {
        JsonPath data= watchlist.createAPIWatchlistWithNameNull();
        Assert.assertEquals(data.get("message"), "Invalid value");
        Assert.assertEquals(data.get("error_code").toString(), "400");
    }
}
