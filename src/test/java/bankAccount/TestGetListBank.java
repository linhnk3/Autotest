package bankAccount;

import finhay.api.bankAccount.GetListBank;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetListBank {
GetListBank listBank = new GetListBank();
@Test
    public void TC_01_Get_List_Bank_Success(){
  Assert.assertEquals(listBank.getAPIListBank(),listBank.getAmountBankList());
}
}

