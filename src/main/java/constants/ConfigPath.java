package constants;

public class ConfigPath {
    public static final String DEV_URL = "https://dev-api.vinasecurities.com";
    public static final String PROD_URL = "https://api.vinasecurities.com";
    public final String GET_INVEST_EFFECTS;
    public final String GET_LIST_BROKER;

    public final String GET_DETAIL_BROKER;
    public final String GET_PROFILE;
    public final String GET_LIST_TRANS;
    public final String GET_DATA_ASSET_GROWTH;
    public final String JOIN_GROUP_BROKER;
    public final String GET_INVESTMENT_PERFORMANCE;
    public final String GET_PROFIT_AND_LOSS_BROKER;
    public final String GET_PORTFOLIO;
    public final String GET_LIST_FUND_TRANS;
    public final String GET_SUMMARY_FUND_ASSET;
    public final String GET_DATA_SAVING;
    public final String GET_DATA_MONEY;
    public final String GET_LIST_GIFT;

    public ConfigPath(String env) {
        System.out.println("[ConfigPath] Environment: " + env);
        if ("dev".equals(env)) {
            GET_INVEST_EFFECTS = DEV_URL + "/trade/brokers/";
            GET_LIST_BROKER = DEV_URL + "/trade/brokers";
            GET_DETAIL_BROKER = DEV_URL+ "/trade/brokers/";
            GET_PROFILE = DEV_URL + "/accounts/v1/users/";
            GET_LIST_TRANS = DEV_URL + "/payments/v1/users/";
            GET_DATA_ASSET_GROWTH = DEV_URL + "/accounts/v1/users/";
            JOIN_GROUP_BROKER = DEV_URL + "/trade/users/brokers/";
            GET_INVESTMENT_PERFORMANCE = DEV_URL + "/trade/brokers/";
            GET_PROFIT_AND_LOSS_BROKER = DEV_URL + "/trade/brokers/";
            GET_PORTFOLIO = DEV_URL + "/trade/sub-accounts/";
            GET_LIST_FUND_TRANS = DEV_URL + "/fund/v1/users/";
            GET_SUMMARY_FUND_ASSET = DEV_URL + "/fund/users/";
            GET_DATA_SAVING = DEV_URL + "/saving/v1/users/";
            GET_DATA_MONEY = DEV_URL + "/payments/v1/users/";
            GET_LIST_GIFT = DEV_URL + "/ps/v1/referral/";

        } else if ("prod".equals(env)) {
            GET_INVEST_EFFECTS = PROD_URL + "/trade/brokers/";
            GET_LIST_BROKER = PROD_URL + "/trade/brokers";
            GET_DETAIL_BROKER = PROD_URL+ "/trade/brokers/";
            GET_PROFILE = PROD_URL + "/accounts/v1/users/";
            GET_LIST_TRANS = PROD_URL + "/payments/v1/users/";
            GET_DATA_ASSET_GROWTH = PROD_URL + "/accounts/v1/users/";
            JOIN_GROUP_BROKER = PROD_URL + "/trade/users/brokers/";
            GET_INVESTMENT_PERFORMANCE = PROD_URL + "/trade/brokers/";
            GET_PROFIT_AND_LOSS_BROKER = PROD_URL + "/trade/brokers/";
            GET_PORTFOLIO = PROD_URL + "/trade/sub-accounts/";
            GET_LIST_FUND_TRANS = PROD_URL + "/fund/v1/users/";
            GET_SUMMARY_FUND_ASSET = PROD_URL + "/fund/users/";
            GET_DATA_SAVING = PROD_URL + "/saving/v1/users/";
            GET_DATA_MONEY = PROD_URL + "/payments/v1/users/";
            GET_LIST_GIFT = PROD_URL + "/ps/v1/referral/";
        } else {
            GET_INVEST_EFFECTS = "https://dev-api.vinasecurities.com/trade/brokers/";
            GET_LIST_BROKER = "https://dev-api.vinasecurities.com/trade/brokers";
            GET_DETAIL_BROKER = "https://dev-api.vinasecurities.com/trade/brokers/";
            GET_PROFILE = "https://dev-api.vinasecurities.com/accounts/v1/users/";
            GET_LIST_TRANS = "https://dev-api.vinasecurities.com/payments/v1/users/";
            GET_DATA_ASSET_GROWTH = "https://dev-api.vinasecurities.com/accounts/v1/users/";
            JOIN_GROUP_BROKER = "https://dev-api.vinasecurities.com/trade/users/brokers/";
            GET_INVESTMENT_PERFORMANCE = "https://dev-api.vinasecurities.com/trade/brokers/";
            GET_PROFIT_AND_LOSS_BROKER = "https://dev-api.vinasecurities.com/trade/brokers/";
            GET_PORTFOLIO = "https://dev-api.vinasecurities.com/trade/sub-accounts/";
            GET_LIST_FUND_TRANS = "https://dev-api.vinasecurities.com/fund/v1/users/";
            GET_SUMMARY_FUND_ASSET = "https://dev-api.vinasecurities.com/fund/users/";
            GET_DATA_SAVING = "https://dev-api.vinasecurities.com/saving/v1/users/";
            GET_DATA_MONEY = "https://dev-api.vinasecurities.com/payments/v1/users/";
            GET_LIST_GIFT = "https://dev-api.vinasecurities.com/ps/v1/referral/";
        }

    }

