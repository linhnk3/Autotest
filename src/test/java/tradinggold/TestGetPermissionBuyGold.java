package tradinggold;

import finhay.api.goldService.GetPermissionBuyGold;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetPermissionBuyGold {
    GetPermissionBuyGold permission =new GetPermissionBuyGold();

    @Test
    public void TC1_Get_Permission_User_No_KYC() {
        Assert.assertEquals(String.valueOf(permission.GetUserNoKYC()), "false");
    }
    @Test
    public void TC1_Get_Permission_User_Have_AUMInvest_No_KYC() {
        Assert.assertEquals(String.valueOf(permission.GetUserHaveAUMInvestAndNoKYC()), "false");
    }
    @Test
    public void TC1_Get_Permission_User_Have_CD3M_No_KYC() {
        Assert.assertEquals(String.valueOf(permission.GetUserHaveAUMCD3MAndNoKYC()), "false");
    }
    @Test
    public void TC1_Get_Permission_User_Have_CD12M_No_KYC() {
        Assert.assertEquals(String.valueOf(permission.GetUserHaveAUMCD12MAndNoKYC()), "false");
    }
    @Test
    public void TC1_Get_Permission_User_Have_AUMInvest_Have_KYC() {
        Assert.assertEquals(String.valueOf(permission.GetUserHaveAUMInvestAndHaveKYC()), "true");
    }
    @Test
    public void TC1_Get_Permission_User_Have_CD3M_Have_KYC() {
        Assert.assertEquals(String.valueOf(permission.GetUserHaveAUMCd3mAndHaveKYC()), "true");
    }
    @Test
    public void TC1_Get_Permission_User_Have_CD12M_Have_KYC() {
        Assert.assertEquals(String.valueOf(permission.GetUserHaveAUMCd12mAndHaveKYC()), "true");
    }
}
