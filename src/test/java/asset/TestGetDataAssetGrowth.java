package asset;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import ultilities.Utils;
import vnscbyfinhay.api.assets.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestGetDataAssetGrowth {
    String env = System.getProperty("env");
    GetProfile profile = new GetProfile(env);
    GetListCastTrans listCastTrans = new GetListCastTrans(env);
    GetListFundTrans listFundTrans = new GetListFundTrans(env);
    GetSummaryFundAsset summaryFundAsset = new GetSummaryFundAsset(env);
    GetDataAssetGrowth assetGrowth = new GetDataAssetGrowth(env);
    GetPortfolio portfolio = new GetPortfolio(env);
    GetDataSaving dataSaving = new GetDataSaving(env);
    GetDataMoney dataMoney = new GetDataMoney(env);

    @Test
    public void TC_O1_Check_data_asset() throws Exception {
        int userId = 244;

        // get subAcc
        JsonPath dataProfile = profile.getAPIProfile(userId);
        Assert.assertEquals(dataProfile.get("message"), "success");
        Assert.assertEquals(dataProfile.get("error_code"), "0");
        HashMap<String, Object> dataApi = dataProfile.get("result");
        List<String> subAccounts = ((ArrayList<Map<String, Object>>) dataApi.get("sub_accounts")).stream().map(x -> (String) x.get("sub_account_id")).collect(Collectors.toList());
        for (String sub : subAccounts) {
            System.out.println(sub);
        }
        // get list cast trans
        List<Map<String, Object>> dataCast = new ArrayList<>();
        for (String subAccount : subAccounts) {
            JsonPath dataListCastTrans = listCastTrans.getAPIListTrans(userId, subAccount, "01/01/2022", "30/05/2024", 50000);
            System.out.println(dataListCastTrans);
            Assert.assertEquals(dataListCastTrans.get("message"), "success");
            Assert.assertEquals(dataListCastTrans.get("error_code"), "0");
            List<Map<String, Object>> dataApiListCastTrans = dataListCastTrans.getList("data");
            dataCast.addAll(dataApiListCastTrans);
        }
        List<BigDecimal> cashIns = dataCast.stream().filter(x -> x.get("transaction_flow").equals("IN") && x.get("transaction_status").equals("COMPLETED")).map(x -> new BigDecimal((String) x.get("amount"))).collect(Collectors.toList());
        List<BigDecimal> cashOuts = dataCast.stream().filter(x -> x.get("transaction_flow").equals("OUT") && x.get("transaction_status").equals("COMPLETED")).map(x -> new BigDecimal((String) x.get("amount"))).collect(Collectors.toList());

        BigDecimal totalCashIn = BigDecimal.ZERO;
        for (BigDecimal x : cashIns) {
            totalCashIn = totalCashIn.add(x);
        }

        BigDecimal totalCashOut = BigDecimal.ZERO;
        for (BigDecimal x : cashOuts) {
            totalCashOut = totalCashOut.add(x);
        }

        System.out.println("Total Cash Out:" + totalCashOut + " \n" + "Total Cash In:" + totalCashIn);

        // get data fund trans
        List<Map<String, Object>> dataFund = new ArrayList<>();
        JsonPath dataListFundTrans = listFundTrans.getAPIListFundTrans(userId);
        Assert.assertEquals(dataListFundTrans.get("message"), "SUCCESS");
        Assert.assertEquals(dataListFundTrans.get("error_code"), "0");
        List<Map<String, Object>> dataApiListFundTrans = dataListFundTrans.getList("result");
        dataFund.addAll(dataApiListFundTrans);
        List<BigDecimal> fundOuts = dataFund.stream().filter(x -> x.get("type").equals("SELL") && x.get("status").equals("COMPLETED")).map(x -> new BigDecimal(String.valueOf(x.get("match_amount")))).collect(Collectors.toList());
        BigDecimal totalFundOut = BigDecimal.ZERO;
        for (BigDecimal x : fundOuts) {
            totalFundOut = totalFundOut.add(x);
        }

        //get summary fund asset
        JsonPath dataSummaryFundAsset = summaryFundAsset.getAPISummaryFundAsset(userId);
        Assert.assertEquals(dataSummaryFundAsset.get("message"), "SUCCESS");
        Assert.assertEquals(dataSummaryFundAsset.get("error_code"), "0");
        HashMap<String, Object> dataApiSummaryFundAsset = dataSummaryFundAsset.get("result");
        int totalFundAsset = (int) dataApiSummaryFundAsset.get("total_asset");
        int pnl = (int) dataApiSummaryFundAsset.get("pnl");
        int fundCapital = totalFundAsset - pnl;

        // get asset
        JsonPath dataAsset = assetGrowth.getAPIDataAssetGrowth(userId, "NOCACHE");
        Assert.assertEquals(dataAsset.get("message"), "success");
        Assert.assertEquals(dataAsset.get("error_code"), "0");
        HashMap<String, Object> dataApi_2 = dataAsset.get("data");
        Map<String, Object> moneyGainData = (Map<String, Object>) dataApi_2.get("money_gain");
        BigDecimal total_cash_in = Utils.toBigDecimal(moneyGainData.get("total_cash_in"));
        Assert.assertEquals(total_cash_in, totalCashIn);
        BigDecimal total_cash_out = Utils.toBigDecimal(moneyGainData.get("total_cash_out"));
        Assert.assertEquals(total_cash_out, totalCashOut);
        long products_amount = Utils.toBigDecimal(moneyGainData.get("products_amount")).longValue();
        long debt_amount = Utils.toBigDecimal(moneyGainData.get("debt_amount")).longValue();
        long money_amount = Utils.toBigDecimal(moneyGainData.get("money_amount")).longValue();
        long fund_capital = Utils.toBigDecimal(moneyGainData.get("fund_capital")).longValue();
        long fund_sell_amount = Utils.toBigDecimal(moneyGainData.get("fund_sell_amount")).longValue();
        long total = Utils.toBigDecimal(moneyGainData.get("total")).longValue();
        Assert.assertEquals(fundCapital, fund_capital);

        // MoneyGain = Product Amount - Total Deposit (from bank) + Total Withdraw (to bank) + Total Sell Fund Amount - Nợ
        long totalAsset =  products_amount + money_amount + fund_sell_amount - debt_amount ;
        Assert.assertEquals(totalAsset, total);

        // so sánh data fund

        Map<String, Object> products = (Map<String, Object>) dataApi_2.get("products");
        BigDecimal funds = Utils.toBigDecimal(products.get("fund"));
        Assert.assertEquals(funds.intValue(), totalFundAsset);

        // so sánh data stock
        BigDecimal totalPortfolio = BigDecimal.ZERO;
        for (String subAccount : subAccounts) {
            System.out.println(subAccount);
            JsonPath dataPortfolio = portfolio.getAPIPortfolio(userId, subAccount, "NOCACHE");
            Assert.assertEquals(dataPortfolio.get("message"), "Success");
            Assert.assertEquals(dataPortfolio.get("error_code"), "0");
            Map<String, Object> dataApi_1 = dataPortfolio.get("data");
            List<HashMap<String, Object>> portfolio = (List<HashMap<String, Object>>) dataApi_1.get("portfolio");
            for (int i = 0; i < portfolio.size(); i++) {
                totalPortfolio = totalPortfolio.add(Utils.toBigDecimal(portfolio.get(i).get("total")).multiply(Utils.toBigDecimal(portfolio.get(i).get("basic_price"))));
            }
        }
        BigDecimal stocks = Utils.toBigDecimal(products.get("stock"));
        Assert.assertEquals(totalPortfolio, stocks);

        // so sánh data saving
        BigDecimal saving = Utils.toBigDecimal(products.get("saving"));
        JsonPath dataSavings = dataSaving.getAPIDataSaving(userId);
        Assert.assertEquals(dataSavings.get("message"), "success");
        Assert.assertEquals(dataSavings.get("error_code"), "0");
        HashMap<String, Object> dataApiSavings = dataSavings.get("data");
        int totalPrinciple = (int) dataApiSavings.get("total_principle");
        Assert.assertEquals(totalPrinciple, saving.intValue());

        // so sánh data money
        Map<String, Object> money_1 = (Map<String, Object>) dataApi_2.get("money");
        BigDecimal totalMoney_1 = Utils.toBigDecimal(money_1.get("ci_balance"));
        BigDecimal totalMoney = Utils.toBigDecimal(money_1.get("total"));
        JsonPath dataMoneys = dataMoney.getAPIDataMoney(userId);
        Assert.assertEquals(dataMoneys.get("message"), "success");
        Assert.assertEquals(dataMoneys.get("error_code"), "0");
        List<Map<String, Object>> dataApiMoney = dataMoneys.getList("result");
        System.out.println(dataApiMoney);
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (int i = 0; i < dataApiMoney.size(); i++) {
            totalBalance = totalBalance.add(Utils.toBigDecimal(dataApiMoney.get(i).get("total_balance")));
        }
        System.out.println(totalBalance);
        Assert.assertEquals(totalBalance.intValue(), totalMoney_1.intValue());

        // get data giá trị tài sản ròng NAV
        BigDecimal netAssetValue = Utils.toBigDecimal(dataApi_2.get("net_asset_value"));
        Map<String, Object> debt = (Map<String, Object>) dataApi_2.get("debt");
        BigDecimal totalDebt = Utils.toBigDecimal(debt.get("total"));
        BigDecimal totalSecureAmount = Utils.toBigDecimal(debt.get("secure_amount"));
        BigDecimal totalDebtNew = totalDebt.subtract(totalSecureAmount);
        BigDecimal netAsset = BigDecimal.valueOf(products_amount).add(totalMoney).add(totalSecureAmount);
        BigDecimal netAssetNAV = netAsset.subtract(totalDebtNew);
        Assert.assertEquals(netAssetValue, netAssetNAV);
    }
}


