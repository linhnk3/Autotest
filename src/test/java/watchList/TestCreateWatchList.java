package watchList;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import vnscbyfinhay.api.watchlist.CreateWatchlist;

public class TestCreateWatchList {
    CreateWatchlist watchlist = new CreateWatchlist();
    @Test
    public void TC_01_givenUserIdAndStockList_whenCreateWatchlist_thenExpectedSuccessAndStatusCode200() throws Exception {
        JsonPath data= watchlist.createAPIWatchlistValid();
        Assert.assertEquals(data.get("message"), "success");
        Assert.assertEquals(data.get("error_code"), "0");
    }
    @Test
    public void TC_02_givenUserInputNameExisted_whenCreateWatchlist_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data= watchlist.createAPIWatchlistInvalid();
        Assert.assertEquals(data.get("message"), "Duplicate entry");
        Assert.assertEquals(data.get("error_code").toString(), "400");
    }
    @Test
    public void TC_03_givenUserInputNameEmpty_whenCreateWatchlist_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data= watchlist.createAPIWatchlistWithNameEmpty();
        Assert.assertEquals(data.get("message"), "tên không hợp lệ");
        Assert.assertEquals(data.get("error_code").toString(), "400");
    }
    @Test
    public void TC_04_givenUserInputNameNull_whenCreateWatchlist_thenExpectedFailAndStatusCode400() throws Exception {
        JsonPath data= watchlist.createAPIWatchlistWithNameNull();
        Assert.assertEquals(data.get("message"), "Invalid value");
        Assert.assertEquals(data.get("error_code").toString(), "400");
    }
}
