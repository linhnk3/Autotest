package tradinggold;

import finhay.api.goldService.GetAssetGoldUser;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ultilities.ReportLogListener;

public class TestGetAssetGoldUser {
    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());
    GetAssetGoldUser assetGoldUser = new GetAssetGoldUser();
    int  assetUser = assetGoldUser.GetAssetGoldAPI() * 2 ;

@Test
    public void TC1_Get_User_Asset(){
ReportLog.info("Assert asset user in API And Database");
    Assert.assertEquals(String.valueOf(String.valueOf(assetGoldUser.getAssetUserInDB())),String.valueOf(assetUser));
}
}
