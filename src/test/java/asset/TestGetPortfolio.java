package asset;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import vnscbyfinhay.api.assets.GetPortfolio;
import vnscbyfinhay.api.assets.GetProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestGetPortfolio {
    String env = System.getProperty("env");
    GetProfile profile = new GetProfile(env);
    GetPortfolio portfolio =new GetPortfolio(env);

    @Test
    public void TC_O1_Check_Correct_Get_Portfolio() throws Exception {
        JsonPath data_profile = profile.getAPIProfile(371);
        HashMap<String, Object> dataApi = data_profile.get("result");
        List<String> subAccounts = ((ArrayList<Map<String, Object>>) dataApi.get("sub_accounts")).stream().map(x -> (String) x.get("sub_account_id")).collect(Collectors.toList());

        List<Map<String, Object>> data = new ArrayList<>();
        for (String subAccount : subAccounts) {
            System.out.println(subAccount);
            JsonPath dataPortfolio = portfolio.getAPIPortfolio( subAccount, "NOCACHE");
            Assert.assertEquals(dataPortfolio.get("message"), "Success");
            Assert.assertEquals(dataPortfolio.get("error_code"), "0");
            Map<String, Object> dataApi_1 = dataPortfolio.get("data");
            List<HashMap<String, Object>> portfolio = (List<HashMap<String, Object>>) dataApi_1.get("portfolio");
            for (int i = 0; i < portfolio.size(); i++) {
                System.out.println(portfolio.get(i).get("symbol"));
                System.out.println((int) portfolio.get(i).get("total") * (int) portfolio.get(i).get("basic_price"));
            }
//            data.addAll(dataApi_1);
        }

    }
}
