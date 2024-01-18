package bankAccount;

import finhay.api.bankAccount.GetInfoBankAccount;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetInfoBankAccount {
GetInfoBankAccount info = new GetInfoBankAccount();
@Test
    public void TC_O1_Check_Correct_Bank_Account(){
    Assert.assertEquals(String.valueOf(info.getInfoBankAccountCorrect()),"true");
}

    @Test
    public void TC_O2_Check_Input_Correct_Bank_Account_But_Not_Exist_in_DB(){
        Assert.assertEquals(String.valueOf(info.getInfoBankAccountNotExist()),"false");
    }
    @Test
    public void TC_O3_Check_Incorrect_Account_Number(){
        Assert.assertEquals(String.valueOf(info.getInfoBankAccountWrongAccountNumber()),"Lỗi xảy ra khi tra cứu người dùng trên VCCB");
    }
}
