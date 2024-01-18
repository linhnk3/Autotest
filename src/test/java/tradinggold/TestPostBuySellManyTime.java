package tradinggold;

import finhay.api.goldService.BuySellWithdrawGoldManyTime;
import finhay.api.goldService.PostSellGold;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ultilities.ReportLogListener;

@Test
public class TestPostBuySellManyTime {
    BuySellWithdrawGoldManyTime buySellWithdrawGoldManyTime = new BuySellWithdrawGoldManyTime();
    PostSellGold sellGold = new PostSellGold();
    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());
    public void TC_01_Buy_Many_Time() throws InterruptedException {
        int firstInventory24K = sellGold.getQuantityInventoryGold24KInDB();
        int firstInventoryOneMace = sellGold.getQuantityInventoryGold24OneMaceKInDB();
        ReportLog.info("1. Get quantity in Inventory table gold 24K " + firstInventory24K);
        ReportLog.info("1. Get quantity in Inventory table gold 24K one mace " + firstInventoryOneMace);
        int firstSumQuantityGold24K = buySellWithdrawGoldManyTime.getStatusTransactionBuyGold24KInDB();
        int firstSumQuantityGold24KOneMace = buySellWithdrawGoldManyTime.getStatusTransactionBuyGoldOneMaceInDB();
        ReportLog.info("1. Get first sum quantity 24K" + firstSumQuantityGold24K);
        ReportLog.info("2. Get first sum quantity one mace  " + firstSumQuantityGold24KOneMace);
        buySellWithdrawGoldManyTime.CallAPiBuyGold();
        ReportLog.info("3. Call api buy gold many time");
        int lastSumQuantityGold24K = buySellWithdrawGoldManyTime.getStatusTransactionBuyGold24KInDB();
        int lastSumQuantityGold24KOneMace = buySellWithdrawGoldManyTime.getStatusTransactionBuyGoldOneMaceInDB();
        ReportLog.info("4. Last sum quantity 24K : " + lastSumQuantityGold24K);
        ReportLog.info("4. Last sum quantity one mace : " + lastSumQuantityGold24KOneMace);
        int result24K = buySellWithdrawGoldManyTime.getStatusTransactionBuyGold24KInDB() - firstSumQuantityGold24K ;
        int resultOneMace = buySellWithdrawGoldManyTime.getStatusTransactionBuyGoldOneMaceInDB() - firstSumQuantityGold24KOneMace ;
        ReportLog.info("4. Result is 24K " + result24K);
        ReportLog.info("4. Result is one mace " + resultOneMace);
        Assert.assertEquals(String.valueOf(sellGold.getQuantityInventoryGold24KInDB()),String.valueOf(firstInventory24K - result24K));
        Assert.assertEquals(String.valueOf(sellGold.getQuantityInventoryGold24OneMaceKInDB()),String.valueOf(firstInventoryOneMace - resultOneMace));
        ReportLog.info("5. Compare after calling api to buy gold");


    }

    // @Test
    public void TC_02_Sell_Many_Time() throws InterruptedException {
        int firstInventory24K = sellGold.getQuantityInventoryGold24KInDB();
        int firstInventoryOneMace = sellGold.getQuantityInventoryGold24OneMaceKInDB();
        ReportLog.info("1. Get quantity in Inventory table gold 24K " + firstInventory24K);
        ReportLog.info("1. Get quantity in Inventory table gold 24K one mace " + firstInventoryOneMace);
        int firstSumQuantityGold24K = buySellWithdrawGoldManyTime.getStatusTransactionSellGoldInDBGoldType24K();
        int firstSumQuantityGold24KOneMace = buySellWithdrawGoldManyTime.getStatusTransactionSellGoldInDBGoldType24KOneMace();
        ReportLog.info("1. Get first sum quantity 24K" + firstSumQuantityGold24K);
        ReportLog.info("2. Get first sum quantity one mace  " + firstInventoryOneMace);
        buySellWithdrawGoldManyTime.CallAPISellGold();
        ReportLog.info("3. Call api sell gold many time");
        int lastSumQuantityGold24K = buySellWithdrawGoldManyTime.getStatusTransactionSellGoldInDBGoldType24K();
        int lastSumQuantityGold24KOneMace = buySellWithdrawGoldManyTime.getStatusTransactionSellGoldInDBGoldType24KOneMace();

        ReportLog.info("4. Last sum quantity 24K : " + lastSumQuantityGold24K);
        ReportLog.info("4. Last sum quantity one mace : " + lastSumQuantityGold24KOneMace);
        int result24K = buySellWithdrawGoldManyTime.getStatusTransactionSellGoldInDBGoldType24K() - firstSumQuantityGold24K;
        int resultOneMace = buySellWithdrawGoldManyTime.getStatusTransactionSellGoldInDBGoldType24KOneMace() - firstSumQuantityGold24KOneMace;
        ReportLog.info("4. Result is 24K " + result24K);
        ReportLog.info("4. Result is one mace " + resultOneMace);
        Assert.assertEquals(String.valueOf(sellGold.getQuantityInventoryGold24KInDB()), String.valueOf(firstInventory24K + result24K));
        Assert.assertEquals(String.valueOf(sellGold.getQuantityInventoryGold24OneMaceKInDB()), String.valueOf(firstInventoryOneMace + resultOneMace));
        ReportLog.info("5. Compare after calling api to sell gold");
    }
        @Test
        public void TC_03_Withdraw_Many_Time() {
            int firstSumStatusUndue = buySellWithdrawGoldManyTime.getSumStatusWithdrawGoldSubmitUndueInDB();
            buySellWithdrawGoldManyTime.CallAPIWithdrawGold();
            int sumStatusAfterCallAPI = buySellWithdrawGoldManyTime.getSumStatusWithdrawGoldSubmitUndueInDB() - firstSumStatusUndue ;
            Assert.assertEquals(String.valueOf(buySellWithdrawGoldManyTime.getSumStatusWithdrawGoldSubmitUndueInDB()),String.valueOf(firstSumStatusUndue + sumStatusAfterCallAPI));
        }







}
