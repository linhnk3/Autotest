package tradinggold;

import finhay.api.goldService.GetHistoryTransaction;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHistoryTransaction {
    GetHistoryTransaction historyTransaction = new GetHistoryTransaction();
 @Test
    public void TC_1_Get_History_Transaction(){

     Assert.assertEquals("success",historyTransaction.GetTransactionHistory());
 }
}
