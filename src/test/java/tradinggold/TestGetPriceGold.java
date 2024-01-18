package tradinggold;


import finhay.api.goldService.GetPriceGold;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetPriceGold {
 GetPriceGold getPriceGold = new GetPriceGold();
    @Test
    public void TC_1_GetPriceGold(){
        Assert.assertEquals(getPriceGold.GetBidPriceInAPI(),getPriceGold.getBidPriceInDB());
        Assert.assertEquals(getPriceGold.GetAskPriceInAPI(),getPriceGold.getAskPriceInDB());

    }
}
