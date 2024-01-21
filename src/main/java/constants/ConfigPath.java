package constants;

public class ConfigPath {
    public static final String DEV_URL = "https://dev-api.vinasecurities.com";
    public static final String PROD_URL = "https://api.vinasecurities.com";
    public final String GET_INVEST_EFFECTS;
    public final String GET_LIST_BROKER;

    public ConfigPath(String env) {
        System.out.println("[ConfigPath] Environment: " + env);
        if ("dev".equals(env)) {
            GET_INVEST_EFFECTS = DEV_URL + "/trade/brokers/";
            GET_LIST_BROKER = DEV_URL + "/trade/brokers";
        } else if ("prod".equals(env)) {
            GET_INVEST_EFFECTS = PROD_URL + "/trade/brokers/";
            GET_LIST_BROKER = PROD_URL + "/trade/brokers";
        } else {
            GET_INVEST_EFFECTS = "https://dev-api.vinasecurities.com/trade/brokers/";
            GET_LIST_BROKER = "https://dev-api.vinasecurities.com/trade/brokers";
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

    public static final String GET_DETAIL_BROKER = "https://dev-api.vinasecurities.com/trade/brokers/";

    public static final String JOIN_GROUP_BROKER = "https://dev-api.vinasecurities.com/trade/users/brokers/";

    public static final String GET_INVESTMENT_PERFORMANCE = "https://dev-api.vinasecurities.com/trade/brokers/";

    public static final String GET_PROFIT_AND_LOSS_BROKER = "https://dev-api.vinasecurities.com/trade/brokers/";

    public static final String GET_DETAIL_WATCHLIST = "https://dev-api.vinasecurities.com/accounts/watchlist/";
    public static final String GET_INFO_BANK_ACCOUNT_CORRECT = "https://stg.finhay.app/gw/accounts/v1/banks/970407?account_number=19029532471027";
    public static final String GET_INFO_BANK_ACCOUNT_NOT_EXIST_IN_DB = "https://stg.finhay.app/gw/accounts/v1/banks/970407?account_number=19032985954011";
    public static final String GET_INFO_BANK_ACCOUNT_WRONG_ACCOUNT_NUMBER = "https://stg.finhay.app/gw/accounts/v1/banks/970407?account_number=1903298595";
    public static final String POST_LOGIN = "https://stg.finhay.app/gw/accounts/v1/auth/login";
    public static final String POST_SAVE_BANK_ACCOUNT = "https://stg.finhay.app/gw/accounts/v1/banks/account";
    public static final String POST_STOCK = "https://stg.finhay.app/gw/stock/users/";
    public static final String POST_STOCK_LOCAL = "http://192.168.1.32:8082/users/";
}