    // endPoint
    public static final String GET_PRICE_GOLD = "https://stg.finhay.app/gw/gold/v1/price/current";
    public static final String POST_BUY_SELL_GOLD = "https://stg.finhay.app/gw/gold/v1/users/";
    public static final String POST_WITHDRAW_GOLD = "https://stg.finhay.app/gw/gold/v1/users/";
    public static final String GET_TOKEN = "https://dev-api.vinasecurities.com/accounts/v1/login";
    public static final String PUT_JOB_FROM_SUBMIT_UNDUE_TO_SUBMIT_DUE = "https://stg.finhay.app/gw/gold/v1/jobs/withdraw_orders/status/_transis_undue_to_due";
    public static final String PUT_JOB_FROM_SUBMIT_DUE_TO_SHOP_CONFIRMED = "https://stg.finhay.app/gw/gold/v1/cms/withdraw_orders/_confirm";
    public static final String PUT_JOB_FROM_SHOP_CONFIRMED_TO_COMPLETED = "https://stg.finhay.app/gw/gold/v1/users/";
    public static final String PUT_JOB_FROM_SUBMIT_DUE_TO_OVERDUE = "https://stg.finhay.app/gw/gold/v1/jobs/withdraw_orders/status/_transis_due_to_overdue";

    public static final String GET_RATING_APP = "https://stg.finhay.app/gw/accounts/v1/users/";
    public static final String GET_LIST_BANK = "https://stg.finhay.app/gw/accounts/v1/banks";

    public static final String GET_LIST_ROLL = "https://dev-api.vinasecurities.com/trade/account/";

    public static final String POST_CREATE_WATCHLIST = "https://dev-api.vinasecurities.com/accounts/watchlist";
    public static final String GET_LIST_NEWS = "https://dev-api.vinasecurities.com/datafeed/news/?stock=";
    public static final String GET_LIST_WATCHLIST = "https://dev-api.vinasecurities.com/accounts/watchlist";
    public static final String GET_NEWEST_NEWS = "https://dev-api.vinasecurities.com/datafeed/news/newest?stock=";

    public static final String GET_DETAIL_NEWS = "https://dev-api.vinasecurities.com/datafeed/news/";

    public static final String GET_DETAIL_WATCHLIST = "https://dev-api.vinasecurities.com/accounts/watchlist/";
    public static final String GET_INFO_BANK_ACCOUNT_CORRECT = "https://stg.finhay.app/gw/accounts/v1/banks/970407?account_number=19029532471027";
    public static final String GET_INFO_BANK_ACCOUNT_NOT_EXIST_IN_DB = "https://stg.finhay.app/gw/accounts/v1/banks/970407?account_number=19032985954011";
    public static final String GET_INFO_BANK_ACCOUNT_WRONG_ACCOUNT_NUMBER = "https://stg.finhay.app/gw/accounts/v1/banks/970407?account_number=1903298595";
    public static final String POST_LOGIN = "https://stg.finhay.app/gw/accounts/v1/auth/login";
    public static final String POST_SAVE_BANK_ACCOUNT = "https://stg.finhay.app/gw/accounts/v1/banks/account";
    public static final String POST_STOCK = "https://stg.finhay.app/gw/stock/users/";
    public static final String POST_STOCK_LOCAL = "http://192.168.1.32:8082/users/";
}
