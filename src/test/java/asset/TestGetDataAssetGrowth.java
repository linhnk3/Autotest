package asset;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import vnscbyfinhay.api.assets.GetDataAssetGrowth;
import vnscbyfinhay.api.assets.GetListTrans;
import vnscbyfinhay.api.assets.GetProfile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestGetDataAssetGrowth {
    String env = System.getProperty("env");
    GetProfile profile = new GetProfile(env);
    GetListTrans listTrans = new GetListTrans(env);
    GetDataAssetGrowth assetGrowth= new GetDataAssetGrowth(env);

    @Test
    public void TC_O1_Check_Correct_Get_Asset() throws Exception {
        JsonPath data_profile = profile.getAPIProfile(371);
        Assert.assertEquals(data_profile.get("message"), "success");
        Assert.assertEquals(data_profile.get("error_code"), "0");
        HashMap<String, Object> dataApi = data_profile.get("result");
        List<String> subAccounts = ((ArrayList<Map<String, Object>>) dataApi.get("sub_accounts")).stream().map(x -> (String) x.get("sub_account_id")).collect(Collectors.toList());

        List<Map<String, Object>> data = new ArrayList<>();
        for (String subAccount : subAccounts) {
            JsonPath dataListTrans = listTrans.getAPIListTrans(371, subAccount, "01/01/2022", "30/01/2024", 50000);
            System.out.println(dataListTrans);
            Assert.assertEquals(dataListTrans.get("message"), "success");
            Assert.assertEquals(dataListTrans.get("error_code"), "0");
            List<Map<String, Object>> dataApi_1 = dataListTrans.getList("data");
            data.addAll(dataApi_1);
        }
        List<BigDecimal> cashIns = data.stream().filter(x -> x.get("transaction_flow").equals("IN")).map(x -> new BigDecimal((String) x.get("amount"))).collect(Collectors.toList());
        List<BigDecimal> cashOuts = data.stream().filter(x -> x.get("transaction_flow").equals("OUT")).map(x -> new BigDecimal((String) x.get("amount"))).collect(Collectors.toList());

        BigDecimal totalCashIn = BigDecimal.ZERO;
        for (BigDecimal x : cashIns) {
            totalCashIn = totalCashIn.add(x);
        }

        BigDecimal totalCashOut = BigDecimal.ZERO;
        for (BigDecimal x : cashOuts) {
            totalCashOut = totalCashOut.add(x);
        }

        System.out.println("Total Cash Out:" + totalCashOut + " \n" + "Total Cash In:" + totalCashIn);

        // get asset
        JsonPath dataAsset = assetGrowth.getAPIDataAssetGrowth(371, "NOCACHE");
        Assert.assertEquals(dataAsset.get("message"), "success");
        Assert.assertEquals(dataAsset.get("error_code"), "0");
        HashMap<String, Object> dataApi_2 = dataAsset.get("data");
        BigDecimal total_cash_in = new BigDecimal(((Map<String, Long>) dataApi_2.get("money_gain")).get("total_cash_in"));
        Assert.assertEquals(total_cash_in, totalCashIn);
        BigDecimal total_cash_out = new BigDecimal(((Map<String, Long>) dataApi_2.get("money_gain")).get("total_cash_out"));
        Assert.assertEquals(total_cash_out, totalCashOut);
        long products_amount = (((Map<String, Long>) dataApi_2.get("money_gain")).get("products_amount"));
        long debt_amount = (((Map<String, Integer>) dataApi_2.get("money_gain")).get("debt_amount"));
        long money_amount = (((Map<String, Long>) dataApi_2.get("money_gain")).get("money_amount"));
        long total = (((Map<String, Long>) dataApi_2.get("money_gain")).get("total"));

        // tinh toan tăng trưởng theo công thức =  Tổng tiền rut ra + giá trị tài sản hiện tại gồm HMV, CCQ, CP - Tổng tiền nap vao- nợ
        long totalAsset = total_cash_out.longValue()+ products_amount + money_amount- debt_amount-total_cash_in.longValue() ;
        Assert.assertEquals(totalAsset, total);
    }

}

