package bankAccount;

import finhay.api.bankAccount.PostAddBankAccount;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestPostBankAccount {
    PostAddBankAccount addBank = new PostAddBankAccount();
    @Test
    public void TC_01_Add_Bank_Account_Success(){
        Assert.assertEquals(addBank.CallAPISaveBankAccountSuccess(),"Success");
        Assert.assertEquals(addBank.getAccountNumberUserID38327(),"123433333");
    }
    @Test
    public void TC_02_Add_Bank_Account_Unsuccessful() throws SQLException {
        Assert.assertEquals(addBank.CallAPISaveBankAccountSuccess(),"Không thể thay đổi tài khoản hợp lệ. Xin vui lòng liên hệ bên hỗ trợ");
        addBank.deleteBankAccountUserID38327();
    }

}
