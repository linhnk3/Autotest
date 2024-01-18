package ratingApp;

import finhay.api.ratingapp.RatingApp;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ultilities.ReportLogListener;

import java.sql.SQLException;

public class TestRatingApp {
    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());
    RatingApp rating = new RatingApp();

     @Test
    public void TC1_User_No_Data() {
        ReportLog.info("User no have data in finhaydb.events_rating");
        Assert.assertEquals(rating.CallApiRatingApp(37412), null);
    }
     @Test
    public void TC2_User_Have_Data_But_Out_Of_Date() {
        ReportLog.info("User no have data in finhaydb.events_rating");
        Assert.assertEquals(rating.CallApiRatingApp(2076), null);
    }
    @Test
    public void TC3_User_Have_Data_Cash_Back() throws SQLException {
        ReportLog.info("User no have data in finhaydb.events_rating in column cashback_success_at <=24h");
        rating.update_CashBack_Success_At_User_1153();
        Assert.assertEquals(rating.CallApiRatingAppHaveResult(1153), "Bạn đánh giá trải nghiệm Hoàn tiền tại Finhay như thế nào?");
    }

    @Test
    public void TC4_User_Have_Data_Payout() throws SQLException {
        ReportLog.info("User no have data in finhaydb.events_rating in column payout_success_at <=24h");
        rating.update_Payout_Success_At_User_1873();
        Assert.assertEquals(rating.CallApiRatingAppHaveResult(1873), "Bạn đánh giá trải nghiệm sử dụng Tích lũy tại Finhay như thế nào?");
    }
    @Test
    public void TC5_User_Have_Inviting_friend() throws SQLException {
        ReportLog.info("User no have data in finhaydb.events_rating in column inviting_friend_success_at <=24h");
        rating.update_Inviting_Friend_Success_At_User_2045();
        Assert.assertEquals(rating.CallApiRatingAppHaveResult(2045), "Bạn đánh giá trải nghiệm Giới thiệu bạn bè tại Finhay như thế nào?");
    }
}