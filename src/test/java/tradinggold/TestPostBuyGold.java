package tradinggold;


import finhay.api.goldService.PostBuyGold;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ultilities.ReportLogListener;

import java.sql.SQLException;


public class TestPostBuyGold {
  PostBuyGold postBuyGold = new PostBuyGold();
    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());

   // @Test
    public void TC1_Buy_Gold_Success_Gold_One_Mace() {
          int inventoryFirst = postBuyGold.getQuantityInventoryGoldOneMace();
        ReportLog.info("get quantity inventory gold one mace " + inventoryFirst);
      int goldBagsFirst = postBuyGold.getQuantityUserGoldBagsGoldOneMace();
        ReportLog.info("get quantity gold one mace user gold bags " + goldBagsFirst);
        Assert.assertEquals(postBuyGold.GetMessageBuyGold(1873),"success");
        ReportLog.info("get message when call api ");
        Assert.assertEquals(postBuyGold.getStatusOrderInDB(),"COMPLETED");
      ReportLog.info("Compare status");
        Assert.assertEquals(String.valueOf(postBuyGold.getQuantityUserGoldBagsGoldOneMace()),String.valueOf(inventoryFirst - 1));
      ReportLog.info("Compare inventory gold one mace");
        Assert.assertEquals(String.valueOf(postBuyGold.getQuantityUserGoldBagsGoldOneMace()),String.valueOf(goldBagsFirst + 1));
      ReportLog.info("Compare user gold bag");
    }

    //@Test
    public void TC2_Buy_Gold_Success_All_Type() {
      int inventoryOneMaceFirst = postBuyGold.getQuantityInventoryGoldOneMace();
      int inventory24kFirst = postBuyGold.getQuantityInventoryGold24K();
      ReportLog.info("get quantity inventory gold one mace " + inventoryOneMaceFirst);
      ReportLog.info("get quantity inventory gold 24K " + inventory24kFirst);
      int goldBagsOneMaceFirst = postBuyGold.getQuantityUserGoldBagsGoldOneMace();
      int goldBags24kFirst = postBuyGold.getQuantityUserGoldBagsGold24K();
      ReportLog.info("get quantity gold one mace user gold bags " + goldBagsOneMaceFirst);
      ReportLog.info("get quantity gold one mace user gold bags " + goldBags24kFirst);
        Assert.assertEquals(postBuyGold.GetMessageBuyGoldAllType(1873),"success");
      Assert.assertEquals(postBuyGold.getStatusOrderInDB(),"COMPLETED");
      ReportLog.info("Compare status");
      Assert.assertEquals(String.valueOf(postBuyGold.getQuantityInventoryGoldOneMace()),String.valueOf(inventoryOneMaceFirst - 1));
      ReportLog.info("Compare inventory gold one mace");
      Assert.assertEquals(String.valueOf(postBuyGold.getQuantityUserGoldBagsGoldOneMace()),String.valueOf(goldBagsOneMaceFirst + 1));
      ReportLog.info("Compare user gold bag one mace");
      Assert.assertEquals(String.valueOf(postBuyGold.getQuantityInventoryGold24K()),String.valueOf(inventory24kFirst - 1));
      ReportLog.info("Compare inventory gold 24k");
      Assert.assertEquals(String.valueOf(postBuyGold.getQuantityUserGoldBagsGold24K()),String.valueOf(goldBags24kFirst + 1));
      ReportLog.info("Compare user gold bag gold 24k");
    }


   // @Test
    //inventory_id = 0 out of gold
    public void TC3_Only_Buy_Gold_Type_24K() throws SQLException {
    postBuyGold.updateInventoryGoldTypeOneMaceReturn0();
        int inventory24kFirst = postBuyGold.getQuantityInventoryGold24K();
        ReportLog.info("get quantity inventory gold 24K " + inventory24kFirst);
        int goldBags24kFirst = postBuyGold.getQuantityUserGoldBagsGold24K();
        ReportLog.info("get quantity gold one mace user gold bags " + goldBags24kFirst);
           Assert.assertEquals(postBuyGold.GetMessageBuyGold(1873),"success");
        Assert.assertEquals(postBuyGold.getStatusOrderInDB(),"COMPLETED");
        ReportLog.info("Compare status");
        Assert.assertEquals(String.valueOf(postBuyGold.getQuantityInventoryGold24K()),String.valueOf(inventory24kFirst - 2));
        ReportLog.info("Compare inventory gold 24k");
        Assert.assertEquals(String.valueOf(postBuyGold.getQuantityUserGoldBagsGold24K()),String.valueOf(goldBags24kFirst + 2));
        ReportLog.info("Compare user gold bag gold 24k");
        postBuyGold.updateInventoryGoldTypeOneMaceTo100();
    }
   // @Test
    public void TC4_Buy_Gold_No_Input_Amount(){
        Assert.assertEquals(postBuyGold.GetMessageUserNoInputAmountGold(1873),"Số chỉ bạn mua không thể nhỏ hơn số lượng tối thiểu của một giao dịch");
    }
   // @Test
    public void TC5_Buy_Gold_Over_Asset_COD(){
        Assert.assertEquals(postBuyGold.GetStatusGoldOverAssetCOD(37342),"COD_USER_ASSET_NOT_ENOUGH");
    }
   // @Test
    public void TC6_Buy_Gold_When_Inventory_Out_Of_Gold() throws SQLException {
        postBuyGold.updateInventoryGoldTypeOneMaceReturn0();
        postBuyGold.updateInventoryGoldType24KReturn0();
        Assert.assertEquals(postBuyGold.GetMessageBuyGoldOutOfGold(37342),"Kho vàng Finhay tạm thời hết hàng.Vui lòng quay trở lại sau.");
    }
  //  @Test
    public void TC7_Buy_Gold_Over_Gold_Quantity_Inventory() throws SQLException {
postBuyGold.updateInventoryGoldType24KReturn1();
postBuyGold.updateInventoryGoldTypeOneMaceReturn1();
        int inventoryOneMaceFirst = postBuyGold.getQuantityInventoryGoldOneMace();
        int inventory24kFirst = postBuyGold.getQuantityInventoryGold24K();
        ReportLog.info("get quantity inventory gold one mace " + inventoryOneMaceFirst);
        ReportLog.info("get quantity inventory gold 24K " + inventory24kFirst);
        int goldBagsOneMaceFirst = postBuyGold.getQuantityUserGoldBagsGoldOneMace();
        int goldBags24kFirst = postBuyGold.getQuantityUserGoldBagsGold24K();
        ReportLog.info("get quantity gold one mace user gold bags " + goldBagsOneMaceFirst);
        ReportLog.info("get quantity gold one mace user gold bags " + goldBags24kFirst);
       Assert.assertEquals(postBuyGold.GetMessageBuyGoldOutOfGold(1873),"success");
        Assert.assertEquals(postBuyGold.getStatusOrderInDB(),"COMPLETED");
        ReportLog.info("Compare status");
        Assert.assertEquals(String.valueOf(postBuyGold.getQuantityInventoryGoldOneMace()),String.valueOf(inventoryOneMaceFirst - 1));
        ReportLog.info("Compare inventory gold one mace");
        Assert.assertEquals(String.valueOf(postBuyGold.getQuantityUserGoldBagsGoldOneMace()),String.valueOf(goldBagsOneMaceFirst + 1));
        ReportLog.info("Compare user gold bag one mace");
        Assert.assertEquals(String.valueOf(postBuyGold.getQuantityInventoryGold24K()),String.valueOf(inventory24kFirst - 1));
        ReportLog.info("Compare inventory gold 24k");
        Assert.assertEquals(String.valueOf(postBuyGold.getQuantityUserGoldBagsGold24K()),String.valueOf(goldBags24kFirst + 1));
        ReportLog.info("Compare user gold bag gold 24k");
    }
    @Test
    public void TC8_Buy_Gold_Over_Limit_Per_Day() throws SQLException {
Assert.assertEquals(postBuyGold.GetMessageBuyGoldOverLimitPerDay(1153),"Số lượng bạn mua vượt quá hạn mức giao dịch trong ngày");
    }
}
