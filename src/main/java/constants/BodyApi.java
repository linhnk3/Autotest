package constants;

import finhay.api.goldService.PostWithdrawGold;

import java.util.HashMap;
import java.util.Map;

public class BodyApi {
    PostWithdrawGold withdrawGold = new PostWithdrawGold();
    static String stock;
    static Integer id;
    static String favorite;
    //Buy gold normal case
    public static final Object body_post_buy_gold_normal_case_gold_one_mace = "{\n" +
            "    \"asset_type\": \"GOLD_RING\",\n" +
            "    \"gold_type\":\"GOLD_24K\",\n" +
            "    \"payment_type\":\"COD\",\n" +
            "    \"order_type\":\"BUY\",\n" +
            "    \"location\":\"Ha Noi\",\n" +
            "    \"province\":\"Ha Noi\",\n" +
            "    \"order_mace\":1,\n" +
            "    \"price\":5200000,\n" +
            "    \"source\":\"iOS\"\n" +
            "}";
    // Buy both gold_type
    public static final Object body_post_buy_gold_normal_case_all_type = "{\n" +
            "    \"asset_type\": \"GOLD_RING\",\n" +
            "    \"gold_type\":\"GOLD_24K\",\n" +
            "    \"payment_type\":\"COD\",\n" +
            "    \"order_type\":\"BUY\",\n" +
            "    \"location\":\"Ha Noi\",\n" +
            "    \"province\":\"Ha Noi\",\n" +
            "    \"order_mace\":1.5,\n" +
            "    \"price\":5200000,\n" +
            "    \"source\":\"iOS\"\n" +
            "}";

    // user buy gold 1 mace but only type 0.5
    public static final Object body_post_buy_gold_case_only_buy_type_gold_24K = "{\n" +
            "    \"asset_type\": \"GOLD_RING\",\n" +
            "    \"gold_type\":\"GOLD_24K\",\n" +
            "    \"payment_type\":\"COD\",\n" +
            "    \"order_type\":\"BUY\",\n" +
            "    \"location\":\"Ha Noi\",\n" +
            "    \"province\":\"Ha Noi\",\n" +
            "    \"order_mace\":1,\n" +
            "    \"price\":5200000,\n" +
            "    \"source\":\"iOS\"\n" +
            "}";

    public static final Object body_post_buy_gold_over_inventory = "{\n" +
            "    \"asset_type\": \"GOLD_RING\",\n" +
            "    \"gold_type\":\"GOLD_24K\",\n" +
            "    \"payment_type\":\"COD\",\n" +
            "    \"order_type\":\"BUY\",\n" +
            "    \"location\":\"Ha Noi\",\n" +
            "    \"province\":\"Ha Noi\",\n" +
            "    \"order_mace\":10,\n" +
            "    \"price\":5200000,\n" +
            "    \"source\":\"iOS\"\n" +
            "}";

    public static final Object body_post_buy_gold_over_asset_COD = "{\n" +
            "    \"asset_type\": \"GOLD_RING\",\n" +
            "    \"gold_type\":\"GOLD_24K\",\n" +
            "    \"payment_type\":\"COD\",\n" +
            "    \"order_type\":\"BUY\",\n" +
            "    \"location\":\"Ha Noi\",\n" +
            "    \"province\":\"Ha Noi\",\n" +
            "    \"order_mace\":50,\n" +
            "    \"price\":5200000,\n" +
            "    \"source\":\"iOS\"\n" +
            "}";

    public static final Object body_post_buy_gold_no_input_amount_gold = "{\n" +
            "    \"asset_type\": \"GOLD_RING\",\n" +
            "    \"gold_type\":\"GOLD_24K\",\n" +
            "    \"payment_type\":\"COD\",\n" +
            "    \"order_type\":\"BUY\",\n" +
            "    \"location\":\"Ha Noi\",\n" +
            "    \"province\":\"Ha Noi\",\n" +
            "    \"order_mace\":0,\n" +
            "    \"price\":5200000,\n" +
            "    \"source\":\"iOS\"\n" +
            "}";
    public static final Object body_post_buy_gold_over_limit_per_day = "{\n" +
            "    \"asset_type\": \"GOLD_RING\",\n" +
            "    \"gold_type\":\"GOLD_24K\",\n" +
            "    \"payment_type\":\"COD\",\n" +
            "    \"order_type\":\"BUY\",\n" +
            "    \"location\":\"Ha Noi\",\n" +
            "    \"province\":\"Ha Noi\",\n" +
            "    \"order_mace\":101,\n" +
            "    \"price\":5200000,\n" +
            "    \"source\":\"iOS\"\n" +
            "}";

    public static final Object response_no_input_amount_gold = "{\n" +
            "    \"error_code\": \"ERR_VALIDATION_NOTNULL\",\n" +
            "    \"message\": \"must not be null\"\n" +
            "}";

    public static final Object body_gold_buy_many_time = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"BUY\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\": 1,\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";

    public static final Object body_gold_sell_many_time = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"SELL\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\": 1,\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";

    public static final Object body_gold_sell_success_gold_24K = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"SELL\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\": 1,\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";

    public static final Object body_gold_sell_over_gold_24K = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"SELL\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\": 90,\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";
    public static final Object Body_Gold_Sell_Over_Limit_For_The_Day_Gold_Gold_24K = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"SELL\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\": 101,\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";

    public static final Object Body_Gold_Sell_Less_Minimum_Gold_24K = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"SELL\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\": 0.4,\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";

    public static final Object Body_Gold_Sell_No_Input_Amount = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"SELL\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\":\"\",\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";

