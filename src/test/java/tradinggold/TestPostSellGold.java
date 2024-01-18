package tradinggold;

import finhay.api.goldService.GetPriceGold;
import finhay.api.goldService.PostSellGold;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ultilities.ReportLogListener;

public class TestPostSellGold {
    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());
    PostSellGold sellGold = new PostSellGold();

      @Test
    public void TC_01_Sell_Gold_24k_Success() {
        int firstInventory = sellGold.getQuantityInventoryGold24KInDB();
        int totalGoldFirst = sellGold.getTotalAmountGoldBagsInDB();
        ReportLog.info("get total amount gold of user" + totalGoldFirst);
        ReportLog.info("get inventory first " + firstInventory);
        ReportLog.info("get price + amount");
        int total = new GetPriceGold().GetBidPriceInAPI() * 1;
        Assert.assertEquals(sellGold.getMessageAPIPostSellGold24KSuccess(1181), "success");
        ReportLog.info("compare status when call api");
        Assert.assertEquals(sellGold.getStatusOrderInDB(), "COMPLETED");
        ReportLog.info("compare inventory when call api");
        Assert.assertEquals(String.valueOf(sellGold.getQuantityInventoryGold24KInDB()), String.valueOf(firstInventory + 2));
        ReportLog.info("compare total amount in cod after call api");
        Assert.assertEquals(String.valueOf(sellGold.getPaidAmountCodTransactionInDB()), String.valueOf(total));
        ReportLog.info("compare total amount gold in user gold bags after call api");
        Assert.assertEquals(String.valueOf(sellGold.getTotalAmountGoldBagsInDB()), String.valueOf(totalGoldFirst - 2));
    }
    @Test
    public void TC_02_Sell_Gold_24k_One_Mace_Success() {
        int firstInventory = sellGold.getQuantityInventoryGold24OneMaceKInDB();
        int totalGoldFirst = sellGold.getTotalAmountGoldBagsInDBUser1873();
        ReportLog.info("get total amount gold of user" + totalGoldFirst);
        ReportLog.info("get inventory first " + firstInventory);
        ReportLog.info("get price + amount");
        int total = new GetPriceGold().GetBidPriceInAPI() * 1;
        Assert.assertEquals(sellGold.getMessageAPIPostSellGold24KOneMaceSuccess(1873), "success");
        ReportLog.info("compare status when call api");
        Assert.assertEquals(sellGold.getStatusOrderInDB(), "COMPLETED");
        ReportLog.info("compare inventory when call api");
        Assert.assertEquals(String.valueOf(sellGold.getQuantityInventoryGold24OneMaceKInDB()), String.valueOf(firstInventory + 1));
        ReportLog.info("compare total amount in cod after call api");
        Assert.assertEquals(String.valueOf(sellGold.getPaidAmountCodTransactionInDBUser1873()), String.valueOf(total));
        ReportLog.info("compare total amount gold in user gold bags after call api");
        Assert.assertEquals(String.valueOf(sellGold.getTotalAmountGoldBagsInDBUser1873()), String.valueOf(totalGoldFirst - 1));
    }
    @Test
    public void TC_03_Sell_Gold_Both_Gold_Type_Success() {
        int firstInventoryOneMace = sellGold.getQuantityInventoryGold24OneMaceKInDB();
        int firstInventory24K = sellGold.getQuantityInventoryGold24KInDB();
        int totalGoldFirst = sellGold.getTotalAmountGoldBagsInDBUser1873();
        ReportLog.info("get total amount gold of user " + totalGoldFirst);
        ReportLog.info("get inventory first gold 24K " + firstInventory24K);
        ReportLog.info("get inventory first gold one mace " + firstInventoryOneMace);
        ReportLog.info("get price + amount");
        int total = (int) (new GetPriceGold().GetBidPriceInAPI() * 1.5);
        Assert.assertEquals(sellGold.getMessageAPIPostSellGoldAllTypeSuccess(1873), "success");
        ReportLog.info("compare status when call api");
        Assert.assertEquals(sellGold.getStatusOrderInDB(), "COMPLETED");
        ReportLog.info("compare inventory when call api");
        Assert.assertEquals(String.valueOf(sellGold.getQuantityInventoryGold24OneMaceKInDB()), String.valueOf(firstInventoryOneMace + 1));
        Assert.assertEquals(String.valueOf(sellGold.getQuantityInventoryGold24KInDB()), String.valueOf(firstInventory24K + 1));
        ReportLog.info("compare total amount in cod after call api");
        Assert.assertEquals(String.valueOf(sellGold.getPaidAmountCodTransactionInDBUser1873()), String.valueOf(total));
        ReportLog.info("compare total amount gold in user gold bags after call api");
        Assert.assertEquals(String.valueOf(sellGold.getTotalAmountGoldBagsInDBUser1873()), String.valueOf(totalGoldFirst - 2));
    }



     @Test
    public void TC_04_User_Have_Not_Gold() {
        Assert.assertEquals(sellGold.getMessageAPIPostSellGoldFail(38214), "Số lượng vàng của bạn không đủ để bán");
        ReportLog.info("compare message when call api");

    }

    @Test
    public void TC_05_User_Sell_Over_Gold() {
        Assert.assertEquals(sellGold.getMessageAPIPostSellOverGold(37342), "Số lượng vàng của bạn không đủ để bán");
        ReportLog.info("compare message when call api sell over amount gold");

    }

    @Test
    public void TC_06_User_Sell_Over_Limit_For_The_Day_Gold() {
        Assert.assertEquals(sellGold.getMessageAPIPostSellOverGoldForTheDay(37342), "Số lượng bạn mua vượt quá hạn mức giao dịch trong ngày");
        ReportLog.info("compare message when call api sell over limit for the day ");

    }

   @Test
    public void TC_07_User_Sell_Gold_Less_Than_Minimum() {
        Assert.assertEquals(sellGold.getMessageAPIPostSellGoldLessThanMinimum(37342), "Số chỉ bạn mua không thể nhỏ hơn số lượng tối thiểu của một giao dịch");
        ReportLog.info("compare message when call api sell less than minimum amount gold ");
    }

   @Test
    public void TC_08_User_Sell_Gold_No_Input_Amount() {
        Assert.assertEquals(sellGold.getMessageAPIPostSellGoldNoInputAmount(37342), "must not be null");
          ReportLog.info("compare message when call api sell gold no input amount ");
    }
}