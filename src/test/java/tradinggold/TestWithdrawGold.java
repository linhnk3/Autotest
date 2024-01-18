package tradinggold;

import constants.BodyApi;
import constants.configPath;
import finhay.api.goldService.PostSellGold;
import finhay.api.goldService.PostWithdrawGold;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ultilities.ReportLogListener;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class TestWithdrawGold {
    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());
    PostWithdrawGold withdraw = new PostWithdrawGold();
    PostSellGold sellGold = new PostSellGold();

    @Test
    public void TC_01_Withdraw_Gold_All_Type_Success() {
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawAllTypeSuccess(1873), "success");
        ReportLog.info("Call api withdraw all type and compare message response");
        Assert.assertEquals(String.valueOf(withdraw.getRequestMaceInDB()), String.valueOf("2"));
        ReportLog.info("Compare request mace withdraw  ");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_UNDUE");
        ReportLog.info("Compare request status ");
    }

    @Test
    public void TC_02_Withdraw_Gold_Type_Gold24K_Success() {
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawTypeGold24KSuccess(1873), "success");
        ReportLog.info("Call api withdraw all type and compare message response");
        Assert.assertEquals(String.valueOf(withdraw.getRequestMaceInDB()), String.valueOf("1"));
        ReportLog.info("Compare request mace withdraw  ");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_UNDUE");
        ReportLog.info("Compare request status ");
    }
   @Test
    public void TC_03_Withdraw_Gold_Type_Gold24K_One_Mace_Success() {
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawTypeGold24KOneMaceSuccess(1873), "success");
        ReportLog.info("Call api withdraw all type and compare message response");
        Assert.assertEquals(String.valueOf(withdraw.getRequestMaceInDB()), String.valueOf("1"));
        ReportLog.info("Compare request mace withdraw  ");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_UNDUE");
        ReportLog.info("Compare request status ");
    }
    @Test
    public void TC_04_Withdraw_Gold_Type_Gold24K_One_Mace_No_Enough_Gold() throws SQLException {
        withdraw.updateAmountGoldTypeOneMaceUser38043();
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawNoEnoughGold(38043), "Một trong các loại vàng bạn đang có không đủ số lượng để rút.");
        ReportLog.info("Call api withdraw all type and compare message response");

    }
    @Test
    public void TC_05_Withdraw_Gold_Type_Gold24K_No_Enough_Gold() throws SQLException {
        withdraw.updateAmountGoldType24KUser38043();
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawNoEnoughGold(38043), "Một trong các loại vàng bạn đang có không đủ số lượng để rút.");
        ReportLog.info("Call api withdraw all type and compare message response");

    }
   @Test
    public void TC_06_Withdraw_Gold_2_Time_But_Not_Enough_Gold() throws SQLException {
        withdraw.updateGoldType24KUser38043AmountIs2();
        withdraw.deleteOrderItemWithdraw();
        withdraw.deleteWithdrawOrderUser38043();
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawTypeGold24KSuccess(38043), "success");
        ReportLog.info("Call api withdraw all type and compare message response");
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawNoEnoughGold(38043), "Một trong các loại vàng bạn đang có không đủ số lượng để rút.");
        withdraw.deleteOrderItemWithdraw();
        withdraw.deleteWithdrawOrderUser38043();

    }
    @Test
    public void TC_07_Withdraw_Gold_After_Sell_Gold_But_Not_Enough_Gold() throws SQLException {
        withdraw.updateGoldType24KUser38043AmountIs2();
       withdraw.deleteOrderItemWithdraw();
        withdraw.deleteWithdrawOrderUser38043();
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawTypeGold24KSuccess(38043), "success");
        ReportLog.info("Call api withdraw all type and compare message response");
        Assert.assertEquals(sellGold.getMessageAPIPostSellGoldFail(38043), "Số lượng vàng của bạn không đủ để bán.");
        withdraw.deleteOrderItemWithdraw();
        withdraw.deleteWithdrawOrderUser38043();

    }
    @Test
    public void TC_07_Withdraw_Gold_Change_Status() throws SQLException {
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawTypeGold24KSuccess(1873), "success");
        ReportLog.info("Call api withdraw all type and compare message response");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_UNDUE");
        ReportLog.info("Compare request status ");
        withdraw.updateRequestTimeNow();
        withdraw.runJobChangeStatusSubmitUndueToSubmitDue();
        ReportLog.info("Change job from submit undue to submit due  ");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_DUE");
        withdraw.runJobChangeStatusSubmitDueToShopConfirmed();
        ReportLog.info("Change job from submit due to shop confirmed  ");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(),"SHOP_CONFIRMED");
        withdraw.runJobChangeStatusFromShopConfirmedToCompleted(1873);
        ReportLog.info("Change job from shop confirmed  to completed  ");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(),"COMPLETED");
    }
    @Test
    public void TC_08_Withdraw_Gold_Change_Status_Submit_OverDue() throws SQLException {
        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawTypeGold24KSuccess(1873), "success");
        ReportLog.info("Call api withdraw all type and compare message response");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_UNDUE");
        ReportLog.info("Compare request status ");
        withdraw.updateRequestTimeNow();
        withdraw.runJobChangeStatusSubmitUndueToSubmitDue();
        ReportLog.info("Change job from submit undue to submit due  ");
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_DUE");
        withdraw.updateExpiredDateNow();
        withdraw.runJobChangeStatusSubmitDueToOverDue();
        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_OVERDUE");

    }
//    @Test
//    public void TC_09_Withdraw_Gold_Change_Status_From_Shop_Confirm_To_OverDue() throws SQLException {
//        Assert.assertEquals(withdraw.getMessageAPIPostWithdrawTypeGold24KSuccess(1873), "success");
//        ReportLog.info("Call api withdraw all type and compare message response");
//        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_UNDUE");
//        ReportLog.info("Compare request status ");
//        withdraw.updateRequestTimeNow();
//        withdraw.runJobChangeStatusSubmitUndueToSubmitDue();
//        ReportLog.info("Change job from submit undue to submit due  ");
//        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(), "SUBMITTED_DUE");
//        withdraw.runJobChangeStatusSubmitDueToShopConfirmed();
//        ReportLog.info("Change job from submit due to shop confirmed  ");
//        Assert.assertEquals(withdraw.getStatusWithdrawGoldInDB(),"SHOP_CONFIRMED");
//        withdraw.runJobChangeStatusFromShopConfirmedToCompleted(1873);
//    }
}