    public static final Object Body_Gold_Sell_Gold_24K_One_Mark_Success = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"SELL\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\": 1,\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";
    public static final Object Body_Gold_Sell_Gold_All_Type_Success = "{\n" +
            "  \"asset_type\": \"GOLD_RING\",\n" +
            "  \"gold_type\": \"GOLD_24K\",\n" +
            "  \"payment_type\": \"COD\",\n" +
            "  \"order_type\": \"SELL\",\n" +
            "  \"location\": \"Ha Noi\",\n" +
            "  \"province\": \"Ha Noi\",\n" +
            "  \"order_mace\": 1.5,\n" +
            "  \"price\": 5200000,\n" +
            "  \"source\": \"iOS\"\n" +
            "}";

    public static final Object Body_Withdraw_Gold_Success_All_Type = "{\n" +
            "  \"items\": {\n" +
            "    \"GOLD_24K\": 2,\n" +
            "    \"GOLD_24K_ONE_MACE\": 1\n" +
            "  },\n" +
            "  \"request_location_id\": 1,\n" +
            "  \"source\": \"IOS\"\n" +
            "}";

    public static final Object Body_Withdraw_Gold_24K_Type = "{\n" +
            "    \"items\": {\n" +
            "        \"GOLD_24K\":2\n" +
            "       \n" +
            "    },\n" +
            "    \"request_location_id\":1,\n" +
            "    \"source\":\"IOS\"\n" +
            "}";

    public static final Object Body_Withdraw_Gold_Success_Gold24K_One_Mace = "{\n" +
            "  \"items\": {\n" +
            "    \"GOLD_24K_ONE_MACE\": 1\n" +
            "  },\n" +
            "  \"request_location_id\": 1,\n" +
            "  \"source\": \"IOS\"\n" +
            "}";

    public static final Object Body_Login_1153 = "{\n" +
            "    \"user_name\": \"0987654982\", \n" +
            "    \"password\": \"Ab@12345\"\n" +
            "}";

    public static final Object Body_Login_38325 = "{\n" +
            "    \"user_name\": \"0987776666\", \n" +
            "    \"password\": \"Ab@12345\"\n" +
            "}";


    public static final Object BODY_SAVE_BANK_ACCOUNT_SUCCESS = "{\n" +
            "    \"account_number\": \"123433333\",\n" +
            "    \"account_name\": \"KIM OANH\",\n" +
            "    \"bank_name\": \"TECHCOMBANK - NH KY THUONG\"\n" +
            "}";

    public static final Object BODY_BUY_STOCK_SUCCESS = "{\n" +
            "    \"accountId\": \"1153\",\n" +
            "    \"symbol\": \"FPT\",\n" +
            "    \"quantity\": 1000\n" +
            "}";

    public static final Object BODY_BUY_ODD_STOCK = "{\n" +
            "    \"accountId\": \"1153\",\n" +
            "    \"symbol\": \"FID\",\n" +
            "    \"quantity\": \"1\"\n" +
            "}";

    public static final Object BODY_SELL_OVER_STOCK = "{\n" +
            "    \"accountId\": \"1153\",\n" +
            "    \"symbol\": \"FPT\",\n" +
            "    \"quantity\": \"100000\"\n" +
            "}";

    public static final Map<String, Object> BODY_GET_LIST_ROLL = new HashMap<String, Object>() {{
        put("fromDate", "2022-01-01");
        put("toDate", "2023-06-08");
        put("catType", "ALL");
    }};

    public static final Map<String, Object> BODY_GET_LIST_NEWS = new HashMap<String, Object>() {{
        put("stock", stock);
    }};

    public static final Map<String, Object> BODY_GET_DETAIL_NEWS = new HashMap<String, Object>() {{
        put("id", id);
    }};

    public static final Map<String, Object> BODY_GET_DETAIL_WATCHLIST = new HashMap<String, Object>() {{
        put("id", id);
    }};

    public static final Map<String, Object> BODY_GET_LIST_WATCHLIST = new HashMap<String, Object>() {{
    }};

    public static final Map<String, Object> BODY_GET_LIST_BROKER = new HashMap<String, Object>() {{
    }};

    public static final Map<String, Object> BODY_GET_DETAIL_BROKER = new HashMap<String, Object>() {{
        put("id", id);
    }};

    public static final Map<String, Object> BODY_JOIN_GROUP_BROKER = new HashMap<String, Object>() {{
        put("id", id);
        put ("favorite",favorite);
    }};
    public static final Map<String, Object> BODY_GET_INVEST_EFFECTS = new HashMap<String, Object>() {{
        put("id", id);
    }};

    public static final Map<String, Object> BODY_GET_INVESTMENT_PERFORMANCE = new HashMap<String, Object>() {{
        put("id", id);
    }};

    public static final Map<String, Object> BODY_GET_PROFIT_AND_LOSS_BROKER = new HashMap<String, Object>() {{
        put("id", id);
    }};
    public static final Object BODY_GET_TOKEN = "{\n" +
            "    \"username\": \"0985802342\",\n" +
            "    \"password\": \"Ab@12345\",\n" +
            "}";

    public static final Object BODY_CREATE_WATCHLIST_SUCCESS = "{\n" +
            "    \"name\": \"mã ck\"\n" +
            "}";

    public static final Object BODY_CREATE_WATCHLIST_WITH_NAME_EMPTY = "{\n" +
            "    \"name\": \"\"\n" +
            "}";

    public static final Object BODY_CREATE_WATCHLIST_WITH_NAME_NULL = "{\n" +
            "    \"name\": null\n" +
            "}";
}
